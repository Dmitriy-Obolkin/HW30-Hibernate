package ua.ithillel.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ithillel.config.HibernateSession;
import ua.ithillel.model.entity.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentMySqlJpaDaoTest {
    private StudentMySqlJpaDao studentMySqlJpaDao;
    private Student john;
    private Student jane;

    @BeforeEach
    void setup() {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        studentMySqlJpaDao = new StudentMySqlJpaDao(sessionFactory);

        john = Student.builder()
                .name("John")
                .email("John@email.com")
                .build();

        jane = Student.builder()
                .name("Jane")
                .email("jane.d@email.com")
                .build();
    }

    @Test
    public void testSaveStudent() {
        Student savedStudent = studentMySqlJpaDao.save(john);

        assertNotNull(savedStudent);
        assertNotNull(savedStudent.getId());
        assertEquals(john, savedStudent);

        studentMySqlJpaDao.remove(savedStudent);
    }

    @Test
    public void testFindById() {
        Student savedStudent = studentMySqlJpaDao.save(john);
        assertNotNull(savedStudent.getId());

        Student foundStudent = studentMySqlJpaDao.findById(savedStudent.getId());
        assertNotNull(foundStudent);
        System.out.println(foundStudent);

        assertEquals(savedStudent, foundStudent);

        studentMySqlJpaDao.remove(savedStudent);
    }

    @Test
    public void testFindAll() {
        Student savedStudent1 = studentMySqlJpaDao.save(john);
        Student savedStudent2 = studentMySqlJpaDao.save(jane);
        assertNotNull(savedStudent1);
        assertNotNull(savedStudent2);

        List<Student> students = studentMySqlJpaDao.findAll();
        assertNotNull(students);
        System.out.println(students);

        assertTrue(students.contains(savedStudent1));
        assertTrue(students.contains(savedStudent2));

        studentMySqlJpaDao.remove(savedStudent1);
        studentMySqlJpaDao.remove(savedStudent2);
    }

    @Test
    public void testRemoveStudent_success() {
        Student savedStudent = studentMySqlJpaDao.save(jane);
        assertNotNull(savedStudent);

        boolean isDeletedStudent = studentMySqlJpaDao.remove(savedStudent);
        assertTrue(isDeletedStudent);
    }

    @Test
    public void testRemoveStudent_fail() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentMySqlJpaDao.remove(jane)
        );
        assertEquals("Student object or its ID cannot be null", exception.getMessage());
    }

    @Test
    public void testRemoveStudentById() {
        Student savedStudent = studentMySqlJpaDao.save(jane);
        assertNotNull(savedStudent);

        boolean isDeletedStudent = studentMySqlJpaDao.remove(savedStudent.getId());
        assertTrue(isDeletedStudent);
    }

    @Test
    public void testUpdateStudent() {
        Student savedStudent = studentMySqlJpaDao.save(jane);
        assertNotNull(savedStudent);

        savedStudent = studentMySqlJpaDao.update(savedStudent.getId(), john);
        assertNotNull(savedStudent);

        assertEquals(john.getName(), savedStudent.getName());
        assertEquals(john.getEmail(), savedStudent.getEmail());

        studentMySqlJpaDao.remove(savedStudent);
    }
}