package com.szy.controller.admin;

import com.szy.model.AddPlanReq;
import com.szy.model.GetPlansReq;
import com.szy.model.PlanOperReq;
import com.szy.model.UpdatePlanReq;
import com.szy.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shizhouyong on 2017/1/4.
 */
@RestController
@RequestMapping("/jg")
public class PlanController {

    @Autowired
    private IPlanService planService;

    @RequestMapping(value = "/v/plan/add", method = {RequestMethod.POST})
    public Object addPlan(@RequestBody AddPlanReq req) {
        return planService.addPlan(req);
    }

    @RequestMapping(value = "/v/plan/delete/{planId}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object deletePlan(@PathVariable int planId) {
        return planService.deletePlan(planId);
    }

    @RequestMapping(value = "/v/plan/get/list", method = {RequestMethod.POST})
    public Object getPlanList(@RequestBody GetPlansReq req) {
        return planService.getPlans(req);
    }

    @RequestMapping(value = "/v/plan/get/details/{planId}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getPlanDetails(@PathVariable int planId) {
        return planService.getPlanDetails(planId);
    }

    @RequestMapping(value = "/v/plan/update", method = {RequestMethod.POST})
    public Object updatePlan(@RequestBody UpdatePlanReq req) {
        return planService.updatePlan(req);
    }

    @RequestMapping(value = "/v/plan/online", method = {RequestMethod.POST})
    public Object planOper(@RequestBody PlanOperReq req) {
        return planService.planOper(req);
    }

}
