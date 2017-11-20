package db;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import answer.TextAnswer;
import course.Course;
import login.ProfessorLogin;
import login.StudentLogin;
import login.UserLogin;

public class DbConnection {
	private static String url = "jdbc:postgresql://swaredb.carzld1axpox.us-east-2.rds.amazonaws.com:5432/postgres";
	private static String driverName = "org.postgresql.Driver"; 
	private static String username = "shadidsh";
	private static String password = "test1234";
	private static Connection con;
	
    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (Exception ex) {
                System.out.println("Failed to create the database connection."); 
            }
        } catch (Exception ex) {
            System.out.println("Driver not found."); 
        }
        return con;
    }
    
    // select all answers for a specific question - quest ids are randomly generated
  /*
    public static ArrayList<TextAnswer> answers_for_question(int questID) {
    	Connection conn = getConnection();
    	ArrayList<TextAnswer>  at =  new ArrayList<TextAnswer>();
    	try{
    		String query = "SELECT * FROM "	+ constants.Constants.DataConstants.ANSWERS + " where qid = ?;";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, questID);
    		ResultSet Rs = stat.executeQuery();    		
    		
    		while (Rs.next()) {
    			// Create and return a list of answer objects
    			
    			Boolean isCorrect = Rs.getBoolean(3);
    			String answer = Rs.getString(4);    			
    			TextAnswer ans = new TextAnswer(questID, answer, isCorrect);
    			at.add(ans);
    			System.out.println(answer);    
    		}
    		
    		conn.close();
    	}catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
		return at;
    	
    }
    */
    
    // select all questions for a specific assessment - assessment ids are randomly generated
    /*
    public static void questions_for_assessments(int assessId) {
    	Connection conn = getConnection();    	
    	try{
    		PreparedStatement stat = conn.prepareStatement("SELECT * FROM "	+ constants.Constants.DataConstants.QUESTIONS + " where aid = ?;");
    		stat.setInt(1, assessId);
    		ResultSet Rs = stat.executeQuery();    		
    		while (Rs.next()) {
    			// Create and return a list of question objects    			
    			// assessID, String name, String question, String answer, int points
    			String name = Rs.getString(3);
    			String question = Rs.getString(4);
    			Integer points = Rs.getInt(5);
    			System.out.println("question name: " + name + ", question: " + question + ", for " + points + " points");    
    		}    		
    		conn.close();
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());    		
    	}    	
    }
	*/	 
    /**
     * 
     * @param title the title of the assessment
     * @param cid the id of the course this assessment belongs to
     * @param name the name of the assessment
     * @param weight the float representing the weight of the assessment, 
     * 	can only be 0 to 1 (0 to 100%). Required if assessment is not optional (db returns error).
     * @param dueDate date when this assessment is due, required if assessment is not optional (db returns error).
     * @param isMult boolean representing whether this question has multiple choices or not
     * @param isOpt boolean representing whether this question is optional or not
     */
  /*  public static int insertAssessment(String title, int cId, String name, Calendar dueDate, Boolean isOpt, float weight) {
    	Connection conn = getConnection();
    	int result = -1;    	
    	try{
    		String insert = "INSERT INTO " + constants.Constants.DataConstants.ASSESSMENTS 
    				+ "(title, cid, name, due_date, weight, is_opt) " +
    				" VALUES(?,?,?,?,?,?) RETURNING cid";
    		
    		PreparedStatement stat = conn.prepareStatement(insert);
    		stat.setString(1, title);
    		stat.setInt(2, cId);
    		
    		stat.setString(3, name);

    		java.sql.Timestamp inDate = new java.sql.Timestamp(  dueDate.getTimeInMillis());

    		stat.setTimestamp(4, inDate);		
    		stat.setFloat(5, weight);
    		stat.setBoolean(6, isOpt);	
    		
    		ResultSet Rs = stat.executeQuery();
    		Rs.next();
    		result =  Rs.getInt(1); 
    		conn.close();
    		
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());    		
    	}    	
    	return result;    	
    }
*/

	/**
	*
	* @param forAssess id of the assessment for which this question is under
	* @param name name of the question
	* @param question the contents of the question
	* @param points the number of points for this questions that contributes to this assessment 
	*/
	/*
	     public static int insertQuestions(int forAssess, String name, String question, int points) {
*/

    /*
	public static int insertAnswers(int forQuest, boolean isCorrect, String answer) {
		Connection conn = getConnection();
		int res = -1;
		try{
    		String insert = "INSERT INTO " + constants.Constants.DataConstants.ANSWERS 
    				+ "(qid, is_correct, answer) " +
    				" VALUES(?,?,?) RETURNING qid";
			PreparedStatement stat = conn.prepareStatement(insert);
		
			stat.setInt(1, forQuest);
			stat.setBoolean(2, isCorrect);
			stat.setString(3, answer);

			ResultSet Rs = stat.executeQuery();
			Rs.next();
			res =  Rs.getInt(1); 
    		
			conn.close();		
		} catch(Exception ex) {
			System.out.println(ex.getMessage());  
		}
		return res;
	}
	/*

/*	public static int insertCourses(int pid, String courseCode, String name, String term) {
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

    		ResultSet Rs = stat.executeQuery();
    		Rs.next();
    		result =  Rs.getInt(1);
    		
    		int user = insertManagedCourses(pid, result);    		
    		
    		conn.close();
    		
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());    		
    	}    	
    	return result;    
	}
	*/
	/*
	public static int insertManagedCourses(int pid, int cid) {
    	Connection conn = getConnection();
    	int result = -1;    	
    	try{
    		String insert = "INSERT INTO " + constants.Constants.DataConstants.COURSECONTROL 
    				+ "(user_id, cid) " +
    				" VALUES(?,?) RETURNING user_id";
    		
    		PreparedStatement stat = conn.prepareStatement(insert);
    		stat.setInt(1, pid);
    		stat.setInt(2, cid);

    		ResultSet Rs = stat.executeQuery();
    		Rs.next();
    		result =  Rs.getInt(1);    		
    		conn.close();
    		
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());    		
    	}    	
    	return result;    
	}
	*/
	
	
	public static void main(String[] args) {
		//checkUser("use");
		//checkUser("user", "pass");
		//ProfessorLogin pf = checkUser("user", "pass");
		//System.out.println(pf == null); 
		//System.out.println(pf.equals(null)); 
		//courses_for_user(1);
		
		
	}
		
}