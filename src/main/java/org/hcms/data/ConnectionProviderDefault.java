package org.hcms.data;
import org.hcms.configuration.Config;

import java.sql.*;
import java.util.Optional;

public final class ConnectionProviderDefault {
	private ConnectionProviderDefault() {}
	public static Optional<Connection> getCon() {
		try {
			Config config = Config.getInstance();
			String url=  config.getProperty(Config.DB_URL) ;
			String uname = config.getProperty(Config.DB_USER);
			String pass = config.getProperty(Config.DB_PASSWORD);
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println(url + " " + pass + " " + " " + uname);
			Connection con=DriverManager.getConnection(url,uname,pass);
			return Optional.of(con);
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
}
