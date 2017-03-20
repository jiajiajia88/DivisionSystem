package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.ShuntMapper;
import com.szy.db.mapper.StuInfoMapper;
import com.szy.db.mapper.SystemMapper;
import com.szy.db.model.*;
import com.szy.model.*;
import com.szy.service.IShuntService;
import com.szy.util.*;
import com.szy.vo.*;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 学生信息服务
 * Created by shizhouyong on 2017/2/17.
 */
@Service("IShuntService")
public class ShuntServiceImpl implements IShuntService {

    private final static DecimalFormat df = new DecimalFormat("0");  //格式化number
    private final static DecimalFormat df2 = new DecimalFormat("0.00");
    private final static DecimalFormat df3 = new DecimalFormat("0.000");

    @Override
    public Response calculateGrade(ShuntToMajorReq req) {
        if (req == null || req.getCategory() == 0 || req.getCategory() == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        GetStuInfoItems items = new GetStuInfoItems();
        items.setFrom(0);
        items.setSize(1000 * 1000 * 1000);
        items.setCategory(req.getCategory());
        items.setGrade(req.getGrade());

        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
        List<StudentInfoQueryDbo> studentInfoList = mapper.selectStudentInfoList(items);
        studentInfoList.forEach(t->{
            double realGps = Double.parseDouble(df3.format(t.getGPA() * 0.7));
            double gradeOne = Double.parseDouble(df2.format((float)t.getEntranceScore() / t.getAdmissionScore()));
            double gradeTwo = Double.parseDouble(df3.format(gradeOne * 0.3));
            double totalGrade = Double.parseDouble(df2.format(realGps + gradeTwo));
            StudentInfoDbo dbo = new StudentInfoDbo();
            dbo.setRealGPA(realGps);
            t.setRealGPA(realGps);
            dbo.setGradeOne(gradeOne);
            t.setGradeOne(gradeOne);
            dbo.setGradeTwo(gradeTwo);
            t.setGradeTwo(gradeTwo);
            dbo.setTotalGrade(totalGrade);
            t.setTotalGrade(totalGrade);
            dbo.setNumber(t.getNumber());
            mapper.updateGrade(dbo);
        });

        studentInfoList = studentInfoList.stream().sorted(Comparator.comparing(StudentInfoQueryDbo::getTotalGrade).reversed().thenComparing(StudentInfoQueryDbo::getGPA)).collect(Collectors.toList());
        int i = 0;
        for (StudentInfoQueryDbo t : studentInfoList) {
            StudentInfoDbo dbo = new StudentInfoDbo();
            dbo.setRank(++i);
            dbo.setNumber(t.getNumber());
            mapper.updateRank(dbo);
        }

        items.setItem("rank");
        items.setSort("asc");

        GetStudentInfoListResp resp = new GetStudentInfoListResp();
        try {
            resp.setStudents(mapper.selectStudentInfoList(items));
            resp.setTotal(mapper.selectStudentInfoListTotal(items));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    public Response shuntToMajor(ShuntToMajorReq req) {
        if (req == null || req.getCategory() == 0 || req.getCategory() == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        int category = req.getCategory();
        int grade = req.getGrade();
        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        GradeDbo gradeDbo = systemMapper.selectGradeById(grade);
        if (gradeDbo == null) {
            return RespEnum.DATA_NOT_FOUND.getResponse();
        }
        String gradeName = gradeDbo.getName();

        ShuntUtil shuntUtil = new ShuntUtil(category, grade);
        shuntUtil.executeShunt();
        Map<Integer, List<VolunteerQueryDbo>> targetMap = shuntUtil.getTargetMap();
        ShuntMapper mapper = DBUtil.getMapper(ShuntMapper.class);

        long cur = System.currentTimeMillis() / 1000;
        targetMap.forEach((k,v)->{
            PlanUnit planUnit = shuntUtil.getPlanUnitMap().get(k);
            int num = planUnit.getClassAmount();
            NewMajorDbo newMajorDbo = new NewMajorDbo();
            newMajorDbo.setMajor(planUnit.getId());
            newMajorDbo.setCategory(category);
            newMajorDbo.setGrade(grade);
            newMajorDbo.setClassNum(num);
            newMajorDbo.setStudentNum(v.size());
            newMajorDbo.setCreateTime(cur);
            newMajorDbo.setStatus(1);
            mapper.insertNewMajor(newMajorDbo);

            StudentInfoDbo studentInfoDbo = new StudentInfoDbo();
            StuInfoMapper stuInfoMapper = DBUtil.getMapper(StuInfoMapper.class);
            v.forEach(t->{
                studentInfoDbo.setNumber(t.getNumber());
                studentInfoDbo.setNewMajor(newMajorDbo.getId());
                stuInfoMapper.updateNewMajor(studentInfoDbo);
            });
        });
        ShuntUtil.shuntUtilMap.put(new PlanKey(grade, category), shuntUtil);
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response shuntToClass(ShuntToClassReq req) {

        if(req == null || req.getCategory() == 0 || req.getGrade() == 0 || req.getNewMajor() ==0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        int category = req.getCategory();
        int grade = req.getGrade();
        int major = req.getNewMajor();

        //获得分流工具对象
        ShuntUtil shuntUtil = ShuntUtil.shuntUtilMap.get(new PlanKey(grade, category));

        //根据新专业的id获取该专业对象
        ShuntMapper shuntResultMapper = DBUtil.getMapper(ShuntMapper.class);
        NewMajorQueryDbo newMajor = shuntResultMapper.selectMajorById(req.getNewMajor());

        //根据专业id对该专业进行分流
        Map<NewClassKey, List<VolunteerQueryDbo>> classMap = shuntUtil.getNewClassMap(newMajor.getMajorId());
        PlanUnit planUnit = shuntUtil.getPlanUnitMap().get(newMajor.getMajorId());
        long cur = System.currentTimeMillis() / 1000;
        NewClassDbo newClassDbo = new NewClassDbo();
        newClassDbo.setMajor(newMajor.getId());
        newClassDbo.setCategory(newMajor.getCategoryId());
        newClassDbo.setGrade(newMajor.getGradeId());
        newClassDbo.setCreateTime(cur);
        final int[] i = {1};
        classMap.forEach((k,v)->{
            newClassDbo.setRealNum(v.size());
            newClassDbo.setPlanNum((int) Math.ceil((double)planUnit.getStuAmount() / planUnit.getClassAmount()));
            newClassDbo.setName(newMajor.getGrade()+"级"+planUnit.getName()+ i[0] +"班");
            newClassDbo.setIndex(i[0]);
            newClassDbo.setStatus(1);
            shuntResultMapper.insertNewClass(newClassDbo);

            StudentInfoDbo studentInfoDbo = new StudentInfoDbo();
            StuInfoMapper stuInfoMapper = DBUtil.getMapper(StuInfoMapper.class);
            v.forEach(t->{
                studentInfoDbo.setNumber(t.getNumber());
                studentInfoDbo.setNewClass(newClassDbo.getId());
                stuInfoMapper.updateNewClass(studentInfoDbo);
            });
            i[0]++;
        });

        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response getNewMajorList(GetNewMajorListReq req) {
        GetNewMajorItems items = new GetNewMajorItems();
        if (req != null) {
            items.setCategory(req.getCategory());
            items.setGrade(req.getGrade());
        }
        ShuntMapper shuntResultMapper = DBUtil.getMapper(ShuntMapper.class);
        return new GetNewMajorListResp(shuntResultMapper.selectNewMajorAsOptionItems(items));
    }

    @Override
    public Response getNewClassList(GetNewClassListReq req) {
        GetNewClassItems items = new GetNewClassItems();
        if (req != null) {
            items.setCategory(req.getCategory());
            items.setGrade(req.getGrade());
            items.setMajor(req.getNewMajor());
        }
        ShuntMapper shuntResultMapper = DBUtil.getMapper(ShuntMapper.class);
        return new GetNewClassListResp(shuntResultMapper.selectNewClassAsOptionItems(items));
    }

    @Override
    public Response shuntAdjust(ShuntAdjustReq req) {
        if (req == null || req.getNumber() == 0) {
            return RespEnum.SUCCESS.getResponse();
        }

        StuInfoMapper stuInfoMapper = DBUtil.getMapper(StuInfoMapper.class);
        if(req.getNewMajor() != 0) {
            StudentInfoDbo studentInfoDbo = new StudentInfoDbo();
            studentInfoDbo.setNumber(req.getNumber());
            studentInfoDbo.setNewMajor(req.getNewMajor());
            try {
                stuInfoMapper.updateNewMajor(studentInfoDbo);
            } catch (Exception e) {
                e.printStackTrace();
                return RespEnum.DATA_UPDATE_ERR.getResponse();
            }
        }

        if(req.getNewClass() != 0) {
            StudentInfoDbo studentInfoDbo = new StudentInfoDbo();
            studentInfoDbo.setNumber(req.getNumber());
            studentInfoDbo.setNewClass(req.getNewClass());
            try {
                stuInfoMapper.updateNewClass(studentInfoDbo);
            } catch (Exception e) {
                e.printStackTrace();
                return RespEnum.DATA_UPDATE_ERR.getResponse();
            }
        }

        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response shuntPublish(ShuntPublishReq req) {
        if(req == null || req.getCategory() == 0 || req.getGrade() == 0) {
            return RespEnum.SUCCESS.getResponse();
        }

        StudentInfoDbo studentInfoDbo = new StudentInfoDbo();
        studentInfoDbo.setCategory(req.getCategory());
        studentInfoDbo.setGrade(req.getGrade());

        StuInfoMapper stuInfoMapper = DBUtil.getMapper(StuInfoMapper.class);
        try {
            stuInfoMapper.updateGradeAndCategory(studentInfoDbo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_UPDATE_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

}
