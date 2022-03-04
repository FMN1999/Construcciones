package datos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Proveedor;

public class ProveedorData extends Coneccion {
	
	public Proveedor getOne(int id) throws SQLException {
		
		Proveedor p=null;
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT idProvedor, razon_social, direccion, telefono FROM provedores WHERE idProverdor=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			p = new Proveedor(rs.getInt("idProvedor"), rs.getString("razon_social"),rs.getString("direccion"),rs.getLong("telefono"));
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new Exception("Ocurrió un error mientras se intentaban recuperar los datos del proveedor.");
		}
		finally {
			this.close();
			return p;
		}
		
		
	}
	
	public ArrayList<Proveedor> getAll() throws SQLException{
		ArrayList<Proveedor> provs=new ArrayList<Proveedor>();
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT idProvedor, razon_social, direccion, telefono FROM provedores");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				provs.add(new Proveedor(rs.getInt("idProvedor"), rs.getString("razon_social"),rs.getString("direccion"),rs.getLong("telefono")));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new SQLException("Ocurrió un error mientras se intentaban recuperar los datos del proveedor.");
		}
		finally {
			this.close();
			return provs;
		}
		
	}
	
	public void Registrar(Proveedor p) throws SQLException, Exception {
		int n=0;
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO provedores(razon_social, direccion, telefono) values(?,?,?)");
			ps.setString(1, p.getRazonSocial());
			ps.setString(2, p.getDireccion());
			ps.setLong(3, p.getTelefono());
			
			n=ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			throw new Exception("No se ha registrado al provedor, intentelo de nuevo!");
		}
		finally{
			this.close();
			
			if(n==0) {
				throw new Exception("No se ha registrado al provedor, intentelo de nuevo!");
			}
		}
		
	}
	
	public void ActualizarDatos(Proveedor p) throws SQLException, Exception {
		int n=0;
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("UPDATE provedores SET razon_social=?, direccion=?, telefono=? WHERE (idprovedor=?)");
			ps.setString(1, p.getRazonSocial());
			ps.setString(2, p.getDireccion());
			ps.setLong(3, p.getTelefono());
			ps.setInt(4, p.getIdProveedor());
			
			n=ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			throw new Exception("No se han registrado los cambios, intentelo de nuevo!");
		}
		finally {
			this.close();
			
			if(n==0) {
				throw new Exception("No se han registrado los cambios, intentelo de nuevo!");
			}
		}
		
	}
	
	public void Eliminar(int id) throws SQLException, Exception {
		int n=0;
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("DELETE FROM provedores WHERE (idprovedor=?)");
			ps.setInt(1, id);
			
			n = ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			throw new SQLException("No fue posible eliminar provedor, está siendo utilizado en otros registros.");
		}
		finally {
			this.close();
			
			if(n==0) {
				throw new SQLException("No fue posible eliminar provedor, está siendo utilizado en otros regstros.");
			}
		}
	}
	
}
