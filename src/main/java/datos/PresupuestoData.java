
package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Maquinaria;
import entidades.Material;
import entidades.Material_a_usar;
import entidades.Obra;
import entidades.Presupuesto;
import entidades.Tarea;
import entidades.Tipo_Tarea;

public class PresupuestoData extends Coneccion {

	public ArrayList<Presupuesto> getPresupuestos(Obra o) throws SQLException{
		ArrayList<Presupuesto> presups=new ArrayList<Presupuesto>();
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT p.idpresupuesto, p.fecha_emision, p.monto, p.id_obra FROM presupuestos p WHERE p.id_obra=? ");
			ps.setInt(1, o.getIdObra());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				presups.add(new Presupuesto(rs.getInt("p.idpresupuesto"), rs.getDate("p.fecha_emision"), rs.getFloat("p.monto"), rs.getInt("p.id_obra")));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return presups;
	}
	
	public ArrayList<Material_a_usar> getMateriales(Presupuesto p) throws SQLException{
		ArrayList<Material_a_usar> ms=new ArrayList<Material_a_usar>();
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT p.idpresupuesto, t.idtarea, t.descripcion, t.cant_m2, tt.idtipo_tarea, tt.descripcion, ptt.precio_m2, m.idmaterial, m.descripcion, mt.cant_a_usar, pm.precio\r\n"
					+ "from presupuestos p\r\n"
					+ "inner join tareas t on t.id_presupuesto=p.idpresupuesto\r\n"
					+ "inner join tipos_tarea tt on t.id_tipo_tarea = tt.idtipo_tarea\r\n"
					+ "inner join precios_tipo_tarea ptt on tt.idtipo_tarea = ptt.id_tipo_tarea_\r\n"
					+ "inner join materiales_tareas mt on mt.id_tarea_=t.idtarea\r\n"
					+ "inner join materiales m on m.idmaterial=mt.id_material_\r\n"
					+ "inner join precios_material pm on pm.id_material = m.idmaterial\r\n"
					+ "where p.idpresupuesto=?");
			ps.setInt(1, p.getId_presupuesto());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Material m = new Material(rs.getInt("m.idmaterial"), rs.getString("m.descripcion"), rs.getFloat("pm.precio"));
				Tipo_Tarea tt= new Tipo_Tarea(rs.getInt("tt.idtipo_tarea"), rs.getString("tt.descripcion"), rs.getFloat("ptt.precio_m2"));
				Tarea t = new Tarea(rs.getInt("t.idtarea"), rs.getString("t.descripcion"), rs.getFloat("t.cant_m2"), tt);
				ms.add(new Material_a_usar(m,t,tt,rs.getInt("mt.cant_a_usar")));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return ms;
	}
	
	public ArrayList<Maquinaria> getMaquinarias(Presupuesto p) throws SQLException{
		ArrayList<Maquinaria> ms=new ArrayList<Maquinaria>();
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT p.idpresupuesto, t.idtarea, m.idmaquina, m.descripcion, m.precioHora\r\n"
					+ "from presupuestos p\r\n"
					+ "inner join tareas t on t.id_presupuesto=p.idpresupuesto\r\n"
					+ "inner join tareas_maquinas tm on tm.id_tarea__=t.idtarea\r\n"
					+ "inner join maquinarias m on m.idmaquina=mt.id_maquina__\r\n"
					+ "where p.idpresupuesto=? ");
			ps.setInt(1, p.getId_presupuesto());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ms.add(new Maquinaria(rs.getInt("m.idmaquina"), rs.getString("m.descripcion"), rs.getFloat("m.precioHora")));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return ms;
	}
	
	public Presupuesto getOne(int id) throws Exception{
		try {
			this.open();
			PreparedStatement ps= this.getCon().prepareStatement("SELECT p.idpresupuesto, p.fecha_emision,"
					+ "p.fecha_aceptacion, p.id_obra, p.monto, p.fecha_cancelacion,p.id_obra from presupuestos p where idpresupuesto=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			Presupuesto p=new Presupuesto(rs.getInt("p.idpresupuesto"), rs.getDate("p.fecha_emision"), rs.getFloat("p.monto"), rs.getInt("p.id_obra"));
			rs.close();
			ps.close();
			return p;
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			this.close();
		}
	}
	
	public ArrayList<Tarea> getTareas(Presupuesto p) throws SQLException{
		ArrayList<Tarea> ts=new ArrayList<Tarea>();
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT tt.idtipo_tarea, tt.descripcion, t.idtarea, t.descripcion, t.cant_m2, ptt.precio_m2\r\n"
					+ "FROM presupuestos p \r\n"
					+ "inner join tareas t on p.idpresupuesto = t.id_presupuesto\r\n"
					+ "inner join tipos_tarea tt on t.id_tipo_tarea = tt.idtipo_tarea\r\n"
					+ "inner join precios_tipo_tarea ptt on tt.idtipo_tarea = ptt.id_tipo_tarea_\r\n"
					+ "where p.idpresupuesto=? ");
			ps.setInt(1, p.getId_presupuesto());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Tipo_Tarea tt= new Tipo_Tarea(rs.getInt("tt.idtipo_tarea"), rs.getString("tt.descripcion"), rs.getFloat("ptt.precio_m2"));
				ts.add(new Tarea(rs.getInt("t.idtarea"), rs.getString("t.descripcion"), rs.getFloat("t.cant_m2"), tt));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return ts;
	}
}
	