package com.example.dbtest.controller;

import com.example.dbtest.entities.Student;
import com.example.dbtest.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private StudentService studentService;

    StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    public boolean saveStudent(@RequestBody Student student){
        return studentService.save(student);
    }
}
