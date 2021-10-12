package entidades;

public class Usuario {
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private long cuil;
	private String tipo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getCuil() {
		return cuil;
	}
	public void setCuil(long cuil) {
		this.cuil = cuil;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Usuario(int id, String nombre, String apellido, String email, String password, long cuil, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.cuil = cuil;
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", password=" + password + ", cuil=" + cuil + ", tipo=" + tipo + "]";
	}
	
	
	
}
