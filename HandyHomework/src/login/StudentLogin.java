package login;

import java.util.ArrayList;
import db.DbConnection;
import course.Course;

public class StudentLogin extends UserLogin {
	String userName;
	String password;
	int sID;
	public StudentLogin(int sID, String userName, String password) {
		super(sID, userName, password);
	}
	
}
