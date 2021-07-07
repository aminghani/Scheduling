package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "timetable")
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "master_id")
    private Master master;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "timetable_id")
    private List<TimetableBell> timetableBells;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id_tt")
    private Course course;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "timetable_student",
    joinColumns = @JoinColumn(name = "timetable_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    public TimeTable(Master master, List<TimetableBell> timetableBells, Course course) {
        this.master = master;
        this.timetableBells = timetableBells;
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public TimeTable(){
        timetableBells = new ArrayList<>();
    }

    public void addTimeTableBell(TimetableBell timetableBell){
        if(timetableBells == null){
            timetableBells = new ArrayList<>();
        }
        timetableBells.add(timetableBell);
    }
    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public List<TimetableBell> getTimetableBells() {
        return timetableBells;
    }

    public void setTimetableBells(List<TimetableBell> timetableBells) {
        this.timetableBells = timetableBells;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "master=" + master +
                ", timetableBells=" + timetableBells +
                ", course=" + course +
                '}';
    }
}
