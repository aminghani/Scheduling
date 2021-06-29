package models;

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

    public Bell(String label, int bellOfDay){
        this.label = label;
        this.bellOfDay = bellOfDay;
    }

    public Bell(){

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBellOfDay(int bellOfDay) {
        this.bellOfDay = bellOfDay;
    }

    @Override
    public String toString() {
        return "Bell{" +
                "label='" + label + '\'' +
                ", bellOfDay=" + bellOfDay +
                '}';
    }
}
