package gui;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import assessment.Assessment;
import assessment.SelectedAssessment;
import course.Course;
import course.SelectedCourse;
import gui.AnswerMultipleChoice;
import login.SelectedUser;
import login.StudentLogin;

public class StudentAnswerMultQuestionTest extends AssertJSwingJUnitTestCase {
	private FrameFixture window;
	
	protected void onSetUp() {
		StudentLogin student = new StudentLogin(149, "stud01", "password");
		SelectedUser.setUser(student);
		Course cs = new Course(2, "CSC130H3", "Intro to its late.", "Winter 2017");
		SelectedCourse.setCourse(cs);
		Assessment assessment = new Assessment(
				221, "MCQTest", "MCQ", true, null, (float) 100);
		SelectedAssessment.setAssess(assessment);
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
