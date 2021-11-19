

import com.google.common.base.CaseFormat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExcelUtil {

   // Executor executor;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    SXSSFWorkbook wb = new SXSSFWorkbook();
    SXSSFSheet sheet = wb.createSheet();
    int rowIdx = 0;
    int cellIdx = 0;
    HttpServletResponse response;
    File excelFile;
    String excelFileName;
    CellStyle headerStyle;
    CellStyle bodyStyle;

    /**
     * methodName : makeExcelFileToResponse
 
     * description : Response에 엑셀 파일을 생성한다
     *
      */
    public <R, T> void makeExcelFileToResponse(String fileName, List<String> headreNames, List<String> columnNames
                                    , Function<T, List<R>> function, HttpServletResponse response, T params){
        String extension = FilenameUtils.getExtension(fileName);
        String newFileName = StringUtils.hasText(extension) ? fileName : fileName + CommonConstants.EXCEL_EXTENTION;
        this.response = response;
        this.response.setContentType("ms-vnd/excel");
        this.response.setHeader("Content-Disposition", "attachment;filename="+newFileName);

        this.excelFileName =newFileName;

        setStyle();
        makeHeaders(headreNames);
        writeData(columnNames, function.apply(params));

    }

//    public <R, T> String makeExcelFileToFile(String fileName, List<String> headreNames, List<String> columnNames
//            , Function<T, List<R>> function, HttpServletResponse response, T params){
//        String extension = FilenameUtils.getExtension(fileName);
//        String newFileName = StringUtils.hasText(extension) ? fileName : fileName + CommonConstants.EXCEL_EXTENTION;
//
//        this.excelFileName =newFileName;
//
//        executor.execute(() -> {
//            //sheet.setRandomAccessWindowSize(1000);  //메모리 size
//            makeHeaders(headreNames);
//            writeData(columnNames, function.apply(params));
//        });
//
//        return excelFile.getAbsolutePath();
//    }

    /**
     * methodName : makeHeaders
  
     * description : WorkSheet에 헤더를 생성한다.
     *
     * @param headerNames
     */
    private void makeHeaders(List<String> headerNames) {
        Row row = sheet.createRow(rowIdx++);
        Cell cell;

        for (String header : headerNames){
            cell = row.createCell(cellIdx++);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }
    }

    private <T> void writeData(List<String> columnNames, List<T> dataList){
        for (T t : dataList) {
            this.writeToRow(columnNames, t);
        }
        try {
            if (null != this.response) {
                wb.write(this.response.getOutputStream());
                wb.dispose();
                wb.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private <T> void writeToRow(List<String> columnNames, T data){
        try {
            Row row = sheet.createRow(rowIdx++);
            Class dataClass = data.getClass();
            String getMethodName;
            Object dataValue;
            cellIdx = 0;
            Cell cell;
            for (String colName : columnNames) {
                getMethodName = "get" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, colName);
                Method method = dataClass.getMethod(getMethodName);
                dataValue = method.invoke(data);

                if (dataValue == null) {
                    dataValue = "";
                }

                cell = row.createCell(cellIdx++);
                writeToCell(cell,dataValue, bodyStyle);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            log.error("Excel Data To Workbook write Exception : " + ex.getMessage());
        }
    }

    private void writeToCell(Cell cell, Object dataValue, CellStyle style){
        if(style != null) {
            cell.setCellStyle(style);
        }
        if (dataValue instanceof String) {
            cell.setCellValue((String) dataValue);
        } else if (dataValue instanceof Short) {
            cell.setCellValue((Short) dataValue);
        } else if (dataValue instanceof Integer) {
            cell.setCellValue((Integer) dataValue);
        } else if (dataValue instanceof Long) {
            cell.setCellValue((Long) dataValue);
        } else if (dataValue instanceof Float) {
            cell.setCellValue((Float) dataValue);
        } else if (dataValue instanceof Double) {
            cell.setCellValue((Double) dataValue);
        } else if (dataValue instanceof BigDecimal) {
            if (dataValue == null || "".equals(dataValue)) {
                cell.setCellValue(new BigDecimal(0).doubleValue());
            } else {
                cell.setCellValue(((BigDecimal) dataValue).doubleValue());
            }
        } else if (dataValue instanceof Date) {
            cell.setCellValue((Date) dataValue);
        } else if (dataValue instanceof Calendar) {
            cell.setCellValue((Calendar) dataValue);
        } else if (dataValue instanceof Boolean) {
            cell.setCellValue((Boolean) dataValue);
        } else {
            cell.setCellValue((String) dataValue);
        }
    }

    private void setStyle(){
        Font headerFont = wb.createFont();
        headerFont.setFontName("맑은 고딕");
        headerFont.setBold(true);
        headerStyle = wb.createCellStyle();
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFont(headerFont);

        Font bodyFont = wb.createFont();
        bodyFont.setFontName("맑은 고딕");
        bodyStyle= wb.createCellStyle();
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setBorderLeft(BorderStyle.THIN);
        bodyStyle.setBorderRight(BorderStyle.THIN);
        bodyStyle.setAlignment(HorizontalAlignment.LEFT);
        bodyStyle.setFont(bodyFont);
    }
}
