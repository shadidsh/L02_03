package gui;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.util.concurrent.Callable;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;

import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;

public class HHLoginTest extends AssertJSwingJUnitTestCase  {
	private FrameFixture window;
	
	
	@Override
	public void onSetUp() {
		HHLogin frame = GuiActionRunner.execute(() -> new HHLogin());
		frame.setVisible(true);
		window = new FrameFixture(robot(), frame);
		
		/*
		 * HHLogin mainWindow = GuiActionRunner.execute(new GuiQuery<HHLogin>() {
		@Override
            protected HHLogin executeInEDT() throws Exception {
			HHLogin fr = new HHLogin();
			fr.setVisible(true);
			fr.setResizable(true);
			fr.setSize(600, 600);
			//fr.setPreferredSize(new Dimension(600, 600));
			//fr.pack();
			return fr;
            }
		});
		*/	
		
		//window.show();
		//window.resizeTo(new Dimension(600, 600));		
		//fail("Not yet implemented");
	}
	
	  @Test
	  public void validateRegisterButton() {	  
			window.button("Register").requireVisible().requireEnabled().click();
	  }

	/*
	@Test
	public void getUserById() {
		window.textBox("usernameField").enterText("user");
		window.textBox("usernameField").requireText("user");
	}
	*/
}
