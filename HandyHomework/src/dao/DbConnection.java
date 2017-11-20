package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import constants.Constants.ConnectionConstants;

public abstract class DbConnection {
	private Connection con; 

    public Connection getConnection() {
        try {
            Class.forName(constants.Constants.ConnectionConstants.DRIVER);
            try {
                con = DriverManager.getConnection(
                		ConnectionConstants.URl, ConnectionConstants.USER, ConnectionConstants.PASS);
            } catch (Exception ex) {
                System.out.println("Failed to create the database connection."); 
            }
        } catch (Exception ex) {
            System.out.println("Driver not found."); 
        }
        return con;
    }
	
}
