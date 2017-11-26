package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import login.ProfessorLogin;
import login.StudentLogin;
import login.UserLogin;

public class DbUser extends DbConnection implements UserDAO  {
	
	@Override
	public int addUser(String user, String pass, boolean isProf) {
    	Connection conn = getConnection();
    	int result = -1;   
    	try {
    		String insert = "INSERT INTO " + constants.Constants.DataConstants.USERS 
    				+ "(username, password, is_prof) " +
    				" VALUES(?,?,?) RETURNING user_id";
    		PreparedStatement stat = conn.prepareStatement(insert);
    		stat.setString(1, user);
    		stat.setString(2, pass);
    		stat.setBoolean(3, isProf);
    		
    		ResultSet Rs =	stat.executeQuery();
    		Rs.next();
    		result =  Rs.getInt(1);
    		conn.close();
    	} catch(Exception ex) {
    		result = -1;
    		System.out.print(ex.getMessage());    		
    	}
		return result;    
    	
    }

	@Override
	public UserLogin getUser(int id) {
    	Connection conn = getConnection();
    	try { 
    		
    		String query = "select * from " + constants.Constants.DataConstants.USERS 
    				+  " where user_id = ?";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, id);
    		
    		ResultSet Rs = stat.executeQuery(); 
    		UserLogin userLog = null;
    		if (Rs.next()) { 
    			Integer userId = Rs.getInt(1);
    			String username = Rs.getString(2);
    			String password = Rs.getString(3);
    			boolean isProf = Rs.getBoolean(4);
    			//String email = Rs.getString(5);
    			
    			if (isProf) {
    				userLog = new ProfessorLogin(userId, username, password);
    			} else {
    				userLog = new StudentLogin(userId, username, password);
    			}
    		}
    		conn.close();
    		return userLog;
    		
    	} catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
    	return null; 
	}

	@Override
	public UserLogin getUser(String user, String pass) {
    	Connection conn = getConnection();
    	try { 
    		
    		String query = "select * from " + constants.Constants.DataConstants.USERS 
    				+  " where username = ? and password = ?";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setString(1, user);
    		stat.setString(2, pass);
    		ResultSet Rs = stat.executeQuery(); 
    		UserLogin userLog = null;
    		if (Rs.next()) { 
    			Integer userId = Rs.getInt(1);
    			String username = Rs.getString(2);
    			String password = Rs.getString(3);
    			boolean isProf = Rs.getBoolean(4);
    			//String email = Rs.getString(5);
    			
    			if (isProf) {
    				userLog = new ProfessorLogin(userId, username, password);
    			} else {
    				userLog = new StudentLogin(userId, username, password);
    			}
    			System.out.println(userId);
    		} 
    		conn.close();
			return userLog;
    	} catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
    	return null;    	
    }

	
	public UserLogin getUser(String user) {
    	Connection conn = getConnection();
    	try { 
    		
    		String query = "select * from " + constants.Constants.DataConstants.USERS 
    				+  " where username = ?";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setString(1, user);
    		ResultSet Rs = stat.executeQuery(); 
    		UserLogin userLog = null;
    		if (Rs.next()) { 
    			Integer userId = Rs.getInt(1);
    			String username = Rs.getString(2);
    			String password = Rs.getString(3);
    			boolean isProf = Rs.getBoolean(4);
    			//String email = Rs.getString(5);
    			
    			if (isProf) {
    				userLog = new ProfessorLogin(userId, username, password);
    			} else {
    				userLog = new StudentLogin(userId, username, password);
    			}
    		}
    		conn.close();
			return userLog;
    	} catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
    	return null;    	
    }

	@Override
	public boolean userExists(String user) {
    	Connection conn = getConnection();
    	try { 
    		
    		String query = "select exists(select 1 from " + constants.Constants.DataConstants.USERS 
    				+  " where username = ?)";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setString(1, user);
    		
    		ResultSet Rs = stat.executeQuery(); 
    		Rs.next();
    		boolean res = Rs.getBoolean(1);
    		conn.close();
    		return res;
    		
    	} catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
    	return false;    
	}

	@Override
	public List<StudentLogin> getStudentsForCourse(int cid) {
		Connection conn = getConnection();
		String query = "SELECT c.user_id, cid, weight_earned, username, password FROM "	
				+ constants.Constants.DataConstants.COURSECONTROL + " m," 
				+ constants.Constants.DataConstants.USERS + " c where m.user_id = c.user_id"
						+ " and m.cid = ? and is_prof = false;";
		
		PreparedStatement stat;
		ArrayList<StudentLogin> students = new ArrayList<StudentLogin>();
		
		try {
			stat = conn.prepareStatement(query);
			stat.setInt(1, cid);
			System.out.println(stat);
			ResultSet Rs = stat.executeQuery();
			
			while (Rs.next()) {
				int uid = Rs.getInt(1);
				cid = Rs.getInt(2);
				
				String username = Rs.getString(4);
				String password = Rs.getString(5);	
				
				StudentLogin as = new StudentLogin(uid, username, password);	
				students.add(as);
			}
			conn.close();
			
		} catch (Exception ex) {
    		System.out.println(ex.getMessage());
		}
		return students;
	}

	@Override
	public void removeAnswersForUser(int uid) {
		Connection conn = getConnection();
		try {
    		String deleteAns = "DELETE FROM " 
		+ constants.Constants.DataConstants.USERANSWERS + " WHERE user_id = ?";
    		PreparedStatement stat = conn.prepareStatement(deleteAns);
    		stat.setInt(1, uid);
    		stat.executeUpdate();
    		conn.close();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());  
		}		
	}
	@Override
	public int removeUser(String user) {
		return -1;
	}

}
