package com.szy.service;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/**
 * Created by shizhouyong on 2017/3/16.
 */
public interface IFileExportService {

    public XSSFWorkbook exportShuntResult(int grade, int category);

    public String getFileName();

    public HWPFDocument getMyVolunteerWord() throws IOException;
}
