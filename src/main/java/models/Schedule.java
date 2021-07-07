package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Schedule {
  /*  private List<Course> courses;
    private List<TimetableBell> timetableBells;
    private int availableClasses;

    public Schedule(List<Course> courses, List<TimetableBell> timetableBells, int availableClasses) {
        this.courses = courses;
        this.timetableBells = timetableBells;
        this.availableClasses = availableClasses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<TimetableBell> getTimetableBells() {
        return timetableBells;
    }

    public void setTimetableBells(List<TimetableBell> timetableBells) {
        this.timetableBells = timetableBells;
    }

    public int getAvailableClasses() {
        return availableClasses;
    }

    public void setAvailableClasses(int availableClasses) {
        this.availableClasses = availableClasses;
    }

    public List<TimeTable> Schedule(){
        List<TimeTable> timeTables = new ArrayList<>();

        int classCounter = 0;
        int classCounter2 = 0;
        int timeTableCounter1 =0;
        int timeTableCounter2 = getTimetableBells().size()-1;
        for(Course course:getCourses()) {
            for (Master master : course.getMasters()) {
                if (classCounter < getAvailableClasses()) {
                    List<TimetableBell> timetableBells = new ArrayList<>();
                    timetableBells.add(getTimetableBells().get(timeTableCounter1));
                    if (course.getUnitsCount()>2){
                        if(classCounter2 < getAvailableClasses()){
                            timetableBells.add(getTimetableBells().get(timeTableCounter2));
                            classCounter2++;
                        }else{
                            timeTableCounter2--;
                            classCounter2=0;
                            timetableBells.add(getTimetableBells().get(timeTableCounter2));
                            classCounter2++;
                        }
                    }
                    timeTables.add(new TimeTable(master, timetableBells, course));
                    classCounter++;
                } else {
                    timeTableCounter1++;
                    classCounter = 0;
                    timetableBells.add(getTimetableBells().get(timeTableCounter1));
                    if (course.getUnitsCount()>2){
                        if(classCounter2 < getAvailableClasses()){
                            timetableBells.add(getTimetableBells().get(timeTableCounter2));
                            classCounter2++;
                        }else{
                            timeTableCounter2--;
                            classCounter2=0;
                            timetableBells.add(getTimetableBells().get(timeTableCounter2));
                            classCounter2++;
                        }
                    }
                    timeTables.add(new TimeTable(master, timetableBells, course));
                    classCounter++;
                }
            }

        }

        return timeTables;
    }*/

    public static void main(String[] args) {
        /*List<Course> courses = new ArrayList<>();
        List<TimetableBell> timetableBells = new ArrayList<>();
        courses.add(new Course("fizik",3,Arrays.asList(new Master(1,"1_f"),new Master(2,"2_f"),new Master(3,"3_f"))));
        courses.add(new Course("iazi",3,Arrays.asList(new Master(1,"1_r"),new Master(2,"2_r"),new Master(3,"3_r"))));
        timetableBells.add(new TimetableBell(new Bell("8-10",0),new Day("sunday",1)));
        timetableBells.add(new TimetableBell(new Bell("10-12",1),new Day("sunday",1)));
        timetableBells.add(new TimetableBell(new Bell("8-10",0),new Day("monday",2)));
        timetableBells.add(new TimetableBell(new Bell("2-4",2),new Day("Thursday",4)));
        System.out.println(new Schedule(courses,timetableBells,2).Schedule().toString());*/
    }
}

/*
for(TimetableBell timetableBell:getTimetableBells()){
        for(Course course:getCourses()){
        int classCounter = 0;
        int masterCounter = 0;
        while (classCounter<getAvailableClasses() && masterCounter<course.getMasters().size()){
        List<TimetableBell> temp = new ArrayList();
        temp.add(timetableBell);
        timeTables.add(new TimeTable(course.getMasters().get(masterCounter),temp,course));
        classCounter++;
        masterCounter++;
        }
        }
        }*/
