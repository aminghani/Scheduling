package DataAccess;

import Algorithm.Schedule;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeTableDAO {

    public TimeTableDAO(){

    }

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Master.class).
                        addAnnotatedClass(Course.class).
                        addAnnotatedClass(TimeTableBell.class).
                        addAnnotatedClass(TimeTable.class).
                        addAnnotatedClass(Bell.class).
                        addAnnotatedClass(Day.class).
                        addAnnotatedClass(Student.class).
                        buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void addTimeTable(Master master, Course course, List<TimeTableBell> timetableBells){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            TimeTable timeTable = new TimeTable();
            timeTable.setMaster(master);
            timeTable.setCourse(course);
            for(TimeTableBell timetableBell:timetableBells){
                timeTable.addTimeTableBell(timetableBell);
            }

            session.save(timeTable);

            session.getTransaction().commit();

        }finally {
            session.close();
        }
    }

    public List<TimeTable> getTimeTables(String StudentId,String CourseId,String MasterId){
        Session session = getHibernateSession();
        List<TimeTable> timeTables = null;

        try {
            if (StudentId != null) {
                int StudentIdTemp = Integer.valueOf(StudentId);
                session.beginTransaction();

                Query query = session.createQuery("FROM TimeTable T WHERE T.students=:theStudentsIds");

                query.setParameter("theStudentsIds", StudentIdTemp);

                timeTables.addAll(query.list());

                session.getTransaction().commit();
            }
            if (CourseId != null) {
                int CourseIdTemp = Integer.valueOf(CourseId);
                session.beginTransaction();

                Query query = session.createQuery("FROM TimeTable T WHERE T.course=:theCourseIds");

                query.setParameter("theCourseIds", CourseIdTemp);

                timeTables.addAll(query.list());

                session.getTransaction().commit();
            }
            if (MasterId != null) {
                int MasterIdTemp = Integer.valueOf(MasterId);
                session.beginTransaction();

                Query query = session.createQuery("FROM TimeTable T WHERE T.master=:theMasterIds");

                query.setParameter("theMasterIds", MasterIdTemp);

                timeTables.addAll(query.list());

                session.getTransaction().commit();
            }
            if (StudentId == null && CourseId == null && MasterId == null) {
                session.beginTransaction();

                timeTables = session.createCriteria(TimeTable.class).list();

                session.getTransaction().commit();
            }
        }finally {
            session.close();
        }
        return timeTables;
    }

    public void addStudentToTimeTable(int timeTableId,int studentId){
        Session session = getHibernateSession();

        try{
            session.beginTransaction();

            TimeTable timeTable = getTimeTableById(timeTableId);

            Student student = new StudentDAO().getStudentById(studentId);

            timeTable.addStudent(student);

            session.saveOrUpdate(timeTable);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public TimeTable getTimeTableById(int id){
        Session session = getHibernateSession();
        TimeTable timeTable = null;

        try{
            session.beginTransaction();

            timeTable = session.get(TimeTable.class,id);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return timeTable;
    }

    public List<TimeTable> process(int rooms){
        Session session = getHibernateSession();

        List<TimeTable> timeTables = null;

        try {
            session.beginTransaction();

            List<TimeTableBell> timeTableBells = new TimeTableBellDAO().getAllTimeTableBells();

            List<Course> courses = new CourseDAO().getAllCourses(null);

            timeTables = new Schedule(timeTableBells,courses,rooms).run();

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return timeTables;
    }
}
