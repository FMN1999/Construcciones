package entidades;

import java.util.ArrayList;

public class Obra {
	private int idObra;
	private String direccion;
	private ArrayList<Presupuesto> presupuestos;
	private String descripcion;
	
	public ArrayList<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	public void setPresupuestos(ArrayList<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
	}

	public int getIdObra() {
		return idObra;
	}

	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	public Obra(int idObra, String direccion, String descripcion) {
		super();
		this.idObra = idObra;
		this.direccion = direccion;
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	

}
