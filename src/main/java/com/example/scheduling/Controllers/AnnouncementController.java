package com.example.scheduling.Controllers;

import DataAccess.AnnouncementDAO;
import models.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnnouncementController {

    @Autowired
    private AnnouncementDAO announcementDAO;

    @GetMapping("/api/Announcements")
    public List<Announcement> getAnnouncements(){
        return announcementDAO.getAllAnnouncements();
    }

    @PostMapping("/api/Announcements")
    public void createAnnouncement(@RequestBody Announcement announcement){
        announcementDAO.createAnnouncement(announcement);
    }

    @GetMapping("/api/Announcements/{id}")
    public Announcement getAnnouncementsById(@PathVariable int id){
        return announcementDAO.getAnnouncementById(id);
    }

    @DeleteMapping("/api/Announcements/{id}")
    public void deleteAnnouncementsById(@PathVariable int id){
        announcementDAO.deleteAnnouncementById(id);
    }
}
