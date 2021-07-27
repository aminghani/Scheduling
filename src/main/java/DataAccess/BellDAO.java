package DataAccess;

import models.Announcement;
import models.Bell;
import models.Day;
import models.TimeTableBell;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BellDAO {

    public BellDAO() {
    }

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Bell.class).
                        addAnnotatedClass(TimeTableBell.class).
                        addAnnotatedClass(Day.class).
                        buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void createBell(Bell bell){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            session.save(bell);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }


    public Bell getBellById(int id){
        Session session = getHibernateSession();
        Bell bell = null;
        try {
            session.beginTransaction();

            bell = session.get(Bell.class, id);

            session.getTransaction().commit();

        }finally {
            session.close();
        }
        return bell;
    }

    public List<Bell> getAllBells(){
        Session session = getHibernateSession();
        List<Bell> bell = null;
        try {
            session.beginTransaction();

            bell =  session.createCriteria(Bell.class).list();

            session.getTransaction().commit();

        }finally {
            session.close();
        }
        return bell;
    }

    public void updateBellById(Bell newBell,int id){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            Bell bell = session.get(Bell.class, id);

            bell.setBellOfDay(newBell.getBellOfDay());

            bell.setLabel(newBell.getLabel());

            session.getTransaction().commit();

        }finally {
            session.close();
        }
    }

    public void deleteBellById(int id){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            session.delete(session.get(Bell.class, id));

            session.getTransaction().commit();

        }finally {
            session.close();
        }
    }
}
