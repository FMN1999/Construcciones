package datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entidades.Trabajador;
import entidades.Tarea;
import entidades.Tipo_Tarea;

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
			if(rs.getInt("trabajadores.disponoble")==1) {
				d= true;
				} 
			else {
				d= false;
				}
			
			Trabajador t=new Trabajador(rs.getInt("usuario.idusuario"), rs.getString("usuario.nombre"), rs.getString("usuario.apellido"),
					rs.getString("usuario.email"), rs.getString("usuario.password"), rs.getLong("trabajadores.cuil"),
					rs.getString("tipo_trabajador.descripcion"), rs.getString("trabajadores.tipo_doc"), rs.getLong("trabajadores.n_doc"),
					rs.getDate("trabajadores.fecha_nac"), d, "Oficial", rs.getFloat("tipo_trabajador.precioHora"));
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
			if(rs.getInt("trabajadores.disponoble")==1) {
				d= true;
				} 
			else {
				d= false;
				}
			Trabajador t=new Trabajador(rs.getInt("usuario.idusuario"), rs.getString("usuario.nombre"), rs.getString("usuario.apellido"),
					rs.getString("usuario.email"), rs.getString("usuario.password"), rs.getLong("trabajadores.cuil"),
					rs.getString("tipo_trabajador.descripcion"), rs.getString("trabajadores.tipo_doc"), rs.getLong("trabajadores.n_doc"),
					rs.getDate("trabajadores.fecha_nac"), d, "Obrero", rs.getFloat("tipo_trabajador.precioHora"));
			trs.add(t);
		}
		rs.close();
		ps.close();
		this.close();
		return trs;
	}
	
	public void Registrar(Trabajador t) throws Exception{
		//las verificaciones de existencia ya fueron realizadas previamente
		//en la capa de logica
		int n=0;
		
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO trabajadores(cuil, tipo_doc, n_doc, fecha_nac,"
					+ " disponoble, tipo_trabajador) "
					+ "VALUES (?,?,?,?,?,?) ");
			ps.setLong(1, t.getCuil());
			ps.setString(2, t.getTipo_doc());
			ps.setLong(3, t.getN_doc());
			
			//ps.setDate(4, (Date) t.getFechaNac());
			java.sql.Date sqlDate = new java.sql.Date(t.getFechaNac().getTime());
			ps.setDate(4, sqlDate);
			
			if(t.isDisponible()) {
				ps.setInt(5, 1); //--> True
			}
			else {
				ps.setInt(5, 0);
			}
			switch(t.getTipoEmpleado()){
			case "Obrero":{
				ps.setInt(6, 2);
				break;
			}
			case "Oficial":{
				ps.setInt(6, 1);
				break;
			}
			}
			
			n=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new Exception("Un error ocurrio mientras se intentaban registrar los datos del empleado: "+e.getMessage());
		}
		finally {
			this.close();
			if(n==0) {
				throw new Exception("Un error ocurrio mientras se intentaban registrar los datos del empleado");
				//deberia eliminarse el usuario en caso de error al registrar en trabajadores
			}
			
		}
	}
	
	public void ActualizarDatos(Trabajador t) throws Exception{
		String sql="UPDATE trabajadores SET tipo_doc=?, n_doc=?, fecha_nac=?, disponoble=?, tipo_trabajador=? WHERE cuil=?"; 
		int n=0;
		this.open();
		try {
			PreparedStatement ps=this.getCon().prepareStatement(sql);
			ps.setString(1, t.getTipo_doc());
			ps.setLong(2, t.getN_doc());
			java.sql.Date sqlDate = new java.sql.Date(t.getFechaNac().getTime());
			ps.setDate(3, sqlDate);
			if(t.isDisponible()) {
				ps.setInt(4, 1);
			}else {
				ps.setInt(4, 0);
			}
			if(t.getTipoEmpleado().equalsIgnoreCase("Oficial")) {
				ps.setInt(5, 1);
			}else {
				//Obrero
				ps.setInt(5, 2);
			}
			ps.setLong(6, t.getCuil());
			
			n=ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("No fue posible actualizar los cambios en el trabajador "+t.getApellido()+" "+t.getNombre()+":"+e.getMessage());
		}
		finally {
			this.close();
			
			if(n==0) {
				throw new Exception("No fue posible actualizar los cambios en el trabajador "+t.getApellido()+" "+t.getNombre());
			}
		}
	}
	
	public void Eliminar(long cuil) throws Exception{
		String sql="DELETE FROM trabajadores WHERE (cuil=?)";
		int n=0;
		this.open();
		try {
			PreparedStatement ps=this.getCon().prepareStatement(sql);
			ps.setLong(1, cuil);
			
			n=ps.executeUpdate();
			
			ps.close();
		}catch(SQLException e) {
			throw new Exception("No fue posible eliminar al trabajador: "+e.getMessage());
		}
		finally {
			this.close();
			if(n==0) {
				throw new Exception("No fue posible eliminar al trabajador");
			}
		}
	}
}
