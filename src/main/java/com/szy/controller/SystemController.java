package com.szy.controller;

import com.szy.model.*;
import com.szy.service.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 系统相关接口
 * Created by shizhouyong on 2017/1/15.
 */

@RestController
@RequestMapping("/jg")
public class SystemController {

    @Autowired
    private ISystemService systemService;

    @RequestMapping(value = "/v/system/grade/add", method = {RequestMethod.POST})
    public Object addGrade(@RequestBody AddGradeReq req) {
        return systemService.addGrade(req);
    }

    @RequestMapping(value = "/v/system/major/add", method = {RequestMethod.POST})
    public Object addMajor(@RequestBody AddMajorReq req) {
        return systemService.addMajor(req);
    }

    @RequestMapping(value = "/v/system/major/get/{category}", method = {RequestMethod.GET})
    public Object getMajorByCategory(@PathVariable int category){
        return systemService.getMajorByCategory(category);
    }

    @RequestMapping(value = "/v/system/category/add", method = {RequestMethod.POST})
    public Object addCategory(@RequestBody AddCategoryReq req) {
        return systemService.addCategory(req);
    }

    @RequestMapping(value = "/v/system/position/add", method = {RequestMethod.POST})
    public Object addPosition(@RequestBody AddPositionReq req) {
        return systemService.addPosition(req);
    }

    @RequestMapping(value = "/v/system/systemInfo/delete", method = {RequestMethod.POST})
    public Object deleteSystemInfo(@RequestBody DeleteSystemInfoReq req) {
        return systemService.deleteSystemInfo(req);
    }

    @RequestMapping(value = "/v/system/systemInfo/get", method = {RequestMethod.POST})
    public Object getSystemInfos(@RequestBody GetSystemInfoListReq req) {
        return systemService.getSystemInfos(req);
    }

    @RequestMapping(value = "/v/system/teacher/add", method = {RequestMethod.POST})
    public Object addTeacher(@RequestBody SaveTeacherReq req){
        return systemService.addTeacher(req);
    }

    @RequestMapping(value = "/v/system/teacher/update", method = {RequestMethod.POST})
    public Object updateTeacher(@RequestBody SaveTeacherReq req){
        return systemService.updateTeacher(req);
    }

    @RequestMapping(value = "/v/system/teacher/list", method = {RequestMethod.GET,RequestMethod.POST})
    public Object teacherList(){
        return systemService.teacherList();
    }

    @RequestMapping(value = "/v/system/teacher/delete/{number}", method = {RequestMethod.POST,RequestMethod.GET})
    public Object deleteTeacher(@PathVariable long number){
        return systemService.deleteTeacher(number);
    }

    @RequestMapping(value = "/v/system/account/stu", method = {RequestMethod.POST})
    public Object getStuAccounts(@RequestBody GetStuAccountReq req) {
        return systemService.getStuAccount(req);
    }

}
