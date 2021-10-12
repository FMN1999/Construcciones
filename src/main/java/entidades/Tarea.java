package entidades;

public class Tarea {
	
	private int idTarea;
	private String descripcion;
	private Float cant_m2;
	private int id_presupuesto;
	private int id_tipo_tarea;
	
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
	public int getId_presupuesto() {
		return id_presupuesto;
	}
	public void setId_presupuesto(int id_presupuesto) {
		this.id_presupuesto = id_presupuesto;
	}
	public int getId_tipo_tarea() {
		return id_tipo_tarea;
	}
	public void setId_tipo_tarea(int id_tipo_tarea) {
		this.id_tipo_tarea = id_tipo_tarea;
	}
	
	

}
