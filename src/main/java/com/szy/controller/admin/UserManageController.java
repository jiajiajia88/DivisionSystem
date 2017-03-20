package com.szy.controller.admin;

import com.szy.model.*;
import com.szy.service.IUserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shizhouyong on 2017/3/19.
 */
@RestController
@RequestMapping("/jg")
public class UserManageController {

    @Autowired
    private IUserManageService userManagementService;

    @RequestMapping(value = "/v/system/account/update/limit", method = {RequestMethod.POST})
    public Object updateAccountLimit(@RequestBody UpdateUserLimitReq req){
        return userManagementService.updateAccountLimit(req);
    }

    @RequestMapping(value = "/v/system/account/delete", method = {RequestMethod.POST})
    public Object deleteAccount(@RequestBody DeleteAccountReq req){
        return userManagementService.deleteAccount(req);
    }

    @RequestMapping(value = "/v/system/teacher/add", method = {RequestMethod.POST})
    public Object addTeacher(@RequestBody SaveTeacherReq req){
        return userManagementService.addTeacher(req);
    }

    @RequestMapping(value = "/v/system/teacher/update", method = {RequestMethod.POST})
    public Object updateTeacher(@RequestBody SaveTeacherReq req){
        return userManagementService.updateTeacher(req);
    }

    @RequestMapping(value = "/v/system/teacher/list", method = {RequestMethod.POST})
    public Object teacherList(@RequestBody GetAdminReq req){
        return userManagementService.teacherList(req);
    }

    @RequestMapping(value = "/v/system/teacher/delete/{number}", method = {RequestMethod.POST,RequestMethod.GET})
    public Object deleteTeacher(@PathVariable long number){
        return userManagementService.deleteTeacher(number);
    }

    @RequestMapping(value = "/v/system/account/stu", method = {RequestMethod.POST})
    public Object getStuAccounts(@RequestBody GetStuAccountReq req) {
        return userManagementService.getStuAccount(req);
    }
}
