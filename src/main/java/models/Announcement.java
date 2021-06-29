package models;

import javax.persistence.*;

@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "text")
    private String text;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timetable_id")
    private TimeTable timeTableId;

    public Announcement( String text, TimeTable timeTableId) {
        this.id = id;
        this.text = text;
        this.timeTableId = timeTableId;
    }
    public Announcement(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TimeTable getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(TimeTable timeTableId) {
        this.timeTableId = timeTableId;
    }
}
