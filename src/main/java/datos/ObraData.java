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
			PreparedStatement ps=this.getCon().prepareStatement("SELECT o.idobra, o.direccion, o.descripcion, o.finalizado FROM obras o  WHERE idObra=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			p = new Obra(rs.getInt("o.idobra"), rs.getString("o.direccion"), rs.getString("o.descripcion"), rs.getBoolean("o.finalizado"));
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new SQLException("Ocurrió un error mientras se intentaban recuperar los datos de la obra.");
		}
		finally {
			this.close();
			}
		return p;
		
		
		
	}
	
	public ArrayList<Obra> getObras(int idCli) throws SQLException{
		ArrayList<Obra> obras=new ArrayList<Obra>();
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT o.idobra, o.direccion, o.descripcion, o.finalizado FROM obras o WHERE o.id_cliente=? ");
			ps.setInt(1, idCli);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				obras.add(new Obra(rs.getInt("o.idobra"), rs.getString("o.direccion"), rs.getString("o.descripcion"), rs.getBoolean("o.finalizado")));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new SQLException("Ocurrió un error mientras se intentaban recuperar los datos de las obras.");

		}
		finally {
			this.close();
		}
		return obras;
		
		
	}
	
	public void Registrar(Obra o, int id) throws Exception {
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("INSERT INTO obras (direccion, id_cliente, descripcion, finalizado) VALUES (?,?, ?, false)");
			ps.setString(1, o.getDireccion());
			ps.setInt(2, id);
			ps.setString(3, o.getDescripcion());
			
			int n=ps.executeUpdate();
			ps.close();
			if(n==0) {
				throw new Exception("No se ha registrado la obra, intentelo de nuevo");
			}
		}
		catch(Exception e) {
			throw new Exception("Ocurrió un error mientras se intentaban registrar los datos de la obra.");

		}
		finally {
			this.close();
		}
	}
	
	public void Actualizar(Obra o) throws Exception {
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("UPDATE obras SET direccion=?, descripcion=? WHERE idobra=?");
			ps.setString(1, o.getDireccion());
			ps.setString(2, o.getDescripcion());
			ps.setInt(3, o.getIdObra());
			
			int n=ps.executeUpdate();
			ps.close();
			if(n==0) {
				throw new Exception("No se han actualizado los cambios en la obra, intentelo de nuevo");
			}
		}
		catch(Exception e) {
			throw new SQLException("Ocurrió un error mientras se intentaban actualizar los datos de la obra.");

		}
		finally {
			this.close();
		}
	}
	
	public void Finalizar(int id) throws Exception{
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("UPDATE obras SET finalizado=true WHERE (idobra=?)");
			ps.setInt(1, id);
			int n=ps.executeUpdate();
			ps.close();
			if(n==0) {
				throw new Exception("No se pudo finalizar la obra. Intentelo de nuevo.");
			}
		}catch(Exception e) {
			throw new Exception("No se pudo finalizar la obra. Intentelo de nuevo.");
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
				throw new Exception("No fue posible eliminar la obra, intentelo de nuevo");
			}
		}
		catch(Exception e) {
			throw new Exception("No fue posible eliminar la obra, está siendo utilizada en otros registros.");

		}
		finally {
			this.close();
		}
	}
	
	}
