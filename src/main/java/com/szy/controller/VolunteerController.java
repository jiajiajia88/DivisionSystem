package com.szy.controller;

import com.szy.model.AddVolunteerReq;
import com.szy.model.GetVolunteersReq;
import com.szy.model.UpdateVolunteerReq;
import com.szy.service.IVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/4.
 */
@RestController
@RequestMapping("/jg")
public class VolunteerController {

    @Autowired
    private IVolunteerService volunteerService;

    @RequestMapping(value = "/volunteer/add", method = {RequestMethod.POST})
    public Object addVolunteer(@RequestBody AddVolunteerReq req, HttpSession session) {
        return volunteerService.addVolunteer(req, session);
    }

    @RequestMapping(value = "/volunteer/delete/{number}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object deleteVolunteer(@PathVariable int planId, HttpSession session) {
        return volunteerService.deleteVolunteer(planId, session);
    }

    @RequestMapping(value = "/volunteer/get/list", method = {RequestMethod.POST})
    public Object getVolunteerList(@RequestBody GetVolunteersReq req, HttpSession session) {
        return volunteerService.getVolunteers(req, session);
    }

    @RequestMapping(value = "/volunteer/get/details/{number}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getVolunteerDetails(@PathVariable int planId, HttpSession session) {
        return volunteerService.getVolunteerDetails(planId, session);
    }

    @RequestMapping(value = "/volunteer/update", method = {RequestMethod.POST})
    public Object updateVolunteer(@RequestBody UpdateVolunteerReq req, HttpSession session) {
        return volunteerService.updateVolunteer(req, session);
    }
}
