package com.szy.service;

import com.szy.Response;
import com.szy.model.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 学生信息相关
 * Created by shizhouyong on 2017/1/24.
 */
public interface ITeacherService {

    public Response uploadStudentInfoByExcel(HttpServletRequest req, MultipartFile file);

    public Response addStudentInfo(SaveStudentInfoReq req);

    public Response updateStudentInfo(SaveStudentInfoReq req);

    public Response getStudentInfoList(GetStudentInfoListReq req);

    public Response getStudentInfoDetails(long number);

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
