package modul;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	Connection con = null;
	String url = "jdbc:mysql:"+System.getenv("homepath")+"/WissLK_MC.db";
	public void test() {
		try{
		con = DriverManager.getConnection(url);
		
		}
		catch(SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
