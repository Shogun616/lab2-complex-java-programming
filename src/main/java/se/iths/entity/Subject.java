package se.iths.entity;

import javax.persistence.*;

@NamedQuery(name = "subjectEntity.findAll", query = "select s from Subject s")
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @ManyToOne
    private Student student;

    public Subject(){}

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void add(Subject subject) {
    }
}
