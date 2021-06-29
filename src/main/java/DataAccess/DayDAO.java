package DataAccess;

import models.Day;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DayDAO {

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Day.class).
                        buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void createDay(String label,int dayOfWeek){
        Session session = getHibernateSession();

        try{
            session.beginTransaction();

            session.save(new Day(label,dayOfWeek));

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public Day getDayById(int id){
        Session session = getHibernateSession();

        Day day = null;

        try{
            session.beginTransaction();

            day = session.get(Day.class, id);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return day;
    }
}
