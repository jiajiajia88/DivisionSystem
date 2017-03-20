package com.szy.service;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/**
 * Created by shizhouyong on 2017/3/16.
 */
public interface IFileExportService {

    /**
     * 导出分流结果表格
     * @param grade
     * @param category
     * @return
     */
    public XSSFWorkbook exportShuntResult(int grade, int category);

    /**
     * 获得文件名，用于service和controller间传值
     * @return
     */
    public String getFileName();

    /**
     * 导出志愿表格
     * @return
     * @throws IOException
     */
    public HWPFDocument getMyVolunteerWord() throws IOException;
}
