package com.example.scheduling.Controllers;

import DataAccess.UserDAO;
import models.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    /*@GetMapping("/api/Users")
    public List<Student> getUsers(@RequestParam(value = "search",required = false) String name){
        return new UserDAO().getAllUsers(name);
    }

    @GetMapping("/api/Users/{id}")
    public List<Student> getUserById(@PathVariable int id){
        return new UserDAO().getUserById(id);
    }
*/
}
