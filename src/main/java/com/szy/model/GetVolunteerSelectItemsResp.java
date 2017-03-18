package com.szy.model;

import com.szy.Response;
import com.szy.vo.PlanUnit;

import java.util.List;

/**
 * Created by shizhouyong on 2017/3/12.
 */
public class GetVolunteerSelectItemsResp extends Response {

    private List<PlanUnit> planUnits;

    public GetVolunteerSelectItemsResp(List<PlanUnit> planUnits) {
        this.planUnits = planUnits;
    }

    public List<PlanUnit> getPlanUnits() {

        return planUnits;
    }

    public void setPlanUnits(List<PlanUnit> planUnits) {
        this.planUnits = planUnits;
    }
}
