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

    /**
     * 新增分流计划
     * @param req
     * @return
     */
    public Response addPlan(AddPlanReq req);

    /**
     * 删除分流计划
     * @param id
     * @return
     */
    public Response deletePlan(int id);

    /**
     *
     * @param req
     * @return
     */
    public Response getPlans(GetPlansReq req);

    /**
     *
     * @param id
     * @return
     */
    public Response getPlanDetails(int id);

    /**
     *
     * @param req
     * @return
     */
    public Response updatePlan(UpdatePlanReq req);

    /**
     *
     * @param req
     * @return
     */
    public Response planOper(PlanOperReq req);
}
