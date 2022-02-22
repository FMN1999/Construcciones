
package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Maquinaria;
import entidades.Material;
import entidades.Obra;
import entidades.Presupuesto;

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
	
	public ArrayList<Material> getMateriales(Presupuesto p) throws SQLException{
		ArrayList<Material> ms=new ArrayList<Material>();
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT p.idpresupuesto, t.idtarea, m.idmaterial, m.descripcion, mt.cant_a_usar, pm.precio \r\n"
					+ "from presupuestos p\r\n"
					+ "inner join tareas t on t.id_presupuesto=p.idpresupuesto\r\n"
					+ "inner join materiales_tareas mt on mt.id_tarea_=t.idtarea\r\n"
					+ "inner join materiales m on m.idmaterial=mt.id_material_\r\n"
					+ "inner join precios_material pm on m.idmaterial=pm.id_material\r\n"
					+ "where p.idpresupuesto=? ");
			ps.setInt(1, p.getId_presupuesto());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ms.add(new Material(rs.getInt("m.idmaterial"), rs.getString("m.descripcion"), rs.getFloat("pm.precio")));
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
					+ "p.fecha_aceptacion, p.id_obra, p.monto, p.fecha_cancelacion from presupuestos, p.id_obra p where idpresupuesto=?");
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
}
	