package DataAccess;

import models.Bell;
import models.Day;
import models.TimetableBell;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

    public void addTimeTableBell(Bell bell, Day day){
        Session session = getHibernateSession();

        try{
            session.beginTransaction();

            TimetableBell timetableBell = new TimetableBell();

            timetableBell.setBell(bell);

            timetableBell.setDay(day);

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
}
