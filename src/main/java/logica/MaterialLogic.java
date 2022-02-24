package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.MaterialData;
import entidades.Material;
import entidades.Proveedor;

public class MaterialLogic {
	public static MaterialData source=new MaterialData();
	
	//no se captura ningun error, todos van a capa de presentacion
	
	public static void ActualizarDatos(Material m, int id_prov) throws SQLException, Exception {
		boolean modificaPre=false;
		Material mVerif=source.getOne(m.getId_material());
		if(mVerif.getPrecio()-m.getPrecio()!=0) {
			modificaPre=true;
		}
		source.ActualizarDatos(m, modificaPre, id_prov);
	}
	//reemplazar
	/*public static ArrayList<Material> getAll() throws Exception{
		ArrayList<Material> mats=source.getAll();
		return mats;
	}*/
	public static ArrayList<Proveedor> getAll(ArrayList<Proveedor> provs) throws Exception{
		for(Proveedor prov:provs) {
			prov.setMateriales(source.getMateriales(prov.getIdProveedor()));
		}
		
		return provs;
	}
	
	public static ArrayList<Material> getAll() throws Exception{
		return source.getAll();
	}
	
	public static void Registrar(Material m, int id_prov) throws Exception {
		source.Registrar(m, id_prov);
	}
	
	public static void Eliminar(int id) throws Exception{
		source.Eliminar(id);
	}
}
