package gui;

import java.awt.Dimension;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import course.Course;
import course.SelectedCourse;
import login.ProfessorLogin;
import login.SelectedUser;

public class ProfModifyStudent  extends AssertJSwingJUnitTestCase  {
	private FrameFixture window;

	@Override
	protected void onSetUp() {
		ProfessorLogin user = new ProfessorLogin(1, "user", "pass");
		SelectedUser.setUser(user);
		Course cs = new Course(2, "CSC130H3", "Intro to its late.", "Winter 2017");
		SelectedCourse.setCourse(cs);
		
		AddOneStudentForm frame = GuiActionRunner.execute(new GuiQuery<AddOneStudentForm>() {

            @Override
            protected AddOneStudentForm executeInEDT() throws Exception {
            	AddOneStudentForm frame = new AddOneStudentForm();
            	frame.setVisible(true);
            	frame.setPreferredSize(new Dimension(368, 240));
            	frame.setBounds(0, 0, 368, 240);
            	frame.pack();
            	return frame;
            }
        });
		
		window = new FrameFixture(robot(), frame);
		
	}
	
	@Test
	public void addStudentDoesntExist() {
		window.textBox("username").setText("Doesnt Exist");
		window.button("addStudent").click();
		window.optionPane().requireVisible().requireMessage(
				"Student Doesnt Exist is not registered, could not enrol student to course");
	}
	
	@Test
	public void addAndRemoveStudent() {
		window.textBox("username").setText("student");
		window.button("addStudent").click();
		window.button("back").click();
		
		window = WindowFinder.findFrame("addStudentsPage").using(robot());
		window.button("back").requireVisible().click();
		
		window = WindowFinder.findFrame("ViewStudentsPage").using(robot());
		window.table("studentTable").cell("student").click();
		window.button("btnRemoveStudent").click();
		window.optionPane().yesButton().click();
	}

}
