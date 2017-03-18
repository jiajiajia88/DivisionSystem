package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.SystemMapper;
import com.szy.db.mapper.UserMapper;
import com.szy.db.model.*;
import com.szy.model.*;
import com.szy.service.ISystemService;
import com.szy.util.DBUtil;
import com.szy.util.SystemInfoUtil;
import com.szy.vo.Filter;
import com.szy.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统信息
 * Created by shizhouyong on 2017/1/8.
 */
@Service("ISystemService")
public class SystemServiceImpl implements ISystemService{

    private final SystemInfoUtil systemInfoUtil;

    @Autowired
    public SystemServiceImpl(SystemInfoUtil systemInfoUtil) {
        this.systemInfoUtil = systemInfoUtil;
    }

    @Override
    public Response addGrade(AddGradeReq req) {
        if(req == null || req.getGrade() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        long cur = System.currentTimeMillis() / 1000;
        GradeDbo grade = new GradeDbo();
        grade.setName(req.getGrade());
        grade.setCreateTime(cur);

        try {
            return systemInfoUtil.addSystemInfo(grade,"GRADE");
        } catch (Exception e) {
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
    }

    @Override
    public Response addMajor(AddMajorReq req) {
        if(req == null || req.getName() == null || req.getCategory() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        final long cur = System.currentTimeMillis() / 1000L;
        MajorDbo dbo = new MajorDbo();
        dbo.setName(req.getName());
        SystemInfo major = systemInfoUtil.getCategoryByName(req.getCategory());
        if (major == null) {
            return RespEnum.CATEGORY_NOT_FOUND.getResponse();
        }
        dbo.setCategory(major.getId());
        dbo.setDescription(req.getDescription());
        dbo.setCreateTime(cur);
        try {
            return systemInfoUtil.addSystemInfo(dbo, "MAJOR");
        } catch (Exception e) {
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
    }

    @Override
    public Response getMajorByCategory(int category) {
        if (category == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        List<MajorQueryDbo> dboList = null;
        try {
            dboList = systemMapper.selectMajorsByCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new GetMajorByCategoryResp(dboList);
    }

    @Override
    public Response addCategory(AddCategoryReq req) {
        if(req == null || req.getName() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        final long cur = System.currentTimeMillis() / 1000L;
        CategoryDbo dbo = new CategoryDbo();
        dbo.setName(req.getName());
        dbo.setDescription(req.getDescription());
        dbo.setCreateTime(cur);
        try {
            return systemInfoUtil.addSystemInfo(dbo, "CATEGORY");
        } catch (Exception e) {
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
    }

    @Override
    public Response addPosition(AddPositionReq req) {
        if(req == null || req.getName() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        final long cur = System.currentTimeMillis() / 1000L;
        PositionDbo dbo = new PositionDbo();
        dbo.setName(req.getName());
        dbo.setDescription(req.getDescription());
        dbo.setCreateTime(cur);

        try {
            return systemInfoUtil.addSystemInfo(dbo, "POSITION");
        } catch (Exception e) {
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
    }

    @Override
    public Response getSystemInfos(GetSystemInfoListReq req) {
        if(req == null || req.getType() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        try {
            return new GetSystemInfoListResp(systemInfoUtil.getSystemInfoList(req.getType()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespEnum.DATA_NOT_FOUND.getResponse();
    }

    @Override
    public Response deleteSystemInfo(DeleteSystemInfoReq req) {
        if(req == null || req.getName() == null || req.getType() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        try {
            systemInfoUtil.deleteSystemInfo(req.getName(), req.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response addTeacher(SaveTeacherReq req) {
        if (req == null || req.getName() == null || req.getNumber() == 0 || req.getPosition() == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        SystemInfo categoryDbo = systemInfoUtil.getPositionByName(req.getPosition());
        if (categoryDbo == null) {
            return RespEnum.POSITION_NOT_FOUND.getResponse();
        }

        long cur = System.currentTimeMillis() / 1000;
        TeacherInfoDbo dbo = new TeacherInfoDbo();
        dbo.setName(req.getName());
        dbo.setNumber(req.getNumber());
        dbo.setPositionId(categoryDbo.getId());
        dbo.setCreateTime(cur);

        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        try {
            systemMapper.insertTeacher(dbo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response updateTeacher(SaveTeacherReq req) {
        if (req == null || req.getName() == null || req.getNumber() == 0 || req.getPosition() == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        SystemInfo categoryDbo = systemInfoUtil.getPositionByName(req.getPosition());
        if (categoryDbo == null) {
            return RespEnum.POSITION_NOT_FOUND.getResponse();
        }

        long cur = System.currentTimeMillis() / 1000;
        TeacherInfoDbo dbo = new TeacherInfoDbo();
        dbo.setName(req.getName());
        dbo.setNumber(req.getNumber());
        dbo.setPositionId(categoryDbo.getId());
        dbo.setUpdateTime(cur);

        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        try {
            systemMapper.updateTeacher(dbo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_UPDATE_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response teacherList() {
        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        List<TeacherInfoQueryDbo> teacherList = systemMapper.selectTeacherInfos();
        GetTeacherListResp resp = new GetTeacherListResp();
        resp.setTeacherList(teacherList);
        return resp;
    }

    @Override
    public Response deleteTeacher(long number) {
        if (number == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        try {
            systemMapper.deleteTeacher(number);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_DELETE_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response getStuAccount(GetStuAccountReq req) {
        if (req == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        Order order = req.getOrder();
        Filter filter = req.getFilter();

        GetStuAccountItems items = new GetStuAccountItems();
        if (order != null) {
            items.setOrder(order);
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
            items.setOriginalClass(filter.getOriginalClass());
            items.setCategory(filter.getCategory());
            items.setGrade(filter.getGrade());
            items.setLimit(filter.getLimit());
        }

        UserMapper userMapper = DBUtil.getMapper(UserMapper.class);
        List<StuAccountDbo> accountDbos = userMapper.selectStuAccount(items);
        accountDbos.forEach(t->t.setLimitDescription(t.getLimit() == -1 ? "待激活" : "已激活"));
        int total = userMapper.selectStuAccountTotal(items);
        return new GetStuAccountResp(accountDbos, total);
    }

}
