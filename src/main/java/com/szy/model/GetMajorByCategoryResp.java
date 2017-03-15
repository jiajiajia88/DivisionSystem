package com.szy.model;

import com.szy.Response;
import com.szy.db.model.MajorQueryDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/3/13.
 */
public class GetMajorByCategoryResp extends Response {

    private List<MajorQueryDbo> majorList;

    public GetMajorByCategoryResp(List<MajorQueryDbo> majorList) {
        this.majorList = majorList;
    }

    public List<MajorQueryDbo> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<MajorQueryDbo> majorList) {
        this.majorList = majorList;
    }
}
