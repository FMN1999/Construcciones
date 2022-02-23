package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.PresupuestoData;
import entidades.Obra;
import entidades.Presupuesto;
import entidades.Material;
import entidades.Material_a_usar;
import entidades.Maquinaria;
import entidades.Tarea;

public class PresupuestoLogic {
	public static PresupuestoData source=new PresupuestoData();
	
	
	public static ArrayList<Presupuesto> getPresuspuestos(Obra o) throws Exception{
		return source.getPresupuestos(o);
	}
	
	public static ArrayList<Material_a_usar> getMateriales(Presupuesto p) throws Exception{
		return source.getMateriales(p);
	}
	
	public static ArrayList<Maquinaria> getMaquinarias(Presupuesto p) throws Exception{
		return source.getMaquinarias(p);
	}
	
	public static ArrayList<Tarea> getTareas(Presupuesto p) throws Exception{
		return source.getTareas(p);
	}
	
	public static String getEstado(Presupuesto p) throws Exception{
		if(p.getFecha_aceptacion()!=null) {
			return "Confrmado";
		} 
		
		if(p.getFecha_caencelacion()!=null) {
			return "Rechazado";
		}
		return "En trámite";
	}
	
	public static Presupuesto getOne(int id) throws Exception{
		return source.getOne(id);
	}
}
