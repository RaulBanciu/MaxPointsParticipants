package MaxPointsParticipantsMV;

import domain.Student;
import org.junit.Test;
import repository.StudentFileRepository;
import validation.StudentValidator;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void addStudentTestId(){
        StudentValidator validator = new StudentValidator();
        StudentFileRepository repo = new StudentFileRepository(validator,"E:\\Lecture02\\MaxPointsParticipants\\files\\studenti.txt");
        Student s1 = new Student("0", "Ion Creanga",931);
        Student s2 = new Student("","Ion Creanga",931);
        Student s3 = new Student(null,"Ion Creanga",931);
        Student s4 = new Student("Test","Mircea Ioan",932);
        repo.save(s1);
        repo.save(s2);
        repo.save(s3);
        repo.save(s4);
        assertEquals(repo.findOne("0").getGrupa(),931);
        assertEquals(repo.findOne(""),null);
        assertEquals(repo.findOne("Test").getGrupa(),932);

    }

    @Test
    public void addStudentTestGrupa(){
        StudentValidator validator = new StudentValidator();
        StudentFileRepository repo = new StudentFileRepository(validator,"E:\\Lecture02\\MaxPointsParticipants\\files\\studenti.txt");
        Student s1 = new Student("1", "Ion Creanga",931);
        Student s2 = new Student("2","Ion Creanga",12);
        Student s3 = new Student("3","Ion Creanga",1234);
        Student s4 = new Student("4","Ion Creanga",148);
        repo.save(s1);
        repo.save(s2);
        repo.save(s3);
        repo.save(s4);
        assertEquals(repo.findOne("1").getGrupa(),931);
        assertEquals(repo.findOne("2"),null);
        assertEquals(repo.findOne("3"),null);
        assertEquals(repo.findOne("4").getGrupa(),148);

    }
    @Test
    public void addStudentTestName(){

        StudentValidator validator = new StudentValidator();
        StudentFileRepository repo = new StudentFileRepository(validator,"E:\\Lecture02\\MaxPointsParticipants\\files\\studenti.txt");
        Student s1 = new Student("6", "Bizonul",932);
        Student s2 = new Student("8","",934);
        repo.save(s1);
        repo.save(s2);
        assertEquals(repo.findOne("6").getNume(),"Bizonul");
        assertEquals(repo.findOne("8"),null);
    }
}