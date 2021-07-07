package DataAccess;

import models.Bell;
import models.Course;
import models.Day;
import models.TimetableBell;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

public class TimeTableBellDAO {

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Bell.class).
                        addAnnotatedClass(Day.class).
                        addAnnotatedClass(TimetableBell.class).
                        buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void addTimeTableBell(TimetableBell timetableBell){
        Session session = getHibernateSession();

        try{
            session.beginTransaction();

            session.save(timetableBell);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public TimetableBell getTimeTableBellById(int id){
        Session session = getHibernateSession();

        TimetableBell timetableBell = null;
        try{
            session.beginTransaction();

            timetableBell = session.get(TimetableBell.class, id);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return timetableBell;
    }

    public List<TimetableBell> getAllTimeTableBells(){
        Session session = getHibernateSession();
        List<TimetableBell> timetableBells = null;

        try{
            session.beginTransaction();

            timetableBells =  session.createCriteria(TimetableBell.class).list();

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return timetableBells;
    }
}
