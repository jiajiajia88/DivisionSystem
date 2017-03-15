package com.szy.service;

import com.szy.Response;
import com.szy.model.DeleteAccountReq;
import com.szy.model.UpdateUserLimitReq;
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
     * 密码更新
     * @param req
     * @return
     */
    public Response updatePassward(UserUpdatePasswordReq req);

    /**
     * 更新账户状态
     * @param req
     * @return
     */
    public Response updateAccountLimit(UpdateUserLimitReq req);

    /**
     * 删除账号
     * @param req
     * @return
     */
    public Response deleteAccount(DeleteAccountReq req);
}
