package com.example.scheduling.Controllers;

import DataAccess.CourseDAO;
import DataAccess.MasterDAO;
import com.example.scheduling.Controllers.util.JwtUtil;
import com.example.scheduling.Controllers.util.MyUserDetailsService;
import models.Course;
import models.Master;
import models.TimeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class CourseController {

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private MasterDAO masterDAO;


    @GetMapping("/api/Courses")
    public List<Course> getAllCourses(@RequestParam(value = "unitCount",required = false) String unitCount){
        return courseDAO.getAllCourses(unitCount);
    }

    @PostMapping("/api/Courses")
    public void createCourse(@RequestBody Course course){
        courseDAO.addCourse(course);
    }

    @GetMapping("/api/Courses/{id}")
    public Course getCourseById(@PathVariable int id){
        return courseDAO.getCourseById(id);
    }

    @PutMapping("/api/Course/{id}")
    public void updateCourseById(@RequestBody Course course,@PathVariable int id){
        courseDAO.updateCourseById(course,id);
    }

    @GetMapping("/api/Courses/{id}/TimeTables")
    public Set<TimeTable> getCourseTimeTableById(@PathVariable int id){
        return courseDAO.getCourseTimeTablesById(id);
    }

    @GetMapping("/api/Courses/{id}/Masters")
    public Set<Master> getCourseMastersById(@PathVariable int id){
        return courseDAO.getCourseMastersById(id);
    }

    @DeleteMapping("/api/Courses/{id}")
    public void deleteCourseById(@PathVariable int id){
        courseDAO.deleteCourseById(id);
    }

    @PostMapping("/api/Courses/{id}/Choose")
    public void chooseCourseForMaster(@PathVariable int id,@RequestHeader("Authorization") String header){
        String jwt = header;
        String username = new JwtUtil().extractUsername(jwt);
        Master master = masterDAO.findByUsername(username);
        courseDAO.addMasterToCourse(id,master.getId());
    }


}
