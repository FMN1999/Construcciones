package entidades;

public class Material {
	
	private int id_material;
	private String descripcion;
	private float precio;
	
	public int getId_material() {
		return id_material;
	}
	public void setId_material(int id_material) {
		this.id_material = id_material;
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
	public Material(int id_material, String descripcion, float precio) {
		super();
		this.id_material = id_material;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	
	
	

}
