
package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			PreparedStatement ps=this.getCon().prepareStatement("SELECT p.idpresupuesto, p.fecha_emision, p.monto, p.id_obra, p.fecha_aceptacion, p.fecha_cancelacion FROM presupuestos p WHERE p.id_obra=? ");
			ps.setInt(1, o.getIdObra());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				presups.add(new Presupuesto(rs.getInt("p.idpresupuesto"), rs.getDate("p.fecha_emision"), rs.getFloat("p.monto"), rs.getInt("p.id_obra"), rs.getDate("p.fecha_aceptacion"), rs.getDate("p.fecha_cancelacion")));
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
	
	public Presupuesto getOne(int id) throws Exception{
		try {
			this.open();
			PreparedStatement ps= this.getCon().prepareStatement("SELECT p.idpresupuesto, p.fecha_emision,"
					+ "p.fecha_aceptacion, p.id_obra, p.monto, p.fecha_cancelacion,p.id_obra from presupuestos p where idpresupuesto=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			Presupuesto p=new Presupuesto(rs.getInt("p.idpresupuesto"), rs.getDate("p.fecha_emision"), rs.getFloat("p.monto"), rs.getInt("p.id_obra"), rs.getDate("p.fecha_aceptacion"), rs.getDate("p.fecha_cancelacion"));
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
	
	public int Registrar(int idobra,Presupuesto p) throws Exception{
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO presupuestos(fecha_emision, monto, id_obra) "
					+ "VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, new java.sql.Date(p.getFecha_emision().getTime()));
			ps.setFloat(2, p.getMonto());
			ps.setInt(3, idobra);
			int n=ps.executeUpdate();
			if(n==0) {
				throw new Exception("No fue posible registrar el presupuesto");
			}
			ResultSet r=ps.getGeneratedKeys();
			if (r.next()) {
		         int idGenerado = r.getInt(1);
		         ps.close();
				return idGenerado;
			}
			throw new Exception("No fue posible recuperar la clave de presupuesto autogenerada");
			
		}catch (Exception e) {
			throw e;
		}
		finally {
			this.close();
		}
	}
	
	public void RegistrarEstadoPresupuesto(Presupuesto p) throws Exception{
		try {
			this.open();
			PreparedStatement ps;
			if(p.getFecha_aceptacion()!=null) {
				ps=this.getCon().prepareStatement("UPDATE presupuestos SET fecha_aceptacion=? WHERE (idpresupuesto=?)");
				ps.setDate(1, new java.sql.Date(p.getFecha_aceptacion().getTime()));
			}
			else {
				ps=this.getCon().prepareStatement("UPDATE presupuestos SET fecha_cancelacion=? WHERE (idpresupuesto=?)");
				ps.setDate(1, new java.sql.Date(p.getFecha_caencelacion().getTime()));
			}
			ps.setInt(2, p.getId_presupuesto());
			int n=ps.executeUpdate();
			if(n==0) {
				throw new Exception("No fue posible registrar su respuesta!");
			}
			ps.close();
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			this.close();
		}
	}
}
	