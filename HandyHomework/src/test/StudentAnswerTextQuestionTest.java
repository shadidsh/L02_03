package test;

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
		// set up student user
		StudentLogin student = new StudentLogin(1, "student", "password");
		SelectedUser.setUser(student);
		// set up course
		Course course = new Course(2, "CSCC01H5F", "Software Engineering", "Fall 2017");
		SelectedCourse.setCourse(course);
		// set up assessment
		Assessment assessment = new Assessment(
				3, "Intro to comp sci", "Title vs name", true, null, (float) 0.99);
		// set up text question with answer
		TextQuestion text = new TextQuestion(4, "write answer", "just write answer", 5);
		TextAnswer forText = new TextAnswer(4, "answer", true); 
		//DbAssessment dbA = new DbAssessment();
		//dbA.insertAssessment("CSCC01H5F", 2, "Software Engineering", null, true, 0);
		//DbQuestions dbQ = new DbQuestions();
		//dbQ.insertQuestions(2, "write answer", "just write answer", 5, false, false);
		//dbQ.insertAnswers(4, true, "answer");
		//
		SelectedAssessment.setAssess(assessment);
		SelectedAssessment.getAssess().addQuestions(text);
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
	public void studentAnswerCorrect() {
		
		window.textBox("txtAns").setText("answer");
		window.button("btnSubmit").click();
		window.optionPane().requireVisible().requireMessage("Finished assessment, points earned: 5");
		tearDown();
	}
	
	@Test
	public void studentAnswerWrong() {
		fail("Not yet implemented");
		tearDown();
	}

}
