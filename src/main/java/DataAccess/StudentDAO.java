package DataAccess;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StudentDAO {

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(TimeTable.class)
                .addAnnotatedClass(Master.class)
                .addAnnotatedClass(TimetableBell.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Bell.class)
                .addAnnotatedClass(Day.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void createStudent(Student student){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            session.save(student);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }


}
