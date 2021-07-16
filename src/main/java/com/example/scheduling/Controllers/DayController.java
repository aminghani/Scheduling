package com.example.scheduling.Controllers;

import DataAccess.DayDAO;
import models.Day;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DayController {

    @GetMapping("/api/Days")
    public List<Day> getAllDays(){
        return new DayDAO().getAllDays();
    }

    @PostMapping("/api/Days")
    public void addDays(@RequestBody Day day){
        new DayDAO().addDay(day);
    }

    @GetMapping("/api/Days/{id}")
    public Day getDayById(@PathVariable int id){
        return new DayDAO().getDayById(id);
    }

    @PutMapping("/api/Days/{id}")
    public void updateDayById(@RequestBody Day day,@PathVariable int id){
        new DayDAO().updateDayById(day,id);
    }

    @DeleteMapping("/api/Days/{id}")
    public void deleteDayById(@PathVariable int id){
        new DayDAO().deleteDayById(id);
    }
}
