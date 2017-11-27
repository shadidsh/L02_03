package dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DbCourseTest {
	private DbUser dbUser;
	private DbUser dbUser2;
	private DbCourse dbCourse;
	private int pid;
	private int cid;
	private int pid2;
	
	@Before
	public void setup() {
		dbUser2 = new DbUser();
		dbUser = new DbUser();
		dbCourse = new DbCourse();
		if (! dbUser.userExists("lastProf2")) {
			pid2 = dbUser2.addUser("Vassos", "321", true);
			pid = dbUser.addUser("lastProf2", "123", true);
			cid = dbCourse.insertCourses(pid, "CSCC01", "Introduction to Software Engineering", "Fall");
		}
	}
	
	@Test
	public void testManagedCourses() {
		assertTrue(dbCourse.checkManagedCourse(pid, cid));
	}

	@Test
	public void testUnEnrollFromCourse() {
		dbCourse.unEnrollFromCourse(cid);
		assertFalse(dbCourse.checkManagedCourse(pid, cid));
	}

	@Test
	public void testInsertManagedCourses() {
		dbCourse.insertManagedCourses(pid2, cid, false);
		assertTrue(dbCourse.checkManagedCourse(pid2, cid));
		
	}
	@Test
	public void testRemoveCourse() {
		dbCourse.removeCourse(cid);
		assertFalse(dbCourse.checkManagedCourse(pid, cid));
	}
	
	@After
	public void tearDown() {
		dbCourse.removeCourse(cid);
		dbUser.removeUser("lastProf2");
		dbUser2.removeUser("Vassos");
	}
}
