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
	
	/*
	public Course(String name, String courseCode, String term, int uid) {
		this.courseCode = courseCode;
		this.name = name;
		this.term = term;
		
		this.cID = DbConnection.insertCourses(uid, courseCode, name, term);	
	} 
	
	*/
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
	
	public void addQuestions(Assessment assess) {
		as.add(assess);
	}
	
}
