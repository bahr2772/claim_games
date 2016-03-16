package sql;
import java.sql.*;

public class writeSql {

	
	   //  Database credentials
	   static final String SQLUSER = "root";
	   static final String SQLPASS = "root";
	   
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/claimgames";

	
	public writeSql(String username, String password, String email){
	    
		  try {
	      // create a mysql database connection
	      Class.forName(JDBC_DRIVER);
	      Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);
	    
	 
	      // the mysql insert statement
	      String query = " INSERT INTO Users (id, username, password, email)"
	        + " values (?, ?, ?, ?)";
	 
	      // create the mysql insert prepared statement
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, null);
	      preparedStmt.setString (2, username);
	      preparedStmt.setString (3, password);
	      preparedStmt.setString (4, email);
	 
	      // execute the prepared statement
	      preparedStmt.execute();
	       
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	  }
	}
	

