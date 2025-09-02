package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
	public ThreadLocal<Connection> threadConnection = new ThreadLocal<>();
	public String url = "jdbc:mysql://localhost:3306/orangehrm";
	public String userName = "root";
	public String password = "";
	
	//Get or create a DB connection for the current thread.
	public Connection connect() throws SQLException
	{
		Connection conn = threadConnection.get();
		if(conn == null || conn.isClosed())
		{
			conn = DriverManager.getConnection(url, userName, password);
			threadConnection.set(conn);
		}
		return conn;
	}
	
	//Close and remove the DB connection for the current thread.
	public void disconnect() throws SQLException
	{
		Connection conn = threadConnection.get();
		conn.close();
		threadConnection.remove();
	}
}
