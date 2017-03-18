package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.ShuntResultMapper;
import com.szy.db.mapper.StuInfoMapper;
import com.szy.db.mapper.SystemMapper;
import com.szy.db.mapper.VolunteerMapper;
import com.szy.db.model.*;
import com.szy.model.*;
import com.szy.service.ITeacherService;
import com.szy.session.LocalUtil;
import com.szy.session.Session;
import com.szy.util.*;
import com.szy.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 学生信息服务
 * Created by shizhouyong on 2017/2/17.
 */
@Service("IStuInfoService")
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private ExcelUtil excelUtil;

    @Autowired
    private AccountCreateUtil accountCreateUtil;

    @Autowired
    private SystemInfoUtil systemInfoUtil;

    private static Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
    private final static DecimalFormat df = new DecimalFormat("0");  //格式化number
    private final static DecimalFormat df2 = new DecimalFormat("0.00");
    private final static DecimalFormat df3 = new DecimalFormat("0.000");
    private final static DecimalFormat df6 = new DecimalFormat("0.000000");

    @Override
    public Response uploadStudentInfoByExcel(HttpServletRequest req, MultipartFile file) {

        Session session = LocalUtil.getSession();
        if (session == null) {
            return RespEnum.NOT_LOGIN.getResponse();
        }

        int category = Integer.parseInt(req.getParameter("category"));
        int grade = Integer.parseInt(req.getParameter("grade"));
        if (file == null || file.isEmpty() || category == 0 || grade == 0)
            return RespEnum.PARAMETER_MiSS.getResponse();

        InputStream in = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            logger.error(e.getMessage());
            return RespEnum.FILE_INPUTSTREAM_ERR.getResponse();
        }
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        List<String> list;
        try {
            list = excelUtil.exportListFromExcel(in, fileType, 0);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return RespEnum.FILE_NEWWORKBOOK_ERR.getResponse();
        }

        long cur = System.currentTimeMillis() / 1000;
        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
        long start = System.currentTimeMillis();
        for (int i = 1; i < list.size(); i++) {
            String[] info = (list.get(i).substring(1)).split("\\|");
            StudentInfo studentInfo = null;
            try {
                studentInfo = new StudentInfo();
                studentInfo.setNumber(Long.parseLong(info[0]));
                studentInfo.setName(info[1]);
                studentInfo.setCategory(category);
                studentInfo.setGrade(grade);
                studentInfo.setOriginalClass(info[8]);
                studentInfo.setSex(info[7]);
                switch (info[7]) {
                    case "男":
                        studentInfo.setSex("M");
                        break;
                    case "女":
                        studentInfo.setSex("F");
                        break;
                    default:
                        studentInfo.setSex("D");
                        break;
                }
                studentInfo.setDorm(info[9]);
                switch (info[10]) {
                    case "^*^BLANK":
                        studentInfo.setNote(null);
                        break;
                    default:
                        studentInfo.setNote(info[10]);
                        break;
                }
                studentInfo.setGPA((Double.parseDouble(df2.format(Double.parseDouble(info[2])))));
                studentInfo.setStuFrom(info[3]);
                switch (info[4]) {
                    case "文科":
                        studentInfo.setDivision(1);
                        break;
                    case "理科":
                        studentInfo.setDivision(2);
                        break;
                    default:
                        studentInfo.setDivision(-1);
                }

                studentInfo.setEntranceScore(Integer.parseInt(info[5]));
                studentInfo.setAdmissionScore(Integer.parseInt(info[6]));
                studentInfo.setCreateUser(session.getNumber());
                studentInfo.setCreateTime(cur);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return RespEnum.DATA_PARSE_ERR.getResponse();
            }

            if (studentInfo.checkStuInfo()) {
                try {
                    StudentInfoDbo studentInfoDbo = studentInfo.createStuInfoDbo();
                    mapper.insertStudentInfo(studentInfoDbo);
                    accountCreateUtil.createStuAccount(studentInfoDbo);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                    return RespEnum.DATA_INSERT_ERR.getResponse();
                }
            } else {
                return RespEnum.PARAMETER_MiSS.getResponse();
            }
        }
        long end = System.currentTimeMillis();
        logger.info("上传时间："+String.valueOf(end - start));
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response addStudentInfo(SaveStudentInfoReq req) {
        Session session = LocalUtil.getSession();
        if (session == null) {
            return RespEnum.NOT_LOGIN.getResponse();
        }

        if (req == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        StudentInfo studentInfo = req.createStudentInfo();
        if (!studentInfo.checkStuInfo()) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        long cur = System.currentTimeMillis() / 1000;
        studentInfo.setCreateTime(cur);
        studentInfo.setCreateUser(session.getNumber());

        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
        try {
            mapper.insertStudentInfo(studentInfo.createStuInfoDbo());
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response updateStudentInfo(SaveStudentInfoReq req) {
        Session session = LocalUtil.getSession();
        if (session == null) {
            return RespEnum.NOT_LOGIN.getResponse();
        }

        if (req == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        StudentInfo studentInfo = req.createStudentInfo();
        if (!studentInfo.checkStuInfo()) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        long cur = System.currentTimeMillis() / 1000;
        studentInfo.setUpdateTime(cur);
        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
        try {
            mapper.insertStudentInfo(studentInfo.createStuInfoDbo());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response getStudentInfoList(GetStudentInfoListReq req) {
        if (req == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        Order order = req.getOrder();
        Filter filter = req.getFilter();

        GetStuInfoItems items = new GetStuInfoItems();
        if (order != null) {
            items.setFrom(order.getFrom());
            items.setSize(order.getSize());
            items.setItem(order.getItem());
            items.setSort(order.getSort());
        } else {
            items.setFrom(0);
            items.setSize(15);
            items.setItem("number");
            items.setSort("asc");
        }

        if (filter != null) {
            items.setSex(filter.getSex());
            items.setDivision(filter.getDivision());
            items.setName(filter.getName());
            items.setNumber(filter.getNumber());
            items.setStuFrom(filter.getStuFrom());
            items.setStatus(filter.getStatus());
            items.setOriginalClass(filter.getOriginalClass());
            items.setCategory(filter.getCategory());
            items.setGrade(filter.getGrade());
        }
        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
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
    public Response getStudentInfoDetails(long number) {
        if (number == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
        StudentInfoQueryDbo studentInfoDbo = null;
        try {
            studentInfoDbo = mapper.selectStudentInfoByNumber(number);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentInfoDbo != null ? new GetStuInfoDetailsResp(studentInfoDbo) : RespEnum.DATA_NOT_FOUND.getResponse();
    }

    @Override
    public Response deleteVolunteer(long number) {
        if (number == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
        try {
            mapper.deleteVolunteer(number);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_DELETE_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response getVolunteers(GetVolunteersReq req) {
        if (req == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        Order order = req.getOrder();
        Filter filter = req.getFilter();

        GetVolunteerItems items = new GetVolunteerItems();
        if (order != null) {
            items.setFrom(order.getFrom());
            items.setSize(order.getSize());
            items.setItem(order.getItem());
            items.setSort(order.getSort());
        }

        if (filter != null) {
            items.setFirstChoose(filter.getFirstChoose());
            items.setSecondChoose(filter.getSecondChoose());
            items.setThirdChoose(filter.getThirdChoose());
            items.setSex(filter.getSex());
            items.setDivision(filter.getDivision());
            items.setName(filter.getName());
            items.setNumber(filter.getNumber());
            items.setStuFrom(filter.getStuFrom());
            items.setGrade(filter.getGrade());
            items.setOriginalClass(filter.getOriginalClass());
            items.setCategory(filter.getCategory());
        }
        VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
        GetVolunteersResp resp = new GetVolunteersResp();
        try {
            resp.setVolunteers(mapper.selectVolunteerList(items));
            resp.setTotal(mapper.selectVolunteerListTotal(items));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    public Response getVolunteerDetails(long number) {
        if (number == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
        VolunteerQueryDbo dbo = null;
        try {
            dbo = mapper.selectVolunteerByNumber(number);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dbo == null) {
            return RespEnum.DATA_NOT_FOUND.getResponse();
        }
        return new GetVolunteerDetailsResp(dbo);
    }

    @Override
    public Response calculateGrade(ShuntReq req) {
        if (req == null || req.getCategory() == 0 || req.getCategory() == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        Order order = req.getOrder() == null ? new Order() : req.getOrder();
        GetStuInfoItems items = new GetStuInfoItems();
        items.setFrom(0);
        items.setSize(1000 * 1000 * 1000);
        items.setCategory(req.getCategory());
        items.setGrade(req.getGrade());

        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
        List<StudentInfoQueryDbo> studentInfoList = mapper.selectStudentInfoList(items);
        for (StudentInfoQueryDbo t : studentInfoList) {
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
        }

        studentInfoList = studentInfoList.stream().sorted(Comparator.comparing(StudentInfoQueryDbo::getTotalGrade).reversed()).collect(Collectors.toList());
        int i = 0;
        for (StudentInfoQueryDbo t : studentInfoList) {
            StudentInfoDbo dbo = new StudentInfoDbo();
            dbo.setRank(++i);
            dbo.setNumber(t.getNumber());
            mapper.updateRank(dbo);
        }

        items.setItem("rank");
        items.setSort("asc");
        items.setFrom(order.getFrom());
        items.setSize(order.getSize());

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
    public Response shunt(ShuntReq req) {
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
        ShuntResultMapper mapper = DBUtil.getMapper(ShuntResultMapper.class);

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
            mapper.insertNewMajor(newMajorDbo);

            NewClassDbo newClassDbo = new NewClassDbo();
            newClassDbo.setMajor(newMajorDbo.getId());
            newClassDbo.setCategory(category);
            newClassDbo.setGrade(grade);
            newClassDbo.setRealNum(v.size());
            newClassDbo.setPlanNum(planUnit.getStuAmount());
            newClassDbo.setCreateTime(cur);

            StuInfoMapper stuInfoMapper = DBUtil.getMapper(StuInfoMapper.class);
            for (int i = 0;i < num;i++) {
                newClassDbo.setName(gradeName+"级"+planUnit.getName()+(i+1)+"班");
                mapper.insertNewClass(newClassDbo);
                List<VolunteerQueryDbo> volunteers = shuntUtil.getNewClassMap().get(new NewClassKey(k,i+1));
                StudentInfoDbo studentInfoDbo = new StudentInfoDbo();
                volunteers.forEach(t->{
                    studentInfoDbo.setNumber(t.getNumber());
                    studentInfoDbo.setNewMajor(newMajorDbo.getId());
                    studentInfoDbo.setNewClass(newClassDbo.getId());
                    stuInfoMapper.updateNewClass(studentInfoDbo);
                });
            }
        });
        return RespEnum.SUCCESS.getResponse();
    }

}
