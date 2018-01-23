package entity;

import java.io.Serializable;

public class TipoElemento implements Serializable   {
	
	private int id_TE;
	private String nombre_TE;
	private int  cant_reserva_max;
	private int tiempo_limite;
	private int dias_anticipacion;
	private boolean encargado; 

	public boolean isEncargado() {
		return encargado;
	}
	public void setEncargado(boolean encargado) {
		this.encargado = encargado;
	}
	public int getId_TE() {
		return id_TE;
	}
	public void setId_TE(int id_TE) {
		this.id_TE = id_TE;
	}
	public String getNombre_TE() {
		return nombre_TE;
	}
	public void setNombre_TE(String nombre_TE) {
		this.nombre_TE = nombre_TE;
	}
	public int getCant_reserva_max() {
		return cant_reserva_max;
	}
	public void setCant_reserva_max(int cant_reserva_max) {
		this.cant_reserva_max = cant_reserva_max;
	}
	public int getTiempo_limite() {
		return tiempo_limite;
	}
	public void setTiempo_limite(int tiempo_limite) {
		this.tiempo_limite = tiempo_limite;
	}
	public int getDias_anticipacion() {
		return dias_anticipacion;
	}
	public void setDias_anticipacion(int dias_anticipacion) {
		this.dias_anticipacion = dias_anticipacion;
	}
	public TipoElemento( String nombre_TE, int cant_reserva_max, int tiempo_limite,
			int dias_anticipacion) {
		
		this.setNombre_TE(nombre_TE);
		this.setCant_reserva_max(cant_reserva_max);
		this.setTiempo_limite(tiempo_limite);
		this.setDias_anticipacion(dias_anticipacion);
	}
	
 public TipoElemento(){
	 
 }
	
	@Override
	public boolean equals(Object te){
		return (te instanceof TipoElemento) &&
			 (((TipoElemento)te).getNombre_TE().equals(this.getNombre_TE()));
					

	}
	
	@Override
	public String toString(){
		return this.getNombre_TE();
	}
	
	@Override
	public int hashCode(){
		return ((Integer)this.getId_TE()).hashCode();
	}
	
	
}