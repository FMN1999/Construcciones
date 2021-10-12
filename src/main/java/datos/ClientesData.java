package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import entidades.Cliente;


public class ClientesData extends Coneccion {
	public Cliente getOne(int id) throws SQLException {
		//return a Product objet from data base
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("SELECT idCliente, razon_Social, telefono, email FROM clientes WHERE idCliente=?");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		rs.next();
		Cliente cli=new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		rs.close();
		ps.close();
		this.close();
		
		return cli;
	}
	
	public ArrayList<Cliente> getAll() throws SQLException {
		//return a Product objet ArrayList from data base 
		ArrayList<Cliente> clientes=new ArrayList<Cliente>();
		this.open();
		Statement st=this.getCon().createStatement();
		ResultSet rs=st.executeQuery("SELECT idCliente, razon_Social, telefono, email FROM clientes");
		while(rs.next()) {
			Cliente cli=new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			clientes.add(cli);
		}
		rs.close();
		st.close();
		this.close();
		
		return clientes;
	}
}
