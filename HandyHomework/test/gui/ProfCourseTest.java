package gui;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import login.ProfessorLogin;
import login.SelectedUser;

public class ProfCourseTest extends AssertJSwingJUnitTestCase {
	private FrameFixture window;
	private ProfessorLogin user;
	
	@Override
	protected void onSetUp() {
		ProfessorLogin user = new ProfessorLogin(1, "user", "pass");
		SelectedUser.setUser(user);
		
		HHCreateCoursePage frame = GuiActionRunner.execute(() -> new HHCreateCoursePage());
		frame.setVisible(true);
		window = new FrameFixture(robot(), frame);
	}
	
	@Test
	public void createEmptyCourse() {
		window.textBox("courseTerm").setText("Fall 2017");
		
		window.button("create").click();
		window.optionPane().requireVisible().requireMessage(
				"One or more fields are empty.");
	}
	
	@Test
	public void createCourseAndViewAndRemove() {

		
		window.textBox("courseTerm").setText("Fall 2017");
		window.textBox("courseName").setText("Intro to Software Engineering");
		window.textBox("courseCode").setText("CSCC01");
		window.button("create").click();
		window.requireNotVisible();
		window = WindowFinder.findFrame("viewCourse").using(robot());
		

		window.list("courses").requireVisible().clickItem("CSCC01:Fall 2017");
		window.button("removeCourse").requireEnabled().requireVisible().click();
	}



}
