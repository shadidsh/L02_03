package login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfessorTest {
	private ProfessorLogin prof;
	@BeforeEach
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
