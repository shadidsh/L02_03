package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assessment.Assessment;

public class DbAssessmentTest {
	private DbUser dbUser;
	private DbCourse dbCourse;
	private DbAssessment dbAssessment;
	private int pid;
	private int cid;
	private int aid;
	private Calendar due;
	@Before
	public void setup() {
		dbUser = new DbUser();
		dbCourse = new DbCourse();
		dbAssessment = new DbAssessment();
		due = Calendar.getInstance();
		due.set(2017, 9, 25, 10, 05, 30);
		if (! dbUser.userExists("crazyProf")) {
			pid = dbUser.addUser("crazyProf", "123", true);
			cid = dbCourse.insertCourses(pid, "CSCC01", "Introduction to Software Engineering", "Fall");
			aid = dbAssessment.insertAssessment("Assessment1", cid, "Dynamic Programming", due, true, (float) 0.5);
		}
	}
	
	@Test
	public void testInsertAssessment() {
		dbAssessment.insertAssessment("Assesment2", cid, "Greedy Algorithm", due, true, (float) 0.99);
		ArrayList<Assessment> assessList = (ArrayList<Assessment>) dbAssessment.getAssessmentForCourse(cid);
		for(Assessment assess: assessList) {
			if(assess.getName().equals("Assessment2")) {
				assertTrue(assessList.contains(assess));
			}
		}
	}
	
	@Test
	public void testGetAssessmentForCourse() {
		dbAssessment.insertAssessment("Assesment2", cid, "Greedy Algorithm", due, true, (float) 0.99);
		ArrayList<Assessment> assessList = (ArrayList<Assessment>) dbAssessment.getAssessmentForCourse(cid);
		for(Assessment assess: assessList) {
			assertTrue(assessList.contains(assess));
		}
	}

	@Test
	public void testRemoveAssessment() {
		int aid2 = dbAssessment.insertAssessment("Assesment2", cid, "Greedy Algorithm", due, true, (float) 0.99);
		ArrayList<Assessment> assessList = (ArrayList<Assessment>) dbAssessment.getAssessmentForCourse(cid);
		dbAssessment.removeAssessment(aid2);
		for(Assessment assess: assessList) {
			if(assess.getName().equals("Assessment2")) {
				assertFalse(assessList.contains(assess));
			}
		}	
	}
	
	@Test
	public void testRemoveAssessmentForCourse() {
		dbAssessment.insertAssessment("Assesment2", cid, "Greedy Algorithm", due, true, (float) 0.99);
		dbAssessment.removeAssessmentForCourse(cid);
		assertTrue(dbAssessment.getAssessmentForCourse(cid).isEmpty());
	}
	
	@After
	public void tearDown() {
		dbCourse.removeCourse(cid);
		dbUser.removeUser("crazyProf");
		dbAssessment.removeAssessment(aid);
	}
}
