package com.szy.controller.student;

import com.szy.model.GetSystemInfoListReq;
import com.szy.model.SaveVolunteerReq;
import com.szy.model.UpdatePhoneReq;
import com.szy.service.IStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 志愿填报和学生信息相关api接口
 * Created by shizhouyong on 2017/1/4.
 */
@RestController
@RequestMapping("/jg")
public class StuController {

    @Autowired
    private IStuService stuService;

    @RequestMapping(value = "/v/stu/save", method = {RequestMethod.POST})
    public Object saveVolunteer(@RequestBody SaveVolunteerReq req) {
        return stuService.saveVolunteer(req);
    }

    @RequestMapping(value = "/v/stu/volunteer/get", method = {RequestMethod.GET})
    public Object getMyVolunteer() {
        return stuService.getMyVolunteer();
    }

    @RequestMapping(value = "/v/stu/phone/update", method = {RequestMethod.POST})
    public Object updateTelePhone(@RequestBody UpdatePhoneReq req) {
        return stuService.updatePhone(req);
    }

    @RequestMapping(value = "/v/stu/systemInfo/get", method = {RequestMethod.POST})
    public Object getSystemInfos(@RequestBody GetSystemInfoListReq req) {
        return stuService.getSystemInfos(req);
    }

    @RequestMapping(value = "/v/stu/volunteer/select/items", method = {RequestMethod.GET})
    public Object getVolunteerSelectItems() {
        return stuService.getVolunteerSelectItems();
    }

    @RequestMapping(value = "/v/stu/shunt/result", method = {RequestMethod.GET})
    public Object getShuntResult() {
        return stuService.getShuntResult();
    }

}
