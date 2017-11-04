package db;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    public static void answers_for_question(int quest_id) {
    	Connection conn = getConnection();
    	
    	try{
    		String query = "SELECT * FROM "	+ constants.Constants.DataConstants.ANSWERS + " where qid = ?;";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, quest_id);
    		ResultSet Rs = stat.executeQuery();
    		
    		while (Rs.next()) {
    			// Create and return a list of answer objects
    			
    			Boolean isCorrect = Rs.getBoolean(3);
    			String answer = Rs.getString(4);
    			System.out.println(answer);    
    		}
    		
    		conn.close();
    	}catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
    	
    }
    
    // select all questions for a specific assessment - assessment ids are randomly generated
    public static void questions_for_assessments(int assess_id) {
    	Connection conn = getConnection();
    	
    	try{
    		PreparedStatement stat = conn.prepareStatement("SELECT * FROM "	+ constants.Constants.DataConstants.QUESTIONS + " where aid = ?;");
    		stat.setInt(1, assess_id);
    		ResultSet Rs = stat.executeQuery();
    		
    		while (Rs.next()) {
    			// Create and return a list of question objects

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
    

	// in the future, something like assessments_for_course() will be used

	 public static void all_assessments() {
		Connection conn = getConnection();
		String query = "Select * from " + constants.Constants.DataConstants.ASSESSMENTS;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			ResultSet Rs = stat.executeQuery();

			while (Rs.next()) {
    			// Create an assessment object
			
				String title = Rs.getString(2);
				String name = Rs.getString(3);
				Boolean isMult = Rs.getBoolean(4);
				
				java.sql.Timestamp dueDate = Rs.getTimestamp(6);
				
				Boolean isOpt = Rs.getBoolean(5);
				float weight = Rs.getFloat(7);
				System.out.println("title: " + title + ", name: " + name + ", ismult: " + isMult 
					+ ", Due: " + sdf.format(dueDate) + ", isOpt: " + isOpt + ", weight: " + weight);
				}


			conn.close();
		} catch(Exception ex) {
			System.out.println(ex.getMessage()); 
		}
	 }



    // insert a new assessment- assessment ids are randomly generated
	// function overload can be used (if opt then exclude due or ismult, 
	// and the isopt boolean, else include both)
    /**
     * 
     * @param title the title of the assessment
     * @param name the name of the assessment
     * @param weight the float representing the weight of the assessment, 
     * 	can only be 0 to 1 (0 to 100%). Required if assessment is not optional (db returns error).
     * @param dueDate date when this assessment is due, required if assessment is not optional (db returns error).
     * @param isMult boolean representing whether this question has multiple choices or not
     * @param isOpt boolean representing whether this question is optional or not
     */
    public static void insert_assessment(String title, String name, Boolean isMult, Calendar dueDate, Boolean isOpt, float weight) {
    	Connection conn = getConnection();
    	
    	try{
    		String insert = "INSERT INTO " + constants.Constants.DataConstants.ASSESSMENTS 
    				+ "(title, name, due_date, weight, is_mult, is_opt) " +
    				" VALUES(?,?,?,?,?,?)";
    		
    		PreparedStatement stat = conn.prepareStatement(insert);
    		stat.setString(1, title);
    		stat.setString(2, name);

    		java.sql.Timestamp inDate = new java.sql.Timestamp(  dueDate.getTimeInMillis());

    		stat.setTimestamp(3, inDate);		
    		stat.setFloat(4, weight);
    		stat.setBoolean(5, isMult);
    		stat.setBoolean(6, isOpt);
    		    		
    		ResultSet Rs = stat.executeQuery();
    		   		
    		conn.close();
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());    		
    	}
    	
    }

	/**
	*
	* @param forAssess id of the assessment for which this question is under
	* @param name name of the question
	* @param question the contents of the question
	* @param points the number of points for this questions that contributes to this assessment 
	*/
	public static void insert_questions(int forAssess, String name, String question, int points) {
		Connection conn = getConnection();

		try{
    		String insert = "INSERT INTO " + constants.Constants.DataConstants.QUESTIONS 
    				+ "(name, aid, question, points) " +
    				" VALUES(?,?,?,?)";
			PreparedStatement stat = conn.prepareStatement(insert);
			stat.setString(1, name);
			stat.setInt(2, forAssess);
			stat.setString(3, question);
			stat.setInt(4, points);

			ResultSet Rs = stat.executeQuery();
			conn.close();		
		} catch(Exception ex) {
			System.out.println(ex.getMessage());  
		}
	}
    

	public static void main(String[] args) {
		 answers_for_question(1);		
	
		 
		 Calendar due = Calendar.getInstance();
		 due.set(2017, 9, 21, 10, 05, 30);
			
		insert_assessment("assessment 2", "Greedy Algorithms.",  new Boolean(false), due, new Boolean(false),  (float) 0.99);
		all_assessments();
		
	}
}