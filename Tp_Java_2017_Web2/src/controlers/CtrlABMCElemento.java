package controlers;
import java.io.Serializable;
import java.util.ArrayList;

import entity.Categoria;
import entity.Elemento;
import data.DataElemento;
import entity.TipoElemento;
import data.DataTipoElemento;


public class CtrlABMCElemento implements Serializable  { 

	private DataElemento dataElem;
	private DataTipoElemento dataTipoEl;
	private ArrayList<Elemento> elems;

	public CtrlABMCElemento(){
	dataElem = new DataElemento();
	dataTipoEl = new DataTipoElemento();
	elems= new ArrayList<Elemento>();
	}

	public void add(Elemento e) throws Exception {
	dataElem.add(e);
	}

	public void delete(Elemento e) throws Exception{
	dataElem.delete(e);
	}

	public void update(Elemento e)throws Exception {
	dataElem.update(e);
	}

	public Elemento getByNombre(Elemento el)throws Exception {
	return dataElem.getByNombre(el);
	}
	public Elemento getByNombre(String nombre_el) throws Exception{
		Elemento el = new Elemento();
		el.setNombre_El(nombre_el);
		return getByNombre(el);
	}

   public ArrayList<Elemento> getAll() throws Exception {
	return dataElem.getAll();
	}
   
	public ArrayList<TipoElemento> getTipoElemento() throws Exception {
		return dataTipoEl.getAll();
		
	}
	public Elemento getById(int id_el) throws Exception{
		return dataElem.getById(id_el);
	}

}