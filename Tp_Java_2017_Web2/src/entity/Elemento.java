package entity;

import java.io.Serializable;

public class Elemento implements Serializable {
	private int id_El;
	private String nombre_El;
	private TipoElemento tipoElemento;
	
	
	public TipoElemento getTipoElemento() {
		return tipoElemento;
	}

	public void setTipoElemento(TipoElemento tipoElemento) {
		this.tipoElemento = tipoElemento;
	}

	public int getId_El() {
		return id_El;
	}

	public void setId_El(int id_El) {
		this.id_El = id_El;
	}
	public String getNombre_El() {
		return nombre_El;
	}
	public void setNombre_El(String nombre_El) {
		this.nombre_El = nombre_El;
	}
	

	public Elemento ( int id_El , String nombre_El,int id_TE,String nombre_Te){
		this.setId_El(id_El);
		this.setNombre_El(nombre_El);
		
		
	}
	public Elemento (){
		
	}
	@Override
	public String toString(){
		return this.getNombre_El();
	}
	

	
	@Override
	public int hashCode(){
		return ((Integer)this.getId_El()).hashCode();
	}
	@Override
	public boolean equals(Object e){
		return (e instanceof Elemento) &&
			 (((Elemento)e).getNombre_El().equals(this.getNombre_El()));
					

	}
}
