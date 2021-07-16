package com.example.scheduling.Controllers;

import DataAccess.MasterDAO;
import DataAccess.StudentDAO;
import com.example.scheduling.Controllers.util.JwtUtil;
import models.Master;
import models.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MasterController {

    @PutMapping(value = "/api/Master/{id}")
    public void changeMasterById(@RequestBody Master master, @PathVariable int id){
        new MasterDAO().changeMasterById(master,id);
    }

    @GetMapping("/api/Masters")
    public List<Master> getAllMasters(@RequestParam(name = "name",required = false) String name){
        return new MasterDAO().getAllMasters(name);
    }

    @PostMapping("/api/Master/Add")
    public void addMaster(@RequestBody Master master){
        new MasterDAO().createMaster(master);
    }

    @PostMapping("/api/Master/AddList")
    public void addMasters(@RequestBody List<Master> masterList){
        new MasterDAO().addMasters(masterList);
    }

    @DeleteMapping("/api/Master/{id}")
    public void deleteMasterById(@PathVariable int id){
        new MasterDAO().deleteMasterById(id);
    }

    @GetMapping("/api/Master/Profile")
    public Master getStudentProfile(@RequestHeader(value = "Authorization") String header){
        String jwt = header;
        String username = new JwtUtil().extractUsername(jwt);
        Master master = new MasterDAO().findByUsername(username);
        return master;
    }
}
