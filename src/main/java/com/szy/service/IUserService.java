package com.szy.service;

import com.szy.Response;
import com.szy.model.UserLoginReq;
import com.szy.model.UserUpdatePasswordReq;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/7.
 */
public interface IUserService {

    /**
     * 登录
     * @param req
     * @param session
     * @return
     */
    public Response login(UserLoginReq req, HttpSession session);

    /**
     * 密码更新
     * @param req
     * @param session
     * @return
     */
    public Response updatePassward(UserUpdatePasswordReq req, HttpSession session);
}
