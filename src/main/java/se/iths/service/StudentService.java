package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void addStudent(Student student){
        entityManager.persist(student);
    }

    public Student findStudentById(Long id){
        return entityManager.find(Student.class, id);
    }

    public void updateStudent(Student student){
        entityManager.merge(student);
    }

    public void deleteStudent(Long id){
        Student foundStudent = entityManager.find(Student.class, id);
        entityManager.remove(foundStudent);
    }

    public List<Student> getAllStudents(){
        return entityManager.createQuery("select s from Student s", Student.class).getResultList();
    }
}
