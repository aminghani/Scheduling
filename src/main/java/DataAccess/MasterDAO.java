package DataAccess;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class MasterDAO {

    public MasterDAO(){

    }
    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Master.class).
                        addAnnotatedClass(Course.class).
                        addAnnotatedClass(TimeTable.class).
                        addAnnotatedClass(TimetableBell.class).
                        addAnnotatedClass(Bell.class).
                        addAnnotatedClass(Day.class).
                        addAnnotatedClass(Student.class).
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

        Master master = null;

        try{
            session.beginTransaction();

            master = session.get(Master.class, id);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return master;
    }

    public List<Master> getAllMasters(String name){
        Session session = getHibernateSession();
        List<Master> masters = null;
        try {
            if(name == null){
                session.beginTransaction();

                masters =  session.createCriteria(Master.class).list();

                session.getTransaction().commit();
            }
            else{
                session.beginTransaction();

                Query query = session.createQuery("FROM Master M WHERE M.lastname=:theLastName");

                query.setParameter("theLastName",name);

                masters = query.list();

                session.getTransaction().commit();
            }
        }finally {
            session.close();
        }
        return masters;
    }

    public void changeMasterById(Master newMaster,int id){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            Master master = session.get(Master.class,id);

            master.setFirstName(newMaster.getFirstName());
            master.setLastname(newMaster.getLastname());
            master.setCode(newMaster.getCode());
            master.setPassword(newMaster.getPassword());
            master.setCourses(newMaster.getCourses());

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public void addMasters(List<Master> masters){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            for(Master master:masters){
                session.save(master);
            }

            session.getTransaction().commit();
        }finally {
            session.close();
        }

    }


}
