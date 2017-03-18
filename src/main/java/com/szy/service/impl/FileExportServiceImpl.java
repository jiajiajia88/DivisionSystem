package com.szy.service.impl;

import com.szy.db.mapper.StuInfoMapper;
import com.szy.db.mapper.VolunteerMapper;
import com.szy.db.model.GetStuInfoItems;
import com.szy.db.model.StudentInfoQueryDbo;
import com.szy.db.model.VolunteerQueryDbo;
import com.szy.service.IFileExportService;
import com.szy.session.LocalUtil;
import com.szy.session.Session;
import com.szy.util.DBUtil;
import com.szy.vo.ExcelEnum;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by shizhouyong on 2017/3/16.
 */
@Service("IFileExportService")
public class FileExportServiceImpl implements IFileExportService {

    private String fileName;

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public HWPFDocument getMyVolunteerWord() throws IOException {
        Session session = LocalUtil.getSession();
        VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
        StuInfoMapper stuInfoMapper = DBUtil.getMapper(StuInfoMapper.class);
        VolunteerQueryDbo volunteerQueryDbo = mapper.selectVolunteerByNumber(session.getNumber());
        StudentInfoQueryDbo studentInfoQueryDbo = stuInfoMapper.selectStudentInfoByNumber(session.getNumber());
        Resource fileRource = new ClassPathResource("word/volunteer_template.doc");

        this.fileName = session.getNumber() + " " + session.getName();

        FileInputStream tempFileInputStream = new FileInputStream(fileRource.getFile());
        HWPFDocument document = new HWPFDocument(tempFileInputStream);
        // 读取文本内容
        Range bodyRange = document.getRange();
        // 替换内容
        bodyRange.replaceText("${name}", studentInfoQueryDbo.getName());
        bodyRange.replaceText("${class}", studentInfoQueryDbo.getOriginalClass());
        bodyRange.replaceText("${number}", String.valueOf(studentInfoQueryDbo.getNumber()));
        bodyRange.replaceText("${phone}", studentInfoQueryDbo.getTelephone());
        bodyRange.replaceText("${firstChoose}", volunteerQueryDbo.getFirstChoose());
        bodyRange.replaceText("${secondChoose}", volunteerQueryDbo.getSecondChoose());
        bodyRange.replaceText("${thirdChoose}", volunteerQueryDbo.getThirdChoose());
        Calendar now = Calendar.getInstance();
        bodyRange.replaceText("${year}", String.valueOf(now.get(Calendar.YEAR)));
        bodyRange.replaceText("${month}", String.valueOf(now.get(Calendar.MONTH)));
        bodyRange.replaceText("${day}", String.valueOf(now.get(Calendar.DAY_OF_MONTH)));

        return document;
    }

    @Override
    public XSSFWorkbook exportShuntResult(int grade, int category) {
        XSSFWorkbook xb = new XSSFWorkbook();
        GetStuInfoItems items = new GetStuInfoItems();
        items.setFrom(0);
        items.setSize(1000 * 1000 * 1000);
        items.setCategory(category);
        items.setGrade(grade);

        Calendar now = Calendar.getInstance();
        int year =  now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);

        StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
        List<StudentInfoQueryDbo> studentInfoList = mapper.selectStudentInfoList(items);
        StudentInfoQueryDbo dbo = studentInfoList.get(2);
        String fileName = dbo.getGrade()+"级"+dbo.getCategory()+"大类分流结果_"+year+"_"+month+"_"+day;
        this.fileName = fileName;
        XSSFSheet sheet = xb.createSheet(fileName);
        XSSFRow row = sheet.createRow(0);
        XSSFCellStyle style = xb.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        List<String> headList = ExcelEnum.SHUNT_RESULT.getHeadList();
        for (int i = 0; i < headList.size(); i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(headList.get(i));
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
            // sheet.SetColumnWidth(i, 100 * 256);
        }

        for (int i = 0; i < studentInfoList.size(); i++) {
            row = sheet.createRow(i + 1);
            StudentInfoQueryDbo student = studentInfoList.get(i);
            row.createCell(0).setCellValue(String.valueOf(student.getNumber()));
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getGrade());
            row.createCell(3).setCellValue(student.getTelephone());
            row.createCell(4).setCellValue(student.getCategory());
            row.createCell(5).setCellValue(student.getOriginalClass());
            row.createCell(6).setCellValue(student.getSex());
            row.createCell(7).setCellValue(student.getGPA());
            row.createCell(8).setCellValue(student.getRealGPA());
            row.createCell(9).setCellValue(student.getStuFrom());
            row.createCell(10).setCellValue(student.getDivision());
            row.createCell(11).setCellValue(student.getEntranceScore());
            row.createCell(12).setCellValue(student.getAdmissionScore());
            row.createCell(13).setCellValue(student.getGradeOne());
            row.createCell(14).setCellValue(student.getGradeTwo());
            row.createCell(15).setCellValue(student.getTotalGrade());
            row.createCell(16).setCellValue(student.getRank());
            row.createCell(17).setCellValue(student.getNewClass());
            row.createCell(18).setCellValue(student.getNewMajor());
            row.createCell(19).setCellValue(student.getDorm());
            row.createCell(20).setCellValue(student.getNote());
        }
        return xb;
    }
}
