package com.szy.service;

import com.szy.Response;
import com.szy.model.UserLoginReq;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/7.
 */
public interface IUserService {

    public Response login(UserLoginReq req, HttpSession session);
}
