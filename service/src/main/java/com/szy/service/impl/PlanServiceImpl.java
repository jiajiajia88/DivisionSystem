package com.szy.service.impl;

import com.alibaba.fastjson.JSON;
import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.PlanMapper;
import com.szy.db.model.GetPlanItems;
import com.szy.db.model.PlanDbo;
import com.szy.model.*;
import com.szy.service.IPlanService;
import com.szy.util.DBUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by shizhouyong on 2017/1/24.
 */
@Service("IPlanService")
public class PlanServiceImpl implements IPlanService {

    @Override
    public Response addPlan(AddPlanReq req, HttpSession session) {
        if(req == null || req.getGrade() == 0 || req.getCategory() == 0){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        long cur = System.currentTimeMillis() / 1000;
        PlanMapper planMapper = DBUtil.getMapper(PlanMapper.class);

        PlanDbo dbo = new PlanDbo();
        dbo.setGrade(req.getGrade());
        dbo.setCategory(req.getCategory());
        if(!StringUtils.isBlank(req.getDetails())){
            List<PlanUnit> majors = null;
            try {
                majors = JSON.parseArray(req.getDetails(), PlanUnit.class);
            } catch (Exception e) {
                e.printStackTrace();
                return RespEnum.DATA_PARSE_ERR.getResponse();
            }
            dbo.setDetails(req.getDetails());
        }
        dbo.setStudentAmount(req.getStudentAmount());
        dbo.setMajorAmount(req.getMajorAmount());
        dbo.setCreateTime(cur);
        dbo.setRemarks(req.getRemarks());
        try{
            planMapper.insertPlan(dbo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }

        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response deletePlan(int id, HttpSession session) {
        if(id == 0){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        PlanMapper planMapper = DBUtil.getMapper(PlanMapper.class);
        try{
            planMapper.deletePlan(id);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_DELETE_ERR.getResponse();
        }

        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response getPlans(GetPlansReq req, HttpSession session) {
        if (req == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        GetPlanItems items;
        if (req.getItems() == null) {
            items = new GetPlanItems();
        } else {
            items = req.getItems();
        }
        PlanMapper planMapper = DBUtil.getMapper(PlanMapper.class);
        List<PlanDbo> planDbos = planMapper.selectPlans(items);
        int total = planMapper.selectPlansTotal(items);
        GetPlanListResp resp = new GetPlanListResp();
        resp.setPlanDbos(planDbos);
        resp.setTotal(total);
        return resp;
    }

    @Override
    public Response getPlanDetails(int id, HttpSession session) {
        if(id == 0){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        PlanMapper planMapper = DBUtil.getMapper(PlanMapper.class);
        PlanDbo planDbo = planMapper.selectPlanDetals(id);
        if (planDbo == null) {
            return RespEnum.DATA_NOT_FOUND.getResponse();
        }
        GetPlanDetailsResp resp = new GetPlanDetailsResp();
        resp.setPlanDbo(planDbo);
        return resp;
    }

    @Override
    public Response updatePlan(UpdatePlanReq req, HttpSession session) {
        if(req == null || req.getGrade() == 0 || req.getCategory() == 0){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        long cur = System.currentTimeMillis() / 1000;
        PlanMapper planMapper = DBUtil.getMapper(PlanMapper.class);

        PlanDbo planDbo = new PlanDbo();
        planDbo.setId(req.getId());
        planDbo.setGrade(req.getGrade());
        planDbo.setCategory(req.getCategory());
        if(!StringUtils.isBlank(req.getDetails())){
            List<PlanUnit> majors = null;
            try {
                majors = JSON.parseArray(req.getDetails(), PlanUnit.class);
            } catch (Exception e) {
                e.printStackTrace();
                return RespEnum.DATA_PARSE_ERR.getResponse();
            }
            planDbo.setDetails(req.getDetails());
        }
        planDbo.setStudentAmount(req.getStudentAmount());
        planDbo.setMajorAmount(req.getMajorAmount());
        planDbo.setUpdateTime(cur);
        planDbo.setRemarks(req.getRemarks());

        try {
            planMapper.updatePlan(planDbo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_UPDATE_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }
}
