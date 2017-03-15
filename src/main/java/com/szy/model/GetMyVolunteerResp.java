package com.szy.model;

import com.szy.Response;
import com.szy.db.model.VolunteerQueryDbo;

/**
 * Created by shizhouyong on 2017/3/13.
 */
public class GetMyVolunteerResp extends Response {

    private VolunteerQueryDbo myVolunteer;

    public GetMyVolunteerResp(VolunteerQueryDbo myVolunteer) {
        this.myVolunteer = myVolunteer;
    }

    public VolunteerQueryDbo getMyVolunteer() {

        return myVolunteer;
    }

    public void setMyVolunteer(VolunteerQueryDbo myVolunteer) {
        this.myVolunteer = myVolunteer;
    }
}
