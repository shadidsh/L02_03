package course;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import assessment.Assessment;
import course.Course;

public class CourseTest {
	private Course course;
	private ArrayList<Assessment> assessList;
	private Assessment assess;
	private Assessment assess2;
	
	@Before
	public void setUp() throws Exception {
		assessList = new ArrayList<Assessment>();
		Calendar due = Calendar.getInstance();
		due.set(2017, 9, 25, 10, 05, 30);
		course = new Course(0, "Introduction to Software Engineering", "CSCC01", "Fall");
		assess = new Assessment(0, "assessment1", "database", false, due, (float) 0.5);
		assess2 = new Assessment(0, "assessment1", "database", true, due, (float) 0.5);
		assessList.add(assess);
	}

	@Test
	public void testGetCourseCode() {
		String courseCode = course.getCourseCode();
		assertEquals("CSCC01", courseCode);
	}

	@Test
	public void testGetName() {
		String name = course.getName();
		assertEquals("Introduction to Software Engineering", name);
	}

	@Test
	public void testGetTerm() {
		String term = course.getTerm();
		assertEquals("Fall", term);
	}

	@Test
	public void testGetcID() {
		int cID = course.getcID();
		assertEquals(0, cID);
	}

	@Test
	public void testAddAssessment() {
		course.addAssessment(assess);
		assertEquals(assessList, course.getAssessment());
	}
	
	@Test
	public void testAddMultipleAssessments() {
		course.addAssessment(assess);
		course.addAssessment(assess2);
		assessList.add(assess2);
		assertEquals(assessList, course.getAssessment());
	}


}
