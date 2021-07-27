package DataAccess;


import models.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CourseDAO {
    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Course.class)
                        .addAnnotatedClass(Master.class)
                        .addAnnotatedClass(TimeTable.class)
                        .addAnnotatedClass(TimeTableBell.class)
                        .addAnnotatedClass(Bell.class)
                        .addAnnotatedClass(Day.class)
                        .addAnnotatedClass(Student.class)
                        .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void addCourse(Course course){
        Session session = getHibernateSession();
        try{
            session.beginTransaction();



            session.save(course);

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

    public void addMasterToCourse(int courseId,int masterId){
        Session session = getHibernateSession();

        try{
            session.beginTransaction();

            Course course = getCourseById(courseId);

            Master master = new MasterDAO().getMasterById(masterId);

            course.addMaster(master);

            session.saveOrUpdate(course);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public List<Course> getAllCourses(String unitCount){
        Session session = getHibernateSession();
        List<Course> courses = null;
        try {
            if(unitCount == null){
                session.beginTransaction();

                courses =  session.createCriteria(Course.class).list();

                session.getTransaction().commit();
            }
            else{
                int unit = Integer.valueOf(unitCount);
                session.beginTransaction();

                Query query = session.createQuery("FROM Course C WHERE C.unitsCount=:theUnitCount");

                query.setParameter("theUnitCount",unit);

                courses = query.list();

                session.getTransaction().commit();
            }

        }finally {
            session.close();
        }
        return courses;
    }

    public void updateCourseById(Course newCourse,int id){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            Course course = session.get(Course.class,id);

            course.setTitle(newCourse.getTitle());

            course.setUnitsCount(newCourse.getUnitsCount());

            course.setMasters(newCourse.getMasters());

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public Set<TimeTable> getCourseTimeTablesById(int id){
        Session session = getHibernateSession();
        Set<TimeTable> timeTables = null;

        try{
            session.beginTransaction();

            Course course = session.get(Course.class,id);

            timeTables = course.getTimeTables();

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return timeTables;

    }

    public Set<Master> getCourseMastersById(int id){
        Session session = getHibernateSession();
        Set<Master> masters = null;

        try{
            session.beginTransaction();

            Course course = session.get(Course.class,id);

            masters = course.getMasters();

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return masters;
    }

   public void deleteCourseById(int id){
       Session session = getHibernateSession();

       try {
           session.beginTransaction();

           Query query = session.createQuery("delete from Course where id=:theId");
           query.setParameter("theId",id);
           query.executeUpdate();

           session.getTransaction().commit();

       }finally {
           session.close();
       }
   }

   /*public void test(){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            Master master = new Master("test","test","pass","code");

            Course course = new Course("test", 3);

            master.addCourse(course);

            course.addMaster(master);

        }

   }*/

}
