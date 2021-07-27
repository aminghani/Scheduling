package com.example.scheduling.Controllers;

import DataAccess.BellDAO;
import models.Bell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BellController {

    @Autowired
    private BellDAO bellDAO;

    @GetMapping("/api/Bells")
    public List<Bell> getAllBells(){
        return bellDAO.getAllBells();
    }

    @PostMapping("/api/Bells")
    public void createBell(@RequestBody Bell bell){
        bellDAO.createBell(bell);
    }

    @GetMapping("/api/Bells/{id}")
    public Bell getBellById(@PathVariable int id){
        return bellDAO.getBellById(id);
    }

    @PutMapping("/api/Bells/{id}")
    public void updateBellById(@RequestBody Bell bell,@PathVariable int id){
        bellDAO.updateBellById(bell,id);
    }

    @DeleteMapping("/api/Bells/{id}")
    public void deleteBellById(@PathVariable int id){
        bellDAO.deleteBellById(id);
    }

}
