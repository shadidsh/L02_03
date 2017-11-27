package gui;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.JOptionPaneFinder;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import assessment.Assessment;
import assessment.SelectedAssessment;
import course.Course;
import course.SelectedCourse;
import login.ProfessorLogin;
import login.SelectedUser;
import question.TextQuestion;

public class ProfCreateQuestion extends AssertJSwingJUnitTestCase  {
	private FrameFixture window;
	private ProfessorLogin user;
	
	@Override
	protected void onSetUp() {
		ProfessorLogin user = new ProfessorLogin(1, "user", "pass");
		SelectedUser.setUser(user);
		Course cs = new Course(131, "T123", "GUI Testing", "Fall 2017");
		SelectedCourse.setCourse(cs);
		Assessment tq = new Assessment(
				163, "Normal", "Creating Questions", true, null, (float) 100);
		SelectedAssessment.setAssess(tq);
		
		HHCreateTextQuestion frame = GuiActionRunner.execute(() -> new HHCreateTextQuestion());
		frame.setVisible(true);
		window = new FrameFixture(robot(), frame);
		
	}
	
	@Test
	public void createQuestionError1() {
		window.textBox("questionName").setText("assess");
		window.button("submit").click();
		window.optionPane().requireVisible().requireMessage("One or more fields are empty.");
	}
	
	@Test
	public void createQuestionError2() {
		window.textBox("content").setText("THIS IS YOOOOOOOOUR QUUUUESTIONS");
		window.button("submit").click();
		window.optionPane().requireVisible().requireMessage("One or more fields are empty.").click();
	}
	
	@Test
	public void createQuestionError3() {
		window.textBox("questionAnswer").setText("THIS IS YOOOOOOOOUR ANSWWWWWWER");
		window.button("submit").click();
		window.optionPane().requireVisible().requireMessage("One or more fields are empty.").click();
	}
	
	@Test
	public void createSpinInputError() {
		window.spinner("spin").increment(22);
		window.button("submit").click();
		window.optionPane().requireVisible().requireMessage("One or more fields are empty.").click();
	}
	
	// FAILS BECAUSE WINDOW ORDER FROM MAIN IS WRONG
	// UPON SUBMITTING A Q IT GOES IDK WHERE
	@Test
	public void createViewAndRemoveQuestion() {
		window.textBox("questionName").setText("THIS IS YOOOOOOOOUR Title");
		window.textBox("content").setText("THIS IS YOOOOOOOOUR QUUUUESTIONS");
		window.textBox("questionAnswer").setText("THIS IS YOOOOOOOOUR ANSWWWWWWER");
		window.button("submit").click();
		window.button("back").click();
		window = WindowFinder.findFrame("SavedQuestions").using(robot());
		window.list("lstQuestion").requireVisible().clickItem("THIS IS YOOOOOOOOUR Title");
		window.button("removeQuestion").requireEnabled().requireVisible().click();
	}
	
}
