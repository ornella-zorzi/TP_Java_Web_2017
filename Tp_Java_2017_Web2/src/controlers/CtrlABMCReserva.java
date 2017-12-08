package controlers;
import entity.*;
import data.*;

import java.io.Serializable;
import java.util.ArrayList;



public class CtrlABMCReserva implements Serializable   {
	private DataPersona dataPer;
	private DataReserva dataRes;
	private DataTipoElemento dataTe;
	private DataElemento dataEL;
	private ArrayList<Reserva> res;
	
	public CtrlABMCReserva(){
		dataRes = new DataReserva();
		dataPer = new DataPersona();
		dataEL= new DataElemento();
		dataTe= new DataTipoElemento();
		res =new ArrayList<Reserva>();
	}
	public void add(Reserva r) throws Exception {
	
		dataRes.add(r);
	}
	
	public void delete(Reserva r) throws Exception{
		
		dataRes.delete(r);
	}
	
	public void update(Reserva r)throws Exception {
		
		dataRes.update(r);
	}
	
/*	public Reserva getByDni(Reserva r)throws Exception {
		//return this.pers.get(this.pers.indexOf(p));
		return dataPer.getByDni(r);
	}*/
	
/*	public Persona getByDni(String dni) throws Exception {
		Persona p=new Persona();
		p.setDni(dni);
		return getByDni(p);
	}*/
	
/*	public Persona getByNombreApellido(Persona p){
		
		for (int i=0; i < this.pers.size(); i++){
			if(pers.get(i).getNombre().equalsIgnoreCase(p.getNombre())
				&& pers.get(i).getApellido().equalsIgnoreCase(p.getApellido())) {
				return pers.get(i);		
			}
		}
		return null; 
		
	}*/
	
	public ArrayList<Reserva> getAll() throws Exception {
		
		return dataRes.getAll();
	}
	public ArrayList<Persona> getPersona() throws Exception{
		return dataPer.getAll();
	}
	/*public ArrayList<Elemento> getElemento() throws Exception{
		return dataEL.getAll();
	}*/
	public ArrayList<TipoElemento> getTipoElemento() throws Exception{
		return dataTe.getAll();
	}
	public ArrayList<Elemento> getElementosDeTipo(int id) throws Exception{
		return dataEL.getAll2(id);
		
	}
	
	public Elemento getById(int id_el) throws Exception{	
		return dataEL.getById(id_el);
	}
	public TipoElemento getByIdTE(int id_te) throws Exception{	
		return dataTe.getByIdTE(id_te);
	}
	public int validaDisponibilidad(Reserva r) throws Exception{
		int i=dataRes.validaDisponibilidad(r);
		return i;
	}

}