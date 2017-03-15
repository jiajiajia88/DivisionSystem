package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.StuInfoMapper;
import com.szy.db.mapper.VolunteerMapper;
import com.szy.db.model.UpdatePhoneDbo;
import com.szy.db.model.VolunteerDbo;
import com.szy.db.model.VolunteerQueryDbo;
import com.szy.model.*;
import com.szy.service.IStuService;
import com.szy.session.LocalUtil;
import com.szy.session.Session;
import com.szy.util.DBUtil;
import com.szy.util.SystemInfoUtil;
import com.szy.util.VolunteerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 志愿填报和学生信息
 * Created by shizhouyong on 2017/1/24.
 */
@Service("IStuService")
public class StuServiceImpl implements IStuService {

    private Logger logger = LoggerFactory.getLogger(StuServiceImpl.class);

    @Autowired
    private SystemInfoUtil systemInfoUtil;

    @Autowired
    private VolunteerUtil volunteerUtil;

    @Override
    public Response saveVolunteer(SaveVolunteerReq req) {

        if (req == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        if (req.getFirstChoose() == 0) {
            if (req.getSecondChoose() != 0 || req.getThirdChoose() != 0) {
                return RespEnum.FIRST_CHOOSE_IS_NULL.getResponse();
            }
        } else {
            if (req.getSecondChoose() == 0) {
                if (req.getThirdChoose() != 0) {
                    return RespEnum.SECOND_CHOOSE_IS_NULL.getResponse();
                }
            }
        }

        Session session = LocalUtil.getSession();

        long cur = System.currentTimeMillis() / 1000;
        VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
        VolunteerDbo dbo = new VolunteerDbo();
        dbo.setCreateTime(cur);
        dbo.setUpdateTime(cur);
        dbo.setNumber(session.getNumber());
        dbo.setFirstChoose(req.getFirstChoose());
        dbo.setSecondChoose(req.getSecondChoose());
        dbo.setThirdChoose(req.getThirdChoose());
        try {
            mapper.insertVolunteer(dbo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response updatePhone(UpdatePhoneReq req) {
        Session session = LocalUtil.getSession();
        if (session == null) {
            return RespEnum.NOT_LOGIN.getResponse();
        }

        if (req == null || req.getTelePhone() == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);

        try {
            mapper.updatePhone(new UpdatePhoneDbo(session.getNumber(),req.getTelePhone()));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新手机号失败！");
            return RespEnum.DATA_UPDATE_ERR.getResponse();
        }

        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response getSystemInfos(GetSystemInfoListReq req) {
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
    public Response getVolunteerSelectItems() {
        Session session = LocalUtil.getSession();
        if (session == null) {
            return RespEnum.NOT_LOGIN.getResponse();
        }

        PlanKey planKey = new PlanKey(session.getGrade(), session.getCategory());
        List<PlanUnit> planUnitList = new ArrayList<>();
        try {
            planUnitList =  volunteerUtil.getUnits(planKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new GetVolunteerSelectItemsResp(planUnitList);
    }

    @Override
    public Response getMyVolunteer() {
        Session session = LocalUtil.getSession();
        VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
        VolunteerQueryDbo volunteerQueryDbo = mapper.selectVolunteerByNumber(session.getNumber());
        return new GetMyVolunteerResp(volunteerQueryDbo);
    }

}
