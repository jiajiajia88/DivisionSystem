package com.szy.model;

import com.szy.Response;
import com.szy.db.model.StudentInfoDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/2/20.
 */
public class GetStudentInfoListResp extends Response{

    private int total;
    private List<StudentInfoDbo> students;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<StudentInfoDbo> getStudents() {
        return students;
    }

    public void setStudents(List<StudentInfoDbo> students) {
        this.students = students;
    }
}
