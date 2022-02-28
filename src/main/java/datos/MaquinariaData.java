package datos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Maquinaria;
import entidades.Material;
import entidades.Tarea;

public class MaquinariaData extends Coneccion {
	
	public Maquinaria getOne(int id) throws SQLException {
		Maquinaria m=null;
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("SELECT idmaquina, descripcion, precioHora FROM maquinarias WHERE idmaquina=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			m= new Maquinaria(rs.getInt("idmaquina"), rs.getString("descripcion"),rs.getFloat("precioHora"));
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
			PreparedStatement ps = this.getCon().prepareStatement("SELECT idmaquina, descripcion, precioHora from maquinarias");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listaMaq.add(new Maquinaria(rs.getInt("idmaquina"), rs.getString("descripcion"),rs.getFloat("precioHora")));
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
			PreparedStatement ps = this.getCon().prepareStatement("INSERT into maquinarias(descripcion, precioHora) values(?,?)");
			ps.setString(1, m.getDescripcion());
			ps.setFloat(2, m.getPrecioHora());
			
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
	
	public void ActualizarDatos(Maquinaria m) throws SQLException, Exception {
		int n=0;
		try {
			this.open();
			PreparedStatement ps=this.getCon().prepareStatement("UPDATE maquinarias SET descripcion=?, precioHora=? WHERE (idmaquina=?)");
			ps.setString(1, m.getDescripcion());
			ps.setFloat(2, m.getPrecioHora());
			ps.setInt(3, m.getIdMaquina());
			
			n=ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			throw new Exception("No se han registrado los cambios, intentelo de nuevo!"+e.getMessage());
		}
		finally {
			this.close();
		}
		
		if(n==0) {
			throw new Exception("No se han registrado los cambios, intentelo de nuevo!");
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
			PreparedStatement ps= this.getCon().prepareStatement("SELECT m.idmaquina, descripcion, ifnull(valor_hora,0.0) as precio FROM maquinarias m "
					+ "left join precios_maquina on m.idmaquina=id_maquina "
					+ "inner join tareas_maquinas tm on m.idmaquina=tm.id_maquina__ "
					+ "where (fecha_desde= (select max(fecha_desde) from precios_maquina where id_maquina=idmaquina)) "
					+ "and tm.id_tarea__=? "
					+ "group by idmaquina");
			ps.setInt(1, idTarea);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Maquinaria m=new Maquinaria(rs.getInt("m.idmaquina"), rs.getString("descripcion"), rs.getFloat("precio"));
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
	public void RegistrarUsoMaquinas(Tarea t, Maquinaria m, int horas) throws Exception {
		int n=0;
		try {
			this.open();
			PreparedStatement ps = this.getCon().prepareStatement("INSERT into tareas_maquinas(id_material__, id_tarea__, hs_uso) values(?,?,?)");
			ps.setInt(1, m.getIdMaquina());
			ps.setInt(2, t.getIdTarea());
			ps.setInt(3, horas);
			
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
