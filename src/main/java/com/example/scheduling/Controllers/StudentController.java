package com.example.scheduling.Controllers;

import DataAccess.MasterDAO;
import DataAccess.StudentDAO;
import com.example.scheduling.Controllers.util.JwtUtil;
import models.Master;
import models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentDAO studentDAO;

    @PutMapping("/api/Students/{id}")
    public void changeStudentById(@RequestBody Student student,@PathVariable int id){
        studentDAO.changeStudentById(student,id);
    }

    @GetMapping("/api/Students")
    public List<Student> getAllStudents(@RequestParam(value = "name",required = false) String name){
        return studentDAO.getAllStudents(name);
    }

    @PostMapping("/api/Students/Add")
    public void addStudent(@RequestBody Student student){
        studentDAO.createStudent(student);
    }

    @PostMapping("/api/Students/AddList")
    public void addStudents(@RequestBody List<Student> studentList){
        studentDAO.addStudents(studentList);
    }

    @DeleteMapping("/api/Students/{id}")
    public void deleteStudentById(@PathVariable int id){
        studentDAO.deleteStudentById(id);
    }


    @GetMapping("/api/Student/Profile")
    public Student getStudentProfile(@RequestHeader(value = "Authorization") String header){
        String jwt = header;
        String username = new JwtUtil().extractUsername(jwt);
        Student student = studentDAO.findByUsername(username);
        return student;
    }
}

