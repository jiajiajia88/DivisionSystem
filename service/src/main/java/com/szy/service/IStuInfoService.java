package com.szy.service;

import com.szy.Response;
import com.szy.model.AddStudentInfoReq;
import com.szy.model.GetVolunteersReq;
import com.szy.model.SaveVolunteerReq;
import com.szy.model.UpdateStudentInfoReq;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * 学生信息相关
 * Created by shizhouyong on 2017/1/24.
 */
public interface IStuInfoService {

    public Response uploadStudentInfoByExcel(@RequestParam("file") MultipartFile file, HttpSession session);

    public Response addStudentInfo(@RequestBody AddStudentInfoReq req, HttpSession session);

    public Response updateStudentInfo(@RequestBody UpdateStudentInfoReq req, HttpSession session);

    public Response getStudentInfoList(HttpSession session);

    public Response getStudentInfoDetails(@PathVariable long number, HttpSession session);
}
