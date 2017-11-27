package gui;

import static org.junit.Assert.*;

import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import answer.TextAnswer;
import assessment.Assessment;
import assessment.SelectedAssessment;
import course.Course;
import course.SelectedCourse;
import login.ProfessorLogin;
import login.SelectedUser;

public class StudentCompleteAssessmentTest {

	@Override
	protected void onSetUp() {
		// set up student user
		StudentLogin student = new StudentLogin(1, "student", "password");
		SelectedUser.setUser(student);
		// set up course
		Course course = new Course(2, "CSCC01H5F", "Software Engineering", "Fall 2017");
		SelectedCourse.setCourse(course);
		// set up assessment
		Assessment assessment = new Assessment(
				3, "Intro to comp sci", "Title vs name", true, null, (float) 0.99);
		SelectedAssessment.setAssess(assessment);
		// set up text question with answer
		TextQuestion text = new TextQuestion(4, "write answer", "just write answer", 5);
		TextAnswer forText = new TextAnswer(4, "answer", true); 
		// set up multiple choice question with answer
		ArrayList<TextAnswer> mcAnswers = new ArrayList<TextAnswer>();
		TextAnswer one = new TextAnswer(5, "option 1", true);
		TextAnswer two = new TextAnswer(5, "option 2", false);
		mcAnswers.add(one);
		mcAnswers.add(two);
		MultQuestion mult = new MultQuestion(5, "pick one", "what is one?", 5, mcAnswers);
		// add questions to assessment
		assessment.addQuestions(text);
		assessment.addQuestions(mult);
		// start form to view assessment
		HHSavedAssessment frame = GuiActionRunner.execute(() -> new HHSavedAssessment());
		frame.setVisible(true);
		window = new FrameFixture(robot(), frame);
	}
	

	@Test
	public void studentAnswerBothCorrect() {
		fail("Not yet implemented");
	}
	
	@Test
	public void studentAnswerBothWrong() {
		fail("Not yet implemented");
	}
	

	@Test
	public void studentAnswerTextCorrectMultWrong() {
		fail("Not yet implemented");
	}
	
	@Test
	public void studentAnswerTextWrongMultCorrect() {
		fail("Not yet implemented");
	}

}

