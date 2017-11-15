package login;

import java.util.ArrayList;
import db.DbConnection;
import course.Course;

public class StudentLogin {
	String userName;
	String password;
	int sID;
	public StudentLogin(int sID, String userName, String password) {
		this.sID = sID;
		this.userName = userName;
		this.password = password;
	}
	String getUserName() {
		return this.userName;
	}

	String getPassword() {
		return this.password;
	}
	
	public int getsID() {
		return this.sID;
	}
	
	public void addCourse(Course course) {
		
	}
}
