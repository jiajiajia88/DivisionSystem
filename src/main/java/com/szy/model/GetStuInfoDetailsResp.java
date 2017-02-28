package com.szy.model;

import com.szy.Response;
import com.szy.db.model.StudentInfoDbo;

/**
 * Created by shizhouyong on 2017/2/20.
 */
public class GetStuInfoDetailsResp extends Response {

    private StudentInfoDbo studentInfo;

    public GetStuInfoDetailsResp(StudentInfoDbo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public StudentInfoDbo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfoDbo studentInfo) {
        this.studentInfo = studentInfo;
    }
}
