package entidades;

public class Tipo_Tarea {
	
	private int id_tipo_tarea;
	private String descripcion;
	private float precio;
	
	public int getId_tipo_tarea() {
		return id_tipo_tarea;
	}
	public void setId_tipo_tarea(int id_tipo_tarea) {
		this.id_tipo_tarea = id_tipo_tarea;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Tipo_Tarea(int idtipotarea, String descr, float prec) {
		this.id_tipo_tarea = idtipotarea;
		this.descripcion = descr;
		this.precio = prec;
	}
	

}
