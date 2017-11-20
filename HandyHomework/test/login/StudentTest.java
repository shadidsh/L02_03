package login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {

	private StudentLogin stud;
	@BeforeEach
	void setUp() throws Exception {
		stud = new StudentLogin(1, "bestStud", "userIsFalse");
	}

	@Test
	void testIsProf() {
		assertEquals(false, stud.isProf());
	}

	@Test
	void testGetUserName() {
		String user = stud.getUserName();
		assertEquals("bestStud", user);
	}

	@Test
	void testGetPassword() {
		String pass = stud.getPassword();
		assertEquals("userIsFalse", pass);
	}

	@Test
	void testGetId() {
		int id = stud.getId();
		assertEquals(1, id);
	}

}
