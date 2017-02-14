package com.szy.service.impl;

import com.szy.Response;
import com.szy.model.AddVolunteerReq;
import com.szy.model.GetVolunteersReq;
import com.szy.model.UpdateVolunteerReq;
import com.szy.service.IVolunteerService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/24.
 */
@Service("IVolunteerService")
public class VolunteerServiceImpl implements IVolunteerService {

    @Override
    public Response addVolunteer(AddVolunteerReq req, HttpSession session) {
        return null;
    }

    @Override
    public Response deleteVolunteer(int id, HttpSession session) {
        return null;
    }

    @Override
    public Response getVolunteers(GetVolunteersReq req, HttpSession session) {
        return null;
    }

    @Override
    public Response getVolunteerDetails(int id, HttpSession session) {
        return null;
    }

    @Override
    public Response updateVolunteer(UpdateVolunteerReq req, HttpSession session) {
        return null;
    }
}
