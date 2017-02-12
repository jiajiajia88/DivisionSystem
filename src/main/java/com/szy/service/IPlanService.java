package com.szy.service;

import com.szy.Response;
import com.szy.model.AddPlanReq;
import com.szy.model.GetPlansReq;
import com.szy.model.UpdatePlanReq;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public interface IPlanService {

    public Response addPlan(AddPlanReq req, HttpSession session);

    public Response deletePlan(int id, HttpSession session);

    public Response getPlans(GetPlansReq req, HttpSession session);

    public Response getPlanDetails(int id, HttpSession session);

    public Response updatePlan(UpdatePlanReq req, HttpSession session);
}
