package DataAccess;

import models.Announcement;
import models.Day;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

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

    public List<Day> getAllDays(){
        Session session = getHibernateSession();
        List<Day> days = null;
        try {
            session.beginTransaction();

            days =  session.createCriteria(Announcement.class).list();

            session.getTransaction().commit();

        }finally {
            session.close();
        }
        return days;
    }

    public void addDay(Day day){
        Session session = getHibernateSession();

        try{
            session.beginTransaction();

            session.save(day);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public void updateDayById(Day newDay,int id){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            Day day = session.get(Day.class,id);

            day.setDayOfWeek(newDay.getDayOfWeek());

            day.setLabel(newDay.getLabel());

            session.getTransaction().commit();

        }finally {
            session.close();
        }
    }

}
