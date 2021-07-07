package DataAccess;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAO {

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(TimeTable.class)
                .addAnnotatedClass(Master.class)
                .addAnnotatedClass(TimetableBell.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Bell.class)
                .addAnnotatedClass(Day.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public void createStudent(Student student){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            session.save(student);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public List<Student> getAllStudents(String name){
        Session session = getHibernateSession();
        List<Student> students = null;
        try {
            if(name == null){
                session.beginTransaction();

                students =  session.createCriteria(Student.class).list();

                session.getTransaction().commit();

            }
            else{
                session.beginTransaction();

                Query query = session.createQuery("FROM Student S WHERE S.lastname=:theLastName");

                query.setParameter("theLastName",name);

                students = query.list();

                session.getTransaction().commit();
            }

        }finally {
            session.close();
        }
        return students;
    }

    public Student getStudentById(int id){
        Session session = getHibernateSession();

        Student student = null;

        try{
            session.beginTransaction();

            student = session.get(Student.class, id);

            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return student;
    }

    public void changeStudentById(Student newStudent,int id){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            Student student = session.get(Student.class,id);

            student.setFirstName(newStudent.getFirstName());
            student.setLastname(newStudent.getLastname());
            student.setCode(newStudent.getCode());
            student.setPassword(newStudent.getPassword());
            student.setTimeTables(newStudent.getTimeTables());

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public void addStudents(List<Student> students){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            for(Student student:students){
                session.save(student);
            }

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

}
