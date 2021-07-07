package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "master")
public class Master implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "password")
    private String password;
    @Column(name = "code")
    private String code;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "master_course",
                joinColumns = @JoinColumn(name = "master_id"),
                inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;


    public Master(String firstName,String lastname,String password,String code) {
        this.id = id;
        this.firstName=firstName;
        this.lastname=lastname;
        this.password=password;
        this.code=code;
    }

    public Master(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
