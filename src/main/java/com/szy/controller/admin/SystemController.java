package com.szy.controller.admin;

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

}
