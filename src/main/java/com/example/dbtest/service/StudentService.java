package com.example.dbtest.service;


import com.example.dbtest.entities.Student;
import com.example.dbtest.entities.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public boolean save(Student student){
        studentRepository.save(student);
        return true;
    }
}
