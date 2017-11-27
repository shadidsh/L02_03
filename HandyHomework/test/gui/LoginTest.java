package gui;

import org.junit.Test;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;

public class LoginTest extends AssertJSwingJUnitTestCase  {
	private FrameFixture window;
	
	
	@Override
	public void onSetUp() {
		HHLogin frame = GuiActionRunner.execute(() -> new HHLogin());
		frame.setVisible(true);
		window = new FrameFixture(robot(), frame);
	}
	
	  @Test
	  public void validateRegisterButton() {	  
			window.button("Register").requireVisible().requireEnabled().click();
	  }
	
	  @Test
	  public void MissingPass() {	  
			window.textBox("username").requireVisible().requireEnabled().setText("user");
		  	window.button("Login").requireVisible().requireEnabled().click();
		  	window.optionPane().requireVisible().requireMessage("Username or password cannot be empty.");
	  }
	  
	  @Test
	  public void EnterIncorrectUserPassLogin() {	  
			window.textBox("username").requireVisible().requireEnabled().setText("user");
			window.textBox("pass").requireVisible().requireEnabled().setText("user");
		  	window.button("Login").requireVisible().requireEnabled().click();
		  	window.optionPane().requireVisible().requireMessage("Incorrect credentials entered.");
	  }

	  @Test
	  public void MissingUser() {	  
		    window.textBox("pass").requireVisible().requireEnabled().setText("user");
		  	window.button("Login").requireVisible().requireEnabled().click();
		  	window.optionPane().requireVisible().requireMessage("Username or password cannot be empty.");
	  }
	  
	  @Test
	  public void EnterValidUserPassLogin() {	  
			window.textBox("username").requireVisible().requireEnabled().setText("user");
			window.textBox("pass").requireVisible().requireEnabled().setText("pass");
		  	window.button("Login").requireVisible().requireEnabled().click();
		  	window.requireNotVisible();
	  }
}
