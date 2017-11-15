package login;


public class SelectedProf {
	 public static ProfessorLogin selectedProf = null;
	 
	 public static void setUser(ProfessorLogin selected) {
		 selectedProf = selected;	 
	 }
	 
	 public static boolean isSelected() {
		 return selectedProf != null;
	 }
	 
	 public static ProfessorLogin getUser() {
		return selectedProf;
	 }

}
