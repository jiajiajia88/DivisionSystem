package com.szy.service;

import com.szy.Response;
import com.szy.model.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 学生信息相关
 * Created by shizhouyong on 2017/1/24.
 */
public interface IShuntService {

    /**
     * 计算总分
     * @param req
     * @return
     */
    public Response calculateGrade(ShuntToMajorReq req);

    /**
     * 分流到专业
     * @param req
     * @return
     */
    public Response shuntToMajor(ShuntToMajorReq req);

    /**
     * 分流到班
     * @param req
     * @return
     */
    public Response shuntToClass(ShuntToClassReq req);

    public Response getNewMajorList(GetNewMajorListReq req);

    public Response getNewClassList(GetNewClassListReq req);

    public Response shuntAdjust(ShuntAdjustReq req);

    /**
     * 分流结果共识（目前弃用，改为修改分流计划状态）
     * @param req
     * @return
     */
    public Response shuntPublish(ShuntPublishReq req);
}
