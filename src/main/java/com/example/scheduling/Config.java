package com.example.scheduling;

import DataAccess.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public DayDAO dayDAO(){
        return new DayDAO();
    }

    @Bean
    public AnnouncementDAO announcementDAO(){
        return new AnnouncementDAO();
    }

    @Bean
    public BellDAO bellDAO(){
        return new BellDAO();
    }

    @Bean
    public CourseDAO courseDAO(){
        return new CourseDAO();
    }

    @Bean
    public MasterDAO masterDAO(){
        return new MasterDAO();
    }

    @Bean
    public StudentDAO studentDAO(){
        return new StudentDAO();
    }

    @Bean
    public TimeTableDAO timeTableDAO(){
        return new TimeTableDAO();
    }

    @Bean
    public TimeTableBellDAO timeTableBellDAO(){
        return new TimeTableBellDAO();
    }
}
