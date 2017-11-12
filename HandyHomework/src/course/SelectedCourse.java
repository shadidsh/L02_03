package course;


public class SelectedCourse {
	 public static Course selectedAs = null;
	 
	 public static void setCourse(Course selected) {
		 selectedAs = selected;	 
	 }
	 
	 public static boolean isSelected() {
		 return selectedAs != null;
	 }
	 
	 public static Course getCourse() {
		return selectedAs;
	 }

}
