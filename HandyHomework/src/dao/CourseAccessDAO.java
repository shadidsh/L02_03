package dao;

import java.util.List;
import course.Course;

public interface CourseAccessDAO {
	
	
	public Course getCourses(int id); 
	public void removeManagedCourses(int uid, int cid);
	public void removeCourse(int cid);
	public int insertCourses(int pid, String courseCode, String name, String term); 
	
	public int insertManagedCourses(int uid, int cid, boolean modAccess);
	public List<Course> managedCourses(int id);
	public void unEnrollFromCourse(int cid);
}
