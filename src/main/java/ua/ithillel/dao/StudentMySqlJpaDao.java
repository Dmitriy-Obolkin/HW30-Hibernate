package ua.ithillel.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.ithillel.exception.StudentNotFoundException;
import ua.ithillel.service.HibernateTransactionManager;
import ua.ithillel.model.entity.Student;

import java.util.List;

public class StudentMySqlJpaDao implements StudentDao {
    private final HibernateTransactionManager transactionManager;

    public StudentMySqlJpaDao(SessionFactory sessionFactory){
        this.transactionManager = new HibernateTransactionManager(sessionFactory);
    }

    @Override
    public Student save(Student student) {
        return transactionManager.executeInsideTransaction(session -> {
            session.persist(student);
            session.flush();
            return student;
        });
    }

    @Override
    public List<Student> findAll() {
        return transactionManager.executeInsideTransaction(session -> {
            Query<Student> query = session.createQuery("from Student", Student.class);
            return query.getResultList();
        });
    }

    @Override
    public Student findById(int id) {
        return transactionManager.executeInsideTransaction(session ->
                session.find(Student.class, id));
    }

    @Override
    public boolean remove(Student student) {
        if(student == null || student.getId() == null){
            throw new IllegalArgumentException("Student object or its ID cannot be null");
        }

        return transactionManager.executeInsideTransaction(session -> {
            session.remove(student);
            return true;
        });
    }

    @Override
    public boolean remove(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("ID must be greater than 0");
        }

        Student existingStudent = findById(id);
        if(existingStudent == null){
            throw new StudentNotFoundException(id);
        }

        remove(existingStudent);
        return true;
    }

    @Override
    public Student update(int id, Student newStudent) {
        return transactionManager.executeInsideTransaction(session -> {
            if (id <= 0 || newStudent == null) {
                throw new IllegalArgumentException("Invalid ID or Student object");
            }

            Student existingStudent = findById(id);

            if (existingStudent == null) {
                throw new StudentNotFoundException(id);
            }

            existingStudent.setName(newStudent.getName());
            existingStudent.setEmail(newStudent.getEmail());

            session.merge(existingStudent);
            session.flush();

            return existingStudent;
        });
    }
}
