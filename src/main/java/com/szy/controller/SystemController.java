package com.szy.controller;

import com.szy.model.*;
import com.szy.service.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/15.
 */

@RestController
@RequestMapping("/jg")
public class SystemController {

    @Autowired
    ISystemService systemService;

    @RequestMapping(value = "/system/grade/add", method = {RequestMethod.POST})
    public Object addGrade(@RequestBody AddGradeReq req, HttpSession session) {
        return systemService.addGrade(req, session);
    }

    @RequestMapping(value = "/system/major/add", method = {RequestMethod.POST})
    public Object addMajor(@RequestBody AddMajorReq req, HttpSession session) {
        return systemService.addMajor(req, session);
    }

    @RequestMapping(value = "/system/category/add", method = {RequestMethod.POST})
    public Object addCategory(@RequestBody AddCategoryReq req, HttpSession session) {
        return systemService.addCategory(req, session);
    }

    @RequestMapping(value = "/system/position/add", method = {RequestMethod.POST})
    public Object addPosition(@RequestBody AddPositionReq req, HttpSession session) {
        return systemService.addPosition(req, session);
    }

    @RequestMapping(value = "/system/systemInfo/delete", method = {RequestMethod.POST})
    public Object deleteSystemInfo(@RequestBody DeleteSystemInfoReq req, HttpSession session) {
        return systemService.deleteSystemInfo(req, session);
    }

    @RequestMapping(value = "/system/systemInfo/get", method = {RequestMethod.POST})
    public Object getSystemInfos(GetSystemInfoListReq req, HttpSession session) {
        return systemService.getSystemInfos(req, session);
    }


}
