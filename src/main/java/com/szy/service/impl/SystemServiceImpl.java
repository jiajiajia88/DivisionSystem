package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.model.CategoryDbo;
import com.szy.db.model.GradeDbo;
import com.szy.db.model.MajorDbo;
import com.szy.db.model.PositionDbo;
import com.szy.model.*;
import com.szy.service.ISystemService;
import com.szy.util.SystemInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/8.
 */
@Service("ISystemService")
public class SystemServiceImpl implements ISystemService{

    @Autowired
    SystemInfoUtil systemInfoUtil;

    @Override
    public Response addGrade(AddGradeReq req, HttpSession session) {
        if(req == null || req.getGrade() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        GradeDbo grade = new GradeDbo();
        grade.setName(req.getGrade());
        try {
            systemInfoUtil.addSystemInfo(grade,"GRADE");
        } catch (Exception e){
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response addMajor(AddMajorReq req, HttpSession session) {
        if(req == null || req.getName() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        final long cur = System.currentTimeMillis() / 1000L;
        MajorDbo dbo = new MajorDbo();
        dbo.setName(req.getName());
        dbo.setDescription(req.getDescription());
        dbo.setCreateTime(cur);
        try {
            systemInfoUtil.addSystemInfo(dbo, "MAJOR");
        } catch (Exception e){
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response addCategory(AddCategoryReq req, HttpSession session) {
        if(req == null || req.getName() == null || req.getStuAmount() == 0){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        final long cur = System.currentTimeMillis() / 1000L;
        CategoryDbo dbo = new CategoryDbo();
        dbo.setName(req.getName());
        dbo.setStuAmount(req.getStuAmount());
        dbo.setCreateTime(cur);
        try {
            systemInfoUtil.addSystemInfo(dbo, "CATEGORY");
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response addPosition(AddPositionReq req, HttpSession session) {
        if(req == null || req.getName() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        final long cur = System.currentTimeMillis() / 1000L;
        PositionDbo dbo = new PositionDbo();
        dbo.setName(req.getName());
        dbo.setDescription(req.getDescription());
        dbo.setCreateTime(cur);
        try {
            systemInfoUtil.addSystemInfo(dbo, "POSITION");
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return null;
    }

    @Override
    public Response getSystemInfos(GetSystemInfoListReq req, HttpSession session) {
        if(req == null || req.getType() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        try {
            return new GetSystemInfoListResp(systemInfoUtil.getSystemInfoList(req.getType()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespEnum.DATA_NOT_FOUND.getResponse();
    }

    @Override
    public Response deleteSystemInfo(DeleteSystemInfoReq req, HttpSession session) {
        if(req == null || req.getName() == null || req.getType() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        try {
            systemInfoUtil.deleteSystemInfo(req.getName(), req.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }


}
