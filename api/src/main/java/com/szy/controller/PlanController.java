package com.szy.controller;

import com.szy.model.AddPlanReq;
import com.szy.model.GetPlansReq;
import com.szy.model.UpdatePlanReq;
import com.szy.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/4.
 */
@RestController
@RequestMapping("/jg")
public class PlanController {

    @Autowired
    private IPlanService planService;

    @RequestMapping(value = "/v/plan/add", method = {RequestMethod.POST})
    public Object addPlan(@RequestBody AddPlanReq req, HttpSession session) {
        return planService.addPlan(req, session);
    }

    @RequestMapping(value = "/v/plan/delete/{planId}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object deletePlan(@PathVariable int planId, HttpSession session) {
        return planService.deletePlan(planId, session);
    }

    @RequestMapping(value = "/v/plan/get/list", method = {RequestMethod.POST})
    public Object getPlanList(@RequestBody GetPlansReq req, HttpSession session) {
        return planService.getPlans(req, session);
    }

    @RequestMapping(value = "/v/plan/get/details/{planId}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getPlanDetails(@PathVariable int planId, HttpSession session) {
        return planService.getPlanDetails(planId, session);
    }

    @RequestMapping(value = "/v/plan/update", method = {RequestMethod.POST})
    public Object updatePlan(@RequestBody UpdatePlanReq req, HttpSession session) {
        return planService.updatePlan(req, session);
    }
}
