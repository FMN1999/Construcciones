package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entidades.Trabajador;

public class TrabajadorData extends Coneccion {
	
	public ArrayList<Trabajador> getOficiales() throws Exception{
		ArrayList<Trabajador> trs=new ArrayList<Trabajador>();
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("SELECT usuario.idusuario,\n" //1
				+ "usuario.nombre,\n" //2
				+ "usuario.apellido,\n" //3
				+ "usuario.email,\n" //4
				+ "usuario.password,\n" //5
				+ "trabajadores.cuil,\n" //6
				+ "tipo_trabajador.descripcion,\n" //7
				+ "trabajadores.tipo_doc,\n" //8
				+ "trabajadores.n_doc,\n" //9
				+ "trabajadores.fecha_nac,\n" //10
				+ "trabajadores.disponoble,\n" //11
				+ "tipo_trabajador.precioHora \n" //12
				+ "FROM construccion.trabajadores \n"
				+ "LEFT JOIN usuario on trabajadores.cuil=usuario.cuil\n"
				+ "INNER JOIN tipo_trabajador on trabajadores.tipo_trabajador=tipo_trabajador.idtipo_trabajador \n"
				+ "WHERE tipo_trabajador.descripcion='Oficial'");
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			boolean d;
			if(rs.getInt(11)==1) {
				d= true;
				} 
			else {
				d= false;
				}
			
			Trabajador t=new Trabajador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getLong(6),
					rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDate(10), d, "Oficial", rs.getFloat(12));
			trs.add(t);
		}
		rs.close();
		ps.close();
		this.close();
		return trs;
	}
	
	public ArrayList<Trabajador> getObreros() throws Exception{
		ArrayList<Trabajador> trs=new ArrayList<Trabajador>();
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("SELECT usuario.idusuario,\n"
				+ "usuario.nombre,\n"
				+ "usuario.apellido,\n"
				+ "usuario.email,\n"
				+ "usuario.password,\n"
				+ "trabajadores.cuil,\n"
				+ "tipo_trabajador.descripcion,\n"
				+ "trabajadores.tipo_doc,\n"
				+ "trabajadores.n_doc,\n"
				+ "trabajadores.fecha_nac,\n"
				+ "trabajadores.disponoble,\n"
				+ "tipo_trabajador.precioHora \n"
				+ "FROM construccion.trabajadores \n"
				+ "LEFT JOIN usuario on trabajadores.cuil=usuario.cuil\n"
				+ "INNER JOIN tipo_trabajador on trabajadores.tipo_trabajador=tipo_trabajador.idtipo_trabajador \n"
				+ "WHERE tipo_trabajador.descripcion='Obrero'");
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			boolean d;
			if(rs.getInt(11)==1) {
				d= true;
				} 
			else {
				d= false;
				}
			Trabajador t=new Trabajador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getLong(6),
					rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDate(10), d, "Obrero", rs.getFloat(12));
			trs.add(t);
		}
		rs.close();
		ps.close();
		this.close();
		return trs;
	}
	
	public void Registrar(Trabajador t) throws Exception{
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO usuario(nombre, apellido, cuil, id_tipo, password, email) "
				+ "VALUES (?,?,?,?,?,?) ");
		ps.setString(1, t.getNombre());
		ps.setString(2, t.getApellido());
		ps.setLong(3, t.getCuil());
		
		//revisar el tipo...
		ps.setInt(4, 0);
		ps.setString(5, t.getPassword());
		ps.setString(6, t.getEmail());
		
		//falta registrar ademas en la tabla de trabajador
	}
	
	public void ActualizarDatos(Trabajador t) throws Exception{
		
	}
	
	public void Eliminar(int id) throws Exception{
		
	}
}
