package datos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Proveedor;

public class ProveedorData extends Coneccion {
	
	public Proveedor getOne(int id) throws SQLException {
		
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("SELECT idProvedor, razon_social, direccion, telefono FROM provedores WHERE idProverdor=?");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		rs.next();
		Proveedor p= new Proveedor(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getLong(4));
		rs.close();
		ps.close();
		this.close();
		return p;
	}
	
	public ArrayList<Proveedor> getAll() throws SQLException{
		ArrayList<Proveedor> provs=new ArrayList<Proveedor>();
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("SELECT idProvedor, razon_social, direccion, telefono FROM provedores");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			provs.add(new Proveedor(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getLong(4)));
		}
		rs.close();
		ps.close();
		this.close();
		return provs;
	}
	
	public void Registrar(Proveedor p) throws SQLException, Exception {
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO provedores(razon_social, direccion, telefono) values(?,?,?)");
		ps.setString(1, p.getRazonSocial());
		ps.setString(2, p.getDireccion());
		ps.setLong(3, p.getTelefono());
		
		int n=ps.executeUpdate();
		
		ps.close();
		this.close();
		
		if(n==0) {
			throw new Exception("No se ha registrado al provedor, intentelo de nuevo!");
		}
	}
	
	public void ActualizarDatos(Proveedor p) throws SQLException, Exception {
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("UPDATE provedores SET razon_social=?, direccion=?, telefono=? WHERE (idprovedor=?)");
		ps.setString(1, p.getRazonSocial());
		ps.setString(2, p.getDireccion());
		ps.setLong(3, p.getTelefono());
		ps.setInt(4, p.getIdProveedor());
		
		int n=ps.executeUpdate();
		
		ps.close();
		this.close();
		
		if(n==0) {
			throw new Exception("No se han registrado los cambios, intentelo de nuevo!");
		}
	}
	
	public void Eliminar(int id) throws SQLException, Exception {
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("DELETE FROM provedores WHERE (idprovedor=?)");
		ps.setInt(1, id);
		
		int n=ps.executeUpdate();
		
		ps.close();
		this.close();
		
		if(n==0) {
			throw new Exception("No fue posible eliminar provedor, intentelo de nuevo!");
		}
	}
	
}
