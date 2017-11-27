package test.gui;

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
import gui.AnswerMultipleChoice;
import login.ProfessorLogin;
import login.SelectedUser;
import login.StudentLogin;
import question.MultQuestion;

public class StudentAnswerMultQuestionTest {

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
		// set up multiple choice question with answer
		ArrayList<TextAnswer> mcAnswers = new ArrayList<TextAnswer>();
		TextAnswer one = new TextAnswer(5, "option 1", true);
		TextAnswer two = new TextAnswer(5, "option 2", false);
		mcAnswers.add(one);
		mcAnswers.add(two);
		MultQuestion mult = new MultQuestion(5, "pick one", "what is one?", 5);
		mult.addAnswers(mcAnswers);
		// assessment.addQuestions(mult);
		// add question to assessment? I'll try without first
		AnswerMultipleChoice frame = GuiActionRunner.execute(() -> new AnswerMultipleChoice());
		frame.setVisible(true);
		window = new FrameFixture(robot(), frame);
	}
	
	@Test
	public void studentAnswerCorrect() {
		fail("Not yet implemented");
	}
	
	@Test
	public void studentAnswerWrong() {
		fail("Not yet implemented");
	}

}
