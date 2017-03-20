package com.szy.service;

import com.szy.Response;
import com.szy.model.GetSystemInfoListReq;
import com.szy.model.PlanOperReq;
import com.szy.model.SaveVolunteerReq;
import com.szy.model.UpdatePhoneReq;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public interface IStuService {

    /**
     * 保存志愿表
     * @param req
     * @return
     */
    public Response saveVolunteer(SaveVolunteerReq req);

    /**
     * 更新手机号
     * @param req
     * @return
     */
    public Response updatePhone(UpdatePhoneReq req);

    /**
     * 获取系统信息：年级、专业、大类、职位
     *
     * @param req
     * @return
     */
    public Response getSystemInfos(GetSystemInfoListReq req);

    /**
     * 获取志愿选择项
     * @return
     */
    public Response getVolunteerSelectItems();

    /**
     * 获取自己的志愿信息
     * @return
     */
    public Response getMyVolunteer();

    public Response getShuntResult();

}
