package com.codewithcaleb.spring_excel_export.service;

import com.codewithcaleb.spring_excel_export.excel.UserExcelExporter;
import com.codewithcaleb.spring_excel_export.model.Student;
import com.codewithcaleb.spring_excel_export.repository.StudentRepository;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue ="attachment; filename=Student_info.xlsx";

        response.setHeader(headerKey,headerValue);
        List<Student> listStudent = studentRepository.findAll();

        UserExcelExporter excelExporter = new UserExcelExporter(listStudent);
        excelExporter.export(response);

    }
}
