package datos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Maquinaria;
import entidades.Material;
import entidades.Tarea;

public class MaquinariaData extends Coneccion {
	
	public Maquinaria getOne(int id) throws SQLException {
		Maquinaria m=null;
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT idmaquina, descripcion, ifnull(pm.valor_hora,0.0) as precio FROM maquinarias "
					+ "left join precios_maquina pm on pm.id_maquina=idmaquina "
					+ "WHERE idmaquina=? and ifnull((fecha_desde= (select max(fecha_desde) from precios_maquina where id_maquina=idmaquina)),true)");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			m= new Maquinaria(rs.getInt("idmaquina"), rs.getString("descripcion"),rs.getFloat("precio"));
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return m;
	}
	public ArrayList<Maquinaria> getAll() throws SQLException {
		
		ArrayList<Maquinaria> listaMaq = new ArrayList<Maquinaria>();
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT idmaquina, descripcion,ifnull(pm.valor_hora,0.0) as precio FROM maquinarias "
					+ "left join precios_maquina pm on pm.id_maquina=idmaquina "
					+ "WHERE ifnull((fecha_desde= (select max(fecha_desde) from precios_maquina where id_maquina=idmaquina)),true)");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listaMaq.add(new Maquinaria(rs.getInt("idmaquina"), rs.getString("descripcion"),rs.getFloat("precio")));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return listaMaq;
		
	}
	
	public void Registrar(Maquinaria m) throws SQLException, Exception {
		int n=0;
		try {
			this.open();
			PreparedStatement ps = this.getCon().prepareStatement("INSERT into maquinarias(descripcion) values(?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, m.getDescripcion());
			
			n = ps.executeUpdate();
			
			
			if (n == 0) {
                ps.close();
                throw new Exception("No se han registrado las tareas, intentelo de nuevo");
            }
			else {
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
			         int idGenerado = generatedKeys.getInt(1);
			         ps.close();
			         if(m.getPrecioHora()!=0 && !Float.isNaN(m.getPrecioHora())) {
							ps=this.getCon().prepareStatement("INSERT INTO precios_maquina(id_maquina, fecha_desde, valor_hora) VALUES (?, sysdate(), ?)");
							//es necesario obtener el nuevo id_material
							
							ps.setInt(1, idGenerado);
							ps.setFloat(2, m.getPrecioHora());
							n=ps.executeUpdate();
							//provoca error si no obtuvo el nuevo id_material
							ps.close();
							if(n==0) {
								throw new Exception("No se ha guardado el precio, intentelo de nuevo");
							}
						}
				}
			}	
			
			
			
			
		} catch (SQLException e) {
			throw new Exception("No se ha registrado la maquinaria, intentelo de nuevo!"+e.getMessage());
		}
		finally {
			this.close();
		}
		if (n==0) {
			throw new Exception("No se ha registrado la maquinaria, intentelo de nuevo!");
		}
	}
	
	public void ActualizarDatos(Maquinaria m) throws SQLException, Exception {
		int n=0;
		try {
			float p = this.getOne(m.getIdMaquina()).getPrecioHora();
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("UPDATE maquinarias SET descripcion=?  WHERE (idmaquina=?)");
			ps.setString(1, m.getDescripcion());
			ps.setInt(2, m.getIdMaquina());
			
			n=ps.executeUpdate();
			
			ps.close();
			if(p != m.getPrecioHora()) {
				ps=this.getCon().prepareStatement("INSERT INTO precios_maquina(id_maquina, fecha_desde, valor_hora) VALUES (?, sysdate(), ?)");
				ps.setInt(1, m.getIdMaquina());
				ps.setFloat(2, m.getPrecioHora());
				
				n=ps.executeUpdate();
				ps.close();
				if(n==0) {
					throw new Exception("No se ha el precio, intentelo de nuevo!");
				}
			}
			
		} catch (SQLException e) {
			throw new Exception("No se han registrado los cambios, intentelo de nuevo!"+e.getMessage());
		}
		finally {
			this.close();
		}
		
		
		
	}
	
	public void Eliminar(int id) throws SQLException, Exception {
		int n=0;
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("DELETE FROM maquinarias WHERE (idmaquina=?)");
			ps.setInt(1, id);
			
			n=ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			throw new Exception("No fue posible eliminar la maquinaria, intentelo de nuevo!"+e.getMessage());
		}
		finally {
			this.close();
		}
		
		if(n==0) {
			throw new Exception("No fue posible eliminar la maquinaria, intentelo de nuevo!");
		}
	}
	public ArrayList<Maquinaria> getMaquinasTarea(int idTarea) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Maquinaria> maquinas=new ArrayList<Maquinaria>();
		try {
			this.open();
			PreparedStatement ps= this.getCon().prepareStatement("SELECT m.idmaquina, descripcion, ifnull(valor_hora,0.0) as precio, tm.hs_uso FROM maquinarias m "
					+ "left join precios_maquina on m.idmaquina=id_maquina "
					+ "inner join tareas_maquinas tm on m.idmaquina=tm.id_maquina__ "
					+ "where (fecha_desde= (select max(fecha_desde) from precios_maquina where id_maquina=idmaquina and fecha_desde <=tm.fecha)) "
					+ "and tm.id_tarea__=? "
					+ "group by idmaquina");
			ps.setInt(1, idTarea);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Maquinaria m=new Maquinaria(rs.getInt("m.idmaquina"), rs.getString("descripcion"), rs.getFloat("precio"));
				m.setCantHoras(rs.getInt("tm.hs_uso"));
				maquinas.add(m);
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
		return maquinas;
	}
	public void RegistrarUsoMaquinas(int idTarea, ArrayList<Maquinaria> maqs) throws Exception {
		try {
			this.open();
			this.getCon().setAutoCommit(false);
			PreparedStatement ps = this.getCon().prepareStatement("INSERT into tareas_maquinas(id_maquina__, id_tarea__, hs_uso, fecha) "
					+ "values(?,?,?, sysdate())");
			for(Maquinaria m:maqs) {
				ps.setInt(1, m.getIdMaquina());
				ps.setInt(2, idTarea);
				ps.setInt(3, m.getCantHoras());
				ps.addBatch();
			}
			
			int[] n = ps.executeBatch();
			
			for (int i : n) {
                if (i == 0) {
                    this.getCon().rollback();
                    ps.close();
                    throw new Exception("No se ha registrado la maquinaria, intentelo de nuevo!");
                }
            }
			this.getCon().commit();
			
			ps.close();
		} catch (SQLException e) {
			throw new Exception("No se ha registrado la maquinaria, intentelo de nuevo!"+e.getMessage());
		}
		finally {
			this.close();
		}
	}

}
