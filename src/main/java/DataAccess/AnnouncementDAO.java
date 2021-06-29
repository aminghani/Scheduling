package DataAccess;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AnnouncementDAO {
    private Session getHibernateSession() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Announcement.class).
                        addAnnotatedClass(TimeTable.class).
                        addAnnotatedClass(Bell.class).
                        addAnnotatedClass(Day.class).
                        addAnnotatedClass(TimetableBell.class).
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
}



