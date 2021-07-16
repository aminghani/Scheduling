package com.example.scheduling.Controllers;

import DataAccess.MasterDAO;
import DataAccess.StudentDAO;
import com.example.scheduling.Controllers.util.JwtUtil;
import models.Master;
import models.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @PutMapping("/api/Students/{id}")
    public void changeStudentById(@RequestBody Student student,@PathVariable int id){
        new StudentDAO().changeStudentById(student,id);
    }

    @GetMapping("/api/Students")
    public List<Student> getAllStudents(@RequestParam(value = "name",required = false) String name){
        return new StudentDAO().getAllStudents(name);
    }

    @PostMapping("/api/Students/Add")
    public void addStudent(@RequestBody Student student){
        new StudentDAO().createStudent(student);
    }

    @PostMapping("/api/Students/AddList")
    public void addStudents(@RequestBody List<Student> studentList){
        new StudentDAO().addStudents(studentList);
    }

    @DeleteMapping("/api/Students/{id}")
    public void deleteStudentById(@PathVariable int id){
        new StudentDAO().deleteStudentById(id);
    }


    @GetMapping("/api/Student/Profile")
    public Student getStudentProfile(@RequestHeader(value = "Authorization") String header){
        String jwt = header;
        String username = new JwtUtil().extractUsername(jwt);
        Student student = new StudentDAO().findByUsername(username);
        return student;
    }
}
