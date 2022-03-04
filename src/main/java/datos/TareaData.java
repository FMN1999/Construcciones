package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import entidades.Material;
import entidades.Obra;
import entidades.Presupuesto;
import entidades.Tarea;
import entidades.Tipo_Tarea;
import entidades.Trabajador;
import entidades.Trabajador.TareaAsignada;

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
						+ "left join precios_tipo_tarea ptt1 on tt.idtipo_tarea=ptt1.id_tipo_tarea_\r\n"
						+ "where (ptt1.fecha_desde= (select max(ptt.fecha_desde) from precios_tipo_tarea ptt where tt.idtipo_tarea=ptt.id_tipo_tarea_ and ptt.fecha_desde <= ?)) and t.id_presupuesto =?\r\n"
						+ "group by t.idtarea, tt.idtipo_tarea\r\n"
						+ "ORDER BY t.idtarea");
				ps.setDate(1, new java.sql.Date(p.getFecha_emision().getTime()));
				ps.setInt(2, p.getId_presupuesto());
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					Date fd=rs.getDate("t.fecha_desde");
					Date fh=rs.getDate("t.fecha_hasta");
					Tarea tarea=new Tarea(rs.getInt("t.idtarea"), rs.getString("t.descripcion"), rs.getFloat("t.cant_m2"), 
							new Tipo_Tarea(rs.getInt("tt.idtipo_tarea"), rs.getString("tt.descripcion"),rs.getFloat("precio"))
							,fd,fh);
					tareas.add(tarea);
				}
				rs.close();
				ps.close();
			}
			catch(Exception e) {
				throw new Exception("Ocurrió un error mientras se intentaban recuperar los datos de las tareas.");
			}
			finally {
				this.close();
			}
			return tareas;
		}
		
		
		/***
		 * 
		 * @see Rcupera las tareas activas al momento de la fecha.
		 *	Cambia la descripcion de la descripcion de la tarea
		 *  para que sea entendible de que obra es
		 * */
		public ArrayList<Tarea> getTareasActivas(Date d) throws SQLException, Exception{
			ArrayList<Tarea> tareas=new ArrayList<Tarea>();
			try {
				this.open();
				PreparedStatement ps= this.getCon().prepareStatement("SELECT t.idtarea, concat(o.direccion,'-',t.descripcion) as descripcion, "
						+ "t.cant_m2, tt.idtipo_tarea, tt.descripcion, 0.0 as precio, t.fecha_desde, t.fecha_hasta\r\n"
						+ "FROM presupuestos p\r\n"
						+ "INNER JOIN tareas t ON p.idpresupuesto=t.id_presupuesto\r\n"
						+ "INNER JOIN tipos_tarea tt on t.id_tipo_tarea=tt.idtipo_tarea \r\n"
						+ "INNER JOIN obras o ON o.idobra=p.id_obra "
						+ "WHERE t.fecha_hasta>=? \r\n"
						+ "GROUP BY t.idtarea, tt.idtipo_tarea\r\n"
						+ "ORDER BY t.idtarea");
				ps.setDate(1, new java.sql.Date(d.getTime()));
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					Date fd=rs.getDate("t.fecha_desde");
					Date fh=rs.getDate("t.fecha_hasta");
					Tarea tarea=new Tarea(rs.getInt("t.idtarea"), rs.getString("descripcion"), rs.getFloat("t.cant_m2"), 
							new Tipo_Tarea(rs.getInt("tt.idtipo_tarea"), rs.getString("tt.descripcion"),rs.getFloat("precio"))//el precio es despreciable
							,fd,fh);
					tareas.add(tarea);
				}
				rs.close();
				ps.close();
			}
			catch(Exception e) {
				throw new Exception("Ocurrió un error mientras se intentaban recuperar los datos de las tareas.");
			}
			finally {
				this.close();
			}
			return tareas;
		}
		
		public Trabajador getTareasMesEmpleado(Trabajador t) throws SQLException, Exception{
			try {
				this.open();
				PreparedStatement ps= this.getCon().prepareStatement("SELECT t.idtarea, concat(o.direccion,'-',t.descripcion) as descripcion, "
						+ "t.cant_m2, tt.idtipo_tarea, tt.descripcion, 0.0 as precio, t.fecha_desde, t.fecha_hasta, "
						+ "trt.fecha, sum(trt.cant_horas_trabajadas) as hs_t "
						+ "FROM presupuestos p\r\n"
						+ "INNER JOIN tareas t ON p.idpresupuesto=t.id_presupuesto\r\n"
						+ "INNER JOIN tipos_tarea tt on t.id_tipo_tarea=tt.idtipo_tarea \r\n"
						+ "INNER JOIN obras o ON o.idobra=p.id_obra "
						+ "INNER JOIN trabajador_tarea trt on trt.id_tarea_asignada=t.idtarea "
						+ "WHERE trt.fecha>=? and trt.fecha<=? and trt.cuil_trabajador=?\r\n"
						+ "GROUP BY t.idtarea, tt.idtipo_tarea, trt.fecha\r\n"
						+ "ORDER BY t.idtarea");
				//ps.setDate(1, new java.sql.Date(d.getTime()));
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
				//Fecha actual
				Calendar calendar = Calendar.getInstance(); 
				//se pone dia 1
				calendar.set(Calendar.DAY_OF_MONTH,1);
				String dia=sdf.format(calendar.getTime());
				Date firsdayM=sdf.parse(dia);
				//se pone el ultimo dia
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				dia=sdf.format(calendar.getTime());
				Date lastdayM=sdf.parse(dia);
				
				ps.setDate(1, new java.sql.Date(firsdayM.getTime()));
				ps.setDate(2, new java.sql.Date(lastdayM.getTime()));
				ps.setLong(3, t.getCuil());
				
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					Date fd=rs.getDate("t.fecha_desde");
					Date fh=rs.getDate("t.fecha_hasta");
					Tarea tarea=new Tarea(rs.getInt("t.idtarea"), rs.getString("descripcion"), rs.getFloat("t.cant_m2"), 
							new Tipo_Tarea(rs.getInt("tt.idtipo_tarea"), rs.getString("tt.descripcion"),rs.getFloat("precio"))//el precio es despreciable
							,fd,fh);
					int canths=rs.getInt("hs_t");
					Date diaAsig=rs.getDate("trt.fecha");
					t.addTareaAsignada(canths, diaAsig, tarea);
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
			return t;
		}
		
		public int HorasTrabajadas(Date d, long cuil) throws SQLException, Exception{
			int cant=0;
			try {
				this.open();
				PreparedStatement ps= this.getCon().prepareStatement("SELECT ifnull(sum(tt.cant_horas_trabajadas),0) as cant FROM trabajador_tarea tt"
						+ " WHERE tt.cuil_trabajador=? and tt.fecha=?");
				ps.setLong(1, cuil);
				ps.setDate(2, new java.sql.Date(d.getTime()));
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					cant=rs.getInt("cant");
				}
				rs.close();
				ps.close();
			}
			catch(Exception e) {
				throw new Exception("Ocurrió un error mientras se intentaban recuperar los datos de la tarea.");
			}
			finally {
				this.close();
			}
			
			return cant;
		}
		
		public void AsignarTrabajador(int idTarea,long cuil, int horas, Date dia) throws Exception {
			int n=0;
			
			try {
				this.open();
				PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO trabajador_tarea(cuil_trabajador, id_tarea_asignada, cant_horas_trabajadas, fecha)"
						+ "VALUES (?,?,?,?)");
				ps.setLong(1, cuil);
				ps.setInt(2, idTarea);
				ps.setInt(3, horas);
				ps.setDate(4, new java.sql.Date(dia.getTime()));
				
				n=ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				throw new Exception("Un error ocurrio mientras se intentaba registrar la asignacion del empleado.");
			}
			finally {
				this.close();
				if(n==0) {
					throw new Exception("No se pudo registrar la asignacion del empleado a la tarea");
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
