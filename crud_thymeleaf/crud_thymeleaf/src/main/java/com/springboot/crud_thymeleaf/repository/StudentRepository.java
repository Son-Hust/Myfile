package com.springboot.crud_thymeleaf.repository;

import com.springboot.crud_thymeleaf.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByName(String name);
}
