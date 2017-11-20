package course;

import java.util.ArrayList;

import assessment.Assessment;
import db.DbConnection;

public class Course {
	int cID;
	String name;
	String courseCode;
	String term;
	private ArrayList<Assessment> as;
	
	public Course(int cID, String name, String courseCode, String term) {
		this.cID = cID;
		this.name = name;
		this.courseCode = courseCode;
		this.term = term;
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
	
	public void addAssessment(Assessment assess) {
		as.add(assess);
	}
	
}
