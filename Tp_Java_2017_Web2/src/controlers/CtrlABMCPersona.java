package controlers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import data.DataCategoria;
import entity.Persona;
import entity.Categoria;
import data.DataPersona;

public class CtrlABMCPersona {
	private DataPersona dataPer;
	private DataCategoria dataCat;
	
private ArrayList<Persona> pers;
	
	
	public CtrlABMCPersona(){
		dataPer = new DataPersona();
		dataCat = new DataCategoria();
		pers= new ArrayList<Persona>();
			  
	}

	public void add(Persona p) throws Exception {
		dataPer.add(p);
	}
	
	public void delete(Persona p) throws Exception{
		dataPer.delete(p);
	}
	
	public void update(Persona p)throws Exception {
		dataPer.update(p);
	}
	
	public Persona getByDni(Persona p)throws Exception {
		return dataPer.getByDni(p);
	}
	
	public Persona getByDni(String dni) throws Exception {
		Persona p=new Persona();
		p.setDni(dni);
		return getByDni(p);
	}
	

	public Persona getByNombreApellido(Persona p){
		
		for (int i=0; i < this.pers.size(); i++){
			if(pers.get(i).getNombre().equalsIgnoreCase(p.getNombre())
				&& pers.get(i).getApellido().equalsIgnoreCase(p.getApellido())) {
				return pers.get(i);		
			}
		}
		return null; 
		
	}
	public Persona getValidacionUsario(Persona per) throws Exception{
		return dataPer.getValidacionUsario(per);
	}
	
	public ArrayList<Persona> getAll() throws Exception {
		return dataPer.getAll();
	}
	public ArrayList<Categoria> getCategoria() throws Exception{
		return dataCat.getAll();
	}
	public Categoria getById(int id_cat) throws Exception{
		return dataCat.getById(id_cat);
	}
	public int validabaja(int id_per) throws Exception {
		return dataPer.validabaja(id_per);
		
	}
	public int validaDni(String dni) throws Exception {
		return dataPer.validaDni(dni);
		
	}
	public int validaUsuario(String usuario) throws Exception {
		return dataPer.validaUsuario(usuario);
		
	}
	
}