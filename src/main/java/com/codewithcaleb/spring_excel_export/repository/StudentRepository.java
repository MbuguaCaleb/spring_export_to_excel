package com.codewithcaleb.spring_excel_export.repository;

import com.codewithcaleb.spring_excel_export.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
