package com.szy.util;

import com.szy.db.model.StudentInfoDbo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * 学生信息工具类
 * Created by shizhouyong on 2017/2/19.
 */
public class StuInfoUtil {

    @Autowired
    private ExcelUtil excelUtil;

    private static Logger logger = LoggerFactory.getLogger(StuInfoUtil.class);

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
    private final static DecimalFormat df = new DecimalFormat("0");  //格式化number
    private final static DecimalFormat df2 = new DecimalFormat("0.00");
    private final static DecimalFormat df3 = new DecimalFormat("0.000");
    private final static DecimalFormat df6 = new DecimalFormat("0.000000");


}
