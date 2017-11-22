package gui;

import static org.junit.Assert.*;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import course.Course;
import course.SelectedCourse;
import login.ProfessorLogin;
import login.SelectedUser;

public class ProfAssessTest extends AssertJSwingJUnitTestCase  {
	private FrameFixture window;

	@Override
	protected void onSetUp() {
		ProfessorLogin user = new ProfessorLogin(1, "user", "pass");
		SelectedUser.setUser(user);
		Course cs = new Course(2, "CSC130H3", "Intro to its late.", "Winter 2017");
		SelectedCourse.setCourse(cs);
		
		HHCreateAssessmentFrame frame = GuiActionRunner.execute(() -> new HHCreateAssessmentFrame());
		frame.setVisible(true);
		window = new FrameFixture(robot(), frame);
	}
	
	@Test
	public void createEmptyAssess() {
		window.textBox("assess").setText("assess");
		window.button("create").click();
		window.optionPane().requireVisible().requireMessage("One or more mandatory fields are empty.");
	}
	
	@Test
	public void createAndViewAssess() {
		window.textBox("assess").setText("assess test");
		window.textBox("title").setText("title of assess");
		window.spinner("spin").increment(22);
		window.button("create").click();
		
		window = WindowFinder.findFrame("SavedAssess").using(robot());
		
		window.list("assess").requireVisible().clickItem("assess test");
		window.button("removeAssess").requireEnabled().requireVisible().click();
	}

}
