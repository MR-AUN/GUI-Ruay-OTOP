package ruay.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		// "oracle.jdbc.driver.OracleDriver"
		String driverName = "com.mysql.cj.jdbc.Driver";
		// "jdbc:oracle:thin:@10.30.2.50:1521:prog2"
//		String connectionURL = "jdbc:mysql://localhost:3306/project_ruay";
//		String username = "root";
//		String password = "";	
		String connectionURL = "jdbc:mysql://202.28.34.205:3306/db63011211014";
		String username = "63011211014";
		String password = "63011211014";	

		System.out.println("-------- MariaDB JDBC Connection Testing ------");

		try {

			Class.forName(driverName);

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your MariaDB JDBC Driver?");
			e.printStackTrace();
		}

		Connection connection = null;
		System.out.println("MariaDB JDBC Driver Registered!");

		try {

			connection = DriverManager.getConnection(connectionURL, username, password);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		System.out.println("Connected to database");
		return connection;
	}
}
