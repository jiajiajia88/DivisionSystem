package com.szy.db.mapper;

import com.szy.db.model.PlanDbo;
import com.szy.db.model.GetPlanItems;
import com.szy.db.model.PlanOperDbo;
import com.szy.db.model.PlanQueryDbo;
import com.szy.model.PlanOperReq;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/9.
 */
public interface PlanMapper {

    public int insertPlan(PlanDbo planDbo);

    public PlanQueryDbo selectPlanByGradeAndCategory(GetPlanItems items);

    public PlanQueryDbo selectPlanDetals(int id);

    public List<PlanQueryDbo> selectPlans(GetPlanItems items);

    public int selectPlansTotal(GetPlanItems items);

    public int deletePlan(int id);

    public int updatePlan(PlanDbo planDbo);

    public List<PlanQueryDbo> selectAllAlivePlans();

    public int updatePlanStatus(PlanOperDbo dbo);
}
