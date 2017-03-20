package com.szy.service;

import com.szy.Response;
import com.szy.model.*;

/**
 * Created by shizhouyong on 2017/1/7.
 */
public interface IUserManageService {

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

    /**
     * 新增教师
     * @param req
     * @return
     */
    public Response addTeacher(SaveTeacherReq req);

    /**
     * 更新教师
     * @param req
     * @return
     */
    public Response updateTeacher(SaveTeacherReq req);

    /**
     * 获取教师列表
     * @param req
     * @return
     */
    public Response teacherList(GetAdminReq req);

    /**
     * 删除教师信息
     * @param number
     * @return
     */
    public Response deleteTeacher(long number);

    /**
     * 获取学生账户列表
     * @param req
     * @return
     */
    public Response getStuAccount(GetStuAccountReq req);
}
