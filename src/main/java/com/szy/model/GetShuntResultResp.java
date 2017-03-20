package com.szy.model;

import com.szy.Response;
import com.szy.db.model.StudentInfoQueryDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/3/20.
 */
public class GetShuntResultResp extends Response {

    private List<StudentInfoQueryDbo> students;

    public GetShuntResultResp(List<StudentInfoQueryDbo> students) {
        this.students = students;
    }

    public List<StudentInfoQueryDbo> getStudents() {
        return students;
    }

    public void setStudents(List<StudentInfoQueryDbo> students) {
        this.students = students;
    }
}
