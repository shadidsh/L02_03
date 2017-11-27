package gui;

import static org.junit.Assert.*;

import java.awt.Dimension;
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
import dao.DbAssessment;
import dao.DbQuestions;
import gui.AnswerStudentQuestions;
import gui.HHSavedAssessments;
import login.ProfessorLogin;
import login.SelectedUser;
import login.StudentLogin;
import question.TextQuestion;

public class StudentAnswerTextQuestionTest extends AssertJSwingJUnitTestCase {
	private FrameFixture window;

	@Override
	protected void onSetUp() {		
		StudentLogin student = new StudentLogin(149, "stud01", "password");
		SelectedUser.setUser(student);
		Course cs = new Course(2, "CSC130H3", "Intro to its late.", "Winter 2017");
		SelectedCourse.setCourse(cs);
		Assessment assessment = new Assessment(
				220, "Title", "Name", true, null, (float) 100);
		SelectedAssessment.setAssess(assessment);
		TextQuestion tq = new TextQuestion(182, "Name", "Content", 5);		
		TextAnswer ta = new TextAnswer(182, "answer", true);
		
		AnswerStudentQuestions frame = GuiActionRunner.execute(() -> new AnswerStudentQuestions() {
			protected AnswerStudentQuestions executeInEDT() throws Exception {
				AnswerStudentQuestions frame = new AnswerStudentQuestions();
            	frame.setVisible(true);
            	frame.pack();
            	return frame;
            }
		});
		frame.setVisible(true);
		window = new FrameFixture(robot(), frame);
	}
	
	@Test
	public void studentPointsCalcAnswerCorrect() {
		window.textBox("Ans").setText("Answer");
		window.button("Submit").click();
		window.optionPane().requireVisible().requireMessage("Finished assessment, points earned: 5");
		tearDown();
	}
	
	@Test
	public void studentPointsCalcAnswerWrong() {
		window.textBox("Ans").setText("wrong answer");
		window.button("Submit").click();
		window.optionPane().requireVisible().requireMessage("Finished assessment, points earned: 0");
		tearDown();
	}

}
