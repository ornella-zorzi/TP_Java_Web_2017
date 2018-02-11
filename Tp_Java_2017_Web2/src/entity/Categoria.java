package entity;

import java.io.Serializable;

public class Categoria  implements Serializable  {

	private int id_cat;
	private String nombre_cat;
	
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public String getNombre_cat() {
		return nombre_cat;
	}
	public void setNombre_cat(String nombre_cat) {
		this.nombre_cat = nombre_cat;
	}
	
	
	@Override
	public String toString(){
		return this.getNombre_cat();
	}
	
	@Override
	public boolean equals(Object o){
		return (o instanceof Categoria && ((Categoria)o).getId_cat()==this.getId_cat());
	}
	
	@Override
	public int hashCode(){
		return ((Integer)this.getId_cat()).hashCode();
	}
	
}
