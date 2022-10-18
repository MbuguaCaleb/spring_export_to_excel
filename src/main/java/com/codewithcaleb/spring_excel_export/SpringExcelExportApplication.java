package com.codewithcaleb.spring_excel_export;

import com.codewithcaleb.spring_excel_export.model.Student;
import com.codewithcaleb.spring_excel_export.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringExcelExportApplication  implements CommandLineRunner {

    private final StudentRepository studentRepository;


    public SpringExcelExportApplication(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringExcelExportApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Student student1 = new Student(1L,"MbuguaCaleb","553-2117","Naivasha","Nakuru");
        Student student2 = new Student(2L,"MercyWanjiru","553-2117","Nakuru","Nakuru");

        studentRepository.save(student1);
        studentRepository.save(student2);

    }
}
