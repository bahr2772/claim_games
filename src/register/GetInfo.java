package register;

import java.sql.Connection;
import java.sql.DriverManager;

import sql.writeSql;

import java.sql.*;

public class GetInfo {

	//  Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "Pa$$word";

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/claimgames";


	// lost pass get from email or user name
	public String getLostPass(String username, String email){

		try
		{
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			PreparedStatement st = conn.prepareStatement("SELECT * FROM Users WHERE username = ? or email = ?");
			st.setString(1, username);
			st.setString(2, email);
			ResultSet r1=st.executeQuery();


			while(r1.next()) 
			{
				String user =  r1.getString("username");
				String dbEmail = r1.getString("email");
				String password = r1.getString("password");

				if(username.equalsIgnoreCase(user) || email.equals(dbEmail) ) 
				{

					String foundUser = "User found. \n Your password is: " + password;
					return foundUser;
				}

			}
		}
		
		catch (SQLException e) 
		{
			System.out.println("SQL Exception: "+ e.toString());
		} 
		catch (ClassNotFoundException cE) 
		{
			System.out.println("Class Not Found Exception: "+ cE.toString());
		}

		return "User not found";
	}


	// login checker  \\
	public int credChecker(String username, String password){

		try
		{
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			PreparedStatement st = conn.prepareStatement("SELECT * FROM Users WHERE username = ? and password = ?");
			st.setString(1, username);
			st.setString(2, password);
			ResultSet r1=st.executeQuery();

			String user;
			String pass;

			while(r1.next()) 
			{
				user =  r1.getString("username");
				pass = r1.getString("password");

				if(username.equalsIgnoreCase(user) && password.equals(pass) ) 
				{
					return 1;
				}
				if(username.equalsIgnoreCase(user) && !password.equals(pass)){
					return 2;
				}

			}
		}

		catch (SQLException e) 
		{
			System.out.println("SQL Exception: "+ e.toString());
		} 
		catch (ClassNotFoundException cE) 
		{
			System.out.println("Class Not Found Exception: "+ cE.toString());
		}


		return 3;

	}


	// check user name exist for register \\
	public boolean usernameChecker(String username){

		boolean usernameExists = false;

		try
		{
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			PreparedStatement st = conn.prepareStatement("SELECT * FROM Users WHERE username = ? ");
			st.setString(1, username);
			ResultSet r1=st.executeQuery();

			String user;

			while(r1.next()) 
			{
				user =  r1.getString("username");
				if(username.equalsIgnoreCase(user)) 
				{
					System.out.println("It already exists");
					usernameExists = true;
				}
			}
		}

		catch (SQLException e) 
		{
			System.out.println("SQL Exception: "+ e.toString());
		} 
		catch (ClassNotFoundException cE) 
		{
			System.out.println("Class Not Found Exception: "+ cE.toString());
		}

		return usernameExists;
	}


	public int newRegister(String password1, String password2, String username, String email){

		boolean check = usernameChecker(username);


		// user name found (true)
		if(check) 
			return 1;

		// check user name is smaller then 4
		if(username.length() < 4 ){
			System.out.println("username to small");
			return 2;
			//password to short or contains invalid chars
		}else if((password1.length() < 2) || password1.contains(" ") || password1.contains(",") || password1.contains("'") || password1.contains("\\")||
				password1.contains("/") || password1.contains(";") || password1.contains(":") || password1.contains("}") ||
				password1.contains("{") || password1.contains("(") || password1.contains(")") || password1.contains(".") || password1.contains("\"")){
			System.out.println("password to short or contains invalid chars");
			return 3;
			// passwords mismatch
		}else if(!password1.equals(password2)){
			System.out.println("passwords not match");
			return 4;
			// check passwords match
		}else if(username.contains(" ") || username.contains(",") || username.contains("'") || username.contains("\\")||
				 username.contains("/") || username.contains(";") || username.contains(":") || username.contains("}") ||
			  	 username.contains("{") || username.contains("(") || username.contains(")") || username.contains(".") || username.contains("\"")){
			System.out.println("user name has space");
			return 5;
		}else if(!email.contains("@")){
			System.out.println("invaild email");
			return 6;
		}else {
		}
		System.out.println("user sent");
		@SuppressWarnings("unused")
		writeSql newUser = new writeSql (username, password1, email);
		System.out.println("User created");
		return 7;
	}


}//close class





