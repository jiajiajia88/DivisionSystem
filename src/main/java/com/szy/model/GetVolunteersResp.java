package com.szy.model;

import com.szy.Response;
import com.szy.db.model.VolunteerDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class GetVolunteersResp extends Response{

    private int total;
    private List<VolunteerDbo> volunteers;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<VolunteerDbo> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<VolunteerDbo> volunteers) {
        this.volunteers = volunteers;
    }
}
