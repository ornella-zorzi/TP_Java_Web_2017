package data;
import entity.*;
import util.ApplicationException;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class DataReserva implements Serializable  {
	public ArrayList<Reserva> getAll() throws ApplicationException{
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Reserva> res = new ArrayList<Reserva>();
		try{ 
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("Select te.id_te, te.nombre_te, e.id_el,e.nombre_el, p.id_per,p.nombre, p.apellido, fecha, hora, detalle, estado from reserva r " +
			"inner join persona p on r.id_per=p.id_per inner join elemento e on e.id_el=r.id_el inner join tipo_elemento te on te.id_te=r.id_te");
			
			if (rs!= null ){
				while(rs.next()){
					Reserva r=new Reserva();
					System.out.println("hola");
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
					r.setHora(rs.getTime("hora"));
					r.setFecha(rs.getDate("fecha"));
					r.setDetalle(rs.getString("detalle"));
					r.setEstado(rs.getString("estado"));
					System.out.println(r.getDetalle());
					res.add(r);
						
				}
			}
			
		} catch (SQLException e ){
			//throw e;
		} catch (ApplicationException ade){
			throw ade;
		} try {
			if(rs!=null) rs.close();
			if (stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e){
				e.printStackTrace();
			}
			return res;
		
	} 

  /* public Persona getByDni(Persona per) throws Exception{
    	Persona p = null ;
    	PreparedStatement stmt= null;
    	ResultSet rs=null;
    	try {
    		 stmt= FactoryConexion.getInstancia().getConn().prepareStatement( "select p.id_per, p.dni, p.nombre, p.apellido, p.email, p.usuario, p.contraseña, p.habilitado, p.id_cat, c.nombre_cat from persona p  inner join categoria c  on p.id_cat=c.id_cat where p.dni=? ");
    		 stmt.setString(1, per.getDni());
    		 rs=stmt.executeQuery();
    		 if(rs!=null && rs.next()){
    			 p=new Persona();
    			 p.setCategoria(new Categoria());
    			 p.setId_per(rs.getInt("id_per"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setDni(rs.getNString("dni"));
					p.setEmail(rs.getString("email"));
					p.setUsuario(rs.getString("usuario"));
					p.setContraseña(rs.getString("contraseña"));
					p.setHabilitado(rs.getBoolean("habilitado"));
					p.getCategoria().setId_cat(rs.getInt("id_cat"));
					p.getCategoria().setNombre_cat(rs.getString("nombre_cat"));
    		 }
    		 
    	} catch (Exception e ){
    		throw e;
    	} finally {
    		try{
    			if(rs!=null)rs.close();
    			if (stmt!=null)stmt.close();
    			FactoryConexion.getInstancia().releaseConn();
    		}catch (SQLException e ){
    			throw e;
    		}
    	} return p;
    	
    }
   */ 
   public void add (Reserva r) throws Exception{
    	PreparedStatement stmt=null;
    	ResultSet keyResultSet=null;
    	try{ stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
				"insert into reserva(id_el, id_te, fecha, hora, detalle, estado, id_per) " +
				 "values (?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
    		  stmt.setInt(1,r.getElemento().getId_El());
    		  stmt.setInt(2,r.getElemento().getTipoElemento().getId_TE());
    		  stmt.setDate(3,r.getFecha()); 
    		  stmt.setTime(4,r.getHora());
    		  stmt.setString(5,r.getDetalle());
    		  stmt.setString(6, r.getEstado());
    		  stmt.setInt(7,r.getPersona().getId_per());
    		  
    		  stmt.executeUpdate();
    		  keyResultSet=stmt.getGeneratedKeys();
    		  if (keyResultSet!=null && keyResultSet.next()){
    			  r.setId_res(keyResultSet.getInt(1));
    		  }
    		
    	} catch (SQLException | ApplicationException e){
    		throw e;
    	}try { 
    			if (keyResultSet!=null)keyResultSet.close();
    			if (stmt!=null)stmt.close();
    			FactoryConexion.getInstancia().releaseConn();
    	} catch (SQLException e ){
    		e.printStackTrace();
    	}
    } 
  /*public void guardarSeparado(java.sql.Date fecha, java.sql.Time hora){
	   			PreparedStatement stmt=null;
		    	ResultSet keyResultSet=null;
		    	try{ stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
						"insert into reserva(fecha,hora) values (?,?)"
						); // el campo fecha es de tipo date y hora de timpo time
				stmt.setDate(1, fecha); //parámetro de entrada del método
				stmt.setTime(2, hora);  //parámetro de entrada del método
				stmt.executeUpdate();
				stmt.close();
			} catch (SQLException | ApplicationException e) {
				e.printStackTrace();
			}
		}
*/


public ResultSet getResultSet() throws ApplicationException{	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT id_res, id_el, id_te, fecha, hora, detalle, estado, id_per " +
					"FROM reserva  inner join elemento  on reserva.id_el=elemento.id_el where id_te=?");			
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			throw e;
		}

		return rs;
		
	}


public void update(Reserva r){
	//ResultSet rs=null;
	PreparedStatement stmt=null;	
	try {
		stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
				"UPDATE reserva SET id_el=?, id_te=?, fecha=?, hora=?, detalle=?, estado=? WHERE id_res=?");	
		
		 stmt.setInt(1,r.getElemento().getId_El());
		  stmt.setInt(2,r.getElemento().getTipoElemento().getId_TE());
		 // stmt.setString(3,r.getPersona().getDni());
		  stmt.setDate(3, (Date) r.getFecha());
		  stmt.setTime(4,(Time) r.getHora());
		  stmt.setString(5,r.getDetalle());
		  stmt.setString(6,r.getEstado());
		  stmt.setInt(7, r.getId_res());
		  
		  stmt.execute();
		
	} catch (SQLException e) {			
		e.printStackTrace();
	} catch (ApplicationException e) {			
		e.printStackTrace();
	}

}

public void delete(Reserva r){
	PreparedStatement stmt=null;		
	try {
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
				"delete from reserva where id_res=?");
		stmt.setInt(1,r.getId_res());
		stmt.execute();
	} catch (SQLException e) {			
		e.printStackTrace();
	} catch (ApplicationException e) {			
		e.printStackTrace();
	}
	
	
}
/*
public void guardarSeparado(java.sql.Date fecha, java.sql.Time hora){
	try {
		
		PreparedStatement stmt=null;
    	ResultSet keyResultSet=null;
		
		stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
				"insert into reserva(fecha,hora) values (?,?)"
				); // el campo fecha es de tipo date y hora de timpo time
		stmt.setDate(1, fecha); //parámetro de entrada del método
		stmt.setTime(2, hora);  //parámetro de entrada del método
		stmt.executeUpdate();
		
	} catch (SQLException | ApplicationException e){
		//throw e;
	}try {
			if (keyResultSet!=null)keyResultSet.close();
			if (stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
	} catch (SQLException e ){
		e.printStackTrace();
	}
}*/

}


