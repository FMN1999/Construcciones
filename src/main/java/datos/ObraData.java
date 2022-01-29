package datos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Material;
import entidades.Obra;
import entidades.Proveedor;

public class ObraData extends Coneccion {
	
	public Obra getOne(int id) throws SQLException {
		
		Obra p=null;
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT o.idobra, o.direccion, o.id_cliente FROM obras o  WHERE idObra=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			p = new Obra(rs.getInt("o.idobra"), rs.getString("o.direccion"), rs.getInt("o.id_cliente"));
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
			}
		return p;
		
		
		
	}
	
	public ArrayList<Obra> getAll() throws SQLException{
		ArrayList<Obra> obras=new ArrayList<Obra>();
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT o.idobra, o.direccion, o.id_cliente FROM obras o ");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				obras.add(new Obra(rs.getInt("o.idobra"), rs.getString("o.direccion"), rs.getInt("o.id_cliente")));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return obras;
		
		
	}
	
	
	public ArrayList<Obra> getObras(int idCli) throws SQLException{
		ArrayList<Obra> obras=new ArrayList<Obra>();
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT o.idobra, o.direccion, o.id_cliente FROM obras o WHERE o.id_cliente=? ");
			ps.setInt(1, idCli);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				obras.add(new Obra(rs.getInt("o.idobra"), rs.getString("o.direccion"), rs.getInt("o.id_cliente")));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			this.close();
		}
		return obras;
		
		
	}
	
	public void Registrar(Obra o) throws Exception {
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO obras (direccion, id_cliente) VALUES (?,?)");
			ps.setString(1, o.getDireccion());
			ps.setInt(2, o.getIdCliente());
			
			int n=ps.executeUpdate();
			ps.close();
			if(n==0) {
				throw new Exception("No se ha registrado la obra, intentelo de nuevo");
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			this.close();
		}
	}
	
	public void Actualizar(Obra o) throws Exception {
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("UPDATE obras SET direccion=?, id_cliente=? WHERE idobra=?");
			ps.setString(1, o.getDireccion());
			ps.setInt(2, o.getIdCliente());
			ps.setInt(3, o.getIdObra());
			
			int n=ps.executeUpdate();
			ps.close();
			if(n==0) {
				throw new Exception("No se ha registrado la obra, intentelo de nuevo");
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
			PreparedStatement ps=this.getCon().prepareStatement("DELETE FROM obras WHERE idobra=?");
			ps.setInt(1, id);
			
			int n=ps.executeUpdate();
			ps.close();
			if(n==0) {
				throw new Exception("No se ha registrado la obra, intentelo de nuevo");
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
