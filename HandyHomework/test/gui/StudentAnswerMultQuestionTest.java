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
import gui.AnswerMultipleChoice;
import gui.AnswerStudentQuestions;
import login.ProfessorLogin;
import login.SelectedUser;
import login.StudentLogin;
import question.MultQuestion;

public class StudentAnswerMultQuestionTest extends AssertJSwingJUnitTestCase {
	private FrameFixture window;
	
	protected void onSetUp() {
		/*// set up student user
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
		*/
		StudentLogin student = new StudentLogin(149, "stud01", "password");
		SelectedUser.setUser(student);
		Course cs = new Course(2, "CSC130H3", "Intro to its late.", "Winter 2017");
		SelectedCourse.setCourse(cs);
		Assessment assessment = new Assessment(
				221, "MCQTest", "MCQ", true, null, (float) 100);
		SelectedAssessment.setAssess(assessment);
		
//		ArrayList<TextAnswer> mcAnswers = new ArrayList<TextAnswer>();
//		TextAnswer one = new TextAnswer(5, "option 1", true);
//		TextAnswer two = new TextAnswer(5, "option 2", false);
//		mcAnswers.add(one);
//		mcAnswers.add(two);
//		MultQuestion mult = new MultQuestion(220, "pick one", "what is one?", 5);
//		mult.addAnswers(mcAnswers);
		
		AnswerMultipleChoice frame = GuiActionRunner.execute(() -> new AnswerMultipleChoice() {
			protected AnswerMultipleChoice executeInEDT() throws Exception {
				AnswerMultipleChoice frame = new AnswerMultipleChoice();
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
		window.radioButton("rdbtnA1").click();
		window.button("Submit").click();
		window.optionPane().requireVisible().requireMessage("Finished assessment, points earned: 4");
		tearDown();
	}
	
	@Test
	public void studentAnswerWrong() {
		window.radioButton("rdbtnA2").click();
		window.button("Submit").click();
		window.optionPane().requireVisible().requireMessage("Finished assessment, points earned: 0");
		tearDown();
	}


}
