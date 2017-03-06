package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.UserMapper;
import com.szy.db.model.UserDbo;
import com.szy.model.Account;
import com.szy.model.UserLoginReq;
import com.szy.model.UserUpdatePasswordReq;
import com.szy.service.IUserService;
import com.szy.util.DBUtil;
import com.szy.util.UserLimitUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * 用户
 * Created by shizhouyong on 2017/1/7.
 */
@Service("IUserService")
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final int LOGIN_SESSION_TIMEOUT = 12 * 60 *60;

    @Override
    public Response login(UserLoginReq req, HttpSession session) {
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

        Account account = new Account(userDbo.getNumber(),userDbo.getLimit(),userDbo.getLoginTime());
        session.setAttribute("account", account);
        session.setMaxInactiveInterval(LOGIN_SESSION_TIMEOUT);
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response updatePassward(UserUpdatePasswordReq req, HttpSession session) {
        Account account = (Account)session.getAttribute("account");
        if (account == null) {
            return RespEnum.NO_USER.getResponse();
        }

        if(req == null || req.getOldPassword() == null || req.getNewPassword() == null)
            return RespEnum.PARAMETER_MiSS.getResponse();

        UserMapper userMapper = DBUtil.getMapper(UserMapper.class);
        UserDbo user = userMapper.selectUserByNumber(account.getNumber());
        if (user == null) {
            logger.error("学工号为"+account.getNumber()+"的用户未能找到！");
            return RespEnum.DATA_NOT_FOUND.getResponse();
        }

        if (!req.getOldPassword().equals(user.getPassword())) {
            return RespEnum.PASSWD_ERR.getResponse();
        }

        UserDbo userDbo = new UserDbo();
        userDbo.setNumber(account.getNumber());
        userDbo.setPassword(req.getNewPassword());

        try{
            userMapper.updatePassword(userDbo);
        } catch (Exception e){
            e.printStackTrace();
            return RespEnum.DATA_UPDATE_ERR.getResponse();
        }

        return RespEnum.SUCCESS.getResponse();
    }
}
