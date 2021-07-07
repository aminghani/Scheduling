package com.example.scheduling.Controllers;

import DataAccess.MasterDAO;
import models.Master;
import models.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MasterController {

    @PutMapping("/api/Master/{id}")
    public void changeMasterById(@RequestBody Master master, @PathVariable int id){
        new MasterDAO().changeMasterById(master,id);
    }

    @PostMapping("/api/Master/Add")
    public void addMaster(@RequestBody Master master){
        new MasterDAO().createMaster(master);
    }

    @PostMapping("/api/Master/AddList")
    public void addMasters(@RequestBody List<Master> masterList){
        new MasterDAO().addMasters(masterList);
    }
}
