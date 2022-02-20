package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Obra;
import entidades.Presupuesto;

public class PresupuestoData extends Coneccion {

	public ArrayList<Presupuesto> getPresupuestos(Obra o) throws SQLException{
		ArrayList<Presupuesto> presups=new ArrayList<Presupuesto>();
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT p.idpresupuesto, p.fecha_emision, p.monto FROM presupuestos p WHERE o.id_obra=? ");
			ps.setInt(1, o.getIdObra());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				presups.add(new Presupuesto(rs.getInt("p.idpresupuesto"), rs.getDate("p.fecha_emision"), rs.getFloat("p.monto")));
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
}
