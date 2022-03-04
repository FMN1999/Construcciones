package entidades;

import java.util.ArrayList;

public class Obra {
	private int idObra;
	private String direccion;
	private ArrayList<Presupuesto> presupuestos;
	private String descripcion;
	private boolean finalizado;
	
	public float gastosMateriales() {
		float total = 0;
		for (Presupuesto pr : this.presupuestos) {
			if(pr.getFecha_aceptacion() != null){
				for(Tarea t: pr.getTareas()) {
					for(Material m: t.getMateriales()) {
						total+= (m.getPrecio() * m.getCantidad());
					}
				}
			}		
		}
		return total;
	}
	
	public float gastosMaquinas() {
		float total = 0;
		for (Presupuesto pr : this.presupuestos) {
			if(pr.getFecha_aceptacion() != null){
				for(Tarea t: pr.getTareas()) {
					for(Maquinaria m: t.getMaquinas()) {
						total+= (m.getCantHoras() * m.getPrecioHora());
					}
				}
			}		
		}
		return total;
	}
	
	public float gastosTareas() {
		float total = 0;
		for (Presupuesto pr : this.presupuestos) {
			if(pr.getFecha_aceptacion() != null){
				for(Tarea t: pr.getTareas()) {
					total+= t.getMontoParcial();
				}
			}		
		}
		return total;
	}
	
	public float totalPresupuestado() {
		float total = 0;
		for (Presupuesto pr : this.presupuestos) {
			if(pr.getFecha_aceptacion() != null){
				total+= pr.getMonto();
				}	
		}
		return total;
	}
	
	public float getBalance() {
		return this.totalPresupuestado() -(this.gastosMaquinas() + this.gastosMateriales() + 
				this.gastosTareas());
	}
	
	
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

	
	public Obra(int idObra, String direccion, String descripcion, boolean finalizado) {
		super();
		this.idObra = idObra;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.finalizado = finalizado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int presupuestosPendientes() {
		int pendientes=0;
		for(Presupuesto p:this.getPresupuestos()) {
			if(p.getFecha_aceptacion()==null && p.getFecha_caencelacion()==null) {
				pendientes+=1;
			}
		}
		return pendientes;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	

}
