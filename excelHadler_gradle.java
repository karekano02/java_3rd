

import com.google.common.base.CaseFormat;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


@Component
public class ExcelHandler<T> implements ResultHandler<T> {

    private SXSSFWorkbook wb;
    private SXSSFSheet sheet;
    private int rowIdx = 0;
    private HttpServletResponse response;
    private String filename;
    private List<String> columnHeaderList;
    private ResultContext<? extends T> context;

    final int headerFlag = 0;
    final int contentsFlag = 1;
    private T result;
    CellStyle headerStyle;
    CellStyle bodyStyle;

    private ExcelHandler() {
        super();
        rowIdx = 0;
    }
    
    public ExcelHandler(HttpServletResponse response, String filename) {
        this();
        this.response = response;
        try {
            this.filename = URLEncoder.encode(filename.replace(" ", "_"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        };
        wb = new SXSSFWorkbook(10000);
        sheet = wb.createSheet();
        setStyle();
    }
    
    public ExcelHandler(HttpServletResponse response,
                        String filename, List<String> columnHeaderList) {
        this(response,filename);
        this.columnHeaderList = columnHeaderList;
    }

    /**
     * methodName : write

     * description : 엑셀의 각 로우별 작성
     *
     * @param type
     * @param currentRow
     * @param style
     * 
     * @return object data
     */
    private void write(int type, int currentRow, CellStyle style){ 

        SXSSFRow row = sheet.createRow(currentRow);
        if(columnHeaderList.size() == 0 ) {
            return;
        }
        for(int i = 0 ; i < columnHeaderList.size() ; i++) {
            SXSSFCell cell = row.createCell(i);
            int  cellIdx = 0;
            switch (type) {
                case headerFlag:
                    for (String colName : columnHeaderList) {
                        cell = row.createCell(cellIdx++);
                        writeToCell(cell,colName,style);
                    }
                    break;
                case contentsFlag:
                    Object resultObject = context.getResultObject();
                    Class dataClass = resultObject.getClass();
                    String getMethodName="";
                    Object dataValue="";
                    for (String colName : columnHeaderList) {
                        try {
                            getMethodName = "get" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, colName);
                            Method method = dataClass.getMethod(getMethodName);
                            dataValue = method.invoke(resultObject);

                            if (dataValue == null) {
                                dataValue = "";
                            }
                        }
                        catch(InvocationTargetException ie){
                            cell = row.createCell(cellIdx++);
                            writeToCell(cell,"No column Name is '"+colName+"'",style);
                        }catch(IllegalAccessException ie2){
                            cell = row.createCell(cellIdx++);
                            writeToCell(cell,"No column Name is '"+colName+"'",style);
                        }catch(NoSuchMethodException se){
                            cell = row.createCell(cellIdx++);
                            writeToCell(cell,"No method Name is '"+getMethodName+"'",style);
                        }
                        cell = row.createCell(cellIdx++);
                        writeToCell(cell,dataValue,style);
                    }
                    break;
            }

        }
    }

    /**
     * methodName : writeToCell

     * description : 데이터의 타입에 따라 엑셀의 각 셀별 작성
     *
     * @param cell
     * @param dataValue
     * @param style
     *
     * @return object data
     */
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

    /**
     * methodName : handleResult

     * description : ResultHandler의 Override 메소드 쿼리 결과 Handler
     *
     * @param resultContext
     *
     * @return object data
     */
    @Override
    public void handleResult(ResultContext resultContext) {
        if(resultContext.getResultObject() == null) {
            return;
        }
        this.context = resultContext;
        result = context.getResultObject();

        if(rowIdx == 0 ) {
            write(headerFlag, rowIdx,headerStyle);
            write(contentsFlag, rowIdx+1,bodyStyle);
        }else {
            write(contentsFlag, rowIdx+1,bodyStyle);
        }
        rowIdx++;
    }

    /**
     * methodName : setStyle
 
     * description : 엑셀 시트의 스타일 설정
     *
     * @return object data
     */
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

    /**
     * methodName : download
    
     * description : Response에 파일을 떨군다
     *
     * @throws IOException the io exception
     */
    public void download() throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename="
                + filename.replaceAll(" ", "_") + ".xlsx;");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream stream = response.getOutputStream();
        OutputStream out = new BufferedOutputStream(stream);
        try {
            response.resetBuffer();
            response.setBufferSize(1024 * 4);
            wb.write(out);
        } catch (Exception e) {
            out.flush();
            out.close();
            stream.close();
        } finally {
            out.flush();
            out.close();
            stream.close();
        }
        if (wb != null) {
            try {
                wb.dispose();
            } catch (Exception e) {
                wb.close();
            } finally {
                wb.close();
            }
        }
        wb.close();
    }

    /**
     * methodName : close
     * author :
     * description :
     */
    public void close() {
        wb.dispose();
        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
