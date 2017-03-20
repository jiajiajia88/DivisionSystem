package com.szy.controller.admin;

import com.szy.model.*;
import com.szy.service.IShuntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 志愿填报和学生信息相关api
 * Created by shizhouyong on 2017/1/4.
 */
@RestController
@RequestMapping("/jg")
public class ShuntController {

    @Autowired
    private IShuntService shuntService;

    @RequestMapping(value = "/v/operation/calculate", method = {RequestMethod.POST})
    public Object calculateGrade(@RequestBody ShuntToMajorReq req) {
        return shuntService.calculateGrade(req);
    }

    @RequestMapping(value = "/v/operation/shunt/major", method = {RequestMethod.POST})
    public Object shuntToMajor(@RequestBody ShuntToMajorReq req) {
        return shuntService.shuntToMajor(req);
    }

    @RequestMapping(value = "/v/operation/shunt/class", method = {RequestMethod.POST})
    public Object shuntToClass(@RequestBody ShuntToClassReq req) {
        return shuntService.shuntToClass(req);
    }

    @RequestMapping(value = "/v/operation/newMajor/list", method = {RequestMethod.POST})
    public Object getNewMajorList(@RequestBody GetNewMajorListReq req) {
        return shuntService.getNewMajorList(req);
    }

    @RequestMapping(value = "/v/operation/newClass/list", method = {RequestMethod.POST})
    public Object getNewClassList(@RequestBody GetNewClassListReq req) {
        return shuntService.getNewClassList(req);
    }

    @RequestMapping(value = "/v/operation/shunt/adjust", method = {RequestMethod.POST})
    public Object shuntAdjust(@RequestBody ShuntAdjustReq req) {
        return shuntService.shuntAdjust(req);
    }

    @RequestMapping(value = "/v/operation/shunt/publish", method = {RequestMethod.POST})
    public Object shuntAdjust(@RequestBody ShuntPublishReq req) {
        return shuntService.shuntPublish(req);
    }

}
