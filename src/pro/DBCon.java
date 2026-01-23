package pro;
import java.sql.*;

public class DBCon {
	Connection con=null;
	
	public DBCon () {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/parttimecat?allowLoadLocalInfile=true&serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Connection getConnection() {
		return con;
	}
	
}
