package DataAccess;

import models.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {
        new CourseDAO().addMasterToCourse(1,1);
    }

    private static void updateMaster(Master master,int id){
        new MasterDAO().changeMasterById(master,id);
    }

    private static void addMaster(Master master){
        new MasterDAO().createMaster(master);
    }

    private static void addMasters(List<Master> masters){
        new MasterDAO().addMasters(masters);
    }

    private static void updateStudent(Student student,int id){
        new StudentDAO().changeStudentById(student,id);
    }

    private static void addStudent(Student student){
        new StudentDAO().createStudent(student);
    }

    private static void addStudents(List<Student> students){
        new StudentDAO().addStudents(students);
    }
}


