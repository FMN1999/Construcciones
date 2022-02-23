package entidades;
import logica.Tipo_TareaLogic;

public class Tarea {
	
	private int idTarea;
	private String descripcion;
	private Float cant_m2;
	private Tipo_Tarea tipo_tarea;
	
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
	}
	public Tipo_Tarea getTipo_tarea() {
		return tipo_tarea;
	}
	public void setTipo_tarea(Tipo_Tarea tipo_tarea) {
		this.tipo_tarea = tipo_tarea;
	}
	/*
	public Tarea(int idTarea, int idTipoTarea, String descTipoTarea, float precioTipoTarea String descripcion, Float cant_m2, Float precio_parcial) {
		this.idTarea = idTarea;
		this.tipo_tarea.setId_tipo_tarea(idTipoTarea);
		this.tipo_tarea.setDescripcion(descTipoTarea);
		this.descripcion = descripcion;
		this.cant_m2 = cant_m2;
		
	}
	*/
	public Float getMontoParcial() {
		return this.cant_m2 * this.tipo_tarea.getPrecio();
	}
	

}
