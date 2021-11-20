package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Material;

public class MaterialData extends Coneccion {

	public ArrayList<Material> getAll() throws SQLException, Exception{
		ArrayList<Material> materiales=new ArrayList<Material>();
		try {
			this.open();
			PreparedStatement ps= this.getCon().prepareStatement("SELECT materiales.idmaterial, descripcion, id_provedor, ifnull(precio,0.0) FROM materiales "
					+ "left join precios_material on materiales.idmaterial=id_material "
					+ "where ifnull(fecha_desde= (select max(fecha_desde) from precios_material where id_material=idmaterial),true) "
					+ "group by idmaterial");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Material m=new Material(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
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
			PreparedStatement ps= this.getCon().prepareStatement("SELECT materiales.idmaterial, descripcion, id_provedor, ifnull(precio,0.0) FROM materiales "
					+ "left join precios_material on materiales.idmaterial=id_material "
					+ "where ifnull(fecha_desde= (select max(fecha_desde) from precios_material where id_material=idmaterial),true) and idmaterial=? "
					+ "group by idmaterial");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			Material m=new Material(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
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
	
	public void Registrar(Material m) throws Exception {
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO materiales(descripcion, id_provedor) VALUES (?,?)");
			ps.setString(1, m.getDescripcion());
			ps.setInt(2, m.getId_provedor());
			
			int n=ps.executeUpdate();
			ps.close();
			if(n==0) {
				throw new Exception("No se ha registrado el producto, intentelo de nuevo");
			}
			if(m.getPrecio()!=0 && !Float.isNaN(m.getPrecio())) {
				ps=this.getCon().prepareStatement("INSERT INTO precios_material(id_material, fecha_desde, precio) VALUES (?, sysdate(), ?)");
				ArrayList<Material> mats=this.getAll();
				//es necesario obtener el nuevo id_material
				for(Material mat:mats) {
					if(mat.getDescripcion().equalsIgnoreCase(m.getDescripcion()) && mat.getId_provedor()==m.getId_provedor()) {
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
	
	public void ActualizarDatos(Material m, boolean modificaPre) throws Exception {
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("UPDATE materiales SET descripcion=?, id_provedor=? WHERE idmaterial=?");
			ps.setString(1, m.getDescripcion());
			ps.setInt(2, m.getId_provedor());
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
	
}
