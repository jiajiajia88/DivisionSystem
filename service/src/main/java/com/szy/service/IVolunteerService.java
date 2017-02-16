package com.szy.service;

import com.szy.Response;
import com.szy.model.*;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public interface IVolunteerService {

    /**
     * 保存志愿表
     * @param req
     * @param session
     * @return
     */
    public Response saveVolunteer(SaveVolunteerReq req, HttpSession session);

    /**
     * 提交志愿表
     * @param number
     * @param session
     * @return
     */
    public Response commitVolunteer(HttpSession session);

    /**
     * 删除志愿表
     * @param number
     * @param session
     * @return
     */
    public Response deleteVolunteer(long number, HttpSession session);

    /**
     * 获得志愿表列表
     * @param req
     * @param session
     * @return
     */
    public Response getVolunteers(GetVolunteersReq req, HttpSession session);

    /**
     * 获得志愿表详情
     * @param number
     * @param session
     * @return
     */
    public Response getVolunteerDetails(long number, HttpSession session);


}
