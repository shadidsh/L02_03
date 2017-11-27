package dao;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
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
		if (! dbUser.userExists("myProf")) {
			pid = dbUser.addUser("myProf", "123", true);
			cid = dbCourse.insertCourses(pid, "CSCC73", "Algorithm design and analysis", "Fall");
			aid = dbAssessment.insertAssessment("Assessment3", cid, "Dynamic Programming", due, true, (float) 0.5);
			qid = dbQuestions.insertQuestions(aid, "Quick", "2+2", 100, false, false);
		}

		System.out.println(pid);
		System.out.println(cid);
		System.out.println(aid);
		System.out.println(qid);
	}
	@Test
	public void testTextQuestions() {
		ArrayList<TextQuestion> TQ = new ArrayList<TextQuestion>();
		TQ = (ArrayList<TextQuestion>) dbQuestions.TextQuestions(aid);
		assertFalse(TQ.isEmpty());
	}

	public void testInsertQuestions() {
		fail("Not yet implemented");
	}

	public void testSingleAnswerQuestion() {
		fail("Not yet implemented");
	}

	public void testInsertAnswers() {
		fail("Not yet implemented");
	}
	@Test 
	public void testHasTextQuestion() {
		assertTrue(dbQuestions.hasTextQuestions(aid));
	}
	@After
	public void tearDown() {
		dbCourse.removeCourse(cid);
		dbUser.removeUser("myProf");
		dbAssessment.removeAssessment(aid);
		dbQuestions.removeQuestion(qid);
	}
}
