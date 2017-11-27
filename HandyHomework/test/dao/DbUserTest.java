package dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import login.ProfessorLogin;

public class DbUserTest {

	private ProfessorLogin prof;
	private DbUser dbUser;
	int result;
	
	@Before
	public void setUp() {
		prof = new ProfessorLogin(0, "bestProf", "userIsTrue");
		dbUser = new DbUser();
		if (! dbUser.userExists("bestProf")) {
			dbUser.addUser(prof.getUserName(), prof.getPassword(), prof.isProf());
		}
	}
 
	@Test
	public void testAddExistingProf() {
		assertEquals(-1, dbUser.addUser(prof.getUserName(), prof.getPassword(), prof.isProf()));
	}
	
	
	@Test
	public void testRemoveExistingProf() {		
		assertEquals(0, dbUser.removeUser("bestProf"));
	}
	
	@Test
	public void testAddNonExistingProf() {
		if(dbUser.userExists("nonExistentProf")) {
			dbUser.removeUser("nonExistentProf");
		}
		ProfessorLogin profNE = new ProfessorLogin(0, "nonExistentProf", "userIsNonExistent");
		int res = dbUser.addUser(profNE.getUserName(), profNE.getPassword(), profNE.isProf());
		assertTrue(res >= 0);
	}
	
}
