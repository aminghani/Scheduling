package DataAccess;

import models.Admin;
import models.Master;
import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDAO {

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void createAdmin(Admin admin){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            session.save(admin);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public List<Admin> getAllAdmins(){
        Session session = getHibernateSession();
        List<Admin> admins = null;
        try {
            session.beginTransaction();

            admins =  session.createCriteria(Admin.class).list();

            session.getTransaction().commit();

        }finally {
            session.close();
        }
        return admins;
    }

    public Admin findByUsername2(String code){
        Session session = getHibernateSession();
        Admin admin = null;
        try {
            session.beginTransaction();
            List<Admin> admins;
            admins= session.createCriteria(Admin.class).list();
            session.getTransaction().commit();
            for(Admin admin1:admins){
                if(admin1.getCode().equals(code)){
                    return admin1;
                }
            }
        }finally {
            session.close();
        }
        return null;
    }
}
