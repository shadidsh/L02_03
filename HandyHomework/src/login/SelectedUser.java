package login;


public class SelectedUser {
	 public static UserLogin selectedUser = null;
	 
	 public static void setUser(UserLogin selected) {
		 selectedUser = selected;	 
	 }
	 
	 public static boolean isSelected() {
		 return selectedUser != null;
	 }
	 
	 public static UserLogin getUser() {
		return selectedUser;
	 }

}
