package datos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Maquinaria;

public class MaquinariaData extends Coneccion {
	
	public Maquinaria getOne(int id) throws SQLException {
			
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("SELECT idmaquina, descripcion, precioHora FROM maquinarias WHERE idmaquina=?");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		rs.next();
		Maquinaria m= new Maquinaria(rs.getInt(1), rs.getString(2),rs.getFloat(3));
		rs.close();
		ps.close();
		this.close();
		return m;
	}
	public ArrayList<Maquinaria> getAll() throws SQLException {
		
		ArrayList<Maquinaria> listaMaq = new ArrayList<Maquinaria>();
		this.open();
		PreparedStatement ps = this.getCon().prepareStatement("SELECT idmaquina, descripcion, precioHora from maquinarias");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			listaMaq.add(new Maquinaria(rs.getInt(1), rs.getString(2),rs.getFloat(3)));
		}
		rs.close();
		ps.close();
		this.close();
		return listaMaq;
		
	}
	
	public void Registrar(Maquinaria m) throws SQLException, Exception {
		
		this.open();
		PreparedStatement ps = this.getCon().prepareStatement("INSERT into maquinarias(descripcion, precioHora) values(?,?)");
		ps.setString(1, m.getDescripcion());
		ps.setFloat(2, m.getPrecioHora());
		
		int n = ps.executeUpdate();
		ps.close();
		this.close();
		
		if (n==0) {
			throw new Exception("No se ha registrado la maquinaria, intentelo de nuevo!");
		}
	}
	
	public void ActualizarDatos(Maquinaria m) throws SQLException, Exception {
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("UPDATE maquinarias SET descripcion=?, precioHora=? WHERE (idmaquina=?)");
		ps.setString(1, m.getDescripcion());
		ps.setFloat(2, m.getPrecioHora());
		ps.setInt(3, m.getIdMaquina());
		
		int n=ps.executeUpdate();
		
		ps.close();
		this.close();
		
		if(n==0) {
			throw new Exception("No se han registrado los cambios, intentelo de nuevo!");
		}
	}
	
	public void Eliminar(int id) throws SQLException, Exception {
		this.open();
		PreparedStatement ps=this.getCon().prepareStatement("DELETE FROM maquinarias WHERE (idmaquina=?)");
		ps.setInt(1, id);
		
		int n=ps.executeUpdate();
		
		ps.close();
		this.close();
		
		if(n==0) {
			throw new Exception("No fue posible eliminar la maquinaria, intentelo de nuevo!");
		}
	}

}
