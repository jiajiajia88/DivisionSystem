package com.szy.util;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shizhouyong on 2017/3/16.
 */
public class WordExportUtil {

    private void build(File tmpFile, Map<String, String> contentMap, String exportFile) throws Exception {
        Resource fileRource = new ClassPathResource("html/resetPassword.html");

        FileInputStream tempFileInputStream = new FileInputStream(fileRource.getFile());
        HWPFDocument document = new HWPFDocument(tempFileInputStream);
        // 读取文本内容
        Range bodyRange = document.getRange();
        // 替换内容
        for (Map.Entry<String, String> entry : contentMap.entrySet()) {
            bodyRange.replaceText("${" + entry.getKey() + "}", entry.getValue());
        }

        //导出到文件
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.write(byteArrayOutputStream);
        OutputStream outputStream = new FileOutputStream(exportFile);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.close();
    }

    public void testExportWord() throws Exception {
        String tmpFile = "D:/temp/template.doc";
        String expFile = "D:/temp/result.doc";
        Map<String, String> datas = new HashMap<String, String>();
        datas.put("title", "标题部份");
        datas.put("content", "这里是内容，测试使用POI导出到Word的内容！");
        datas.put("author", "知识林");
        datas.put("url", "http://www.zslin.com");

        build(new File(tmpFile), datas, expFile);
    }

}
