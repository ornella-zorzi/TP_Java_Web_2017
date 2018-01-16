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
	public ArrayList<Reserva> getAll() throws Exception {
		
		return dataRes.getAll();
	}
   public ArrayList<Reserva> getReservasPendientes(Persona p) throws Exception {
		
		return dataRes.getReservasPendientes(p);
	}
	public ArrayList<Persona> getPersona() throws Exception{
		return dataPer.getAll();
	}
	public ArrayList<TipoElemento> getTipoElemento() throws Exception{
		return dataTe.getAll();
	}
	public ArrayList<Elemento> getElementosDeTipo(int id) throws Exception{
		return dataEL.getAll2(id);
		
	}
	
	public int validaDisponibilidad(Reserva r) throws Exception{
		int i=dataRes.validaDisponibilidad(r);
		return i;
	}
	public Reserva getById(Reserva r) throws Exception{	
		return dataRes.getById(r);
	}

}