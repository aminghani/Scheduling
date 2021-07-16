package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "master_id")
    @JsonBackReference
    private Master master;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "timetable_id")
    private Set<TimeTableBell> timetableBells;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id_tt")
    private Course course;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "timetable_student",
    joinColumns = @JoinColumn(name = "timetable_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonIgnore
    private List<Student> students;

    public TimeTable(Master master, Set<TimeTableBell> timetableBells, Course course) {
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
        timetableBells = new HashSet<>();
    }

    public void addTimeTableBell(TimeTableBell timetableBell){
        if(timetableBells == null){
            timetableBells = new HashSet<>();
        }
        timetableBells.add(timetableBell);
    }

    public void addStudent(Student student){
        if (students == null){
            students = new ArrayList<>();
        }
        students.add(student);
    }
    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Set<TimeTableBell> getTimetableBells() {
        return timetableBells;
    }

    public void setTimetableBells(Set<TimeTableBell> timetableBells) {
        this.timetableBells = timetableBells;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
