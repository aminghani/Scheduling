package DataAccess;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TimeTableDAO {

    public TimeTableDAO(){

    }

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Master.class).
                        addAnnotatedClass(Course.class).
                        addAnnotatedClass(TimetableBell.class).
                        addAnnotatedClass(TimeTable.class).
                        addAnnotatedClass(Bell.class).
                        addAnnotatedClass(Day.class).
                        buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void addTimeTable(Master master, Course course, List<TimetableBell> timetableBells){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            TimeTable timeTable = new TimeTable();
            timeTable.setMaster(master);
            timeTable.setCourse(course);
            for(TimetableBell timetableBell:timetableBells){
                timeTable.addTimeTableBell(timetableBell);
            }

            session.save(timeTable);

            session.getTransaction().commit();

        }finally {
            session.close();
        }
    }
}
