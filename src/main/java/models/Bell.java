package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "bell")
public class Bell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "label")
    private String label;

    @Column(name = "bellOfDay")
    private int bellOfDay;

    @OneToOne(mappedBy = "bell")
    @JsonBackReference
    private TimeTableBell timetableBell;



    public Bell(){

    }

    public Bell(String label, int bellOfDay){
        this.label = label;
        this.bellOfDay = bellOfDay;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getBellOfDay() {
        return bellOfDay;
    }

    public void setBellOfDay(int bellOfDay) {
        this.bellOfDay = bellOfDay;
    }

    public TimeTableBell getTimetableBell() {
        return timetableBell;
    }

    public void setTimetableBell(TimeTableBell timetableBell) {
        this.timetableBell = timetableBell;
    }
}
