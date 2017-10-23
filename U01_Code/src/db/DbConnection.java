package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbConnection {
	public static void main(String[] args) {
		
		String url1 = "jdbc:postgresql://swaredb.carzld1axpox.us-east-2.rds.amazonaws.com:5432/postgres";
		
		String url2 = "jdbc:postgresql://localhost:5432/postgres";
		try{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url1, "shadidsh", "test1234");
			PreparedStatement stat = con.prepareStatement("SELECT * FROM textquestions;");
			
			ResultSet Rs = stat.executeQuery();

			while (Rs.next()) {
				System.out.println(Rs.getInt(1) );
			}
			
		} catch(Exception ex) {
			System.out.print(ex.getMessage());
		}
		
	//	Rs.close();
	//	stat.close();
	}
}