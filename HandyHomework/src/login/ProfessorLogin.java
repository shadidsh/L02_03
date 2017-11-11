package login;

public class ProfessorLogin extends LoginAbstract {

	private String userName;
	private String password;
	public ProfessorLogin(String userName, String password) {
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
