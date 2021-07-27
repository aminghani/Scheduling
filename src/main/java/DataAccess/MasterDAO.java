package DataAccess;

import com.example.scheduling.Controllers.util.Encoder;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class MasterDAO {

    public MasterDAO(){

    }
    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Master.class).
                        addAnnotatedClass(Course.class).
                        addAnnotatedClass(TimeTable.class).
                        addAnnotatedClass(TimeTableBell.class).
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

    //        master.setPassword(new Encoder().bCryptPasswordEncoder().encode(master.getPassword()));

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

            master.setFirstname(newMaster.getFirstname());
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
     //           master.setPassword(new Encoder().bCryptPasswordEncoder().encode(master.getPassword()));

                session.save(master);
            }

            session.getTransaction().commit();
        }finally {
            session.close();
        }

    }

    public void deleteMasterById(int id){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from Master where id=:theId");
            query.setParameter("theId",id);
            query.executeUpdate();

            session.getTransaction().commit();

        }finally {
            session.close();
        }
    }

    public Master findByUsername(String code){
        Session session = getHibernateSession();
        Master master = null;
        try{
            session.beginTransaction();

            Query query = session.createQuery("FROM Master M WHERE M.code=:theCode");

            query.setParameter("theCode",code);

            master = (Master) query.getSingleResult();

            session.getTransaction().commit();

        }finally {
            session.close();
        }

        return master;
    }

    public Master findByUsername2(String code){
        Session session = getHibernateSession();
        Student student = null;
        try {
            session.beginTransaction();
            List<Master> master;
            master= session.createCriteria(Master.class).list();
            session.getTransaction().commit();
            for(Master master1:master){
                if(master1.getCode().equals(code)){
                    return master1;
                }
            }
        }finally {
            session.close();
        }
        return null;
    }

}
