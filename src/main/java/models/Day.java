package models;

import javax.persistence.*;

@Entity
@Table(name = "day")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "label")
    private String label;
    @Column(name = "dayOfWeek")
    private int dayOfWeek;

    public Day(String label, int dayOfWeek) {
        this.label = label;
        this.dayOfWeek = dayOfWeek;
    }
    public Day(){

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Day{" +
                "label='" + label + '\'' +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
