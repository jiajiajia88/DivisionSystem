package com.szy.service;

import com.szy.Response;
import com.szy.model.GetVolunteersReq;

/**
 * 学生信息相关
 * Created by shizhouyong on 2017/1/24.
 */
public interface IVolunteerManageService {

    /**
     * 删除志愿表
     * @param number
     * @return
     */
    public Response deleteVolunteer(long number);

    /**
     * 获得志愿表列表
     * @param req
     * @return
     */
    public Response getVolunteers(GetVolunteersReq req);

    /**
     * 获得志愿表详情
     * @param number
     * @return
     */
    public Response getVolunteerDetails(long number);

}
