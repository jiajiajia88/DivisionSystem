package com.szy.model;

import com.szy.Response;
import com.szy.db.model.PlanDbo;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class GetPlanDetailsResp extends Response{

    private PlanDbo planDbo;

    public PlanDbo getPlanDbo() {
        return planDbo;
    }

    public void setPlanDbo(PlanDbo planDbo) {
        this.planDbo = planDbo;
    }
}