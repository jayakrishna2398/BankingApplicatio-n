package bankAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionUtil {
	public static  Connection getConnection()  {
		
	String driverClassName = "com.mysql.cj.jdbc.Driver";
	String url =  "jdbc:mysql://localhost:3306/banking";
	String username = "root";
	String password  = "root";
	
	Connection con = null;
	try {
	Class.forName(driverClassName);
	con = DriverManager.getConnection(url,username,password);
	//System.out.println(con);
	}
	catch(ClassNotFoundException e) {
		e.printStackTrace();
		throw new RuntimeException("unable to load the driver class");
	}
	catch(SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("unable to load DB exception");
	}
	return con;
	}

	
	}