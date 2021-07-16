package com.example.scheduling.Controllers;

import DataAccess.CourseDAO;
import DataAccess.MasterDAO;
import DataAccess.TimeTableBellDAO;
import com.example.scheduling.Controllers.util.JwtUtil;
import models.Master;
import models.TimeTableBell;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeTableBellController {


    @GetMapping("/api/TimeTableBells")
    public List<TimeTableBell> getAllTimeTableBells(){
        return new TimeTableBellDAO().getAllTimeTableBells();
    }

    @PostMapping("/api/TimeTableBells")
    public void createTimeTableBell(@RequestBody TimeTableBell timetableBell){
        new TimeTableBellDAO().addTimeTableBell(timetableBell);
    }

    @GetMapping("/api/TimeTableBells/{id}")
    public TimeTableBell getTimeTableBellById(@PathVariable int id){
        return new TimeTableBellDAO().getTimeTableBellById(id);
    }

    @DeleteMapping("/api/TimeTableBells/{id}")
    public void deleteTimeTableBellById(@PathVariable int id){
        new TimeTableBellDAO().deleteTimeTableBellById(id);
    }



}
