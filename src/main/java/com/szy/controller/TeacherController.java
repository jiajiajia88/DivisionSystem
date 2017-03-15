package com.szy.controller;

import com.szy.model.GetStudentInfoListReq;
import com.szy.model.GetVolunteersReq;
import com.szy.model.SaveStudentInfoReq;
import com.szy.service.ITeacherService;
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
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @RequestMapping(value = "/v/stuInfo/add/excel", method = {RequestMethod.POST, RequestMethod.GET})
    public Object uploadStudentInfoByExcel(HttpServletRequest req, @RequestParam("file") MultipartFile file) {
        return teacherService.uploadStudentInfoByExcel(req, file);
    }

    @RequestMapping(value = "/v/stuInfo/add", method = {RequestMethod.POST})
    public Object addStudentInfo(@RequestBody SaveStudentInfoReq req) {
        return teacherService.addStudentInfo(req);
    }

    @RequestMapping(value = "/v/stuInfo/update", method = {RequestMethod.POST})
    public Object updateStudentInfo(@RequestBody SaveStudentInfoReq req) {
        return teacherService.updateStudentInfo(req);
    }

    @RequestMapping(value = "/v/stuInfo/list", method = {RequestMethod.POST})
    public Object getStudentInfoList(@RequestBody GetStudentInfoListReq req) {
        return teacherService.getStudentInfoList(req);
    }

    @RequestMapping(value = "/v/stuInfo/details/{number}", method = {RequestMethod.POST})
    public Object getStudentInfoDetails(@PathVariable long number) {
        return teacherService.getStudentInfoDetails(number);
    }

    @RequestMapping(value = "/v/volunteer/delete/{number}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object deleteVolunteer(@PathVariable long number) {
        return teacherService.deleteVolunteer(number);
    }

    @RequestMapping(value = "/v/volunteer/get/list", method = {RequestMethod.POST})
    public Object getVolunteerList(@RequestBody GetVolunteersReq req) {
        return teacherService.getVolunteers(req);
    }

    @RequestMapping(value = "/v/volunteer/get/details/{number}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getVolunteerDetails(@PathVariable long number) {
        return teacherService.getVolunteerDetails(number);
    }

}
