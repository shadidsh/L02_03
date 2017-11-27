package gui;

import static org.junit.Assert.*;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import assessment.Assessment;
import assessment.SelectedAssessment;
import course.Course;
import course.SelectedCourse;
import gui.AnswerStudentQuestions;
import login.SelectedUser;
import login.StudentLogin;

public class StudentTextQAssessmentTest extends AssertJSwingJUnitTestCase {
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
	public void studentGoToNextQUponSubmit() {
		JLabelFixture oldButton = window.label("QuestName");
		String oldQ = oldButton.text();
		window.textBox("Ans").setText("w.e");
		window.button("Submit").click();
		JLabelFixture newButton = window.label("QuestName");
		String newQ = newButton.text();
		assertFalse(oldQ.compareTo(newQ) == 0);
		tearDown();
	}
	
	@Test
	public void studentPointsCalcTwoQsOneAnsWrong() {
		window.textBox("Ans").setText("Answer");
		window.button("Submit").click();
		window.textBox("Ans").setText("Answer");
		window.button("Submit").click();
		window.optionPane().requireVisible().requireMessage("Finished assessment, points earned: 5");
		tearDown();
	}
	
	@Test
	public void studentPointsCalcTwoQsBothWrong() {
		window.textBox("Ans").setText("Answer1");
		window.button("Submit").click();
		window.textBox("Ans").setText("Answer1");
		window.button("Submit").click();
		window.optionPane().requireVisible().requireMessage("Finished assessment, points earned: 0");
		tearDown();
	}
	
	@Test
	public void studentPointsCalcTwoQsBothRight() {
		window.textBox("Ans").setText("Answer");
		window.button("Submit").click();
		window.textBox("Ans").setText("Answer2");
		window.button("Submit").click();
		window.optionPane().requireVisible().requireMessage("Finished assessment, points earned: 10");
		tearDown();
	}

}
