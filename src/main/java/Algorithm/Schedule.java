package Algorithm;

import models.*;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private List<TimeTableBell> timeTableBells;
    private List<Course> courses;
    private int rooms;

    public Schedule(List<TimeTableBell> timeTableBells, List<Course> courses, int rooms) {
        this.timeTableBells = timeTableBells;
        this.courses = courses;
        this.rooms = rooms;
    }

    public Schedule(){

    }

    public List<TimeTable> run(){
        List<TimeTable> timeTables = new ArrayList<>();
        int roomCounter = 0;
        int timeTableBellCounter = 0;
        int CourseCounter = 0;
        while(timeTableBellCounter<timeTableBells.size() && CourseCounter<courses.size()){
            while(roomCounter<rooms && CourseCounter<courses.size()){
                TimeTable timeTable = new TimeTable();
                timeTable.setCourse(courses.get(CourseCounter));
                timeTable.addTimeTableBell(timeTableBells.get(timeTableBellCounter));
                CourseCounter++;
                roomCounter++;
                timeTables.add(timeTable);
            }
            roomCounter = 0;
            timeTableBellCounter++;
        }
        return timeTables;
    }

}
