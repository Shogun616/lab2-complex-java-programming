package se.iths.service;

import se.iths.entity.Subject;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void addSubject(Subject subject){
        entityManager.persist(subject);
    }

    public Subject findSubjectById(Long id){
        return entityManager.find(Subject.class, id);
    }

    public void updateSubject(Subject subject){
        entityManager.merge(subject);
    }

    public void deleteSubject(Long id){
        Subject foundSubject = entityManager.find(Subject.class, id);
        entityManager.remove(foundSubject);
    }

    public List<Subject> getAllSubjects(){
        return entityManager.createQuery("select s from Subject s", Subject.class).getResultList();
    }
}
