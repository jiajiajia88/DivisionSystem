package com.szy.controller;

import com.szy.model.AddStudentInfoReq;
import com.szy.model.UpdateStudentInfoReq;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * 志愿填报和学生信息相关api
 * Created by shizhouyong on 2017/1/4.
 */
@RestController
@RequestMapping("/jg")
public class StuInfoController {

    @RequestMapping(value = "/stuInfo/add/excel", method = {RequestMethod.POST, RequestMethod.GET})
    public Object uploadStudentInfoByExcel(@RequestParam("file") MultipartFile file, HttpSession session) {
        return null;
    }

    @RequestMapping(value = "/stuInfo/add", method = {RequestMethod.POST})
    public Object addStudentInfo(@RequestBody AddStudentInfoReq req, HttpSession session) {
        return null;
    }

    @RequestMapping(value = "/stuInfo/update", method = {RequestMethod.POST})
    public Object updateStudentInfo(@RequestBody UpdateStudentInfoReq req, HttpSession session) {
        return null;
    }

    @RequestMapping(value = "/stuInfo/list", method = {RequestMethod.POST})
    public Object getStudentInfoList(HttpSession session) {
        return null;
    }

    @RequestMapping(value = "/stuInfo/details/{number}", method = {RequestMethod.POST})
    public Object getStudentInfoDetails(@PathVariable long number, HttpSession session) {
        return null;
    }

}
