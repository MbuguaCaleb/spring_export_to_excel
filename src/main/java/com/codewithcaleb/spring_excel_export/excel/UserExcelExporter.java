package com.codewithcaleb.spring_excel_export.excel;

import com.codewithcaleb.spring_excel_export.model.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Student>  listStudent;

    public UserExcelExporter(List<Student> listStudent) {
        this.listStudent = listStudent;
        workbook = new XSSFWorkbook();
    }

    //creating an Excel Cell
    //I should have a way to Generate the Excel File Cells
    private void createCell(Row row, int columnCount, Object value, CellStyle style){
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if(value instanceof Long){
            cell.setCellValue((Long) value);
        }
        else if(value instanceof  Integer){
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else{
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

    //creating Excel Header
    private void writeHeaderLine(){
        sheet = workbook.createSheet("Student");

        //creating the Heading Row
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font= workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        //The Header Will not have any Column
        createCell(row,0,"Student Information",style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
        font.setFontHeightInPoints((short)(10));


        //Creating the Second Heading Row
        //With the name of the Columns
        row = sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row,0,"Student Id",style);
        createCell(row,1,"Student Name",style);
        createCell(row,2,"Student Address",style);
        createCell(row,3,"Student City",style);
        createCell(row,4,"Student Pin",style);

    }


    //Saving the Data inside the Excel
    private void writeDataLines(){
        //i will begin to insert data from the second row
        int rowCount = 2;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        //I get a List of Records and Loop through to Create My Rows
        for(Student stu: listStudent){
            //I am creating one Row in each interation and then i insert my columns
            Row row = sheet.createRow(rowCount ++);
            int columnCount =0;
            createCell(row,columnCount++, stu.getSid(),style );
            createCell(row,columnCount++, stu.getSname(),style );
            createCell(row,columnCount++, stu.getSaddress(),style );
            createCell(row,columnCount++, stu.getScity(),style );
            createCell(row,columnCount++, stu.getSpin(),style );
        }

    }


    //Exporting the Excel Sheet
    //If its a utility function its better to Put the exception on the method
    //If i am calling the method i surround it with a try catch
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = null;

        outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

}
