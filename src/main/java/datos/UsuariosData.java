package datos;

import java.sql.*;
import java.util.ArrayList;

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
}