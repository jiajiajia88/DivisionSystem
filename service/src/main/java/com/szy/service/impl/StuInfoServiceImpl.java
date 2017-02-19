package com.szy.service.impl;

import com.szy.Response;
import com.szy.model.AddStudentInfoReq;
import com.szy.model.UpdateStudentInfoReq;
import com.szy.service.IStuInfoService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 *
 * Created by shizhouyong on 2017/2/17.
 */
@Service("IStuInfoService")
public class StuInfoServiceImpl implements IStuInfoService {

    @Override
    public Response uploadStudentInfoByExcel(@RequestParam("file") MultipartFile file, HttpSession session) {
        return null;
    }

    @Override
    public Response addStudentInfo(@RequestBody AddStudentInfoReq req, HttpSession session) {
        return null;
    }

    @Override
    public Response updateStudentInfo(@RequestBody UpdateStudentInfoReq req, HttpSession session) {
        return null;
    }

    @Override
    public Response getStudentInfoList(HttpSession session) {
        return null;
    }

    @Override
    public Response getStudentInfoDetails(@PathVariable long number, HttpSession session) {
        return null;
    }
}
