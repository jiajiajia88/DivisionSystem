package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.VolunteerMapper;
import com.szy.db.model.*;
import com.szy.model.*;
import com.szy.service.IVolunteerManageService;
import com.szy.util.AccountCreateUtil;
import com.szy.util.DBUtil;
import com.szy.util.ExcelUtil;
import com.szy.util.SystemInfoUtil;
import com.szy.vo.Filter;
import com.szy.vo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 学生信息服务
 * Created by shizhouyong on 2017/2/17.
 */
@Service("IVolunteerManageService")
public class VolunteerManageServiceImpl implements IVolunteerManageService {

    @Autowired
    private ExcelUtil excelUtil;

    @Autowired
    private AccountCreateUtil accountCreateUtil;

    @Autowired
    private SystemInfoUtil systemInfoUtil;

    private static Logger logger = LoggerFactory.getLogger(VolunteerManageServiceImpl.class);

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
    private final static DecimalFormat df = new DecimalFormat("0");  //格式化number
    private final static DecimalFormat df2 = new DecimalFormat("0.00");
    private final static DecimalFormat df3 = new DecimalFormat("0.000");
    private final static DecimalFormat df6 = new DecimalFormat("0.000000");

    @Override
    public Response deleteVolunteer(long number) {
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
    public Response getVolunteers(GetVolunteersReq req) {
        if (req == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        Order order = req.getOrder();
        Filter filter = req.getFilter();

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
            items.setGrade(filter.getGrade());
            items.setOriginalClass(filter.getOriginalClass());
            items.setCategory(filter.getCategory());
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
    public Response getVolunteerDetails(long number) {
        if (number == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
        VolunteerQueryDbo dbo = null;
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
