package entidades;
import java.util.Date;
import java.util.ArrayList;

public class Trabajador extends Usuario {
	private long cuil;
	private String tipo_doc;
	private long n_doc;
	private Date fechaNac;
	private boolean disponible;
	private String tipoEmpleado;
	private float precioHS;
	private ArrayList<TareaAsignada> tareasAsignadas;
	
	
	public long getCuil() {
		return cuil;
	}
	public void setCuil(long cuil) {
		this.cuil = cuil;
	}
	public String getTipo_doc() {
		return tipo_doc;
	}
	public void setTipo_doc(String tipo_doc) {
		this.tipo_doc = tipo_doc;
	}
	public long getN_doc() {
		return n_doc;
	}
	public void setN_doc(long n_doc) {
		this.n_doc = n_doc;
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
	public String getTipoEmpleado() {
		return tipoEmpleado;
	}
	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}
	public float getPrecioHS() {
		return precioHS;
	}
	public void setPrecioHS(float precioHS) {
		this.precioHS = precioHS;
	}
	public Trabajador(int id, String nombre, String apellido, String email, String password, long cuil, String tipo, 
			String tipo_doc, long n_doc, Date fechaNac, boolean disponible,
			String tipoEmpleado, float precioHS) {
		super(id, nombre, apellido, email, password, cuil, tipo);
		this.cuil = cuil;
		this.tipo_doc = tipo_doc;
		this.n_doc = n_doc;
		this.fechaNac = fechaNac;
		this.disponible = disponible;
		this.tipoEmpleado = tipoEmpleado;
		this.precioHS = precioHS;
		this.setTareasAsignadas(new ArrayList<TareaAsignada>());
	}
	public ArrayList<TareaAsignada> getTareasAsignadas() {
		return tareasAsignadas;
	}
	public void setTareasAsignadas(ArrayList<TareaAsignada> tareasAsignadas) {
		this.tareasAsignadas = tareasAsignadas;
	}
	
	public void addTareaAsignada(int hs, Date fecha, Tarea t) {
		this.getTareasAsignadas().add(new TareaAsignada(hs, fecha, t));
	}
	//usar clase interna para recuperar las tareas asignadas
	public class TareaAsignada {
		public Date fecha_asignada;
		public int hs_asignadas;
		public Tarea tarea;
		
		public TareaAsignada(int hs, Date fecha, Tarea t) {
			this.fecha_asignada=fecha;
			this.hs_asignadas=hs;
			this.tarea=t;
		}
	}
	
}
