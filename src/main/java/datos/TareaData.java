package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Material;
import entidades.Obra;
import entidades.Presupuesto;
import entidades.Tarea;
import entidades.Tipo_Tarea;

public class TareaData extends Coneccion
{		
		//*************************************************
		//** Devuelve todos los materiales en existencia **
		//*************************************************
		public ArrayList<Tarea> getTareas(Presupuesto p) throws SQLException, Exception{
			ArrayList<Tarea> tareas=new ArrayList<Tarea>();
			try {
				this.open();
				PreparedStatement ps= this.getCon().prepareStatement("SELECT t.idtarea, t.descripcion, t.cant_m2, tt.idtipo_tarea, tt.descripcion, ifnull(precio_m2,0.0) as precio\r\n"
						+ "FROM presupuestos p\r\n"
						+ "inner join tareas t on p.idpresupuesto=t.id_presupuesto\r\n"
						+ "INNER JOIN tipos_tarea tt on t.id_tipo_tarea=tt.idtipo_tarea \r\n"
						+ "left join precios_tipo_tarea on tt.idtipo_tarea=precios_tipo_tarea.id_tipo_tarea_\r\n"
						+ "where (fecha_desde= (select max(fecha_desde) from precios_tipo_tarea where tt.idtipo_tarea=precios_tipo_tarea.id_tipo_tarea_ and fecha_desde <= ?)) and t.id_presupuesto =?\r\n"
						+ "group by t.idtarea, tt.idtipo_tarea\r\n"
						+ "ORDER BY t.idtarea");
				ps.setDate(1, new java.sql.Date(p.getFecha_emision().getTime()));
				ps.setInt(2, p.getId_presupuesto());
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					Tarea tarea=new Tarea(rs.getInt("t.idtarea"), rs.getString("t.descripcion"), rs.getFloat("t.cant_m2"), 
							new Tipo_Tarea(rs.getInt("tt.idtipo_tarea"), rs.getString("tt.descripcion"),rs.getFloat("precio"))//por ahora no recupera precio del tipo tarea
							);
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
		/*
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
		}*/
		
		public int Registrar(int idpresupuesto, Tarea t) throws Exception {
			try {
				this.open();
				PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO tareas (descripcion, cant_m2, "
						+ "id_presupuesto, id_tipo_tarea ) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, t.getDescripcion());
				ps.setFloat(2, t.getCant_m2());
				ps.setInt(3, idpresupuesto);
				ps.setInt(4, t.getTipo_tarea().getId_tipo_tarea());
				
				int n=ps.executeUpdate();
				if (n == 0) {
	                    ps.close();
	                    throw new Exception("No se han registrado las tareas, intentelo de nuevo");
	                }
				else {
					ResultSet generatedKeys = ps.getGeneratedKeys();
					if (generatedKeys.next()) {
					         int idGenerado = generatedKeys.getInt(1);
					         ps.close();
					         return idGenerado;
					}
				}
				ps.close();
				throw new Exception("No se han registrado las tareas, intentelo de nuevo");
				
			}
			catch(Exception e) {
				throw e;
			}
			finally {
				this.close();
			}
		}
		/*
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
