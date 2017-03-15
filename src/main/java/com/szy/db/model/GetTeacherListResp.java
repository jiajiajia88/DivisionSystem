package com.szy.db.model;

import com.szy.Response;

import java.util.List;

/**
 * Created by shizhouyong on 2017/3/8.
 */
public class GetTeacherListResp extends Response {

    private List<TeacherInfoQueryDbo> teacherList;

    public List<TeacherInfoQueryDbo> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherInfoQueryDbo> teacherList) {
        this.teacherList = teacherList;
    }
}
