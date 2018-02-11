package data;
import entity.*;
import util.ApplicationException;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

public class DataTipoElemento implements Serializable  {

	public ArrayList<TipoElemento> getAll() throws ApplicationException{
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<TipoElemento> tipoEl = new ArrayList<TipoElemento>();
		try{ 
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("Select *  from tipo_elemento");
			if (rs!= null ){
				while(rs.next()){
					TipoElemento te = new TipoElemento();		
					te.setId_TE(rs.getInt("id_te"));
					te.setNombre_TE(rs.getString("nombre_te"));  
					te.setCant_reserva_max(rs.getInt("cant_reserva_max"));
				    te.setTiempo_limite(rs.getInt("tiempo_limite"));
				    te.setDias_anticipacion(rs.getInt("dias_anticipacion"));
				    te.setEncargado(rs.getBoolean("encargado"));
				    
					tipoEl.add(te);
						
				} 
				
			}
			
		} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error al recuperar listado de tipo de elementos.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
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
			return tipoEl;
	} 
	public ArrayList<TipoElemento> getPublico() throws ApplicationException{
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<TipoElemento> tipoEl = new ArrayList<TipoElemento>();
		try{ 
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("Select *  from tipo_elemento where encargado is false ");
			if (rs!= null ){
				while(rs.next()){
					TipoElemento te = new TipoElemento();		
					te.setId_TE(rs.getInt("id_te"));
					te.setNombre_TE(rs.getString("nombre_te"));  
					te.setCant_reserva_max(rs.getInt("cant_reserva_max"));
				    te.setTiempo_limite(rs.getInt("tiempo_limite"));
				    te.setDias_anticipacion(rs.getInt("dias_anticipacion"));
				    te.setEncargado(rs.getBoolean("encargado"));
				    
					tipoEl.add(te);
						
				} 
				
			}
			
		}catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error al recuperar listado para usuarios.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (ApplicationException ade){
			throw ade;
		}try {
			if(rs!=null) rs.close();
			if (stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (ApplicationException ade){
			throw ade;
		}
			return tipoEl;
	} 
	 public TipoElemento getByNombre(TipoElemento tel) throws Exception{
		 	TipoElemento te = null;
	    	PreparedStatement stmt= null;
	    	ResultSet rs=null;
	    	try {
	    		 stmt= FactoryConexion.getInstancia().getConn().prepareStatement( "select * from tipo_elemento  where nombre_te=?");
	    		stmt.setString(1, tel.getNombre_TE());
	    		 rs=stmt.executeQuery();
	    		 if(rs!=null && rs.next()){
	    			 te=new TipoElemento();
	    			 te.setId_TE(rs.getInt("id_te"));
	    			 te.setNombre_TE(rs.getString("nombre_te"));
	    			 te.setCant_reserva_max(rs.getInt("cant_reserva_max"));
	    			 te.setTiempo_limite(rs.getInt("tiempo_limite"));
	    			 te.setDias_anticipacion(rs.getInt("dias_anticipacion"));
	    			 te.setEncargado(rs.getBoolean("encargado"));
	    
	    		 }
	    		 
	    	} catch (SQLException e) {
	    		ApplicationException ade=new ApplicationException(e, "Error al buscar tipo de elemento.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
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
	    	} return te;
	 }
	 public void add (TipoElemento tipoel ) throws Exception{
	    	PreparedStatement stmt=null;
	    	ResultSet keyResultSet=null;
	    	try{ stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into tipo_elemento(nombre_te,cant_reserva_max, tiempo_limite,dias_anticipacion,encargado) " +
					 "values (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
	    		  stmt.setString(1,tipoel.getNombre_TE());
	    		  stmt.setInt(2,tipoel.getCant_reserva_max());
	    		  stmt.setInt(3,tipoel.getTiempo_limite());
	    		  stmt.setInt(4,tipoel.getDias_anticipacion());
	    		  stmt.setBoolean(5,tipoel.isEncargado());
	    		  
	    		  stmt.executeUpdate();
	    		  keyResultSet=stmt.getGeneratedKeys();
	    		  if (keyResultSet!=null && keyResultSet.next()){
	    			  tipoel.setId_TE(keyResultSet.getInt(1));
	    		  }	    		
	    	} catch (SQLException e) {
	    		ApplicationException ade=new ApplicationException(e, "Error al agregar agregar tipo de elemento.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
	    		throw ade;
	    	} catch (ApplicationException ade){
	    		throw ade;
	    	}
	    	try { 
	    			if (keyResultSet!=null)keyResultSet.close();
	    			if (stmt!=null)stmt.close();
	    			FactoryConexion.getInstancia().releaseConn();
	    	} catch (SQLException e) {
				ApplicationException ade=new ApplicationException(e, "Error.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
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
						"SELECT id_te,nombre_te,cant_reserva_max,tiempo_limite,dias_anticipacion,encargado" +
						"FROM  tipo_elemento  where nombre_te=?");		
				rs = stmt.executeQuery();
				
			} catch (SQLException e) {
				ApplicationException ade=new ApplicationException(e, "Error.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
				throw ade;
			} catch (ApplicationException ade){
				throw ade;
			}
			return rs;	
		}

	 public void update(TipoElemento tipoel) throws ApplicationException{
			ResultSet rs=null;
			PreparedStatement stmt=null;	
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(	
						"UPDATE tipo_elemento SET nombre_te=?, cant_reserva_max=?, tiempo_limite=?,dias_anticipacion=?, encargado=? WHERE id_te=?");		
				
				stmt.setString(1,tipoel.getNombre_TE());
				stmt.setInt(2,tipoel.getCant_reserva_max());
				stmt.setInt(3,tipoel.getTiempo_limite());
				stmt.setInt(4,tipoel.getDias_anticipacion());
				stmt.setBoolean(5,tipoel.isEncargado());
				stmt.setInt(6,tipoel.getId_TE());
				stmt.execute();
			} catch (SQLException e) {
				ApplicationException ade=new ApplicationException(e, "Error al modificar tipo de elemento.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
				throw ade;
			} catch (ApplicationException ade){
				throw ade;
			}
			
		} 

	 public void delete(TipoElemento tipoel) throws ApplicationException{
			PreparedStatement stmt=null;		
			try {
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						"delete from tipo_elemento where id_te=?");
				stmt.setInt(1,tipoel.getId_TE());
				stmt.execute();
			} catch (SQLException e) {
				ApplicationException ade=new ApplicationException(e, "Error al eliminar tipo de elemento.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
				throw ade;
			} catch (ApplicationException ade){
				throw ade;
			} 	
			
}
	 public TipoElemento getById(int id_te) throws Exception{
		 	TipoElemento te = null ; 
		    PreparedStatement stmt= null;
		    ResultSet rs=null;
		    try {
		    		 stmt= FactoryConexion.getInstancia().getConn().prepareStatement( "select id_te,nombre_te,cant_reserva_max, tiempo_limite,dias_anticipacion, encargado from tipo_elemento where id_te=? ");
		    		 stmt.setInt(1,id_te);
		    		 rs=stmt.executeQuery();
		    		 if(rs!=null && rs.next()){
		    			 te=new TipoElemento();
		    			 te.setId_TE(rs.getInt("id_te"));
		    			 te.setNombre_TE(rs.getString("nombre_te"));
		    			 te.setCant_reserva_max(rs.getInt("cant_reserva_max"));
		    			 te.setTiempo_limite(rs.getInt("tiempo_limite"));
		    		     te.setDias_anticipacion(rs.getInt("dias_anticipacion"));
		    		     te.setEncargado(rs.getBoolean("encargado"));
		    			 }
		    		 
		    	} catch (SQLException e) {
		    		ApplicationException ade=new ApplicationException(e, "Error al recuperar tipo de elemento.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
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
		    	} return te;
		    	
		    }
	 
	 public TipoElemento getByIdTE(int id_te) throws Exception{
			TipoElemento te = null ;
		    PreparedStatement stmt= null;
		    ResultSet rs=null;
		    try {
		    		 stmt= FactoryConexion.getInstancia().getConn().prepareStatement( "select id_te, nombre_te from tipo_elemento where id_te=? ");
		    		 stmt.setInt(1,id_te);
		    		 rs=stmt.executeQuery();
		    		 if(rs!=null && rs.next()){
		    			 te=new TipoElemento();
		    			 te.setId_TE(rs.getInt("id_te"));
		    			 te.setNombre_TE(rs.getString("nombre_te"));
}
		    		 
		    	} catch (SQLException e) {
		    		ApplicationException ade=new ApplicationException(e, "Error al recuperar tipo de elemento.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
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
		    			ApplicationException ade=new ApplicationException(e, "Error al recuperar listado de tipo de elementos.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
		    			throw ade;
		    		} catch (ApplicationException ade){
		    			throw ade;
		    		}
		    	} return te;
		    	
		    }
	 
	 public int validabaja(int id_te) throws ApplicationException{PreparedStatement stmt= null;
		ResultSet rs=null;
		int i=0;
		try{ 
		stmt= FactoryConexion.getInstancia().getConn().prepareStatement( "select * from tipo_elemento te where (te.`id_te` in(select id_te from reserva where id_te=?) or te.id_te in( select id_te from elemento where id_te=?))");
		stmt.setInt(1,id_te);	
		stmt.setInt(2,id_te);	
			
		 rs=stmt.executeQuery();
		 if(rs!=null && rs.next()){
					i=1;
			}
		 
		} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error al validar.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (ApplicationException ade){
			throw ade;
		}
		
		 return i;		
	}	
	 
	 
	 
}
