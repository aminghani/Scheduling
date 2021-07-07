package com.example.scheduling.Controllers;

import DataAccess.AnnouncementDAO;
import models.Announcement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnnouncementController {

    @GetMapping("/api/Announcements")
    public List<Announcement> getAnnouncements(){
        return new AnnouncementDAO().getAllAnnouncements();
    }

    @PostMapping("/api/Announcements")
    public void createAnnouncement(@RequestBody Announcement announcement){
        new AnnouncementDAO().createAnnouncement(announcement);
    }

    @GetMapping("/api/Announcements/{id}")
    public Announcement getAnnouncementsById(@PathVariable int id){
        return new AnnouncementDAO().getAnnouncementById(id);
    }

    @DeleteMapping("/api/Announcements/{id}")
    public void deleteAnnouncementsById(@PathVariable int id){
        new AnnouncementDAO().deleteAnnouncementById(id);
    }
}
