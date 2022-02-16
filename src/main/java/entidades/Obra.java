package entidades;

public class Obra {
	private int idObra;
	private String direccion;
	
	public int getIdObra() {
		return idObra;
	}

	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	public Obra(int idObra, String direccion) {
		super();
		this.idObra = idObra;
		this.direccion = direccion;
	}
	
	

}
