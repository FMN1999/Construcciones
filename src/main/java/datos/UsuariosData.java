package datos;

import java.sql.*;
import java.util.ArrayList;

import entidades.Trabajador;
import entidades.Usuario;

public class UsuariosData extends Coneccion {
	public Usuario getOne(int id) throws SQLException {
		//return a Product objet from data base
		//int id, String nombre, String apellido, String email, String password, int cuil, String tipo
		Usuario usr=null;
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT idusuario, nombre, apellido, email,"
					+ " password, cuil, descripcion FROM usuario "
					+ "INNER JOIN tipo_usuario ON id_tipo=idtipo_usuario WHERE idusuario=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			usr = new Usuario(rs.getInt("idusuario"), rs.getString("nombre"), rs.getString("apellido"),
					rs.getString("email"), rs.getString("password"), rs.getLong("cuil"), rs.getString("descripcion"));
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return usr;
		
	}
	
	public ArrayList<Usuario> getAll() throws SQLException {
		//return a Product objet ArrayList from data base
		ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
		try {
			this.open();
			Statement st=this.getCon().createStatement();
			ResultSet rs=st.executeQuery("SELECT idusuario, nombre, apellido, email,"
					+ " password, cuil, descripcion FROM usuario "
					+ "INNER JOIN tipo_usuario ON id_tipo=idtipo_usuario");
			while(rs.next()) {
				Usuario usr=new Usuario(rs.getInt("idusuario"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("email"), rs.getString("password"), rs.getLong("cuil"), rs.getString("descripcion"));
				usuarios.add(usr);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		
		return usuarios;
	}
	
	/**
	 *@see -> Recupera usuario segun direccion de correo electronico 
	 */
	public Usuario get(String dir) throws SQLException {
		//int id, String nombre, String apellido, String email, String password, int cuil, String tipo
		Usuario usr=null;
		try {
			String str="SELECT idusuario, nombre, apellido, email, password, cuil, "
					+ "descripcion FROM usuario INNER JOIN tipo_usuario "
					+ "ON id_tipo=idtipo_usuario WHERE email=?";
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement(str);
			ps.setString(1, dir);
			ResultSet rs=ps.executeQuery();
			rs.next();
			usr = new Usuario(rs.getInt("idusuario"), rs.getString("nombre"), rs.getString("apellido"),
					rs.getString("email"), rs.getString("password"), rs.getLong("cuil"), rs.getString("descripcion"));
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return usr;
	}
	/**
	 *@see -> Recupera usuario segun cuil 
	 */
	public Usuario get(long cuil) throws SQLException {
		//int id, String nombre, String apellido, String email, String password, int cuil, String tipo
		Usuario usr=null;
		try {
			String str="SELECT idusuario, nombre, apellido, email, password, cuil, "
					+ "descripcion FROM usuario INNER JOIN tipo_usuario "
					+ "ON id_tipo=idtipo_usuario WHERE cuil=?";
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement(str);
			ps.setLong(1, cuil);
			ResultSet rs=ps.executeQuery();
			rs.next();
			usr = new Usuario(rs.getInt("idusuario"), rs.getString("nombre"), rs.getString("apellido"),
					rs.getString("email"), rs.getString("password"), rs.getLong("cuil"), rs.getString("descripcion"));
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return usr;
	}

	public void Registrar(Usuario u) throws Exception{
		//las verificaciones de existencia ya fueron realizadas previamente
		//en la capa de logica
		int n=0;
		
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO usuario(nombre, apellido, cuil, id_tipo, password, email) "
					+ "VALUES (?,?,?,?,?,?) ");
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getApellido());
			ps.setLong(3, u.getCuil());
			/* Tipos de usuario:
			 * Administrador-> 1
			 * Cliente-> 2
			 * Trabajador-> 3  
			 * */
			switch(u.getTipo()) {
				case "Administrador":{
					ps.setInt(4, 1); 
					break;
				}
				case "Cliente":{
					ps.setInt(4, 2); 
					break;
				}
				case "Trabajador":{
					ps.setInt(4, 3);
					break;
				}
			}
			
			ps.setString(5, u.getPassword());
			ps.setString(6, u.getEmail());
			
			n=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new Exception("Un error ocurrio mientras se intentaba registrar el usuario: "+e.getMessage());
		}
		finally {
			this.close();
			if(n==0) {
				throw new Exception("Un error ocurrio mientras se intentaba registrar el usuario");
			}
		}
			
		}

}