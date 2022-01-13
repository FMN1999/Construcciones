package main.java.datos;


import java.sql.PreparedStatement;
import main.java.entidades.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ClientesData extends Coneccion {
	public ArrayList<Cliente> getAll() throws SQLException {
		//return a Product objet from data base
		ArrayList<Cliente> cal=new ArrayList<Cliente>();
		this.open();
		PreparedStatement ps = this.getCon().prepareStatement("SELECT u.idusuario,\n"
				+ "u.nombre,\n"
				+ "u.apellido,\n"
				+ "u.email,\n"
				+ "u.password,\n"
				+ "u.cuil,\n"
				+ "c.razon_social,\n"
				+ "c.telefono,\n"
				+ "FROM usuario u \n"
				+ "INNER JOIN clientes c u on c.cuil=u.cuil");
		ResultSet rs=ps.executeQuery();
		rs.next();
		Cliente c = new Cliente(rs.getInt("u.idusuario"), rs.getString("u.nombre"), rs.getString("u.apellido"),
				rs.getString("u.email"), rs.getString("u.password"), rs.getLong("u.cuil"),
				"Cliente", rs.getString("c.razon_social"), rs.getString("c.telefono"));
		cal.add(c);
		rs.close();
		ps.close();
		this.close();
		
		return cal;
	}
	
	public void Registrar(Cliente c) throws Exception{
		int n = 0;
		
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO clientes(cuil,\n"
					+ "telefono, razon_social) \n"
					+ "VALUES (?,?,?) ");
			ps.setLong(1, c.getCuil());
			ps.setString(2, c.getTelefono());
			ps.setString(3, c.getRazonSocial());
			
			n=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new Exception("Un error ocurrio mientras se intentaban registrar los datos del cliente: "+e.getMessage());
		}
		finally {
			this.close();
			if(n==0) {
				throw new Exception("Un error ocurrio mientras se intentaban registrar los datos del cliente");
				//deberia eliminarse el usuario en caso de error al registrar en clientes
			}
			
		}
	}
	
	public void ActualizarDatos(Cliente c) throws Exception{
		String sql="UPDATE clientes SET cuil=?, telefono=?, razon_social=? WHERE cuil=?"; 
		int n=0;
		this.open();
		try {
			PreparedStatement ps=this.getCon().prepareStatement(sql);
			ps.setLong(1, c.getCuil());
			ps.setString(2, c.getRazonSocial());
			ps.setString(3, c.getRazonSocial());
			
			n=ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("No fue posible actualizar los cambios en el cliente "+c.getApellido()+" "+c.getNombre()+":"+e.getMessage());
		}
		finally {
			this.close();
			
			if(n==0) {
				throw new Exception("No fue posible actualizar los cambios en el trabajador "+c.getApellido()+" "+c.getNombre());
			}
		}
	}
	
	public void Eliminar(long cuil) throws Exception{
		String sql="DELETE FROM clientes WHERE (cuil=?)";
		int n=0;
		this.open();
		try {
			PreparedStatement ps=this.getCon().prepareStatement(sql);
			ps.setLong(1, cuil);
			
			n=ps.executeUpdate();
			
			ps.close();
		}catch(SQLException e) {
			throw new Exception("No fue posible eliminar al cliente: "+e.getMessage());
		}
		finally {
			this.close();
			if(n==0) {
				throw new Exception("No fue posible eliminar al cliente");
			}
		}
	}
}
