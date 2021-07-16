package DataAccess;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class AnnouncementDAO {
    private Session getHibernateSession() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Announcement.class).
                        addAnnotatedClass(TimeTable.class).
                        addAnnotatedClass(Bell.class).
                        addAnnotatedClass(Day.class).
                        addAnnotatedClass(TimeTableBell.class).
                        addAnnotatedClass(Master.class).
                        addAnnotatedClass(Course.class).
                        addAnnotatedClass(Student.class).
                        buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void createAnnouncement(Announcement announcement){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            session.save(announcement);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public List<Announcement> getAllAnnouncements(){
        Session session = getHibernateSession();
        List<Announcement> announcements = null;
        try {
            session.beginTransaction();

            announcements =  session.createCriteria(Announcement.class).list();

            session.getTransaction().commit();

        }finally {
            session.close();
        }
        return announcements;
    }

    public Announcement getAnnouncementById(int id){
        Session session = getHibernateSession();
        Announcement announcements = null;
        try {
            session.beginTransaction();

            announcements =  session.get(Announcement.class,id);

            session.getTransaction().commit();

        }finally {
            session.close();
        }
        return announcements;
    }

    public void deleteAnnouncementById(int id){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from Announcement where id=:theId");
            query.setParameter("theId",id);
            query.executeUpdate();

            session.getTransaction().commit();

        }finally {
            session.close();
        }
    }
}



