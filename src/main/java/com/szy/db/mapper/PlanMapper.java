package com.szy.db.mapper;

import com.szy.db.model.PlanDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/9.
 */
public interface PlanMapper {

    public int insertPlan(PlanDbo planDbo);
    public List<PlanDbo> selectPlans();
    public int deletePlan(int id);
}
