package DataAccess;

import com.example.scheduling.Controllers.util.Encoder;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAO{

    private Session getHibernateSession(){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(TimeTable.class)
                .addAnnotatedClass(Master.class)
                .addAnnotatedClass(TimeTableBell.class)
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

        //    student.setPassword(new Encoder().bCryptPasswordEncoder().encode(student.getPassword()));

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

            student.setFirstname(newStudent.getFirstname());
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
          //      student.setPassword(new Encoder().bCryptPasswordEncoder().encode(student.getPassword()));

                session.save(student);
            }

            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    public void deleteStudentById(int id){
        Session session = getHibernateSession();

        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from Student where id=:theId");
            query.setParameter("theId",id);
            query.executeUpdate();

            session.getTransaction().commit();

        }finally {
            session.close();
        }
    }


    public Student findByUsername(String code){
        Session session = getHibernateSession();
        Student student = null;
        try{
            session.beginTransaction();

            Query query = session.createQuery("FROM Student S WHERE S.code=:theCode");

            query.setParameter("theCode",code);

            student = (Student) query.getSingleResult();

            session.getTransaction().commit();

        }finally {
            session.close();
        }

        return student;
    }

    public Student findByUsername2(String code){
        Session session = getHibernateSession();
        Student student = null;
        try {
            session.beginTransaction();
            List<Student> students;
            students= session.createCriteria(Student.class).list();
            session.getTransaction().commit();
            for(Student student1:students){
                if(student1.getCode().equals(code)){
                    return student1;
                }
            }
        }finally {
            session.close();
        }
        return null;
    }
}
