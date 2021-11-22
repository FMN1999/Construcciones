package entidades;

public class Maquinaria {
	
	private int idMaquina;
	private String descripcion;
	private float precioHora;
	
	public int getIdMaquina() {
		return idMaquina;
	}
	public void setIdMaquina(int idMaquina) {
		this.idMaquina = idMaquina;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecioHora() {
		return precioHora;
	}
	public void setPrecioHora(float precioHora) {
		this.precioHora = precioHora;
	}
	
	public Maquinaria(int idMaquina, String descripcion, float precioHora) {
		super();
		this.idMaquina = idMaquina;
		this.descripcion = descripcion;
		this.precioHora = precioHora;
	}
	
	

}
