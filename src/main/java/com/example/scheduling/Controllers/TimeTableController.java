package com.example.scheduling.Controllers;

import DataAccess.TimeTableBellDAO;
import DataAccess.TimeTableDAO;
import models.TimeTable;
import models.TimetableBell;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
