package datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import entidades.Maquinaria;
import entidades.Tarea;
import entidades.Tipo_Tarea;

import java.util.HashMap;

import entidades.Trabajador;

public class TrabajadorData extends Coneccion {
	
	public Trabajador getOne(long cuil) throws SQLException {
		Trabajador t=null;
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("select t.cuil, t.tipo_doc, t.n_doc, t.fecha_nac, t.disponoble, t.tipo_trabajador,\r\n"
					+ "u.idusuario, u.nombre, u.apellido, u.email, u.password, u.id_tipo\r\n"
					+ "from trabajadores t \r\n"
					+ "inner join usuario u on u.cuil = t.cuil\r\n"
					+ "where t.cuil = ?;");
			ps.setLong(1, cuil);
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			t= new Trabajador(rs.getInt("u.idusuario"), rs.getString("u.nombre"), rs.getString("u.apellido"), 
					rs.getString("u.email"), rs.getString("u.password"),rs.getLong("t.cuil"), "tipo", rs.getString("t.tipo_doc"),
					rs.getLong("t.n_doc"), rs.getDate("t.fecha_nac"), rs.getBoolean("t.disponoble"),rs.getString("t.tipo_trabajador"),
					00);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return t;
	}
	
	public Map<Tarea, Float> setTareaEmpleados(Trabajador t){
		Map<Tarea, Float> tem = new HashMap<Tarea,Float>();
		this.open();
		try {
			PreparedStatement ps=this.getCon().prepareStatement("select t.cuil, ta.idtarea, ta.cant_m2, ta.descripcion, tt.cant_horas_trabajadas,\r\n"
					+ "tipo.idtipo_tarea, tipo.descripcion, ptt.precio_m2,\r\n"
					+ "(ttra.precioHora * tt.cant_horas_trabajadas) as monto_a_pagar\r\n"
					+ "from trabajadores t\r\n"
					+ "inner join trabajador_tarea tt on tt.cuil_trabajador = t.cuil\r\n"
					+ "inner join tareas ta on ta.idtarea = tt.id_tarea_asignada\r\n"
					+ "inner join tipos_tarea tipo on tipo.idtipo_tarea = ta.id_tipo_tarea\r\n"
					+ "inner join precios_tipo_tarea ptt on ptt.id_tipo_tarea_ = tipo.idtipo_tarea\r\n"
					+ "inner join tipo_trabajador ttra on ttra.idtipo_trabajador = t.tipo_trabajador\r\n"
					+ "where (ptt.fecha_desde= (select max(pt.fecha_desde) from precios_tipo_tarea pt where tipo.idtipo_tarea=pt.id_tipo_tarea_)) \r\n"
					+ "and t.cuil=?;");
			ps.setLong(1, t.getCuil());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Tipo_Tarea tipo = new Tipo_Tarea(rs.getInt("tipo.idtipo_tarea"), rs.getString("tipo.descripcion"),rs.getFloat("ptt.precio_m2"));
				Tarea tarea = new Tarea(rs.getInt("ta.idtarea"), rs.getString("ta.descripcion"), rs.getFloat("ta.cant_m2"), tipo);
				tem.put(tarea, rs.getFloat("monto_a_pagar"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		return tem;		
	}
	
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
