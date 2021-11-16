package entidades;

public class Material {
	
	private int id_material;
	private String descripcion;
	private int id_provedor;
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
	public int getId_provedor() {
		return id_provedor;
	}
	public void setId_provedor(int id_provedor) {
		this.id_provedor = id_provedor;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Material(int id_material, String descripcion, int id_provedor, float precio) {
		super();
		this.id_material = id_material;
		this.descripcion = descripcion;
		this.id_provedor = id_provedor;
		this.precio = precio;
	}
	
	
	
	

}
