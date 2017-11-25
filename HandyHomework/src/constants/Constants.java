package constants;

public class Constants {


	 
	 public class ConnectionConstants {
		 public static final String URl = "jdbc:postgresql://swaredb.carzld1axpox.us-east-2.rds.amazonaws.com:5432/postgres";
		 public static final String DRIVER = "org.postgresql.Driver"; 
		 public static final String USER = "shadidsh";
		 public static final String PASS = "test1234";
	 }
	 
	 
	 public class DataConstants {
		    // ----- Table name constants for access -----
		 public static final String COURSES   = "courses";
		 public static final String ASSESSMENTS   = "assessments";
		 public static final String QUESTIONS   = "questions";
		 public static final String ANSWERS   = "answers";
		 public static final String USERS   = "users";
		 
		 public static final String COURSECONTROL   = "courseControl";
		 public static final String USERANSWERS   = "user_answers";
		 
	 }
	
}
