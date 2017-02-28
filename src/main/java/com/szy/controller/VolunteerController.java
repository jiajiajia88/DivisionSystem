package com.szy.controller;

import com.szy.model.GetVolunteersReq;
import com.szy.model.SaveVolunteerReq;
import com.szy.service.IVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 志愿填报和学生信息相关api
 * Created by shizhouyong on 2017/1/4.
 */
@RestController
@RequestMapping("/jg")
public class VolunteerController {

    @Autowired
    private IVolunteerService volunteerService;

    @RequestMapping(value = "/student/v/volunteer/save", method = {RequestMethod.POST})
    public Object saveVolunteer(@RequestBody SaveVolunteerReq req, HttpSession session) {
        return volunteerService.saveVolunteer(req, session);
    }

    @RequestMapping(value = "/student/v/volunteer/commit", method = {RequestMethod.GET,RequestMethod.POST})
    public Object commitVolunteer(HttpSession session) {
        return volunteerService.commitVolunteer(session);
    }

    @RequestMapping(value = "/teacher/v/volunteer/delete/{number}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object deleteVolunteer(@PathVariable long number, HttpSession session) {
        return volunteerService.deleteVolunteer(number, session);
    }

    @RequestMapping(value = "/teacher/v/volunteer/get/list", method = {RequestMethod.POST})
    public Object getVolunteerList(@RequestBody GetVolunteersReq req, HttpSession session) {
        return volunteerService.getVolunteers(req, session);
    }

    @RequestMapping(value = "/teacher/v/volunteer/get/details/{number}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getVolunteerDetails(@PathVariable long number, HttpSession session) {
        return volunteerService.getVolunteerDetails(number, session);
    }

}
