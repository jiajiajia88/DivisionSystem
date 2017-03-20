package com.szy.controller;

import com.szy.service.IFileExportService;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * Created by shizhouyong on 2017/3/16.
 */
@Controller
@RequestMapping("/jg")
public class FileExportController {

    @Autowired
    private IFileExportService fileExportService;

    @RequestMapping(value = "/v/export/shuntResult")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int grade = Integer.parseInt(request.getParameter("grade"));
        int category = Integer.parseInt(request.getParameter("category"));

        OutputStream outputStream = response.getOutputStream();
        XSSFWorkbook xb = fileExportService.exportShuntResult(grade, category);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="+fileExportService.getFileName()+".xlsx");
        xb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(value = "/v/export/myVolunteer")
    public void exportWord(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //输出文件
        OutputStream outputStream = response.getOutputStream();
        HWPFDocument document = fileExportService.getMyVolunteerWord();
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/msword");//导出word格式
        response.addHeader("Content-Disposition", "attachment;filename=" +
                new String( (fileExportService.getFileName() + ".doc").getBytes(), "iso-8859-1"));

        document.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
