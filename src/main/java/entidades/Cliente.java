package entidades;

import java.util.ArrayList;

public class Cliente extends Usuario{
	private int idCliente;
	private String razonSocial;
	private String telefono;
	private long cuil;
	private ArrayList<Obra> obras;
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
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
	
	public Obra getObra(int idObra) {
		for (Obra o : getObras()) {
			if (o.getIdObra() == idObra) {
				return o;
			}
		}
		return null;
	}
	
	public ArrayList<Obra> getObras() {
		return obras;
	}
	public void setObras(ArrayList<Obra> obras) {
		this.obras = obras;
	}
	
	
	public Cliente(int id, String nombre, String apellido, String email, String password, long cuil, String tipo, 
			String razonSocial, String telefono, int idCliente)
	{
		super(id, nombre, apellido, email, password, cuil, tipo);
		this.idCliente = idCliente;
		this.cuil = cuil;
		this.razonSocial = razonSocial;
		this.telefono = telefono;
	}
	
	

}
