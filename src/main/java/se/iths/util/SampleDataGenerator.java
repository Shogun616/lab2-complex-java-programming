package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData(){
        Student student1 = new Student("Sara", "Hamilton", "sara.hamilton@mail.com", "135684565");
        Student student2 = new Student("Ed", "Thomas", "ed.thomas@mail.com", "135684565");
        Student student3 = new Student("Jason", "Hamilton", "jason.hamilton@mail.com", "135684565");
        Student student4 = new Student("Lisa", "Smith", "lisa.smith@mail.com", "135684565");
        Student student5 = new Student("John", "Smith", "john.smith@mail.com", "135684565");
        Student student6 = new Student("Mathilda", "Hamilton", "mathilda.hamilton@mail.com", "135684565");
        Student student7 = new Student("Robert", "Thomas", "robert.thomas@mail.com", "135684565");
        Student student8 = new Student("Eva", "Hamilton", "eva.hamilton@mail.com", "135684565");
        Student student9 = new Student("Veronika", "Smith", "veronika.smith@mail.com", "135684565");
        Student student10 = new Student("Albert", "Wesker", "albert.wesker@mail.com", "135684565");

        Subject subject1 = new Subject("as", "Math & Statistics");
        Subject subject2 = new Subject("sa", "Language");
        Subject subject3 = new Subject("", "Religion & Philosophy");
        Subject subject4 = new Subject("", "IT & Data Science");
        Subject subject5 = new Subject("", "History");


        Teacher teacher1 = new Teacher("", "", "", "");
        Teacher teacher2 = new Teacher("", "", "", "");
        Teacher teacher3 = new Teacher("", "", "", "");
        Teacher teacher4 = new Teacher("", "", "", "");
        Teacher teacher5 = new Teacher("", "", "", "");


//        entityManager.persist(student1);
//        entityManager.persist(student2);
//        entityManager.persist(student3);
//        entityManager.persist(student4);
//        entityManager.persist(student5);
//        entityManager.persist(student6);
//        entityManager.persist(student7);
//        entityManager.persist(student8);
//        entityManager.persist(student9);
//        entityManager.persist(student10);
    }
}
