package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.UserMapper;
import com.szy.db.model.UserDbo;
import com.szy.model.Account;
import com.szy.model.UserLoginReq;
import com.szy.service.IUserService;
import com.szy.util.DBUtil;
import com.szy.util.UserLimitUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/7.
 */
@Service("IUserService")
public class UserServiceImpl implements IUserService {

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
        return RespEnum.SUCCESS.getResponse();
    }
}
