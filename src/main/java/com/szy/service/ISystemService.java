package com.szy.service;

import com.szy.Response;
import com.szy.model.*;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/8.
 */
public interface ISystemService {

    public Response addGrade(AddGradeReq req, HttpSession session);

    public Response addMajor(AddMajorReq req, HttpSession session);

    public Response addCategory(AddCategoryReq req, HttpSession session);

    public Response addPosition(AddPositionReq req, HttpSession session);

    public Response getSystemInfos(GetSystemInfoListReq req, HttpSession session);

    public Response deleteSystemInfo(DeleteSystemInfoReq req, HttpSession session);

}
