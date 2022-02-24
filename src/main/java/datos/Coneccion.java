package datos;

import java.sql.*;

public class Coneccion {
	protected Connection con;
	
	public void open() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
<<<<<<< HEAD
			setCon(DriverManager.getConnection("jdbc:mysql://localhost:3306/construccion","root","42330102"));
=======
			setCon(DriverManager.getConnection("jdbc:mysql://localhost:3306/construccion","root","1234"));
>>>>>>> 6ac2d4f118145915f9bc0297984f2a8ce6a0f8eb
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void close() {
		try {
			getCon().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public Connection getCon() {
		return con;
	}
	

	public void setCon(Connection con) {
		this.con = con;
	}
}
