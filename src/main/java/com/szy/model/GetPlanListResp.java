package com.szy.model;

import com.szy.Response;
import com.szy.db.model.PlanDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class GetPlanListResp extends Response{

    private int total;
    private List<PlanDbo> planDbos;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PlanDbo> getPlanDbos() {
        return planDbos;
    }

    public void setPlanDbos(List<PlanDbo> planDbos) {
        this.planDbos = planDbos;
    }
}
