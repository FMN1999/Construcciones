package entidades;
import java.util.Date;

public class Presupuesto {

	private int id_presupuesto;
	private Date fecha_aceptacion;
	private Date fecha_emision;
	private int idObra;
	
	public int getId_presupuesto() {
		return id_presupuesto;
	}
	public void setId_presupuesto(int id_presupuesto) {
		this.id_presupuesto = id_presupuesto;
	}
	public Date getFecha_aceptacion() {
		return fecha_aceptacion;
	}
	public void setFecha_aceptacion(Date fecha_aceptacion) {
		this.fecha_aceptacion = fecha_aceptacion;
	}
	public Date getFecha_emision() {
		return fecha_emision;
	}
	public void setFecha_emision(Date fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	public int getIdObra() {
		return idObra;
	}
	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}
	
}
