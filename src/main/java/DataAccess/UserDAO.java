package DataAccess;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {

    public UserDAO(){

    }

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Master.class).
                        addAnnotatedClass(Course.class).
                        addAnnotatedClass(TimeTableBell.class).
                        addAnnotatedClass(TimeTable.class).
                        addAnnotatedClass(Bell.class).
                        addAnnotatedClass(Day.class).
                        addAnnotatedClass(Student.class).
                        addAnnotatedClass(Admin.class).
                        buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

   /* public List<User> getAllUsers(String search){
        Session session = getHibernateSession();
        List<User> users = new ArrayList<>();
        try {
            users.addAll(new AdminDAO().getAllAdmins());
            users.addAll(new StudentDAO().getAllStudents(search));
            users.addAll(new MasterDAO().getAllMasters(search));
        }finally {

        }
        return users;
    }

    public List<User> getUserById(int id){
        List<User> users = new ArrayList<>();
        MasterDAO masterDAO = new MasterDAO();
        StudentDAO studentDAO = new StudentDAO();
        if (masterDAO.getMasterById(id) != null){
            users.add(masterDAO.getMasterById(id));
        }
        if(studentDAO.getStudentById(id) != null){
            users.add(studentDAO.getStudentById(id));
        }
        return users;
    }*/
}
