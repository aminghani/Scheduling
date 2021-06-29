package DataAccess;


import models.Course;
import models.Master;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CourseDAO {
    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Course.class)
                        .addAnnotatedClass(Master.class)
                        .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void addCourse(String title, int unitCounts, List<Master> masters){
        Session session = getHibernateSession();
        try{
            session.beginTransaction();

            Course tempCourse = new Course(title,unitCounts);
            for(Master master:masters){
                tempCourse.addMaster(master);
            }
            session.save(tempCourse);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public Course getCourseById(int id){
        Session session = getHibernateSession();

        Course course = null;
        try{
            session.beginTransaction();

            course = session.get(Course.class,id);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return course;
    }
}
