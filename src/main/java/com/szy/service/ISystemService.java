package com.szy.service;

import com.szy.Response;
import com.szy.model.*;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/8.
 */
public interface ISystemService {

    /**
     * 新增年级
     * @param req
     * @param session
     * @return
     */
    public Response addGrade(AddGradeReq req, HttpSession session);

    /**
     * 新增专业
     * @param req
     * @param session
     * @return
     */
    public Response addMajor(AddMajorReq req, HttpSession session);

    /**
     * 新增大类
     * @param req
     * @param session
     * @return
     */
    public Response addCategory(AddCategoryReq req, HttpSession session);

    /**
     * 新增职位
     * @param req
     * @param session
     * @return
     */
    public Response addPosition(AddPositionReq req, HttpSession session);

    /**
     * 获取系统信息：年级、专业、大类、职位
     * @param req
     * @param session
     * @return
     */
    public Response getSystemInfos(GetSystemInfoListReq req, HttpSession session);

    /**
     * 删除系统信息
     * @param req
     * @param session
     * @return
     */
    public Response deleteSystemInfo(DeleteSystemInfoReq req, HttpSession session);

}
