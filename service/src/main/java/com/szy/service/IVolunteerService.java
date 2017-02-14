package com.szy.service;

import com.szy.Response;
import com.szy.model.*;

import javax.servlet.http.HttpSession;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public interface IVolunteerService {

    public Response addVolunteer(AddVolunteerReq req, HttpSession session);

    public Response deleteVolunteer(int id, HttpSession session);

    public Response getVolunteers(GetVolunteersReq req, HttpSession session);

    public Response getVolunteerDetails(int id, HttpSession session);

    public Response updateVolunteer(UpdateVolunteerReq req, HttpSession session);
}
