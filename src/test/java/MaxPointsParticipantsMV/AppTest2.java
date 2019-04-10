package MaxPointsParticipantsMV;

import domain.Pair;
import domain.Student;
import domain.Tema;
import domain.Nota;
import org.junit.Test;
import repository.StudentFileRepository;
import repository.TemaFileRepository;
import repository.NotaFileRepository;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.NotaValidator;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest2
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    @Test
    public void addStudentTest(){
        StudentValidator validator = new StudentValidator();
        StudentFileRepository repo = new StudentFileRepository(validator,"E:\\Lecture02\\MaxPointsParticipants\\files\\studenti.txt");
        Student s4 = new Student("Test","Mircea Ioan",932);
        repo.save(s4);
        assertEquals(repo.findOne("Test").getGrupa(),932);

    }

    @Test
    public void addTheme(){
        TemaValidator validator = new TemaValidator();
        TemaFileRepository repo = new TemaFileRepository(validator,"E:\\Lecture02\\MaxPointsParticipants\\files\\tema.txt");
        Tema t1 = new Tema("1","ceva",2,1);
        repo.save(t1);
        assertEquals(repo.findOne("1").getDescriere(),"ceva");
    }

    @Test
    public void addNota(){
        NotaValidator validator = new NotaValidator();
        NotaFileRepository repo = new NotaFileRepository(validator,"E:\\Lecture02\\MaxPointsParticipants\\files\\nota.txt");
        Nota s4 = new Nota(new Pair<>("Test","1"),5.5,3,"good");
        repo.save(s4);
        assertEquals(repo.findOne(new Pair<>("Test","1")).getNota(),5.5);
    }

    @Test
    public void addAll(){
	StudentValidator validator = new StudentValidator();
        StudentFileRepository repo = new StudentFileRepository(validator,"E:\\Lecture02\\MaxPointsParticipants\\files\\studenti.txt");
        Student s1 = new Student("Test1","Mircea Ioan",932);
	repo.save(s1);
	TemaValidator validatort = new TemaValidator();
        TemaFileRepository repot = new TemaFileRepository(validatort,"E:\\Lecture02\\MaxPointsParticipants\\files\\tema.txt");
        Tema t1 = new Tema("1","ceva",2,1);
	repot.save(t1);
        NotaValidator validatorn= new NotaValidator();
        NotaFileRepository repon = new NotaFileRepository(validatorn,"E:\\Lecture02\\MaxPointsParticipants\\files\\nota.txt");
        Nota n1 = new Nota(new Pair<>("Test1","2"),5.5,3,"good");
        repon.save(n1);
	assertEquals(repo.findOne("Test1").getGrupa(),932);
	assertEquals(repot.findOne("1").getDescriere(),"ceva");
        assertTrue(repon.findOne(new Pair<>("Test1","2")).getNota()==5.5);
    }	
}