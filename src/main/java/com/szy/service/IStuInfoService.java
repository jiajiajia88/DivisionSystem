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
public interface IStuInfoService {

    public Response uploadStudentInfoByExcel(HttpServletRequest req, MultipartFile file, HttpSession session);

    public Response addStudentInfo(SaveStudentInfoReq req, HttpSession session);

    public Response updateStudentInfo(SaveStudentInfoReq req, HttpSession session);

    public Response getStudentInfoList(GetStudentInfoListReq req, HttpSession session);

    public Response getStudentInfoDetails(long number, HttpSession session);
}
