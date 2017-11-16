package login;

import java.util.ArrayList;
import db.DbConnection;
import course.Course;

public class ProfessorLogin extends UserLogin {
	
    String userName;
	String password;
	int pID;
	public ProfessorLogin(int pID, String userName, String password) {
		super(pID, userName, password);
	}
	@Override
	public boolean isProf() {
		return true;
	}
	
	
}
