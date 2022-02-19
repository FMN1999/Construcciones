package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Material;
import entidades.Obra;
import entidades.Tarea;
import entidades.Tipo_Tarea;

public class TareaData extends Coneccion
{		
		//*************************************************
		//** Devuelve todos los materiales en existencia **
		//*************************************************
		/*public ArrayList<Tarea> getTareas(int idObra) throws SQLException, Exception{
			ArrayList<Tarea> tareas=new ArrayList<Tarea>();
			try {
				this.open();
				PreparedStatement ps= this.getCon().prepareStatement("SELECT idtarea, descripcion, cant_m2 FROM tareas WHERE  "
						+ "ORDER BY idtarea");
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					Tarea tarea=new Tarea(rs.getInt("idtarea"), rs.getString("descripcion"), rs.getFloat("cant_m2"), rs.getInt("id_presupuesto"), rs.getInt("id_tipo_tarea"));
					tareas.add(tarea);
				}
				rs.close();
				ps.close();
			}
			catch(Exception e) {
				throw e;
			}
			finally {
				this.close();
			}
			return tareas;
		}
		
		public ArrayList<Tarea> getTarea(int idTipo) throws SQLException{
			ArrayList<Tarea> tareas=new ArrayList<Tarea>();
			try {
				this.open();
				PreparedStatement ps=this.getCon().prepareStatement("SELECT idtarea, descripcion, cant_m2, id_presupuesto, id_tipo_tarea FROM tareas WHERE id_tipo_tarea = ?");
				ps.setInt(1, idTipo);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					Tarea tarea=new Tarea(rs.getInt("idtarea"), rs.getString("descripcion"), rs.getFloat("cant_m2"), rs.getInt("id_presupuesto"), rs.getInt("id_tipo_tarea"));
					tareas.add(tarea);
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				throw e;
			}
			finally {
				this.close();
			}
			return tareas;	
		}
		
		public void Registrar(Tarea t) throws Exception {
			try {
				this.open();
				PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO tareas (descripcion, cant_m2, id_presupuesto, id_tipo_tarea ) VALUES (?,?,null,?)");
				ps.setString(1, t.getDescripcion());
				ps.setFloat(2, t.getCant_m2());
				ps.setInt(3, t.getId_tipo_tarea());
				
				int n=ps.executeUpdate();
				ps.close();
				if(n==0) {
					throw new Exception("No se ha registrado la tarea, intentelo de nuevo");
				}
			}
			catch(Exception e) {
				throw e;
			}
			finally {
				this.close();
			}
		}
		
		public void Actualizar(Tarea t) throws Exception {
			try {
				this.open();
				PreparedStatement ps=this.getCon().prepareStatement("UPDATE tareas SET descripcion=?, cant_m2=?, id_presupuesto=null, id_tipo_tarea=?  WHERE idtarea=?");
				ps.setString(1, t.getDescripcion());
				ps.setFloat(2, t.getCant_m2());
				ps.setInt(3, t.getId_tipo_tarea());
				ps.setInt(4, t.getIdTarea());
				
				int n=ps.executeUpdate();
				ps.close();
				if(n==0) {
					throw new Exception("No se ha actualizado la tarea, intentelo de nuevo");
				}
			}
			catch(Exception e) {
				throw e;
			}
			finally {
				this.close();
			}
		}
		
		public void Eliminar(int id) throws Exception {
			try {
				this.open();
				PreparedStatement ps=this.getCon().prepareStatement("DELETE FROM tareas WHERE idtarea=?");
				ps.setInt(1, id);
				
				int n=ps.executeUpdate();
				ps.close();
				if(n==0) {
					throw new Exception("No se ha eliminado la tarea, intentelo de nuevo");
				}
			}
			catch(Exception e) {
				throw e;
			}
			finally {
				this.close();
			}
		}*/
		
}
