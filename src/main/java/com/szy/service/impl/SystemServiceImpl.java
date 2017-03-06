package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.model.*;
import com.szy.model.*;
import com.szy.service.ISystemService;
import com.szy.util.SystemInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * 系统信息
 * Created by shizhouyong on 2017/1/8.
 */
@Service("ISystemService")
public class SystemServiceImpl implements ISystemService{

    private final SystemInfoUtil systemInfoUtil;

    @Autowired
    public SystemServiceImpl(SystemInfoUtil systemInfoUtil) {
        this.systemInfoUtil = systemInfoUtil;
    }

    @Override
    public Response addGrade(AddGradeReq req, HttpSession session) {
        if(req == null || req.getGrade() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        long cur = System.currentTimeMillis() / 1000;
        GradeDbo grade = new GradeDbo();
        grade.setName(req.getGrade());
        grade.setCreateTime(cur);

        try {
            return systemInfoUtil.addSystemInfo(grade,"GRADE");
        } catch (Exception e) {
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
    }

    @Override
    public Response addMajor(AddMajorReq req, HttpSession session) {
        if(req == null || req.getName() == null || req.getCategory() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        final long cur = System.currentTimeMillis() / 1000L;
        MajorDbo dbo = new MajorDbo();
        dbo.setName(req.getName());
        SystemInfo major = systemInfoUtil.getCategoryByName(req.getCategory());
        if (major == null) {
            return RespEnum.CATEGORY_NOT_FOUND.getResponse();
        }
        dbo.setCategory(major.getId());
        dbo.setDescription(req.getDescription());
        dbo.setCreateTime(cur);
        try {
            return systemInfoUtil.addSystemInfo(dbo, "MAJOR");
        } catch (Exception e) {
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
    }

    @Override
    public Response addCategory(AddCategoryReq req, HttpSession session) {
        if(req == null || req.getName() == null){
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        final long cur = System.currentTimeMillis() / 1000L;
        CategoryDbo dbo = new CategoryDbo();
        dbo.setName(req.getName());
        dbo.setDescription(req.getDescription());
        dbo.setCreateTime(cur);
        try {
            return systemInfoUtil.addSystemInfo(dbo, "CATEGORY");
        } catch (Exception e) {
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
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
            return systemInfoUtil.addSystemInfo(dbo, "POSITION");
        } catch (Exception e) {
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
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
