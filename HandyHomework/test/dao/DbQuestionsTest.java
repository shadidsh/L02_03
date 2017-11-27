package dao;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import question.TextQuestion;

public class DbQuestionsTest {
	private DbUser dbUser;
	private DbCourse dbCourse;
	private DbAssessment dbAssessment;
	private DbQuestions dbQuestions;
	private int pid;
	private int cid;
	private int aid;
	private int qid;
	private Calendar due;
	
	@Before
	public void setup() {
		dbUser = new DbUser();
		dbCourse = new DbCourse();
		dbAssessment = new DbAssessment();
		dbQuestions = new DbQuestions();
		due = Calendar.getInstance();
		due.set(2017, 9, 25, 10, 05, 30);
		if (! dbUser.userExists("myProf3")) {
			pid = dbUser.addUser("myProf3", "123", true);
			cid = dbCourse.insertCourses(pid, "CSCC7399", "Algorithm design and analysis9", "Fall2019");
			aid = dbAssessment.insertAssessment("Assessme1nt3", cid, "Dynam1ic Programming", due, true, (float) 0.5);
			qid = dbQuestions.insertQuestions(aid, "Quic1k", "2+2", 100, false, false);
		}
	}
	
	@Test
	public void testTextQuestions() {
		java.util.List<TextQuestion> TQ = dbQuestions.TextQuestions(aid);
		assertFalse(TQ.isEmpty());
	}
	@Test
	public void testRemoveQuestionsForAssessments() {
		dbQuestions.removeQuestionsForAssessments(aid);
		assertFalse(dbQuestions.hasTextQuestions(aid));
	}
	
	@Test
	public void testRemoveQuestion() {
		dbQuestions.removeQuestion(qid);
		assertFalse(dbQuestions.hasTextQuestions(aid));
	}
	
	@Test
	public void testInsertAnswers() {
		dbQuestions.insertAnswers(qid, true, "4");
		assertEquals("4", dbQuestions.singleAnswerQuestion(qid).getAnswer());
	}
	
	@Test 
	public void testHasTextQuestion() {
		assertTrue(dbQuestions.hasTextQuestions(aid));
	}
	
	@After
	public void tearDown() {
		dbUser.removeUser("myProf3");
		dbQuestions.removeQuestion(qid);
		dbAssessment.removeAssessment(aid);
		dbCourse.removeCourse(cid);		
	}
}
