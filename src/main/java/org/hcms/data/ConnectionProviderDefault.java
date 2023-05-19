package org.hcms.data;
import java.sql.*;
public class ConnectionProviderDefault {
	public static Connection getCon()
	{
		try {
			String url="jdbc:mysql://localhost:3306/HealthcareMangaementSystem";
			String uname="test-user";
			String pass="password";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,uname,pass);
			return con;
		}catch(Exception e) {
			return null;
		}
	}
}
