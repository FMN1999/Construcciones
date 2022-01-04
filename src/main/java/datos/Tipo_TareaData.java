package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Material;
import entidades.Tipo_Tarea;

public class Tipo_TareaData extends Coneccion
{		
		//*************************************************
		//** Devuelve todos los materiales en existencia **
		//*************************************************
		public ArrayList<Tipo_Tarea> getAll() throws SQLException, Exception{
			ArrayList<Tipo_Tarea> tps=new ArrayList<Tipo_Tarea>();
			try {
				this.open();
				PreparedStatement ps= this.getCon().prepareStatement("SELECT tipos_tarea.idtipo_tarea, descripcion, ifnull(precio_m2,0.0) as precio FROM tipos_tarea "
						+ "left join precios_tipo_tarea on tipos_tarea.idtipo_tarea=precios_tipo_tarea.id_tipo_tarea_ "
						+ "where ifnull(fecha_desde= (select max(fecha_desde) from precios_tipo_tarea where tipos_tarea.idtipo_tarea=precios_tipo_tarea.id_tipo_tarea_),true) "
						+ "group by tipos_tarea.idtipo_tarea");
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					Tipo_Tarea tt=new Tipo_Tarea(rs.getInt("tipos_tarea.idtipo_tarea"), rs.getString("descripcion"), rs.getFloat("precio"));
					tps.add(tt);
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
			return tps;
		}
		
		public Tipo_Tarea getOne(int id) throws SQLException, Exception{
			try {
				this.open();
				PreparedStatement ps= this.getCon().prepareStatement("SELECT tipos_tarea.idtipo_tarea, descripcion, ifnull(precio_m2,0.0) as precio FROM tipos_tarea "
						+ "left join precios_tipo_tarea on tipos_tarea.idtipo_tarea=precios_tipo_tarea.id_tipo_tarea_ "
						+ "where ifnull(fecha_desde= (select max(fecha_desde) from precios_tipo_tarea where tipos_tarea.idtipo_tarea=precios_tipo_tarea.id_tipo_tarea_),true) and idtipo_tarea=? "
						+ "group by tipos_tarea.idtipo_tarea");
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				rs.next();
				Tipo_Tarea tt=new Tipo_Tarea(rs.getInt("tipos_tarea.idtipo_tarea"), rs.getString("descripcion"), rs.getFloat("precio"));
				rs.close();
				ps.close();
				return tt;
			}
			catch(Exception e) {
				throw e;
			}
			finally {
				this.close();
			}
		}
		
		public void Registrar(Tipo_Tarea tt) throws Exception {
			try {
				this.open();
				PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO tipos_tarea(descripcion) VALUES (?)");
				ps.setString(1, tt.getDescripcion());
				
				int n=ps.executeUpdate();
				ps.close();
				if(n==0) {
					throw new Exception("No se ha registrado el tipo de tarea, intentelo de nuevo");
				}
				if(tt.getPrecio()!=0 && !Float.isNaN(tt.getPrecio())) {
					ps=this.getCon().prepareStatement("INSERT INTO precios_tipo_tarea(id_tipo_tarea_, fecha_desde, precio_m2) VALUES (?, sysdate(), ?)");
					ArrayList<Tipo_Tarea> tps=this.getAll();
					//es necesario obtener el nuevo id_material
					for(Tipo_Tarea tt2:tps) {
						if(tt2.getDescripcion().equalsIgnoreCase(tt.getDescripcion())) {
							ps.setInt(1, tt2.getId_tipo_tarea());
							break;
						}
					}
					
					ps.setFloat(2, tt.getPrecio());
					n=ps.executeUpdate();
					//provoca error si no obtuvo el nuevo id_tipo_Tarea
					ps.close();
					if(n==0) {
						throw new Exception("No se ha guardado el precio, intentelo de nuevo");
					}
				}
			}
			catch(Exception e) {
				throw e;
			}
			finally {
				this.close();
			}
		}
		
		public void ActualizarDatos(Tipo_Tarea tt, boolean modificaPre) throws Exception {
			try {
				this.open();
				PreparedStatement ps=this.getCon().prepareStatement("UPDATE tipos_tarea SET descripcion=? WHERE idtipo_tarea=?");
				ps.setString(1, tt.getDescripcion());
				ps.setInt(2, tt.getId_tipo_tarea());
				int n=ps.executeUpdate();
				ps.close();
				if(n==0) {
					throw new Exception("No se ha guardado los cambios en el tipo de tarea, intentelo de nuevo");
				}
				if(modificaPre) {
					ps=this.getCon().prepareStatement("INSERT INTO precios_tipo_tarea(id_tipo_tarea_, fecha_desde, precio_m2) VALUES (?, sysdate(), ?)");
					ps.setInt(1, tt.getId_tipo_tarea());
					ps.setFloat(2, tt.getPrecio());
					n=ps.executeUpdate();
					ps.close();
					if(n==0) {
						throw new Exception("No se ha guardado el cambio en precios, intentelo de nuevo");
					}
					
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
			int n=0;
			try {
				this.open();
				PreparedStatement ps=this.getCon().prepareStatement("DELETE FROM precios_tipo_tarea WHERE (id_tipo_tarea_=?)");
				ps.setInt(1, id);
				ps.executeUpdate();
				ps.close();
				//primero hay que eliminar los precios
				ps=this.getCon().prepareStatement("DELETE FROM tipos_tarea WHERE (idtipo_tarea=?)");
				ps.setInt(1, id);
				n=ps.executeUpdate();
				if(n==0) {
					throw new Exception("No se ha eliminado el tipo de tarea, intentelo de nuevo");
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
