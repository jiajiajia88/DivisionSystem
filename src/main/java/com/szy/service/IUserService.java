package com.szy.service;

import com.szy.Response;
import com.szy.model.UserLoginReq;
import com.szy.model.UserUpdatePasswordReq;

/**
 * Created by shizhouyong on 2017/1/7.
 */
public interface IUserService {

    /**
     * 登录
     * @param req
     * @return
     */
    public Response login(UserLoginReq req);

    /**
     * 登出
     * @param number
     * @return
     */
    public Response logout(long number);

    /**
     * 密码更新
     * @param req
     * @return
     */
    public Response updatePassward(UserUpdatePasswordReq req);

}
