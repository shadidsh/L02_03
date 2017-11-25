package dao;
import java.util.ArrayList;
import java.util.List;

import course.Course;
import login.StudentLogin;
import login.UserLogin;


public interface UserDAO {

	/**Should raise Unable to insert user error.*/
    public int addUser(String user, String pass, boolean isProf);
	public UserLogin getUser(int id); 
	public UserLogin getUser(String user, String pass);
	public boolean userExists(String user);	
	public List<StudentLogin> getStudentsForCourse(int cid);
	
	void removeAnswersForUser(int uid);
}
