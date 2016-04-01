package sql;
//STEP 1. Import required packages
import java.sql.*;

public class readSql {

   public void displayTable() {
   Statement stmt = null;
   Connection conn = null;
   try{
	   
	   Class.forName(DbInfo.JDBC_DRIVER);
		conn = DriverManager.getConnection(DbInfo.DB_URL, DbInfo.SQLUSER, DbInfo.SQLPASS);

      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT id, username, password, email FROM Users";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("id");
         String username = rs.getString("username");
         String password = rs.getString("password");
         String email = rs.getString("email");

         //Display values
         System.out.print("ID: " + id);
         System.out.print(", Username: " + username);
         System.out.print(", Password: " + password);
         System.out.println(", Email: " + email);
      }
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample