package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import assessment.Assessment;

public class DbAssessment extends DbConnection  implements AssessmentDAO {

	@Override
	public int insertAssessment(String title, int cid, String name, Calendar dueDate, Boolean isOpt, float weight) {
    	Connection conn = getConnection();
    	int result = -1;    	
    	try{
    		String insert = "INSERT INTO " + constants.Constants.DataConstants.ASSESSMENTS 
    				+ "(title, cid, name, due_date, weight, is_opt) " +
    				" VALUES(?,?,?,?,?,?) RETURNING aid";
    		
    		PreparedStatement stat = conn.prepareStatement(insert);
    		stat.setString(1, title);
    		stat.setInt(2, cid);
    		
    		stat.setString(3, name);

    		java.sql.Timestamp inDate = new java.sql.Timestamp(  dueDate.getTimeInMillis());

    		stat.setTimestamp(4, inDate);		
    		stat.setFloat(5, weight);
    		stat.setBoolean(6, isOpt);	
    		System.out.println(stat);
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
	public List<Assessment> getAssessmentForCourse(int cid) {
		Connection conn = getConnection();
		String query = "SELECT * FROM "	
				+ constants.Constants.DataConstants.ASSESSMENTS + " where cid = ?;";
		PreparedStatement stat;
		ArrayList<Assessment> assess = new ArrayList<Assessment>();
		
		try {
			stat = conn.prepareStatement(query);
			stat.setInt(1, cid);
			ResultSet Rs = stat.executeQuery();
			Calendar due = Calendar.getInstance();
			
			while (Rs.next()) {
				int aid = Rs.getInt(1);
				cid = Rs.getInt(2);
				String title = Rs.getString(3);
				String name = Rs.getString(4);
				Boolean isOpt = Rs.getBoolean(5);
				java.sql.Timestamp dueDate = Rs.getTimestamp(6);
				float weight = Rs.getFloat(7);
				
				if (dueDate != null) {
					due.setTimeInMillis(dueDate.getTime());
				}		
				
				Assessment as = new Assessment(aid, title, name, isOpt, due, weight);	
				assess.add(as);
			}
			conn.close();
			
		} catch (Exception ex) {
    		System.out.println(ex.getMessage());
		}
		return assess;
	}

	@Override
	public void removeAssessment(int aid) {
		 Connection conn = getConnection();
		 try{
	    		String delete = "DELETE FROM " + constants.Constants.DataConstants.ASSESSMENTS + " WHERE aid = ?";
	    		PreparedStatement stat = conn.prepareStatement(delete);	    		
	    		
	    		DbQuestions dbq = new DbQuestions();	    		
	    		dbq.removeQuestionsForAssessments(aid);
	    		
	    		stat.setInt(1, aid);
	    		ResultSet Rs = stat.executeQuery();
	    		conn.close();
		 }catch(Exception ex) {
	    		System.out.println(ex.getMessage());    		
	     }
	}

	@Override
	public void removeAssessmentForCourse(int cid) {
		List<Assessment> ac = getAssessmentForCourse(cid);
		for (Assessment assess : ac) {
			this.removeAssessment(assess.getAid());
		}		
	}
}
