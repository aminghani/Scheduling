package com.example.scheduling.Controllers;

import DataAccess.BellDAO;
import models.Bell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BellController {


    @GetMapping("/api/Bells")
    public List<Bell> getAllBells(){
        return new BellDAO().getAllBells();
    }

    @PostMapping("/api/Bells")
    public void createBell(@RequestBody Bell bell){
        new BellDAO().createBell(bell);
    }

    @GetMapping("/api/Bells/{id}")
    public Bell getBellById(@PathVariable int id){
        return new BellDAO().getBellById(id);
    }

    @PutMapping("/api/Bells/{id}")
    public void updateBellById(@RequestBody Bell bell,@PathVariable int id){
        new BellDAO().updateBellById(bell,id);
    }

    @DeleteMapping("/api/Bells/{id}")
    public void deleteBellById(@PathVariable int id){
        new BellDAO().deleteBellById(id);
    }

}
