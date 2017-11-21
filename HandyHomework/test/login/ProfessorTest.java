package login;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ProfessorTest {
	private ProfessorLogin prof;
	@Before
	public void setUp() throws Exception {
		prof = new ProfessorLogin(0, "bestProf", "userIsTrue");
	}

	@Test
	public void testIsProf() {
		assertEquals(true, prof.isProf());
	}

	@Test
	public void testGetUserName() {
		String user = prof.getUserName();
		assertEquals("bestProf", user);
	}

	@Test
	public void testGetPassword() {
		String pass = prof.getPassword();
		assertEquals("userIsTrue", pass);
	}

	@Test
	public void testGetId() {
		int id = prof.getId();
		assertEquals(0, id);
	}

}
