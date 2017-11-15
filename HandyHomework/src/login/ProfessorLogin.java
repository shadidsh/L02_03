package login;

import java.util.ArrayList;
import db.DbConnection;
import course.Course;

public class ProfessorLogin {
	
    String userName;
	String password;
	int pID;
	public ProfessorLogin(int pID, String userName, String password) {
		this.pID = pID;
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}
	
	public int getpID() {
		return this.pID;
	}
}
