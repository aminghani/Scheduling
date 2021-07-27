package com.example.scheduling.Controllers;

import DataAccess.DayDAO;
import models.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DayController {

    @Autowired
    private DayDAO dayDAO;

    @GetMapping("/api/Days")
    public List<Day> getAllDays(){
        return dayDAO.getAllDays();
    }

    @PostMapping("/api/Days")
    public void addDays(@RequestBody Day day){
        dayDAO.addDay(day);
    }

    @GetMapping("/api/Days/{id}")
    public Day getDayById(@PathVariable int id){
        return dayDAO.getDayById(id);
    }

    @PutMapping("/api/Days/{id}")
    public void updateDayById(@RequestBody Day day,@PathVariable int id){
        dayDAO.updateDayById(day,id);
    }

    @DeleteMapping("/api/Days/{id}")
    public void deleteDayById(@PathVariable int id){
        dayDAO.deleteDayById(id);
    }
}
