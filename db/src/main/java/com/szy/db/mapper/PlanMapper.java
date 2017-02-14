package com.szy.db.mapper;

import com.szy.db.model.PlanDbo;
import com.szy.db.model.GetPlanItems;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/9.
 */
public interface PlanMapper {

    public int insertPlan(PlanDbo planDbo);

    public PlanDbo selectPlanDetals(int id);

    public List<PlanDbo> selectPlans(GetPlanItems items);

    public int selectPlansTotal(GetPlanItems items);

    public int deletePlan(int id);

    public int updatePlan(PlanDbo planDbo);
}
