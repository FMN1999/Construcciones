package entidades;
import java.util.Date;

public class Trabajador {
	private int cuil;
	private String tipo_doc;
	private int n_doc;
	private String nombreYApellido;
	private Date fechaNac;
	private boolean disponible;
	
	public int getCuil() {
		return cuil;
	}
	public void setCuil(int cuil) {
		this.cuil = cuil;
	}
	public String getTipo_doc() {
		return tipo_doc;
	}
	public void setTipo_doc(String tipo_doc) {
		this.tipo_doc = tipo_doc;
	}
	public int getN_doc() {
		return n_doc;
	}
	public void setN_doc(int n_doc) {
		this.n_doc = n_doc;
	}
	public String getNombreYApellido() {
		return nombreYApellido;
	}
	public void setNombreYApellido(String nombreYApellido) {
		this.nombreYApellido = nombreYApellido;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	

}
