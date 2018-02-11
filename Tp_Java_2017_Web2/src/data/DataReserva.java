package data;
import entity.*;
import util.ApplicationException;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

public class DataReserva implements Serializable  {
	public ArrayList<Reserva> getAll() throws ApplicationException{
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Reserva> res = new ArrayList<Reserva>();
		try{ 
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("Select r.id_res, te.id_te, te.nombre_te, e.id_el,e.nombre_el, p.id_per,p.nombre, p.apellido, fecha, hora_inicio,hora_fin, detalle from reserva r " +
			"inner join persona p on r.id_per=p.id_per inner join elemento e on e.id_el=r.id_el inner join tipo_elemento te on te.id_te=r.id_te");
			
			if (rs!= null ){
				while(rs.next()){
					Reserva r=new Reserva();
					r.setId_res(rs.getInt("r.id_res"));
					r.setTipoelemento(new TipoElemento());
					r.getTipoelemento().setId_TE(rs.getInt("te.id_te"));
					r.getTipoelemento().setNombre_TE(rs.getString("te.nombre_te"));
					r.setElemento(new Elemento());
					r.getElemento().setId_El(rs.getInt("e.id_el"));
					r.getElemento().setNombre_El(rs.getString("e.nombre_el"));
					r.setPersona(new Persona());
					r.getPersona().setId_per(rs.getInt("p.id_per"));
					r.getPersona().setNombre(rs.getString("p.nombre"));
					r.getPersona().setApellido(rs.getString("p.apellido"));
					r.setHora_inicio(rs.getTime("hora_inicio"));
				    r.setHora_fin(rs.getTime("hora_fin"));
					r.setFecha(rs.getDate("fecha"));
					r.setDetalle(rs.getString("detalle"));
					res.add(r);
						
				}
			}
		}
			catch (SQLException e) {
				ApplicationException ade=new ApplicationException(e, "Error al recuperar listado de reservas.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
				throw ade;
			} catch (ApplicationException ade){
				throw ade;
			}
			try {
			if(rs!=null) rs.close();
			if (stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				ApplicationException ade=new ApplicationException(e, "Error.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
				throw ade;
			} catch (ApplicationException ade){
				throw ade;
			}
			return res;
		
	} 
	public ArrayList<Reserva> getReservasPendientes(Persona p) throws ApplicationException{
		Reserva r = null ;
    	PreparedStatement stmt= null;
    	ResultSet rs=null;
		ArrayList<Reserva> res = new ArrayList<Reserva>();
		try{ 
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("Select r.id_res, te.id_te, te.nombre_te, e.id_el,e.nombre_el, p.id_per,p.nombre, p.apellido, fecha, hora_inicio,hora_fin, detalle from reserva r " +
					"inner join persona p on r.id_per=p.id_per inner join elemento e on e.id_el=r.id_el inner join tipo_elemento te on te.id_te=r.id_te where r.id_per=? and r.fecha>=curdate() ");
			stmt.setInt(1, p.getId_per());
			rs = stmt.executeQuery();
			
			if (rs!= null ){
				while(rs.next()){
					r=new Reserva();
					r.setId_res(rs.getInt("r.id_res"));
					r.setTipoelemento(new TipoElemento());
					r.getTipoelemento().setId_TE(rs.getInt("te.id_te"));
					r.getTipoelemento().setNombre_TE(rs.getString("te.nombre_te"));
					r.setElemento(new Elemento());
					r.getElemento().setId_El(rs.getInt("e.id_el"));
					r.getElemento().setNombre_El(rs.getString("e.nombre_el"));
					r.setPersona(new Persona());
					r.getPersona().setId_per(rs.getInt("p.id_per"));
					r.getPersona().setNombre(rs.getString("p.nombre"));
					r.getPersona().setApellido(rs.getString("p.apellido"));
					r.setHora_inicio(rs.getTime("hora_inicio"));
					r.setHora_fin(rs.getTime("hora_fin"));
					r.setFecha(rs.getDate("fecha"));
					r.setDetalle(rs.getString("detalle"));
					res.add(r);
						
				}
			}
			
		} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error al recuperar listado de reservas pendientes.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (ApplicationException ade){
			throw ade;
		}
		try {
			if(rs!=null) rs.close();
			if (stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (ApplicationException ade){
			throw ade;
		}
			return res;
		
	} 

   public void add (Reserva r) throws Exception{
    	PreparedStatement stmt=null;
    	ResultSet keyResultSet=null;
    	try{ stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
				"insert into reserva(id_el, id_te, fecha, hora_inicio,hora_fin, detalle, id_per) " +
				 "values (?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
    		  stmt.setInt(1,r.getElemento().getId_El());
    		  stmt.setInt(2,r.getTipoelemento().getId_TE());
    		  stmt.setDate(3,r.getFecha()); 
    		  stmt.setTime(4,r.getHora_inicio());
    		  stmt.setTime(5,r.getHora_fin());
    		  stmt.setString(6,r.getDetalle());
    		  stmt.setInt(7,r.getPersona().getId_per());
    		  
    		  stmt.executeUpdate();
    		  keyResultSet=stmt.getGeneratedKeys();
    		  if (keyResultSet!=null && keyResultSet.next()){
    			  r.setId_res(keyResultSet.getInt(1));
    		  }
    		
    	} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error al agregar la reserva.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (ApplicationException ade){
			throw ade;
		}try { 
    			if (keyResultSet!=null)keyResultSet.close();
    			if (stmt!=null)stmt.close();
    			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error .\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (ApplicationException ade){
			throw ade;
		}
    } 

public ResultSet getResultSet() throws ApplicationException{	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT id_res, id_el, id_te, fecha, hora_inicio,hora_fin, detalle, id_per " +
					"FROM reserva  inner join elemento  on reserva.id_el=elemento.id_el where id_te=?");			
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (ApplicationException ade){
			throw ade;
		}
		return rs;
		
	}


public void update(Reserva r) throws ApplicationException{
	PreparedStatement stmt=null;	
	try {
		stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
				"UPDATE reserva SET id_el=?, id_te=?, fecha=?, hora_inicio=?,hora_fin=?, detalle=? WHERE id_res=?");	
		
		 stmt.setInt(1,r.getElemento().getId_El());
		  stmt.setInt(2,r.getElemento().getTipoElemento().getId_TE());
		 // stmt.setString(3,r.getPersona().getDni());
		  stmt.setDate(3, (Date) r.getFecha());
		  stmt.setTime(4,(Time) r.getHora_inicio());
		  stmt.setTime(5,(Time) r.getHora_fin());
		  stmt.setString(6,r.getDetalle());
		  stmt.setInt(7, r.getId_res());
		  
		  stmt.execute();
		
	}catch (SQLException e) {
		ApplicationException ade=new ApplicationException(e, "Error al modificar reserva.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
		throw ade;
	} catch (ApplicationException ade){
		throw ade;
	}

}

public void delete(Reserva r) throws ApplicationException{
	PreparedStatement stmt=null;		
	try {
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
				"delete from reserva where id_res=?");
		stmt.setInt(1,r.getId_res());
		stmt.execute();
	} catch (SQLException e) {
		ApplicationException ade=new ApplicationException(e, "Error al eliminar reserva.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
		throw ade;
	} catch (ApplicationException ade){
		throw ade;
	}
	
	
}

public int  validaDisponibilidad(Reserva re) throws ApplicationException{
	PreparedStatement stmt= null;
	ResultSet rs=null;
	ArrayList<Reserva> res = new ArrayList<Reserva>();
	int i=0;
	try{ 
	stmt= FactoryConexion.getInstancia().getConn().prepareStatement( "select * from reserva r where (?<r.`hora_fin` and ?>r.`hora_inicio`) and (r.id_el=? and r.id_te=? and r.fecha=?)");
	stmt.setTime(1,re.getHora_inicio());
	stmt.setTime(2,re.getHora_fin());	
	stmt.setInt(3,re.getElemento().getId_El());
	stmt.setInt(4,re.getTipoelemento().getId_TE());
	stmt.setDate(5,re.getFecha());
		
		 
		 rs=stmt.executeQuery();
		 if(rs!=null && rs.next()){
					i=1;
			}
		
	} catch (SQLException e) {
		ApplicationException ade=new ApplicationException(e, "Error al validar disponibilidad.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
		throw ade;
	} catch (ApplicationException ade){
		throw ade;
	} try {
		if(rs!=null) rs.close();
		if (stmt!=null) stmt.close();
		FactoryConexion.getInstancia().releaseConn();
	} catch (SQLException e) {
		ApplicationException ade=new ApplicationException(e, "Error.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
		throw ade;
	} catch (ApplicationException ade){
		throw ade;
	}
		return (i);
		
	
}
public Reserva getById(Reserva r) throws Exception{
	Reserva re = null ;
	PreparedStatement stmt= null;
	ResultSet rs=null;
	try {
		 stmt= FactoryConexion.getInstancia().getConn().prepareStatement( "Select r.id_res, te.id_te, te.nombre_te, e.id_el,e.nombre_el, p.id_per,p.nombre, p.apellido, fecha, hora_inicio,hora_fin, detalle from reserva r " +
			"inner join persona p on r.id_per=p.id_per inner join elemento e on e.id_el=r.id_el inner join tipo_elemento te on te.id_te=r.id_te where id_res=?");
		 stmt.setInt(1, r.getId_res());
		 rs=stmt.executeQuery();
		 if(rs!=null && rs.next()){
			    re=new Reserva();
				re.setId_res(rs.getInt("r.id_res"));
				re.setTipoelemento(new TipoElemento());
				re.getTipoelemento().setId_TE(rs.getInt("te.id_te"));
				re.getTipoelemento().setNombre_TE(rs.getString("te.nombre_te"));
				re.setElemento(new Elemento());
				re.getElemento().setId_El(rs.getInt("e.id_el"));
				re.getElemento().setNombre_El(rs.getString("e.nombre_el"));
				re.setPersona(new Persona());
				re.getPersona().setId_per(rs.getInt("p.id_per"));
				re.getPersona().setNombre(rs.getString("p.nombre"));
				re.getPersona().setApellido(rs.getString("p.apellido"));
				re.setHora_inicio(rs.getTime("hora_inicio"));
				re.setHora_fin(rs.getTime("hora_fin"));
				re.setFecha(rs.getDate("fecha"));
				re.setDetalle(rs.getString("detalle"));
		 }
		 
	} catch (SQLException e) {
		ApplicationException ade=new ApplicationException(e, "Error al recuperar reserva.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
		throw ade;
	} catch (ApplicationException ade){
		throw ade;
	}
	finally {
		try{
			if(rs!=null)rs.close();
			if (stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (ApplicationException ade){
			throw ade;
		}
	} return re;
	
}
public int  getcantReservasdeTipo(int id_per, int id_te, int id_el) throws ApplicationException{
	PreparedStatement stmt= null;
	ResultSet rs=null;
	int i=0;
	try{ 
	stmt= FactoryConexion.getInstancia().getConn().prepareStatement( "select * from reserva r where (r.id_per=? and r.id_te=? and r.id_el=? and r.fecha>=curdate())");
	stmt.setInt(1,id_per);
	stmt.setInt(2,id_te);	
	stmt.setInt(3,id_el);
		
		 rs=stmt.executeQuery();
		 if (rs!= null ){
				while(rs.next()){
					i++;
				}
		 }
	} catch (SQLException e) {
		ApplicationException ade=new ApplicationException(e, "Error al calcular cantidad de reservas pendientes de un tipo.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
		throw ade;
	} catch (ApplicationException ade){
		throw ade;
	}
	 return (i);
}	
			
}



