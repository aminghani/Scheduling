package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "timetablebell")
public class TimeTableBell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bell_id")
    @JsonManagedReference
    private Bell bell;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "day_id")
    private Day day;

    public TimeTableBell(Bell bell, Day day) {
        this.bell = bell;
        this.day = day;
    }

    public TimeTableBell(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bell getBell() {
        return bell;
    }

    public void setBell(Bell bell) {
        this.bell = bell;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "TimeTableBell{" +
                "bell=" + bell +
                ", day=" + day +
                '}';
    }
}
