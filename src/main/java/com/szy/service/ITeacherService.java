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

    /**
     * 从Excel批量导入学生信息
     * @param req
     * @param file
     * @return
     */
    public Response uploadStudentInfoByExcel(HttpServletRequest req, MultipartFile file);

    /**
     * 单个导入学生信息
     * @param req
     * @return
     */
    public Response addStudentInfo(SaveStudentInfoReq req);

    /**
     * 更新学生信息
     * @param req
     * @return
     */
    public Response updateStudentInfo(SaveStudentInfoReq req);

    /**
     * 获得学生信息列表
     * @param req
     * @return
     */
    public Response getStudentInfoList(GetStudentInfoListReq req);

    /**
     * 获得学生信息详情
     * @param number
     * @return
     */
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

    public Response calculateGrade(ShuntReq req);

    public Response shunt(ShuntReq req);
}
