package com.szy.model;

import com.szy.Response;
import com.szy.db.model.StudentInfoDbo;
import com.szy.db.model.StudentInfoQueryDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/2/20.
 */
public class GetStudentInfoListResp extends Response{

    private int total;
    private List<StudentInfoQueryDbo> students;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<StudentInfoQueryDbo> getStudents() {
        return students;
    }

    public void setStudents(List<StudentInfoQueryDbo> students) {
        this.students = students;
    }
}
