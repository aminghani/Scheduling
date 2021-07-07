package com.example.scheduling.Controllers;

import DataAccess.StudentDAO;
import models.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @PutMapping("/api/Students/{id}")
    public void changeStudentById(@RequestBody Student student,@PathVariable int id){
        new StudentDAO().changeStudentById(student,id);
    }

    @PostMapping("/api/Students/Add")
    public void addStudent(@RequestBody Student student){
        new StudentDAO().createStudent(student);
    }

    @PostMapping("/api/Students/AddList")
    public void addStudents(@RequestBody List<Student> studentList){
        new StudentDAO().addStudents(studentList);
    }
}
