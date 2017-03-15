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
     *
     * @param req
     * @return
     */
    public Response addGrade(AddGradeReq req);

    /**
     * 新增专业
     *
     * @param req
     * @return
     */
    public Response addMajor(AddMajorReq req);

    /**
     * 根据大类获取专业
     *
     * @param category
     * @return
     */
    public Response getMajorByCategory(int category);

    /**
     * 新增大类
     *
     * @param req
     * @return
     */
    public Response addCategory(AddCategoryReq req);

    /**
     * 新增职位
     *
     * @param req
     * @return
     */
    public Response addPosition(AddPositionReq req);

    /**
     * 获取系统信息：年级、专业、大类、职位
     *
     * @param req
     * @return
     */
    public Response getSystemInfos(GetSystemInfoListReq req);

    /**
     * 删除系统信息
     *
     * @param req
     * @return
     */
    public Response deleteSystemInfo(DeleteSystemInfoReq req);

    public Response addTeacher(SaveTeacherReq req);

    public Response updateTeacher(SaveTeacherReq req);

    public Response teacherList();

    public Response deleteTeacher(long number);

    public Response getStuAccount(GetStuAccountReq req);

}
