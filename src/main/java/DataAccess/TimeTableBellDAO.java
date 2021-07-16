package DataAccess;

import models.Bell;
import models.Day;
import models.TimeTableBell;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class TimeTableBellDAO {

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(TimeTableBell.class).
                        addAnnotatedClass(Bell.class).
                        addAnnotatedClass(Day.class).
                        buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void addTimeTableBell(TimeTableBell timetableBell){
        Session session = getHibernateSession();

        try{
            session.beginTransaction();

            session.save(timetableBell);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public TimeTableBell getTimeTableBellById(int id){
        Session session = getHibernateSession();

        TimeTableBell timetableBell = null;
        try{
            session.beginTransaction();

            timetableBell = session.get(TimeTableBell.class, id);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return timetableBell;
    }

    public List<TimeTableBell> getAllTimeTableBells(){
        Session session = getHibernateSession();
        List<TimeTableBell> timetableBells = null;

        try{
            session.beginTransaction();

            timetableBells =  session.createCriteria(TimeTableBell.class).list();

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return timetableBells;
    }

    public void deleteTimeTableBellById(int id){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from TimeTableBell where id=:theId");
            query.setParameter("theId",id);
            query.executeUpdate();

            session.getTransaction().commit();

        }finally {
            session.close();
        }
    }
}
