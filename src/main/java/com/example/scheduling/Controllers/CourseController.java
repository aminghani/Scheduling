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


    @GetMapping("/api/Courses")
    public List<Course> getAllCourses(@RequestParam(value = "unitCount",required = false) String unitCount){
        return new CourseDAO().getAllCourses(unitCount);
    }

    @PostMapping("/api/Courses")
    public void createCourse(@RequestBody Course course){
        new CourseDAO().addCourse(course);
    }

    @GetMapping("/api/Courses/{id}")
    public Course getCourseById(@PathVariable int id){
        return new CourseDAO().getCourseById(id);
    }

    @PutMapping("/api/Course/{id}")
    public void updateCourseById(@RequestBody Course course,@PathVariable int id){
        new CourseDAO().updateCourseById(course,id);
    }

    @GetMapping("/api/Courses/{id}/TimeTables")
    public Set<TimeTable> getCourseTimeTableById(@PathVariable int id){
        return new CourseDAO().getCourseTimeTablesById(id);
    }

    @GetMapping("/api/Courses/{id}/Masters")
    public Set<Master> getCourseMastersById(@PathVariable int id){
        return new CourseDAO().getCourseMastersById(id);
    }

    @DeleteMapping("/api/Courses/{id}")
    public void deleteCourseById(@PathVariable int id){
        new CourseDAO().deleteCourseById(id);
    }

    @PostMapping("/api/Courses/{id}/Choose")
    public void chooseCourseForMaster(@PathVariable int id,@RequestHeader("Authorization") String header){
        String jwt = header;
        String username = new JwtUtil().extractUsername(jwt);
        Master master = new MasterDAO().findByUsername(username);
        new CourseDAO().addMasterToCourse(id,master.getId());
    }


}
