package models;

import javax.persistence.*;

@Entity
@Table(name = "timetablebell")
public class TimetableBell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bell_id")
    private Bell bellMapped;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id")
    private Day day;

    public TimetableBell(Bell bell, Day day) {
        this.bellMapped = bell;
        this.day = day;
    }

    public TimetableBell(){

    }

    public Bell getBell() {
        return bellMapped;
    }

    public void setBell(Bell bell) {
        this.bellMapped = bell;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "TimetableBell{" +
                "bell=" + bellMapped +
                ", day=" + day +
                '}';
    }
}
