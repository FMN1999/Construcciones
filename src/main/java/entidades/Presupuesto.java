package entidades;
import java.util.ArrayList;
import java.util.Date;

public class Presupuesto {

	private int id_presupuesto;
	private Date fecha_aceptacion;
	private Date fecha_caencelacion;
	private Date fecha_emision;
	private float monto;
	private int id_obra;
	private ArrayList<Tarea> tareas;
	
	public Date getFecha_caencelacion() {
		return fecha_caencelacion;
	}
	public void setFecha_caencelacion(Date fecha_caencelacion) {
		this.fecha_caencelacion = fecha_caencelacion;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
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
	
	public ArrayList<Tarea> getTareas() {
		return tareas;
	}
	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = tareas;
	}
	public Presupuesto(int id_presupuesto, Date fecha_emision, float monto ,int idobra, Date aceptacion, Date cancelacion) {
		super();
		this.id_presupuesto = id_presupuesto;
		this.fecha_emision = fecha_emision;
		this.monto=monto;
		this.id_obra = idobra;
		this.fecha_aceptacion =aceptacion;
		this.fecha_caencelacion = cancelacion;
	}
	public Presupuesto() {
		// TODO Auto-generated constructor stub
		this.id_presupuesto=0;
	}
	public int getId_obra() {
		return id_obra;
	}
	public void setId_obra(int id_obra) {
		this.id_obra = id_obra;
	}
	
}
