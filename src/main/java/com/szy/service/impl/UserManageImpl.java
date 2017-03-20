package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.SystemMapper;
import com.szy.db.mapper.UserMapper;
import com.szy.db.model.*;
import com.szy.model.*;
import com.szy.service.IUserManageService;
import com.szy.util.CacheUtil;
import com.szy.util.DBUtil;
import com.szy.util.SystemInfoUtil;
import com.szy.vo.Filter;
import com.szy.vo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务
 * Created by shizhouyong on 2017/1/7.
 */
@Service("IUserManageService")
public class UserManageImpl implements IUserManageService {

    private Logger logger = LoggerFactory.getLogger(UserManageImpl.class);

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private SystemInfoUtil systemInfoUtil;

    @Override
    public Response updateAccountLimit(UpdateUserLimitReq req) {
        if (req == null || req.getLimit() == 0 || req.getNumberList() == null || req.getNumberList().isEmpty())
            return RespEnum.PARAMETER_MiSS.getResponse();

        UserMapper userMapper = DBUtil.getMapper(UserMapper.class);
        List<Long> numberList = req.getNumberList();
        int status = req.getLimit();
        numberList.forEach(t->{
            UpdateUserLimitDbo dbo = new UpdateUserLimitDbo(t, status);
            try {
                userMapper.updateUserLimit(dbo);
            } catch (Exception e) {
                logger.error("学号为"+t+"的账号更新失败！");
                e.printStackTrace();
            }
        });

        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response deleteAccount(DeleteAccountReq req) {
        if (req == null || req.getNumberList() == null || req.getNumberList().isEmpty())
            return RespEnum.PARAMETER_MiSS.getResponse();

        UserMapper userMapper = DBUtil.getMapper(UserMapper.class);
        req.getNumberList().forEach(t->{
            try {
                userMapper.deleteUser(t);
            } catch (Exception e) {
                logger.error("number为"+t+"的用户删除失败！");
                e.printStackTrace();
            }
        });

        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response addTeacher(SaveTeacherReq req) {
        if (req == null || req.getName() == null || req.getNumber() == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        long cur = System.currentTimeMillis() / 1000;
        TeacherInfoDbo dbo = new TeacherInfoDbo();
        dbo.setName(req.getName());
        dbo.setNumber(req.getNumber());
        dbo.setCreateTime(cur);

        UserDbo userDbo = new UserDbo();
        userDbo.setName(req.getName());
        userDbo.setNumber(req.getNumber());
        userDbo.setCreateTime(cur);
        userDbo.setPassword("123456");
        userDbo.setLimit(-1);
        userDbo.setStatus(1);

        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        UserMapper userMapper = DBUtil.getMapper(UserMapper.class);
        try {
            systemMapper.insertTeacher(dbo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }

        try {
            userMapper.insertUser(userDbo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response updateTeacher(SaveTeacherReq req) {
        if (req == null || req.getName() == null || req.getNumber() == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        long cur = System.currentTimeMillis() / 1000;
        TeacherInfoDbo dbo = new TeacherInfoDbo();
        dbo.setName(req.getName());
        dbo.setNumber(req.getNumber());
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
    public Response teacherList(GetAdminReq req) {
        String name = null;
        if (req != null) {
            name = req.getName();
        }
        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        GetAdminListDbo dbo = new GetAdminListDbo();
        dbo.setName(name);
        List<TeacherInfoQueryDbo> teacherList = systemMapper.selectTeacherInfos(dbo);
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
        UserMapper userMapper = DBUtil.getMapper(UserMapper.class);
        try {
            systemMapper.deleteTeacher(number);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_DELETE_ERR.getResponse();
        }

        try {
            userMapper.deleteUser(number);
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
