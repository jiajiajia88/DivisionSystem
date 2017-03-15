package com.szy.service.impl;

import com.alibaba.fastjson.JSON;
import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.PlanMapper;
import com.szy.db.mapper.SystemMapper;
import com.szy.db.model.*;
import com.szy.model.*;
import com.szy.service.IPlanService;
import com.szy.util.DBUtil;
import com.szy.util.VolunteerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 分流计划
 * Created by shizhouyong on 2017/1/24.
 */
@Service("IPlanService")
public class PlanServiceImpl implements IPlanService {

    @Autowired
    private VolunteerUtil volunteerUtil;

    @Override
    public Response addPlan(AddPlanReq req) {
        if(req == null || req.getGrade() == 0 || req.getCategory() == 0){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        long cur = System.currentTimeMillis() / 1000;
        PlanMapper planMapper = DBUtil.getMapper(PlanMapper.class);
        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        PlanDbo dbo = new PlanDbo();
        dbo.setGrade(req.getGrade());
        dbo.setCategory(req.getCategory());

        final int[] stuAmount = {0};
        List<PlanUnit> majors = req.getDetails();
        if (majors != null && majors.size() > 0){

            majors.forEach(t-> {
                stuAmount[0] = stuAmount[0] + t.getStuAmount();
                MajorQueryDbo majorQueryDbo = systemMapper.selectMajorById(t.getId());
                if (majorQueryDbo != null) {
                    t.setName(majorQueryDbo.getName());
                }
            });
            dbo.setDetails(JSON.toJSONString(majors));
            dbo.setStudentAmount(stuAmount[0]);
            dbo.setMajorAmount(majors.size());
        }

        dbo.setEndTime(req.getEndTime());
        dbo.setCreateTime(cur);
        dbo.setRemarks(req.getRemarks());
        try{
            planMapper.insertPlan(dbo);
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate entry"))
                return RespEnum.DUPLICATE_DATA.getResponse();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        volunteerUtil.updateMap();
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response deletePlan(int id) {
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
    public Response getPlans(GetPlansReq req) {
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
        List<PlanQueryDbo> planDbos = planMapper.selectPlans(items);
        int total = planMapper.selectPlansTotal(items);

        List<PlanResp> planRespList = new ArrayList<>();
        for (PlanQueryDbo dbo : planDbos) {
            PlanResp planResp = new PlanResp(dbo.getId(),dbo.getGradeId(),dbo.getCategoryId(),dbo.getGrade(), dbo.getCategory(),dbo.getStudentAmount(),dbo.getMajorAmount(),
                    dbo.getEndTime(),dbo.getCreateTime(),dbo.getUpdateTime(),dbo.getStatus());

            String details = dbo.getDetails();
            if (details != null) {
                List<PlanUnit> planUnits = JSON.parseArray(details, PlanUnit.class);

                planResp.setUnits(planUnits);
            }
            planRespList.add(planResp);
        }

        GetPlanListResp resp = new GetPlanListResp();
        resp.setPlanList(planRespList);
        resp.setTotal(total);
        return resp;
    }

    @Override
    public Response getPlanDetails(int id) {
        if(id == 0){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        PlanMapper planMapper = DBUtil.getMapper(PlanMapper.class);
        PlanQueryDbo dbo = planMapper.selectPlanDetals(id);
        if (dbo == null) {
            return RespEnum.DATA_NOT_FOUND.getResponse();
        }

        PlanResp planResp = new PlanResp(dbo.getId(),dbo.getGradeId(),dbo.getCategoryId(),dbo.getGrade(), dbo.getCategory(),dbo.getStudentAmount(),dbo.getMajorAmount(),
                dbo.getEndTime(),dbo.getCreateTime(),dbo.getUpdateTime(),dbo.getStatus());

        String details = dbo.getDetails();
        if (details != null) {
            List<PlanUnit> planUnits = JSON.parseArray(details, PlanUnit.class);
            planResp.setUnits(planUnits);
        }

        GetPlanDetailsResp resp = new GetPlanDetailsResp();
        resp.setPlan(planResp);
        return resp;
    }

    @Override
    public Response updatePlan(UpdatePlanReq req) {
        if(req == null || req.getGrade() == 0 || req.getCategory() == 0){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        long cur = System.currentTimeMillis() / 1000;
        PlanMapper planMapper = DBUtil.getMapper(PlanMapper.class);
        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        PlanDbo dbo = new PlanDbo();
        dbo.setId(req.getId());
        dbo.setGrade(req.getGrade());
        dbo.setCategory(req.getCategory());
        final int[] stuAmount = {0};
        List<PlanUnit> majors = req.getDetails();
        if (majors != null && majors.size() > 0){

            majors.forEach(t-> {
                stuAmount[0] = stuAmount[0] + t.getStuAmount();
                MajorQueryDbo majorQueryDbo = systemMapper.selectMajorById(t.getId());
                if (majorQueryDbo != null) {
                    t.setName(majorQueryDbo.getName());
                }
            });
            dbo.setDetails(JSON.toJSONString(majors));
            dbo.setStudentAmount(stuAmount[0]);
            dbo.setMajorAmount(majors.size());
        }

        dbo.setEndTime(req.getEndTime());
        dbo.setUpdateTime(cur);
        dbo.setRemarks(req.getRemarks());

        try {
            planMapper.updatePlan(dbo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_UPDATE_ERR.getResponse();
        }
        volunteerUtil.updateMap();
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response planOper(PlanOperReq req) {
        if (req == null || req.getId() == 0 || req.getOper() == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        PlanOperDbo dbo = new PlanOperDbo(req.getId(), req.getOper());
        PlanMapper planMapper = DBUtil.getMapper(PlanMapper.class);
        int n = 0;

        try {
            n = planMapper.updatePlanStatus(dbo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (n != 1) {
            return RespEnum.DATA_UPDATE_ERR.getResponse();
        }
        volunteerUtil.updateMap();
        return RespEnum.SUCCESS.getResponse();
    }

}
