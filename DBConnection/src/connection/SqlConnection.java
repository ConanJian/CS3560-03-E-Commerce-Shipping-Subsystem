package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

public class SqlConnection 
{
	private Connection connection;
	public SqlConnection(String url, String user, String pass)
	{
		try 
		{
			connection = DriverManager.getConnection(url, user, pass);
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	public void GetCustomer()
	{
		try(Statement stmt = connection.createStatement();)
		{
			String query = "Select * from Customer;";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				System.out.println(rs.getString("customerId")+"FirstName: "+rs.getString("firstName"));
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
}