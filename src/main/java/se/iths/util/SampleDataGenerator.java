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

        Subject subject1 = new Subject("Den nya affärsredovisningen: lösningar ", "Math & Statistics");
        Subject subject2 = new Subject("Genki: An Integrated Course in Elementary Japanese I Workbook [third Edition] ", "Language");
        Subject subject3 = new Subject("Human Rights: A Very Short Introduction", "Religion & Philosophy");
        Subject subject4 = new Subject("Clean Code: A Handbook Of Agile Software Craftsmanship", "IT & Data Science");
        Subject subject5 = new Subject("The Age of Migration", "History");


        Teacher teacher1 = new Teacher("Jill", "Johnson", "jill.johnson@mail.com", "465789416");
        Teacher teacher2 = new Teacher("Adam", "Jensen", "adam.jensen@mail.com", "7654489633");
        Teacher teacher3 = new Teacher("Chris", "Edison", "chris.edison@mail.com", "626545654");
        Teacher teacher4 = new Teacher("Lucy", "Valentine", "lucy.valentine@mail.com", "526316845");
        Teacher teacher5 = new Teacher("Tusse", "Anderson", "tusse.anderson@mail.com", "265498451");

        student1.addSubject(subject5);
        student1.addSubject(subject3);
        student1.addSubject(subject1);
        student2.addSubject(subject4);
        student2.addSubject(subject2);
        student2.addSubject(subject5);
        student3.addSubject(subject4);
        student3.addSubject(subject2);
        student3.addSubject(subject1);
        student4.addSubject(subject4);
        student4.addSubject(subject1);
        student4.addSubject(subject3);
        student5.addSubject(subject1);
        student5.addSubject(subject2);
        student5.addSubject(subject5);
        student6.addSubject(subject1);
        student6.addSubject(subject3);
        student6.addSubject(subject5);
        student7.addSubject(subject2);
        student7.addSubject(subject1);
        student7.addSubject(subject4);
        student8.addSubject(subject2);
        student8.addSubject(subject5);
        student8.addSubject(subject3);
        student9.addSubject(subject2);
        student9.addSubject(subject4);
        student9.addSubject(subject1);
        student10.addSubject(subject5);
        student10.addSubject(subject3);
        student10.addSubject(subject1);

        teacher1.addSubject(subject4);
        teacher2.addSubject(subject1);
        teacher3.addSubject(subject2);
        teacher4.addSubject(subject5);
        teacher5.addSubject(subject3);

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(student4);
        entityManager.persist(student5);
        entityManager.persist(student6);
        entityManager.persist(student7);
        entityManager.persist(student8);
        entityManager.persist(student9);
        entityManager.persist(student10);

        entityManager.persist(teacher1);
        entityManager.persist(teacher2);
        entityManager.persist(teacher3);
        entityManager.persist(teacher4);
        entityManager.persist(teacher5);
    }
}
