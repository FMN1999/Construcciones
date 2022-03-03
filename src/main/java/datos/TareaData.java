package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import entidades.Material;
import entidades.Obra;
import entidades.Presupuesto;
import entidades.Tarea;
import entidades.Tipo_Tarea;
import entidades.Trabajador;

public class TareaData extends Coneccion
{		
		//*************************************************
		//** Falta cargar el tipo_tarea **
		//*************************************************
		public ArrayList<Tarea> getTareas(Presupuesto p) throws SQLException, Exception{
			ArrayList<Tarea> tareas=new ArrayList<Tarea>();
			try {
				this.open();
				PreparedStatement ps= this.getCon().prepareStatement("SELECT t.idtarea, t.descripcion, t.cant_m2, tt.idtipo_tarea, tt.descripcion, ifnull(precio_m2,0.0) as precio, "
						+ "t.fecha_desde, t.fecha_hasta\r\n"
						+ "FROM presupuestos p\r\n"
						+ "inner join tareas t on p.idpresupuesto=t.id_presupuesto\r\n"
						+ "INNER JOIN tipos_tarea tt on t.id_tipo_tarea=tt.idtipo_tarea \r\n"
						+ "left join precios_tipo_tarea on tt.idtipo_tarea=precios_tipo_tarea.id_tipo_tarea_\r\n"
						+ "where (t.fecha_desde= (select max(ptt.fecha_desde) from precios_tipo_tarea ptt where tt.idtipo_tarea=ptt.id_tipo_tarea_ and ptt.fecha_desde <= ?)) and t.id_presupuesto =?\r\n"
						+ "group by t.idtarea, tt.idtipo_tarea\r\n"
						+ "ORDER BY t.idtarea");
				ps.setDate(1, new java.sql.Date(p.getFecha_emision().getTime()));
				ps.setInt(2, p.getId_presupuesto());
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					Date fd=rs.getDate("t.fecha_desde");
					Date fh=rs.getDate("t.fecha_hasta");
					Tarea tarea=new Tarea(rs.getInt("t.idtarea"), rs.getString("t.descripcion"), rs.getFloat("t.cant_m2"), 
							new Tipo_Tarea(rs.getInt("tt.idtipo_tarea"), rs.getString("tt.descripcion"),rs.getFloat("precio"))//por ahora no recupera precio del tipo tarea
							,fd,fh);
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
		
		public void registrarTrabajador(Trabajador tr, Tarea ta, int horas) throws Exception {
			int n=0;
			
			try {
				this.open();
				PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO trabajador_tarea(cuil_trabajador, id_tarea_asignada, cant_horas_trabajadas)"
						+ "VALUES (?,?,?) ");
				ps.setLong(1, tr.getCuil());
				ps.setInt(2, ta.getIdTarea());
				ps.setInt(3, horas);
				
				
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
		
		public int Registrar(int idpresupuesto, Tarea t) throws Exception {
			try {
				this.open();
				PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO tareas (descripcion, cant_m2, "
						+ "id_presupuesto, id_tipo_tarea, fecha_desde, fecha_hasta ) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, t.getDescripcion());
				ps.setFloat(2, t.getCant_m2());
				ps.setInt(3, idpresupuesto);
				ps.setInt(4, t.getTipo_tarea().getId_tipo_tarea());
				ps.setDate(5, new java.sql.Date(t.getFechaDesde().getTime()));
				ps.setDate(6, new java.sql.Date(t.getFechaHasta().getTime()));
				
				
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
}
