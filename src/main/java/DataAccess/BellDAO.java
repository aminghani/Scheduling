package DataAccess;

import models.Bell;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BellDAO {

    public BellDAO() {
    }

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Bell.class).
                        buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void createBell(String label,int bellOfDay){

        Session session = getHibernateSession();

        try {

            session.beginTransaction();

            session.save(new Bell(label,bellOfDay));

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
}
