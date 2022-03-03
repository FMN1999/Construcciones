package entidades;
import java.util.ArrayList;
import java.util.Date;

import logica.Tipo_TareaLogic;

public class Tarea {
	
	private int idTarea;
	private String descripcion;
	private Float cant_m2;
	private Tipo_Tarea tipo_tarea;
	private ArrayList<Material> materiales;
	private ArrayList<Maquinaria> maquinas;
	private Date fechaDesde;
	private Date fechaHasta;
	
	public int getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Float getCant_m2() {
		return cant_m2;
	}
	public void setCant_m2(Float cant_m2) {
		this.cant_m2 = cant_m2;
	}
	
	public Tarea(int idTarea, String descripcion, Float cant_m2, Tipo_Tarea tipo_tarea ) {
		this.idTarea = idTarea;
		this.descripcion = descripcion;
		this.cant_m2 = cant_m2;
		this.tipo_tarea= tipo_tarea;
		this.maquinas=new ArrayList<Maquinaria>();
		this.materiales=new ArrayList<Material>();
	}
	public Tipo_Tarea getTipo_tarea() {
		return tipo_tarea;
	}
	public void setTipo_tarea(Tipo_Tarea tipo_tarea) {
		this.tipo_tarea = tipo_tarea;
	}
	public ArrayList<Material> getMateriales() {
		return materiales;
	}
	public void setMateriales(ArrayList<Material> materiales) {
		this.materiales = materiales;
	}
	public Float getMontoParcial() {
		return this.cant_m2 * this.tipo_tarea.getPrecio();
	}
	public ArrayList<Maquinaria> getMaquinas() {
		return maquinas;
	}
	public void setMaquinas(ArrayList<Maquinaria> maquinas) {
		this.maquinas = maquinas;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}
