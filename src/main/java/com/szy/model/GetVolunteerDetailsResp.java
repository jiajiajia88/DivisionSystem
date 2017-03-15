package com.szy.model;

import com.szy.Response;
import com.szy.db.model.VolunteerDbo;
import com.szy.db.model.VolunteerQueryDbo;

/**
 * Created by shizhouyong on 2017/2/16.
 */
public class GetVolunteerDetailsResp extends Response{

   private VolunteerQueryDbo volunteer;

    public GetVolunteerDetailsResp(VolunteerQueryDbo volunteer) {
        this.volunteer = volunteer;
    }

    public VolunteerQueryDbo getVolunteer() {

        return volunteer;
    }

    public void setVolunteer(VolunteerQueryDbo volunteer) {
        this.volunteer = volunteer;
    }
}
