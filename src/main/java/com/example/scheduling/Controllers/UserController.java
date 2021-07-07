package com.example.scheduling.Controllers;

import DataAccess.UserDAO;
import models.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/api/Users")
    public List<User> getUsers(@RequestParam(value = "search",required = false) String name){
        return new UserDAO().getAllUsers(name);
    }

    @GetMapping("/api/Users/{id}")
    public List<User> getUserById(@PathVariable int id){
        return new UserDAO().getUserById(id);
    }

}
