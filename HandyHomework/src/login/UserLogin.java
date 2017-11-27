package login;

public abstract class UserLogin {
    private String userName;
	private String password;
	private int uID;
	public UserLogin(int uID, String userName, String password) {
		this.uID = uID;
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	public int getId() {
		return uID;
	}
	
	public abstract boolean isProf();
	
}
