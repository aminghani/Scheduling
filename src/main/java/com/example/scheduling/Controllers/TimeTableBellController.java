package com.example.scheduling.Controllers;

import DataAccess.CourseDAO;
import DataAccess.MasterDAO;
import DataAccess.TimeTableBellDAO;
import com.example.scheduling.Controllers.util.JwtUtil;
import models.Master;
import models.TimeTableBell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeTableBellController {

    @Autowired
    private TimeTableBellDAO timeTableBellDAO;


    @GetMapping("/api/TimeTableBells")
    public List<TimeTableBell> getAllTimeTableBells(){
        return timeTableBellDAO.getAllTimeTableBells();
    }

    @PostMapping("/api/TimeTableBells")
    public void createTimeTableBell(@RequestBody TimeTableBell timetableBell){
        timeTableBellDAO.addTimeTableBell(timetableBell);
    }

    @GetMapping("/api/TimeTableBells/{id}")
    public TimeTableBell getTimeTableBellById(@PathVariable int id){
        return timeTableBellDAO.getTimeTableBellById(id);
    }

    @DeleteMapping("/api/TimeTableBells/{id}")
    public void deleteTimeTableBellById(@PathVariable int id){
        timeTableBellDAO.deleteTimeTableBellById(id);
    }



}
