package entidades;

public class Cliente extends Usuario{
	private String razonSocial;
	private String telefono;
	private long cuil;
	
	public long getCuil() {
		return cuil;
	}
	public void setCuil(long cuil) {
		this.cuil = cuil;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Cliente(int id, String nombre, String apellido, String email, String password, long cuil, String tipo, 
			String razonSocial, String telefono)
	{
		super(id, nombre, apellido, email, password, cuil, tipo);
		this.cuil = cuil;
		this.razonSocial = razonSocial;
		this.telefono = telefono;
	}
	
	

}
