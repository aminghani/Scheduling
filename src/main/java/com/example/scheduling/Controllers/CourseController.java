package com.example.scheduling.Controllers;

import DataAccess.CourseDAO;
import models.Course;
import models.Master;
import models.TimeTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<TimeTable> getCourseTimeTableById(@PathVariable int id){
        return new CourseDAO().getCourseTimeTablesById(id);
    }

    @GetMapping("/api/Courses/{id}/Masters")
    public List<Master> getCourseMastersById(@PathVariable int id){
        return new CourseDAO().getCourseMastersById(id);
    }

    @PostMapping("/api/Courses/{id}/Choose")
    public void chooseCourseMasterById(@PathVariable int id){
        new CourseDAO();
    }

}
