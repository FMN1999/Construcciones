package logica;

import java.util.ArrayList;

import datos.ObraData;
import entidades.Cliente;
import entidades.Material;
import entidades.Obra;
import entidades.Proveedor;

public class ObraLogic {
	public static ObraData source=new ObraData();
	
	public static Obra getOne(int id) throws Exception {
		Obra o;
		try {
			o=source.getOne(id);
			o.setPresupuestos(PresupuestoLogic.getPresuspuestos(o, false));
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
		
		return o;
	}
	
	public static Cliente setObras(Cliente c) throws Exception{
		c.setObras(source.getObras(c.getIdCliente()));
		for(Obra o:c.getObras()) {
			o.setPresupuestos(PresupuestoLogic.getPresuspuestos(o, false));
		}
		return c;
	}
	
	
	public static ArrayList<Cliente> getAll(ArrayList<Cliente> clientes) throws Exception{
		for(Cliente cli:clientes) {
			cli.setObras(source.getObras(cli.getIdCliente()));
		}
		
		return clientes;
	}
	
	public static void Registrar(Obra o, int cliente ) throws Exception {
		source.Registrar(o, cliente);
	}
	
	public static void Actualizar(Obra o) throws Exception {
		source.Actualizar(o);
	}
	
	public static void Eliminar(int id) throws Exception {
		source.Eliminar(id);
	}
	
	
}
