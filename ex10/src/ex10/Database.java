package ex10;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	public static Connection connect() {
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"java",
					"pass");
			System.out.println("?��?��?���?");
		}catch(Exception e) {
			System.out.println("DB?���?:" + e.toString());
		}
		return con;
	}
}