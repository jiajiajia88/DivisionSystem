package com.szy.controller.admin;

import com.szy.model.*;
import com.szy.service.IStuInfoManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 志愿填报和学生信息相关api
 * Created by shizhouyong on 2017/1/4.
 */
@RestController
@RequestMapping("/jg")
public class StuInfoMangeController {

    @Autowired
    private IStuInfoManageService stuInfoManageService;

    @RequestMapping(value = "/v/stuInfo/add/excel", method = {RequestMethod.POST, RequestMethod.GET})
    public Object uploadStudentInfoByExcel(HttpServletRequest req, @RequestParam("file") MultipartFile file) {
        return stuInfoManageService.uploadStudentInfoByExcel(req, file);
    }

    @RequestMapping(value = "/v/stuInfo/add", method = {RequestMethod.POST})
    public Object addStudentInfo(@RequestBody SaveStudentInfoReq req) {
        return stuInfoManageService.addStudentInfo(req);
    }

    @RequestMapping(value = "/v/stuInfo/update", method = {RequestMethod.POST})
    public Object updateStudentInfo(@RequestBody SaveStudentInfoReq req) {
        return stuInfoManageService.updateStudentInfo(req);
    }

    @RequestMapping(value = "/v/stuInfo/list", method = {RequestMethod.POST})
    public Object getStudentInfoList(@RequestBody GetStudentInfoListReq req) {
        return stuInfoManageService.getStudentInfoList(req);
    }

    @RequestMapping(value = "/v/stuInfo/details/{number}", method = {RequestMethod.POST})
    public Object getStudentInfoDetails(@PathVariable long number) {
        return stuInfoManageService.getStudentInfoDetails(number);
    }

}
