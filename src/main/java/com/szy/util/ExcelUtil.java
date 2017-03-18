package com.szy.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作Excel表格的功能类
 */

@Component
public class ExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * Excel 2003
     */
    private final static String XLS = ".xls";
    /**
     * Excel 2007
     */
    private final static String XLSX = ".xlsx";
    /**
     * 分隔符
     */
    private final static String SEPARATOR = "|";
    /**
     * 空单元格
     */
    private final static String BLANK = "^*^BLANK";

    /**
     * 由Excel文件的Sheet导出至List
     *
     * @param file
     * @param sheetNum
     * @return
     */
    public List<String> exportListFromExcel(File file, int sheetNum)
            throws IOException {
        return exportListFromExcel(new FileInputStream(file),
                FilenameUtils.getExtension(file.getName()), sheetNum);
    }
    /**
     * 由Excel流的Sheet导出至List
     *
     * @param is
     * @param extensionName
     * @param sheetNum
     * @return
     * @throws IOException
     */
    public List<String> exportListFromExcel(InputStream is, String extensionName, int sheetNum) throws IOException {

        Workbook workbook = null;

        if (extensionName.toLowerCase().equals(XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (extensionName.toLowerCase().equals(XLSX)) {
            workbook = new XSSFWorkbook(is);
        }

        return exportListFromExcel(workbook, sheetNum);
    }
    /**
     * 由指定的Sheet导出至List
     *
     * @param workbook
     * @param sheetNum
     * @return
     * @throws IOException
     */
    private List<String> exportListFromExcel(Workbook workbook, int sheetNum) {
        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheetAt(sheetNum);
        List<String> list = new ArrayList<>();

        int minRowIx = sheet.getFirstRowNum();
        int maxRowIx = sheet.getLastRowNum();
        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
            Row row = sheet.getRow(rowIx);
            StringBuilder sb = new StringBuilder();

            short minColIx = row.getFirstCellNum();
            short maxColIx = row.getLastCellNum();
            for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                Cell cell = row.getCell((int) colIx);
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue == null) {
                    sb.append(SEPARATOR).append(BLANK);
                    break;
                }
                switch (cellValue.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        sb.append(SEPARATOR).append(cellValue.getBooleanValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        // 这里的日期类型会被转换为数字类型，需要判别后区分处理
                        if (DateUtil.isCellDateFormatted(cell)) {
                            sb.append(SEPARATOR).append(cell.getDateCellValue());
                        } else {
                            //把手机号码转换为字符串
                            Double d = cell.getNumericCellValue();
                            DecimalFormat df = new DecimalFormat("#.##");
                            sb.append(SEPARATOR).append(df.format(d));
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        sb.append(SEPARATOR).append(cellValue.getStringValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        sb.append(SEPARATOR).append(BLANK);
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        break;
                    default:
                        break;
                }
            }
            list.add(sb.toString());
        }
        return list;
    }


    /**
     * 获取解析单元格数据为字符串
     * @param evaluator
     * @param cell
     * @return
     */
    public String getCellValue(FormulaEvaluator evaluator, Cell cell) {
        CellValue cellValue = evaluator.evaluate(cell);
        if (cellValue == null) {
            return null;
        }
        // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
        // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
        switch (cellValue.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                break;
            case Cell.CELL_TYPE_NUMERIC:
                // 这里的日期类型会被转换为数字类型，需要判别后区分处理
                if (DateUtil.isCellDateFormatted(cell)) {
                    return String.valueOf(cell.getDateCellValue());
                } else {
                    //把手机号码转换为字符串
                    DecimalFormat df = new DecimalFormat("#");
                    return df.format(cellValue.getNumberValue());
                }
            case Cell.CELL_TYPE_STRING:
                return cellValue.getStringValue();
            case Cell.CELL_TYPE_FORMULA:
                break;
            case Cell.CELL_TYPE_BLANK:
                return "BLANK";
            case Cell.CELL_TYPE_ERROR:
                break;
            default:
                break;
        }

        return null;
    }

}