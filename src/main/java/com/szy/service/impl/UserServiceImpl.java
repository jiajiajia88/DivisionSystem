package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.StuInfoMapper;
import com.szy.db.mapper.SystemMapper;
import com.szy.db.mapper.UserMapper;
import com.szy.db.model.StudentInfoQueryDbo;
import com.szy.db.model.TeacherInfoQueryDbo;
import com.szy.db.model.UpdateUserLimitDbo;
import com.szy.db.model.UserDbo;
import com.szy.model.*;
import com.szy.service.IUserService;
import com.szy.session.LocalUtil;
import com.szy.session.Session;
import com.szy.util.CacheUtil;
import com.szy.util.DBUtil;
import com.szy.util.SystemInfoUtil;
import com.szy.util.UserLimitUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 用户服务
 * Created by shizhouyong on 2017/1/7.
 */
@Service("IUserService")
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private SystemInfoUtil systemInfoUtil;

    @Override
    public Response login(UserLoginReq req) {
        if (req == null || req.getNumber() == 0 || req.getPassword() == null)
            return RespEnum.PARAMETER_MiSS.getResponse();

        UserMapper userMapper = DBUtil.getMapper(UserMapper.class);

        UserDbo userDbo = userMapper.selectUserByNumber(req.getNumber());
        try {
            if (userDbo == null)
                return RespEnum.NO_USER.getResponse();
            if (!userDbo.getPassword().equals(req.getPassword()))
                return RespEnum.PASSWD_ERR.getResponse();
            if (!UserLimitUtil.verify(userDbo.getLimit(), UserLimitUtil.USER_LOGIN))
                return RespEnum.NO_ACCESS.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.UNKNOWN_ERROR.getResponse();
        }

        final long cur = System.currentTimeMillis() / 1000;


        String ss = null;
        if(UserLimitUtil.verify(userDbo.getLimit(), UserLimitUtil.USER_STUDENT)) {

            StuInfoMapper stuInfoMapper = DBUtil.getMapper(StuInfoMapper.class);
            StudentInfoQueryDbo studentInfo = null;
            try {
                studentInfo = stuInfoMapper.selectStudentInfoByNumber(userDbo.getNumber());
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("【登录接口】无法获取学号为："+ req.getNumber() + "的学生信息！");
            }
            Session session = new Session();
            if (studentInfo == null) {
                return RespEnum.NO_USER.getResponse();
            }

            session.setNumber(userDbo.getNumber());
            session.setLoginTime(cur);
            session.setPasswd(userDbo.getPassword());
            session.setLimit(userDbo.getLimit());
            session.setGrade(studentInfo.getGradeId());
            session.setTelePhone(studentInfo.getTelephone());
            session.setName(studentInfo.getName());
            session.setCategory(studentInfo.getCategoryId());
            try {
                ss = session.createSessionKey(userDbo.getNumber());
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                logger.error("创建sessionKey失败！");
                e.printStackTrace();
            }
            cacheUtil.setSession(session);
            return new UserLoginResp(userDbo.getNumber(), studentInfo.getName(), studentInfo.getTelephone(), ss, session.getLimit(), cur + CacheUtil.SESSION_EXPIRE);
        } else {
            Session session = new Session();
            session.setNumber(userDbo.getNumber());
            session.setLoginTime(cur);
            session.setPasswd(userDbo.getPassword());
            session.setLimit(userDbo.getLimit());
            if(UserLimitUtil.verify(userDbo.getLimit(), UserLimitUtil.USER_STUDENT)) {
                SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
                TeacherInfoQueryDbo teacherInfoQueryDbo = systemMapper.selectTeacherInfoByNumber(userDbo.getNumber());
                session.setName(teacherInfoQueryDbo.getName());
            } else {
                session.setName("管理员");
            }

            try {
                ss = session.createSessionKey(userDbo.getNumber());
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                logger.error("创建sessionKey失败！");
                e.printStackTrace();
            }
            cacheUtil.setSession(session);
            return new UserLoginResp(userDbo.getNumber(), session.getName(), null, ss, session.getLimit(), cur + CacheUtil.SESSION_EXPIRE);
        }
    }

    @Override
    public Response logout(long number) {
        if (number == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        Session session = LocalUtil.getSession();
        if (session == null) {
            return RespEnum.NOT_LOGIN.getResponse();
        }
        cacheUtil.delSeeion(session.getKey());
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response updatePassward(UserUpdatePasswordReq req) {

        if (req == null || req.getOldPassword() == null || req.getNewPassword() == null)
            return RespEnum.PARAMETER_MiSS.getResponse();

        Session session = LocalUtil.getSession();
        if (session == null) {
            return RespEnum.NOT_LOGIN.getResponse();
        }

        UserMapper userMapper = DBUtil.getMapper(UserMapper.class);
        UserDbo user = userMapper.selectUserByNumber(session.getNumber());
        if (user == null) {
            logger.error("学工号为" + session.getNumber() + "的用户未能找到！");
            return RespEnum.DATA_NOT_FOUND.getResponse();
        }

        if (!req.getOldPassword().equals(user.getPassword())) {
            return RespEnum.PASSWD_ERR.getResponse();
        }

        UserDbo userDbo = new UserDbo();
        userDbo.setNumber(session.getNumber());
        userDbo.setPassword(req.getNewPassword());

        try {
            userMapper.updatePassword(userDbo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_UPDATE_ERR.getResponse();
        }

        return RespEnum.SUCCESS.getResponse();
    }

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


}
