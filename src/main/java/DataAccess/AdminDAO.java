package DataAccess;

import models.Admin;
import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AdminDAO {

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void createStudent(Admin admin){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            session.save(admin);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }
}
