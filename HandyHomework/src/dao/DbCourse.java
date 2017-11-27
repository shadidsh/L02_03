package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import course.Course;

public class DbCourse extends DbConnection implements CourseAccessDAO {

	@Override
	public List<Course> managedCourses(int id) {
    	Connection conn = getConnection();
    	ArrayList<Course> cs =  new ArrayList<Course>();
    	
    	try {
    		String query = "Select c.cid, c.courseCode, c.name, c.term from " 
    				+ constants.Constants.DataConstants.COURSECONTROL + " m," 
    				+ constants.Constants.DataConstants.COURSES + " c where m.user_id = ? "
    						+ " and m.cid = c.cid;";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, id);
    		ResultSet Rs = stat.executeQuery();    	
    		while (Rs.next()) { 
    			Integer cId = Rs.getInt(1);
    			String courseCode = Rs.getString(2);
    			String name = Rs.getString(3);
    			String term = Rs.getString(4);    			
    			Course course = new Course(cId, name, courseCode, term);
    			cs.add(course);
    			
    			System.out.println(cId);
    		}     		
    		conn.close();
    		return cs;
    	} catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}    	
		return cs;
	}

	@Override
	public int insertCourses(int pid, String courseCode, String name, String term) {
    	Connection conn = getConnection();
    	int result = -1;    	
    	try{
    		String insert = "INSERT INTO " + constants.Constants.DataConstants.COURSES 
    				+ "(courseCode, name, term) " +
    				" VALUES(?,?,?) RETURNING cid";
    		
    		PreparedStatement stat = conn.prepareStatement(insert);
    		stat.setString(1, courseCode);
    		stat.setString(2, name);
    		stat.setString(3, term);
    		System.out.println(stat);
    		ResultSet Rs = stat.executeQuery();
    		Rs.next();
    		result =  Rs.getInt(1);
    		
    		int user = insertManagedCourses(pid, result, false);    		
    		
    		conn.close();
    		return result; 
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());    		
    	}    	
    	return result;    
	}

	@Override
	public Course getCourses(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public boolean checkManagedCourse(int uid, int cid) {
    	Connection conn = getConnection();
    	boolean result = false;    	
    	try{
    		String exist = "select exists(select 1 from " + constants.Constants.DataConstants.COURSECONTROL 
    				+  " where user_id = ? and cid = ?)"; 
    		
    		PreparedStatement stat = conn.prepareStatement(exist);
    		stat.setInt(1, uid);
    		stat.setInt(2, cid);
    		
    		ResultSet Rs = stat.executeQuery();
    		Rs.next();
    		result =  Rs.getBoolean(1);    		
    		conn.close();
    		
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());    		
    	}    	
    	return result;  
	}
	

	@Override
	public int insertManagedCourses(int uid, int cid, boolean modAccess) {
    	Connection conn = getConnection();
    	int result = -1;    	
    	try{
    		String insert = "INSERT INTO " + constants.Constants.DataConstants.COURSECONTROL 
    				+ "(user_id, cid, is_admin) " +
    				" VALUES(?,?, ?) RETURNING user_id";
    		
    		PreparedStatement stat = conn.prepareStatement(insert);
    		stat.setInt(1, uid);
    		stat.setInt(2, cid);
    		stat.setBoolean(3, modAccess);
    		
    		ResultSet Rs = stat.executeQuery();
    		Rs.next();
    		result =  Rs.getInt(1);    		
    		conn.close();
    		
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());    		
    	}    	
    	return result;  
	}

	@Override
	public void removeManagedCourses(int uid, int cid) {
		 Connection conn = getConnection();
		 try{
	    		String delete = "DELETE FROM " +
	    				constants.Constants.DataConstants.COURSECONTROL 
	    				+ " WHERE user_id = ? and cid = ?";
			    PreparedStatement stat = conn.prepareStatement(delete);
	    		stat.setInt(1, uid);
	    		stat.setInt(2, cid);
	    		System.out.println(stat);
	    		   		
	    		stat.executeUpdate(); 
	    		
	    		stat.clearParameters();
				String query = "select exists(select 1 from " + constants.Constants.DataConstants.COURSECONTROL 
		    				+  " where cid = ?)";				
				stat = conn.prepareStatement(query);
		    	stat.setInt(1, cid);
		    	System.out.println(stat);
	    		ResultSet Rs = stat.executeQuery(); 
	    		Rs.next();
	    		if (!Rs.getBoolean(1)) {
	    			removeCourse(cid);
	    		}
	    		
	    		conn.close();
		 }catch(Exception ex) {
	    		System.out.println(ex.getMessage());    		
	     }
	}

	@Override
	public void removeCourseListings(int cid) {
    	Connection conn = getConnection();
		String delete = "DELETE FROM " +
				constants.Constants.DataConstants.COURSECONTROL 
				+ " WHERE cid = ?";
		this.unEnrollFromCourse(cid);
		DbAssessment dbAssess = new DbAssessment();
		dbAssess.removeAssessmentForCourse(cid);
    	try { 
    		    		
    		PreparedStatement stat = conn.prepareStatement(delete);
    		stat.setInt(1, cid);
    		
    		stat.executeUpdate(); 
    		conn.close();
    	} catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
	}

	@Override
	public void unEnrollFromCourse(int uid) {
    	Connection conn = getConnection();
		String delete = "DELETE FROM " +
				constants.Constants.DataConstants.COURSECONTROL 
				+ " WHERE user_id = ?";
    	try { 
    		PreparedStatement stat = conn.prepareStatement(delete);
    		stat.setInt(1, uid);
    		stat.executeUpdate(); 
    		conn.close();
    	} catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
	}

	@Override
	public void removeCourse(int cid) {
    	Connection conn = getConnection();
		String delete = "DELETE FROM " +
				constants.Constants.DataConstants.COURSES 
				+ " WHERE cid = ?";
		this.removeCourseListings(cid);
    	try { 
    		    		
    		PreparedStatement stat = conn.prepareStatement(delete);
    		stat.setInt(1, cid);
    		
    		stat.executeUpdate(); 
    		conn.close();
    	} catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
		
	}

}
