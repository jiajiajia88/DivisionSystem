package com.szy.service;

import com.szy.Response;
import com.szy.model.AddPlanReq;
import com.szy.model.GetPlansReq;
import com.szy.model.PlanOperReq;
import com.szy.model.UpdatePlanReq;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public interface IPlanService {

    public Response addPlan(AddPlanReq req);

    public Response deletePlan(int id);

    public Response getPlans(GetPlansReq req);

    public Response getPlanDetails(int id);

    public Response updatePlan(UpdatePlanReq req);

    public Response planOper(PlanOperReq req);
}
