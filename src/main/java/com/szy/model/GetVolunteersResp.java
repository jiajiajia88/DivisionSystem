package com.szy.model;

import com.szy.Response;
import com.szy.db.model.VolunteerDbo;
import com.szy.db.model.VolunteerQueryDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class GetVolunteersResp extends Response{

    private int total;
    private List<VolunteerQueryDbo> volunteers;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<VolunteerQueryDbo> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<VolunteerQueryDbo> volunteers) {
        this.volunteers = volunteers;
    }
}
