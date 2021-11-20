package se.iths.entity;

import javax.persistence.*;

@NamedQuery(name = "subjectEntity.findAll", query = "select s from Subject s")
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String category;


    public Subject(String title, String category, Student student) {
        this.title = title;
        this.category = category;
        this.student = student;
    }

    public Subject(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @ManyToOne
    private Student student;

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    private Teacher teacher;

    public Teacher getTeacher(){
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
