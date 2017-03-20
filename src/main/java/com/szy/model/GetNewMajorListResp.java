package com.szy.model;

import com.szy.Response;
import com.szy.db.model.NewMajorQueryDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/3/19.
 */
public class GetNewMajorListResp extends Response{

    private List<NewMajorQueryDbo> newMajors;

    public GetNewMajorListResp(List<NewMajorQueryDbo> newMajors) {
        this.newMajors = newMajors;
    }

    public List<NewMajorQueryDbo> getNewMajors() {
        return newMajors;
    }

    public void setNewMajors(List<NewMajorQueryDbo> newMajors) {
        this.newMajors = newMajors;
    }
}
