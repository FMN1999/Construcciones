package datos;

import java.sql.*;

public class Coneccion {
	protected Connection con;
	
	public void open() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			setCon(DriverManager.getConnection("jdbc:mysql://localhost:3306/construccion","root","12345"));
			
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
