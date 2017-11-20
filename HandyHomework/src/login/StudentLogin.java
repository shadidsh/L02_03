package login;

public class StudentLogin extends UserLogin {
	public StudentLogin(int sID, String userName, String password) {
		super(sID, userName, password);
	}
	
	@Override
	public boolean isProf() {
		return false;
	}
	
}
