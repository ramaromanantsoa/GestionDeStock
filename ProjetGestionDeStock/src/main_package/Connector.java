package main_package;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
	private static String host = "localhost";
	private static String dbname = "stock";
	private static String addition = host+"/"+dbname;
	private static String dbuser = "root";
	private static String dbpasswd = "root1234";
	private static Connection connection;
	public static Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://"+addition, dbuser, dbpasswd);
		}
		catch (Exception e) {
			////
		}
		return connection;
	}
}