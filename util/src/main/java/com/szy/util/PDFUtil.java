package com.szy.util;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * PDF工具类
 * Created by shizhouyong on 2017/1/4.
 */

public class PDFUtil {

    public static void main(String[] args) {
        PDFUtil p001 = new PDFUtil();
        String filename = "P001.pdf";
        p001.createPDF(filename);
    }

    public void createPDF(String filename) {
        // step 1
        Document document = new Document(PageSize.A4);
        // step 2
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.addTitle("ID.NET");
            document.addAuthor("dotuian");
            document.addSubject("This is the subject of the PDF file.");
            document.addKeywords("This is the keyword of the PDF file.");

            // step 3
            document.open();
            // step 4
            document.add(new Paragraph("Hello World!"));
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } finally {
            // step 5
            document.close();
        }
    }
}
