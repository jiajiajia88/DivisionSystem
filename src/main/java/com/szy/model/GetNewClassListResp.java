package com.szy.model;

import com.szy.Response;
import com.szy.db.model.NewClassQueryDbo;
import com.szy.db.model.NewMajorQueryDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/3/19.
 */
public class GetNewClassListResp extends Response{

    private List<NewClassQueryDbo> newClasses;

    public GetNewClassListResp(List<NewClassQueryDbo> newClasses) {
        this.newClasses = newClasses;
    }

    public List<NewClassQueryDbo> getNewClasses() {
        return newClasses;
    }

    public void setNewClasses(List<NewClassQueryDbo> newClasses) {
        this.newClasses = newClasses;
    }
}
