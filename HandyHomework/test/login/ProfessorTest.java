package login;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


class ProfessorTest {
	private ProfessorLogin prof;
	@Before
	void setUp() throws Exception {
		prof = new ProfessorLogin(0, "bestProf", "userIsTrue");
	}

	@Test
	void testIsProf() {
		assertEquals(true, prof.isProf());
	}

	@Test
	void testGetUserName() {
		String user = prof.getUserName();
		assertEquals("bestProf", user);
	}

	@Test
	void testGetPassword() {
		String pass = prof.getPassword();
		assertEquals("userIsTrue", pass);
	}

	@Test
	void testGetId() {
		int id = prof.getId();
		assertEquals(0, id);
	}

}
