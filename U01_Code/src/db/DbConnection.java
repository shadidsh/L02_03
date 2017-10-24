package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	public static void main(String[] args) {
		Connection conn = getConnection();
		
		try{
			PreparedStatement stat = conn.prepareStatement("SELECT * FROM textquestions;");
			
			ResultSet Rs = stat.executeQuery();

		//	while (Rs.next()) {
		//		System.out.println(Rs.getInt(1) + " " + Rs.getString(2) + " " + Rs.getString(3) + " " + Rs.getString(4));
		//	}
			conn.close();
		} catch(Exception ex) {
			
			System.out.print(ex.getMessage());
		}
		
		
	}
}