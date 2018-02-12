package controlers;
import entity.*;
import data.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;



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
	
	public static String getFechaActual() {
	    Date ahora = new java.util.Date();
	    SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	    return formateador.format(ahora);
	}
	public String getMailReserva(Reserva r, Persona p){
		String texto="Datos de su reserva\nIdReserva: "+r.getId_res()+"\nApellido:\t"+ p.getApellido()+"\nNombre:\t"+ p.getNombre()+"\nTipoElemento:\t"+r.getTipoelemento().getNombre_TE()+"\nElemento:\t"+r.getElemento().getNombre_El()+"\nDetalle:\t"+r.getDetalle()+"\nFecha:\t"+r.getFecha()+"\nHora_inicio:\t"+r.getHora_inicio()+"\nHora_fin:\t"+r.getHora_fin();
			
		return texto;
	}
	public String getMailCancelar(Reserva r, Persona p){
		String texto="Su reserva ha sido cancelada\n";
			texto= texto + p.getApellido() +"\t"+ p.getNombre()+"\t"+r.getId_res()+"\t"+
					r.getTipoelemento().getNombre_TE()+"\t"+ r.getElemento()+"\t"+r.getFecha()+"\t"+
					r.getHora_inicio()+"\t"+ r.getHora_fin()+"\t"+r.getDetalle()+"\n";
		return texto;
	}
public int getcantReservasdeTipo(int id_per, int id_te, int id_el) throws Exception {
	    int cant=dataRes.getcantReservasdeTipo(id_per,id_te, id_el);
		return  cant;
	}
    
}