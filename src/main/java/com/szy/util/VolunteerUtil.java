package com.szy.util;

import com.alibaba.fastjson.JSON;
import com.szy.db.mapper.PlanMapper;
import com.szy.db.model.PlanQueryDbo;
import com.szy.vo.PlanKey;
import com.szy.vo.PlanUnit;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将所有正在进行中的分流计划放入内存map中维护
 * Created by shizhouyong on 2017/1/9.
 */
@Component
public class VolunteerUtil {

    private static volatile Map<PlanKey, List<PlanUnit>> planMap = new ConcurrentHashMap<>();

    public void updateMap() {
        PlanMapper planMapper = DBUtil.getMapper(PlanMapper.class);
        List<PlanQueryDbo> planQueryDboList = planMapper.selectAllAlivePlans();
        Map<PlanKey, List<PlanUnit>> addPlanMap = new ConcurrentHashMap<>();
        for (PlanQueryDbo planQueryDbo : planQueryDboList) {
            if (planQueryDbo.getDetails() != null) {
                List<PlanUnit> planUnits = JSON.parseArray(planQueryDbo.getDetails(), PlanUnit.class);

                PlanKey planKey = new PlanKey(planQueryDbo.getGradeId(),planQueryDbo.getCategoryId());
                addPlanMap.put(planKey, planUnits);
            }
        }

        planMap = addPlanMap;
    }

    public List<PlanUnit> getUnits(PlanKey planKey) throws Exception {
        return planMap.get(planKey);
    }

}
