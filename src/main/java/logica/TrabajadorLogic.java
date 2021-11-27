package logica;

import java.util.ArrayList;

import datos.TrabajadorData;
import entidades.Trabajador;

public class TrabajadorLogic {

	private static TrabajadorData source=new TrabajadorData();
	
	public static ArrayList<Trabajador> getOficiales() throws Exception {
		ArrayList<Trabajador> trs=source.getOficiales();
		return trs;
	}
	
	public static ArrayList<Trabajador> getObreros() throws Exception {
		ArrayList<Trabajador> trs=source.getObreros();
		return trs;
	}
}
