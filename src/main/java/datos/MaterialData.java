package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.Material;
import entidades.Tarea;

public class MaterialData extends Coneccion {

	//*****************************************************
	//** Devuelve la lista de materiales de un proveedor **
	//*****************************************************
	public ArrayList<Material> getMateriales(int idprov) throws SQLException, Exception{
		ArrayList<Material> materiales=new ArrayList<Material>();
		try {
			this.open();
			PreparedStatement ps= this.getCon().prepareStatement("SELECT materiales.idmaterial, descripcion, id_provedor, ifnull(precio,0.0) as precio FROM materiales "
					+ "left join precios_material on materiales.idmaterial=id_material "
					+ "where ifnull(fecha_desde= (select max(fecha_desde) from precios_material where id_material=idmaterial),true) "
					+ "and id_provedor=? "
					+ "group by idmaterial");
			ps.setInt(1, idprov);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Material m=new Material(rs.getInt("materiales.idmaterial"), rs.getString("descripcion"), rs.getFloat("precio"));
				materiales.add(m);
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
		return materiales;
	}
	
	public ArrayList<Material> getMaterialesTarea(int idTarea) throws SQLException, Exception{
		ArrayList<Material> materiales=new ArrayList<Material>();
		try {
			this.open();
			PreparedStatement ps= this.getCon().prepareStatement("SELECT materiales.idmaterial, descripcion, ifnull(precio,0.0) as precio FROM materiales "
					+ "left join precios_material on materiales.idmaterial=id_material "
					+ "inner join materiales_tareas mt on materiales.idmaterial=mt.id_material_ "
					+ "where (fecha_desde= (select max(fecha_desde) from precios_material where id_material=idmaterial and fecha_desde <= mt.fecha)) "
					+ "and mt.id_tarea_=? "
					+ "group by idmaterial");
			ps.setInt(1, idTarea);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Material m=new Material(rs.getInt("materiales.idmaterial"), rs.getString("descripcion"), rs.getFloat("precio"));
				materiales.add(m);
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
		return materiales;
	}
	
	//*************************************************
	//** Devuelve todos los materiales en existencia **
	//*************************************************
	public ArrayList<Material> getAll() throws SQLException, Exception{
		ArrayList<Material> materiales=new ArrayList<Material>();
		try {
			this.open();
			PreparedStatement ps= this.getCon().prepareStatement("SELECT materiales.idmaterial, descripcion, id_provedor, ifnull(precio,0.0) as precio FROM materiales "
					+ "left join precios_material on materiales.idmaterial=id_material "
					+ "where ifnull(fecha_desde= (select max(fecha_desde) from precios_material where id_material=idmaterial),true) "
					+ "group by idmaterial");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Material m=new Material(rs.getInt("materiales.idmaterial"), rs.getString("descripcion"), rs.getFloat("precio"));
				materiales.add(m);
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
		return materiales;
	}
	
	public Material getOne(int id) throws SQLException, Exception{
		try {
			this.open();
			PreparedStatement ps= this.getCon().prepareStatement("SELECT materiales.idmaterial, descripcion, id_provedor, ifnull(precio,0.0) as precio FROM materiales "
					+ "left join precios_material on materiales.idmaterial=id_material "
					+ "where ifnull(fecha_desde= (select max(fecha_desde) from precios_material where id_material=idmaterial),true) and idmaterial=? "
					+ "group by idmaterial");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			Material m=new Material(rs.getInt("materiales.idmaterial"), rs.getString("descripcion"), rs.getFloat("precio"));
			rs.close();
			ps.close();
			return m;
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			this.close();
		}
	}
	
	public void Registrar(Material m,int id_prov) throws Exception {
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO materiales(descripcion, id_provedor) VALUES (?,?)");
			ps.setString(1, m.getDescripcion());
			ps.setInt(2, id_prov);
			
			int n=ps.executeUpdate();
			ps.close();
			if(n==0) {
				throw new Exception("No se ha registrado el producto, intentelo de nuevo");
			}
			if(m.getPrecio()!=0 && !Float.isNaN(m.getPrecio())) {
				ps=this.getCon().prepareStatement("INSERT INTO precios_material(id_material, fecha_desde, precio) VALUES (?, sysdate(), ?)");
				ArrayList<Material> mats=this.getMateriales(id_prov);
				//es necesario obtener el nuevo id_material
				for(Material mat:mats) {
					if(mat.getDescripcion().equalsIgnoreCase(m.getDescripcion())) {
						ps.setInt(1, mat.getId_material());
						break;
					}
				}
				
				ps.setFloat(2, m.getPrecio());
				n=ps.executeUpdate();
				//provoca error si no obtuvo el nuevo id_material
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
	
	public void ActualizarDatos(Material m, boolean modificaPre, int id_prov) throws Exception {
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("UPDATE materiales SET descripcion=?, id_provedor=? WHERE idmaterial=?");
			ps.setString(1, m.getDescripcion());
			ps.setInt(2, id_prov);
			ps.setInt(3, m.getId_material());
			int n=ps.executeUpdate();
			ps.close();
			if(n==0) {
				throw new Exception("No se ha guardado los cambios en el producto, intentelo de nuevo");
			}
			if(modificaPre) {
				ps=this.getCon().prepareStatement("INSERT INTO precios_material(id_material, fecha_desde, precio) VALUES (?, sysdate(), ?)");
				ps.setInt(1, m.getId_material());
				ps.setFloat(2, m.getPrecio());
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
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("DELETE FROM materiales WHERE (idmaterial=?)");
			//primero hay que eliminar los precios
			ps.setInt(1, id);
			int n=ps.executeUpdate();
			if(n==0) {
				throw new Exception("No se ha eliminado el material, intentelo de nuevo");
			}
		}
		
		catch(Exception e) {
			throw e;
		}
		finally {
			this.close();
		}
	}

	public void RegistrarUsoMateriales(Tarea t, Material m, int cantidad, Date fecha) throws Exception {
		int n=0;
		try {
			this.open();
			PreparedStatement ps = this.getCon().prepareStatement("INSERT into materiales_tareas(id_material_, id_tarea_, cant_a_usar, fecha) values(?,?, ? , ?)");
			ps.setInt(1, m.getId_material());
			ps.setInt(2, t.getIdTarea());
			ps.setInt(3, cantidad);
			ps.setDate(4, (java.sql.Date) fecha);
			
			n = ps.executeUpdate();
			ps.close();
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
	
}
