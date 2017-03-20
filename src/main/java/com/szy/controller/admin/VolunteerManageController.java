package com.szy.controller.admin;

import com.szy.model.GetVolunteersReq;
import com.szy.service.IVolunteerManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shizhouyong on 2017/3/19.
 */
@RestController
@RequestMapping("/jg")
public class VolunteerManageController {

    @Autowired
    private IVolunteerManageService volunteerManageService;

    @RequestMapping(value = "/v/volunteer/delete/{number}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object deleteVolunteer(@PathVariable long number) {
        return volunteerManageService.deleteVolunteer(number);
    }

    @RequestMapping(value = "/v/volunteer/get/list", method = {RequestMethod.POST})
    public Object getVolunteerList(@RequestBody GetVolunteersReq req) {
        return volunteerManageService.getVolunteers(req);
    }

    @RequestMapping(value = "/v/volunteer/get/details/{number}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getVolunteerDetails(@PathVariable long number) {
        return volunteerManageService.getVolunteerDetails(number);
    }
}
