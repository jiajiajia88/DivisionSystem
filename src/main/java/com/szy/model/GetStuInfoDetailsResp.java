package com.szy.model;

import com.szy.Response;
import com.szy.db.model.StudentInfoDbo;
import com.szy.db.model.StudentInfoQueryDbo;

/**
 * Created by shizhouyong on 2017/2/20.
 */
public class GetStuInfoDetailsResp extends Response {

    private StudentInfoQueryDbo studentInfo;

    public StudentInfoQueryDbo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfoQueryDbo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public GetStuInfoDetailsResp(StudentInfoQueryDbo studentInfo) {

        this.studentInfo = studentInfo;
    }
}
