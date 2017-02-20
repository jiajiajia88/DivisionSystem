package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.StuInfoMapper;
import com.szy.db.mapper.VolunteerMapper;
import com.szy.db.model.*;
import com.szy.model.*;
import com.szy.service.IVolunteerService;
import com.szy.util.DBUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * 志愿填报和学生信息
 * Created by shizhouyong on 2017/1/24.
 */
@Service("IVolunteerService")
public class VolunteerServiceImpl implements IVolunteerService {

    @Override
    public Response saveVolunteer(SaveVolunteerReq req, HttpSession session) {
        if (req == null || req.getNumber() == 0) {
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

        long cur = System.currentTimeMillis() / 1000;
        VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
        VolunteerDbo dbo = new VolunteerDbo();
        dbo.setCreateTime(cur);
        dbo.setUpdateTime(cur);
        dbo.setNumber(req.getNumber());
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
    public Response commitVolunteer(HttpSession session) {
        Account account = (Account)session.getAttribute("account");

        if (account == null) {
            return RespEnum.NOT_LOGIN.getResponse();
        }

        long cur = System.currentTimeMillis() / 1000;
        VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
        VolunteerDbo dbo = mapper.selectVolunteerByNumber(account.getNumber());
        if (dbo.getFirstChoose() == 0) {
            return RespEnum.FIRST_CHOOSE_IS_NULL.getResponse();
        }
        dbo.setCommitTime(cur);
        try {
            mapper.updateVolunteerCommitStatus(dbo);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_UPDATE_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response deleteVolunteer(long number, HttpSession session) {
        if (number == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
        try {
            mapper.deleteVolunteer(number);
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_DELETE_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response getVolunteers(GetVolunteersReq req, HttpSession session) {
        if (req == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        Order order = req.getOrder();
        VolunteerFilter filter = req.getFilter();

        GetVolunteerItems items = new GetVolunteerItems();
        if (order != null) {
            items.setFrom(order.getFrom());
            items.setSize(order.getSize());
            items.setItem(order.getItem());
            items.setSort(order.getSort());
        }

        if (filter != null) {
            items.setFirstChoose(filter.getFirstChoose());
            items.setSecondChoose(filter.getSecondChoose());
            items.setThirdChoose(filter.getThirdChoose());
            items.setSex(filter.getSex());
            items.setDivision(filter.getDivision());
            items.setName(filter.getName());
            items.setNumber(filter.getNumber());
            items.setStuFrom(filter.getStuFrom());
            items.setStatus(filter.getStatus());
            items.setOriginalClass(filter.getOriginalClass());
            items.setSex(filter.getSex());
            items.setCategory(filter.getCategory());
            if (filter.getCreateTime() != null) {
                items.setStartCreateTime(filter.getCreateTime().getStart());
                items.setEndCreateTime(filter.getCreateTime().getEnd());
            }
            if (filter.getUpdateTime() != null) {
                items.setStartUpdateTime(filter.getUpdateTime().getStart());
                items.setEndUpdateTime(filter.getUpdateTime().getEnd());
            }
        }
        VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
        GetVolunteersResp resp = new GetVolunteersResp();
        try {
            resp.setVolunteers(mapper.selectVolunteerList(items));
            resp.setTotal(mapper.selectVolunteerListTotal(items));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    public Response getVolunteerDetails(long number, HttpSession session) {
        if (number == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
        VolunteerDbo dbo = null;
        try {
            dbo = mapper.selectVolunteerByNumber(number);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dbo == null) {
            return RespEnum.DATA_NOT_FOUND.getResponse();
        }
        return new GetVolunteerDetailsResp(dbo);
    }

}
