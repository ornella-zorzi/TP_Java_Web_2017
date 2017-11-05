package entity;

import java.io.Serializable;

public class Persona  {
	private String dni;
	private String nombre;
	private String apellido;
	private String email;
	private String usuario;
	private String contraseña;
	private boolean habilitado;
	private int id_per;
	private Categoria categoria;
	
    public int getId_per() {
		return id_per;
	}
	public void setId_per(int id_per) {
		this.id_per = id_per;
	}
	public Categoria getCategoria(){
		return categoria;
	}
	
	public void setCategoria(Categoria categoria){
		this.categoria=categoria;
	}
		
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	public Persona(){
		
	}
	public Persona(String dni, String nombre, String apellido,String email, String usuario, String contraseña,boolean habilitado){
		this.setDni(dni);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setEmail(email);
		this.setUsuario(usuario);
		this.setContraseña(contraseña);
		this.setHabilitado(habilitado);
			
	}
	
	@Override
	public boolean equals(Object p){
		return (p instanceof Persona) &&
			 (((Persona)p).getDni().equals(this.getDni()));
					

	}

}
