package ua.ithillel.dao;

import ua.ithillel.model.entity.Student;

import java.util.List;

public interface StudentDao {
    Student save(Student student);
    boolean remove(Student student);
    boolean remove(int id);
    Student update(int id, Student newStudent);
    List<Student> findAll();
    Student findById(int id);
}
