package se.iths.service;

import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public void addTeacher(Teacher teacher){
        entityManager.persist(teacher);
    }

    public Teacher findTeacherById(Long id){
        return entityManager.find(Teacher.class, id);
    }

    public void updateTeacher(Teacher teacher){
        entityManager.merge(teacher);
    }

    public void deleteTeacher(Long id){
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        entityManager.remove(foundTeacher);
    }

    public List<Teacher> getAllTeachers(){
        return entityManager.createQuery("select t from Teacher t", Teacher.class).getResultList();
    }
}
