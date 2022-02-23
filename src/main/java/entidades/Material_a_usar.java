package entidades;

public class Material_a_usar {
	private Tipo_Tarea tt;
	private Tarea t;
	private Material m;
	private int cant_a_usar;
	
	public int getCant_a_usar() {
		return cant_a_usar;
	}
	public void setCant_a_usar(int cant_a_usar) {
		this.cant_a_usar = cant_a_usar;
	}
	public Tipo_Tarea getTt() {
		return tt;
	}
	public void setTt(Tipo_Tarea tt) {
		this.tt = tt;
	}
	public Tarea getT() {
		return t;
	}
	public void setT(Tarea t) {
		this.t = t;
	}
	public Material getM() {
		return m;
	}
	public void setM(Material m) {
		this.m = m;
	}
	
	
	public Material_a_usar(Material m, Tarea t, Tipo_Tarea tt, int cant_a_usar) {
		super();
		this.m=m;
		this.t=t;
		this.tt=tt;
		this.cant_a_usar=cant_a_usar;
	}
	
	public Float getMonto() {
		return this.cant_a_usar * this.m.getPrecio();
	}

}
