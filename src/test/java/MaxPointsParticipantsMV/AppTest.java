package MaxPointsParticipantsMV;

import domain.Student;
import domain.Tema;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import repository.*;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

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

    @Test
    public void addTheme1(){
        TemaValidator validator = new TemaValidator();
        TemaFileRepository repo = new TemaFileRepository(validator,"E:\\Lecture02\\MaxPointsParticipants\\files\\tema.txt");
        Tema t1 = new Tema("1","ceva",2,1);
        repo.save(t1);
        assertEquals(repo.findOne("1").getDescriere(),"ceva");
    }

    @Test
    public void addTheme2(){
        TemaValidator validator = new TemaValidator();
        TemaFileRepository repo = new TemaFileRepository(validator,"E:\\Lecture02\\MaxPointsParticipants\\files\\tema.txt");
        Tema t2 = new Tema("2","",3,1);
        repo.save(t2);
        assertEquals(repo.findOne("2"),null);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAssignmentNumberValid(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        // add assignment with a null id

        assertEquals(1, service.saveTema("1","descriere",3,4));

        thrown.expect(ValidationException.class);

        thrown.expectMessage("Numar tema invalid!\n");

    }


    @Test
    public void testAssignmentNumberInvalidEmpty(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        thrown.expect(ValidationException.class);

        thrown.expectMessage("Numar tema invalid!\n");

        assertEquals(0, service.saveTema("","descriere",3,4));


    }

    @Test
    public void testAssignmentNumberInvalidNull(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        thrown.expect(ValidationException.class);

        thrown.expectMessage("Numar tema invalid!\n");

        assertEquals(0, service.saveTema(null,"descriere",3,4));


    }

    @Test
    public void testAddDescriereValid(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        assertEquals(1, service.saveTema("123","descriere",3,2));
    }

    @Test
    public void testAddDescriereInvalidEmpty(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        thrown.expect(ValidationException.class);
        thrown.expectMessage("Descriere invalida!\n");

        assertEquals(0, service.saveTema("123","",3,2));


    }

    @Test
    public void testAddDescriereInvalidNull(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        thrown.expect(ValidationException.class);
        thrown.expectMessage("Descriere invalida!\n");

        assertEquals(0, service.saveTema("123",null,3,2));


    }

    @Test
    public void testAddDeadlineValidEqualLower(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        assertEquals(1,service.saveTema("1234","descriere",1,1));
    }

    @Test
    public void testAddDeadlineValidHigherLower(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        assertEquals(1,service.saveTema("1234","descriere",3,2));
    }

    @Test
    public void testAddDeadlineValidEqualHigher(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        assertEquals(1,service.saveTema("1234","descriere",14,1));
    }

    @Test
    public void testAddDeadlineValidLowerHigher(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        assertEquals(1,service.saveTema("1234","descriere",13,1));
    }


    @Test
    public void testAddDeadlineInvalidLower() {
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator, "files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator, "files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator, "files/note.txt");

        Service service = new Service(studentRepo, temaRepo, notaRepo);

        thrown.expect(ValidationException.class);
        thrown.expectMessage("Deadlineul trebuie sa fie intre 1-14.\n");

        assertEquals(0,service.saveTema("1234", "descriere",0,1));
    }

    @Test
    public void testAddDeadlineLowerStart(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        thrown.expect(ValidationException.class);
        thrown.expectMessage("Deadlineul trebuie sa fie intre 1-14.\n");

        assertEquals(0,service.saveTema("1234", "descriere",1,2));
    }


    @Test
    public void testAddDeadlineHigher(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        thrown.expect(ValidationException.class);
        thrown.expectMessage("Deadlineul trebuie sa fie intre 1-14.\n");

        assertEquals(0,service.saveTema("1234", "descriere",15,2));
    }

    @Test
    public void testAddDeadlineEqualStart(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        thrown.expect(ValidationException.class);
        thrown.expectMessage("Deadlineul trebuie sa fie intre 1-14.\n");

        assertEquals(1,service.saveTema("1234", "descriere",2,2));
    }

    @Test
    public void testAddStartlineValidEqualLower(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        assertEquals(1,service.saveTema("1234","descriere",1,1));
    }

    @Test
    public void testAddStartlineValidHigherLower(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        assertEquals(1,service.saveTema("1234","descriere",3,2));
    }

    @Test
    public void testAddStartlineValidEqualHigher(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        assertEquals(1,service.saveTema("1234","descriere",14,14));
    }

    @Test
    public void testAddStartlineValidLowerHigher(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        assertEquals(1,service.saveTema("1234","descriere",14,13));
    }


    @Test
    public void testAddStartlineInvalidLower() {
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator, "files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator, "files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator, "files/note.txt");

        Service service = new Service(studentRepo, temaRepo, notaRepo);

        thrown.expect(ValidationException.class);
        thrown.expectMessage("Deadlineul trebuie sa fie intre 1-14.\n");

        assertEquals(0,service.saveTema("1234", "descriere",1,0));
    }

    @Test
    public void testAddStartlineHigherDead(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        thrown.expect(ValidationException.class);
        thrown.expectMessage("Deadlineul trebuie sa fie intre 1-14.\n");

        assertEquals(0,service.saveTema("1234", "descriere",1,2));
    }


    @Test
    public void testAddStartlineHigher(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        thrown.expect(ValidationException.class);
        thrown.expectMessage("Deadlineul trebuie sa fie intre 1-14.\n");

        assertEquals(0,service.saveTema("1234", "descriere",10,15));
    }

    @Test
    public void testAddStartlineEqualDead(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator,"files/tema.txt");

        StudentValidator studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator,"files/studenti.txt");

        NotaValidator notaValidator = new NotaValidator();
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator,"files/note.txt");

        Service service = new Service(studentRepo,temaRepo,notaRepo);

        thrown.expect(ValidationException.class);
        thrown.expectMessage("Deadlineul trebuie sa fie intre 1-14.\n");

        assertEquals(1,service.saveTema("1234", "descriere",2,2));
    }
}