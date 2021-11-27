package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entidades.Trabajador;

public class TrabajadorData extends Coneccion {
	
	public ArrayList<Trabajador> getOficiales() throws Exception{
		ArrayList<Trabajador> trs=new ArrayList<Trabajador>();
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("SELECT trabajadores.cuil,"
				+ "    trabajadores.tipo_doc,"
				+ "    trabajadores.n_doc,\n"
				+ "    trabajadores.nombre_completo,"
				+ "    trabajadores.fecha_nac,"
				+ "    trabajadores.disponoble,"
				+ "    tipo_trabajador.descripcion,"
				+ "		tipo_trabajador.precioHora "
				+ " FROM construccion.trabajadores "
				+ " INNER JOIN tipo_trabajador on trabajadores.tipo_trabajador=tipo_trabajador.idtipo_trabajador "
				+ " WHERE tipo_trabajador.descripcion='Oficial'");
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			boolean d;
			if(rs.getInt(6)==1) {
				d= true;
				} 
			else {
				d= false;
				}
			
			Trabajador t=new Trabajador(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getString(4), rs.getDate(5), d, rs.getString(7), rs.getFloat(8));
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
		PreparedStatement ps=this.getCon().prepareStatement("SELECT trabajadores.cuil,\n"
				+ "    trabajadores.tipo_doc,"
				+ "    trabajadores.n_doc,"
				+ "    trabajadores.nombre_completo,"
				+ "    trabajadores.fecha_nac,"
				+ "    trabajadores.disponoble,"
				+ "    tipo_trabajador.descripcion,"
				+ " 	tipo_trabajador.precioHora "
				+ " FROM construccion.trabajadores"
				+ " INNER JOIN tipo_trabajador on trabajadores.tipo_trabajador=tipo_trabajador.idtipo_trabajador"
				+ " WHERE tipo_trabajador.descripcion='Obrero'");
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			boolean d;
			if(rs.getInt(6)==1) {
				d= true;
				} 
			else {
				d= false;
				}
			
			Trabajador t=new Trabajador(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getString(4), rs.getDate(5), d, rs.getString(7), rs.getFloat(8));
			trs.add(t);
		}
		rs.close();
		ps.close();
		this.close();
		return trs;
	}
	
	public void Registrar(Trabajador t) throws Exception{
		
	}
	
	public void ActualizarDatos(Trabajador t) throws Exception{
		
	}
	
	public void Eliminar(int id) throws Exception{
		
	}
}
