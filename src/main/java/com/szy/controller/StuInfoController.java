package com.szy.controller;

import com.szy.model.GetStudentInfoListReq;
import com.szy.model.SaveStudentInfoReq;
import com.szy.service.IStuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 志愿填报和学生信息相关api
 * Created by shizhouyong on 2017/1/4.
 */
@RestController
@RequestMapping("/jg")
public class StuInfoController {

    @Autowired
    private IStuInfoService stuInfoService;

    @RequestMapping(value = "/v/stuInfo/add/excel", method = {RequestMethod.POST, RequestMethod.GET})
    public Object uploadStudentInfoByExcel(HttpServletRequest req, @RequestParam("file") MultipartFile file, HttpSession session) {
        return stuInfoService.uploadStudentInfoByExcel(req, file, session);
    }

    @RequestMapping(value = "/v/stuInfo/add", method = {RequestMethod.POST})
    public Object addStudentInfo(@RequestBody SaveStudentInfoReq req, HttpSession session) {
        return stuInfoService.addStudentInfo(req, session);
    }

    @RequestMapping(value = "/v/stuInfo/update", method = {RequestMethod.POST})
    public Object updateStudentInfo(@RequestBody SaveStudentInfoReq req, HttpSession session) {
        return stuInfoService.updateStudentInfo(req, session);
    }

    @RequestMapping(value = "/v/stuInfo/list", method = {RequestMethod.POST})
    public Object getStudentInfoList(@RequestBody GetStudentInfoListReq req, HttpSession session) {
        return stuInfoService.getStudentInfoList(req, session);
    }

    @RequestMapping(value = "/v/stuInfo/details/{number}", method = {RequestMethod.POST})
    public Object getStudentInfoDetails(@PathVariable long number, HttpSession session) {
        return stuInfoService.getStudentInfoDetails(number, session);
    }

}
