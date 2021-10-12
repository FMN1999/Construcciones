package entidades;
import java.util.Date;

public class Maquinaria {
	
	private int idMaquina;
	private String descripcion;
	private Date precioHora;
	
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
	public Date getPrecioHora() {
		return precioHora;
	}
	public void setPrecioHora(Date precioHora) {
		this.precioHora = precioHora;
	}
	

}
