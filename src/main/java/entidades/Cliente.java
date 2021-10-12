package entidades;

public class Cliente {
	private int idCliente;
	private String razonSocial;
	private String telefono;
	private String mail;
	
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Cliente(int idCliente, String razonSocial, String telefono, String mail) {
		super();
		this.idCliente = idCliente;
		this.razonSocial = razonSocial;
		this.telefono = telefono;
		this.mail = mail;
	}
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", razonSocial=" + razonSocial + ", telefono=" + telefono + ", mail="
				+ mail + "]";
	}
	
	

}
