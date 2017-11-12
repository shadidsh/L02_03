package course;

public class Course {
	int cID;
	String name;
	String courseCode;
	String term;
	public Course(int cID, String name, String courseCode, String term) {
		this.name = name;
		this.courseCode = courseCode;
		this.term = term;
		this.cID = cID;
	}
	
	
	public String getCourseCode() {
		return this.courseCode;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getTerm() {
		return this.term;
	}
	
	public int getcID() {
		return this.cID;
	}
	
}
