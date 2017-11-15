package login;

public class UserLogin {
    String userName;
	String password;
	int uID;
	public UserLogin(int uID, String userName, String password) {
		this.uID = uID;
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
		return this.uID;
	}
}
