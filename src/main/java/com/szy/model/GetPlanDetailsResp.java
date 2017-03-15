package com.szy.model;

import com.szy.Response;
import com.szy.db.model.PlanDbo;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class GetPlanDetailsResp extends Response{

    private PlanResp plan;

    public PlanResp getPlan() {
        return plan;
    }

    public void setPlan(PlanResp plan) {
        this.plan = plan;
    }
}