package com.szy.model;

import com.szy.Response;
import com.szy.db.model.PlanDbo;
import com.szy.db.model.PlanQueryDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class GetPlanListResp extends Response{

    private int total;
    private List<PlanResp> planList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PlanResp> getPlanList() {
        return planList;
    }

    public void setPlanList(List<PlanResp> planList) {
        this.planList = planList;
    }
}
