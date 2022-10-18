package com.codewithcaleb.spring_excel_export.controller;

import com.codewithcaleb.spring_excel_export.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/web")
public class HomeController {

    private final StudentService service;

    public HomeController(StudentService service) {
        this.service = service;
    }


    @RequestMapping("/home")
    public  String homePage(){
        return  "homePage";
    }

    @GetMapping("/export/excel")
    private void exportToExcel(HttpServletResponse response){
        try {
            service.exportToExcel(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
