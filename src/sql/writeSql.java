package sql;
import java.sql.*;

public class writeSql {

	public writeSql(String username, String password, String email){

		try {
			// create a mysql database connection
			Class.forName(DbInfo.JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DbInfo.DB_URL, DbInfo.SQLUSER, DbInfo.SQLPASS);

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


