package sysc4806.graduateAdmissions.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests for the Application object. These tests check that an Application Object can be created correctly.
 * Since we are using lombok to generate the constructors, getters, setters, etc. they are all explicitly tested here.
 *
 * @author Madelyn
 */

class ApplicationTest {

    private Application application;
    private long ID = 5;
    private String applicant = "Jane Doe";
    private Term term = new Term("Tommorrow!", Season.FALL, "2020", true);
    private Department department = Department.SYSC;
    private String degree = "test case";
    private String professors = "Babak";
    private Status status = Status.INCOMPLETE;
    private double gpa = 10.2;
    private String resumeFileName = "resume.pdf";

    @BeforeEach
    public void setUp(){
        application = application.builder().build();
    }

    /**Test to ensure default values of fields of a Application are set up correctly by the no args constructor*/
    @Test
    public void testNoArgsConstructor(){
        assertNotNull(application);
        assertNull(application.getApplicant());
        assertNull(application.getTerm());
        assertNull(application.getDepartment());
        assertNull(application.getDegree());
        assertNull(application.getProfessors());
        assertNull(application.getStatus());
        assertEquals(0, application.getId());
    }

    /**Test to ensure that the all args constructor correctly sets fields*/
    @Test
    public void testArgsConstructor(){
        application = new Application(ID, applicant, term, department, degree, professors, status, gpa, resumeFileName);
        assertEquals(ID, application.getId());
        assertEquals(applicant, application.getApplicant());
        assertEquals(term, application.getTerm());
        assertEquals(department, application.getDepartment());
        assertEquals(degree, application.getDegree());
        assertEquals(professors, application.getProfessors());
        assertEquals(status, application.getStatus());
        assertEquals(gpa, application.getGpa());
        assertEquals(resumeFileName, application.getResumeFileName());
    }

    /**Test to ensure that the builder correctly sets fields*/
    @Test
    public void testBuilder(){
        application = application.builder().id(ID).applicant(applicant).build();
        assertEquals(ID, application.getId());
        assertEquals(applicant, application.getApplicant());
    }

    /**Test that the setID method correctly sets the id field*/
    @Test
    public void setId(){
        application.setId(ID);
        assertEquals(ID, application.getId());
    }

    /**Test that the set applicant method correctly sets the applicant field*/
    @Test
    public void setApplicant(){
        application.setApplicant(applicant);
        assertEquals(applicant, application.getApplicant());
    }

    /**Test that the setTerm method correctly sets the term field*/
    @Test
    public void setTerm(){
        application.setTerm(term);
        assertEquals(term, application.getTerm());
    }

    /**Test that the setDepartment method correctly sets the department field*/
    @Test
    public void setDepartment(){
        application.setDepartment(department);
        assertEquals(department, application.getDepartment());
    }

    /**Test that the generated toString behaves as expected*/
    @Test
    public void testToString(){
        application = new Application(ID, applicant, term, department, degree, professors, status, gpa, resumeFileName);
        assertEquals("Application(id=5, applicant=Jane Doe, term=Term(id=0, active=true, deadline=Tommorrow!, " +
                        "season=FALL, year=2020), department=SYSC, degree=test case, professors=Babak, status=INCOMPLETE," +
                        " gpa=10.2, resumeFileName=resume.pdf)",
                application.toString());
    }

    /**Test that two Application objects with identical fields are considered equal*/
    @Test
    public void testEquals(){
        Application applicationA = new Application(ID, applicant, term, department, degree, professors, status, gpa, resumeFileName);
        Application applicationB = new Application(ID, applicant, term, department, degree, professors, status, gpa, resumeFileName);
        assertEquals(applicationA, applicationB);
    }

    /**Test that two Application objects with all fields equal except their ids are still considered equal*/
    @Test
    public void testEqualsDifferentID(){
        Application applicationA = new Application(ID, applicant, term, department, degree, professors, status, gpa, resumeFileName);
        Application applicationB = new Application(ID+1, applicant, term, department, degree, professors, status, gpa, resumeFileName);
        assertEquals(applicationA, applicationB);
    }

}