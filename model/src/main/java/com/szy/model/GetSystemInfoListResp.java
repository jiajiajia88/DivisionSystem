package com.szy.model;

import com.szy.Response;
import com.szy.db.model.SystemInfo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/10.
 */
public class GetSystemInfoListResp extends Response{

    private List<SystemInfo> gradeList;

    public GetSystemInfoListResp(List<SystemInfo> gradeList) {
        this.gradeList = gradeList;
    }

    public List<SystemInfo> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<SystemInfo> gradeList) {
        this.gradeList = gradeList;
    }
}
