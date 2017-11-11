package login;

public class StudentLogin extends LoginAbstract{
	private String userName;
	private String password;
	public StudentLogin(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	String getUserName() {
		return this.userName;
	}

	String getPassword() {
		return this.password;
	}
	
}
