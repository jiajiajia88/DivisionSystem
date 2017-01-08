package com.szy.controller;

import com.szy.model.UserLoginReq;
import com.szy.model.UserUpdatePasswordReq;
import com.szy.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/4.
 */
@RestController
@RequestMapping("/jg")
public class UserController {

    @Autowired
    private IUserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/user/login", method = {RequestMethod.POST})
    public Object login(@RequestBody UserLoginReq req, HttpSession session) {
        return userService.login(req, session);
    }

    @RequestMapping(value = "/v/user/password/update", method = {RequestMethod.POST})
    public Object updatePasswd(@RequestBody UserUpdatePasswordReq req, HttpSession session) {
        return userService.updatePassward(req, session);
    }
}
