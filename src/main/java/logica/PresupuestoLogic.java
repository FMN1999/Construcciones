package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.PresupuestoData;
import entidades.Obra;
import entidades.Presupuesto;

public class PresupuestoLogic {
	public static PresupuestoData source=new PresupuestoData();
	
	
	public static ArrayList<Presupuesto> getPresuspuestos(Obra o) throws Exception{
		ArrayList<Presupuesto> presups=source.getPresupuestos(o);
		for(Presupuesto p: presups) {
			p.setTareas(TareaLogic.getTareas(p.getId_presupuesto()));
		}
		
		return presups;
	}
}
