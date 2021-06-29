package DataAccess;

import models.Day;
import models.Master;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MasterDAO {

    public MasterDAO(){

    }

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Master.class).
                        buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void createMaster(Master master){
        Session session = getHibernateSession();

        try{
            session.beginTransaction();

            session.save(master);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public Master getMasterById(int id){
        Session session = getHibernateSession();

        Master day = null;

        try{
            session.beginTransaction();

            day = session.get(Master.class, id);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return day;
    }


}
