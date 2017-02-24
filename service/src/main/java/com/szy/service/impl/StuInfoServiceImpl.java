package com.szy.service.impl;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.StuInfoMapper;
import com.szy.db.model.GetStuInfoItems;
import com.szy.db.model.Order;
import com.szy.db.model.StuInfoFilter;
import com.szy.db.model.StudentInfoDbo;
import com.szy.model.*;
import com.szy.service.IStuInfoService;
import com.szy.util.DBUtil;
import com.szy.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by shizhouyong on 2017/2/17.
 */
@Service("IStuInfoService")
public class StuInfoServiceImpl implements IStuInfoService {

    @Autowired
    private ExcelUtil excelUtil;

    private static Logger logger = LoggerFactory.getLogger(StuInfoServiceImpl.class);

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
    private final static DecimalFormat df = new DecimalFormat("0");  //格式化number
    private final static DecimalFormat df2 = new DecimalFormat("0.00");
    private final static DecimalFormat df3 = new DecimalFormat("0.000");
    private final static DecimalFormat df6 = new DecimalFormat("0.000000");

    @Override
    public Response uploadStudentInfoByExcel(HttpServletRequest req, MultipartFile file, HttpSession session) {

        Account account = (Account)session.getAttribute("account");
        if (account == null) {
            return RespEnum.NO_USER.getResponse();
        }

        int category = Integer.parseInt(req.getParameter("category"));
        if (file == null || file.isEmpty() || category == 0)
            return RespEnum.PARAMETER_MiSS.getResponse();

        InputStream in = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            logger.error(e.getMessage());
            return RespEnum.FILE_INPUTSTREAM_ERR.getResponse();
        }
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        List<String> list;
        try {
            list = excelUtil.exportListFromExcel(in, fileType, 0);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return RespEnum.FILE_NEWWORKBOOK_ERR.getResponse();
        }

        long cur = System.currentTimeMillis() / 1000;
        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
        long start = System.currentTimeMillis();
        for (int i = 1; i < list.size(); i++) {
            String[] info = (list.get(i).substring(1)).split("\\|");
            StudentInfo studentInfo = null;
            try {
                studentInfo = new StudentInfo();
                studentInfo.setNumber(Long.parseLong(info[0]));
                studentInfo.setName(info[1]);
                studentInfo.setCategory(category);
                studentInfo.setOriginalClass(info[8]);
                studentInfo.setSex(info[7]);
                switch (info[7]) {
                    case "男":
                        studentInfo.setSex("F");
                        break;
                    case "女":
                        studentInfo.setSex("M");
                        break;
                    default:
                        studentInfo.setSex("D");
                        break;
                }
                studentInfo.setDorm(info[9]);
                switch (info[10]) {
                    case "^*^BLANK":
                        studentInfo.setNote(null);
                        break;
                    default:
                        studentInfo.setNote(info[10]);
                        break;
                }
                studentInfo.setGPA((Double.parseDouble(df.format(Double.parseDouble(info[2])))));
                studentInfo.setStuFrom(info[3]);
                switch (info[4]) {
                    case "文科":
                        studentInfo.setDivision(1);
                        break;
                    case "理科":
                        studentInfo.setDivision(2);
                        break;
                    default:
                        studentInfo.setDivision(-1);
                }

                studentInfo.setEntranceScore(Integer.parseInt(info[5]));
                studentInfo.setAdmissionScore(Integer.parseInt(info[6]));
                studentInfo.setCreateUser(account.getNumber());
                studentInfo.setCreateTime(cur);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return RespEnum.DATA_PARSE_ERR.getResponse();
            }

            if (studentInfo.checkStuInfo()) {
                try {
                    mapper.insertStudentInfo(studentInfo.createStuInfoDbo());
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                    return RespEnum.DATA_INSERT_ERR.getResponse();
                }
            } else {
                return RespEnum.PARAMETER_MiSS.getResponse();
            }
        }
        long end = System.currentTimeMillis();
        logger.info(String.valueOf(end - start));
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response addStudentInfo(AddStudentInfoReq req, HttpSession session) {
        if (req == null || req.getStudentInfo() == null || !req.getStudentInfo().checkStuInfo()) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        long cur = System.currentTimeMillis() / 1000;
        StudentInfo studentInfo = req.getStudentInfo();
        studentInfo.setCreateTime(cur);

        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
        try {
            mapper.insertStudentInfo(studentInfo.createStuInfoDbo());
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response updateStudentInfo(UpdateStudentInfoReq req, HttpSession session) {
        if (req == null || req.getStudentInfo() == null || !req.getStudentInfo().checkStuInfo()) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }
        long cur = System.currentTimeMillis() / 1000;
        StudentInfo studentInfo = req.getStudentInfo();
        studentInfo.setUpdateTime(cur);
        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
        try {
            mapper.insertStudentInfo(studentInfo.createStuInfoDbo());
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.DATA_INSERT_ERR.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    @Override
    public Response getStudentInfoList(GetStudentInfoListReq req, HttpSession session) {
        if (req == null) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        Order order = req.getOrder();
        StuInfoFilter filter = req.getFilter();

        GetStuInfoItems items = new GetStuInfoItems();
        if (order != null) {
            items.setFrom(order.getFrom());
            items.setSize(order.getSize());
            items.setItem(order.getItem());
            items.setSort(order.getSort());
        }

        if (filter != null) {
            items.setSex(filter.getSex());
            items.setDivision(filter.getDivision());
            items.setName(filter.getName());
            items.setNumber(filter.getNumber());
            items.setStuFrom(filter.getStuFrom());
            items.setStatus(filter.getStatus());
            items.setOriginalClass(filter.getOriginalClass());
            items.setSex(filter.getSex());
            items.setCategory(filter.getCategory());
            if (filter.getCreateTime() != null) {
                items.setStartCreateTime(filter.getCreateTime().getStart());
                items.setEndCreateTime(filter.getCreateTime().getEnd());
            }
            if (filter.getUpdateTime() != null) {
                items.setStartUpdateTime(filter.getUpdateTime().getStart());
                items.setEndUpdateTime(filter.getUpdateTime().getEnd());
            }
        }
        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
        GetStudentInfoListResp resp = new GetStudentInfoListResp();
        try {
            resp.setStudents(mapper.selectStudentInfoList(items));
            resp.setTotal(mapper.selectStudentInfoListTotal(items));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    public Response getStudentInfoDetails(long number, HttpSession session) {
        if (number == 0) {
            return RespEnum.PARAMETER_MiSS.getResponse();
        }

        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
        StudentInfoDbo studentInfoDbo = null;
        try {
            studentInfoDbo = mapper.selectStudentInfoByNumber(number);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentInfoDbo != null ? new GetStuInfoDetailsResp(studentInfoDbo) : RespEnum.DATA_NOT_FOUND.getResponse();
    }
}
