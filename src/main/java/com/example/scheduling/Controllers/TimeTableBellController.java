package com.example.scheduling.Controllers;

import DataAccess.TimeTableBellDAO;
import models.TimetableBell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeTableBellController {


    @GetMapping("/api/TimeTableBells")
    public List<TimetableBell> getAllTimeTableBells(){
        return new TimeTableBellDAO().getAllTimeTableBells();
    }

    @PostMapping("/api/TimeTableBells")
    public void createTimeTableBell(@RequestBody TimetableBell timetableBell){
        new TimeTableBellDAO().addTimeTableBell(timetableBell);
    }

    @GetMapping("/api/TimeTableBells/{id}")
    public TimetableBell getTimeTableBellById(@PathVariable int id){
        return new TimeTableBellDAO().getTimeTableBellById(id);
    }


}
