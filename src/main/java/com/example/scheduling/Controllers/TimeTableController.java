package com.example.scheduling.Controllers;

import DataAccess.CourseDAO;
import DataAccess.MasterDAO;
import DataAccess.StudentDAO;
import DataAccess.TimeTableDAO;
import com.example.scheduling.Controllers.util.JwtUtil;
import models.Master;
import models.Student;
import models.TimeTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeTableController {

    @GetMapping("/api/TimeTables")
    public List<TimeTable> getTimeTables(@RequestParam(value = "StudentId",required = false) String studentId,
                                         @RequestParam(value = "CourseId",required = false) String courseId,
                                         @RequestParam(value = "MasterId",required = false) String masterId){
        return new TimeTableDAO().getTimeTables(studentId,courseId,masterId);
    }

    @GetMapping("/api/TimeTables/{id}")
    public TimeTable getTimeTableById(@PathVariable int id){
        return new TimeTableDAO().getTimeTableById(id);
    }

    @PostMapping("/api/TimeTables/{id}/Choose")
    public void chooseTimeTableForStudent(@PathVariable int id,@RequestHeader("Authorization") String header){
        String jwt = header;
        String username = new JwtUtil().extractUsername(jwt);
        Student student = new StudentDAO().findByUsername(username);
        new TimeTableDAO().addStudentToTimeTable(id,student.getId());
    }

}
