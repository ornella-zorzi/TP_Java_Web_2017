package data;
import entity.*;
import util.ApplicationException;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

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
				    
					tipoEl.add(te);
						
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
	    			 System.out.println(te.getCant_reserva_max());
	    			 System.out.println(te.getId_TE());
	    			 System.out.println(te.getNombre_TE());
	    			 System.out.println(te.getTiempo_limite());
	    			 System.out.println(te.getDias_anticipacion());

	    		 }
	    		 
	    	} catch (Exception ex ){
	    		throw ex;
	    	} finally {
	    		try{
	    			if(rs!=null)rs.close();
	    			if (stmt!=null)stmt.close();
	    			FactoryConexion.getInstancia().releaseConn();
	    		}catch (SQLException ex ){
	    			throw ex;
	    		}
	    	} return te;
	 }
	 public void add (TipoElemento tipoel ) throws Exception{
	    	PreparedStatement stmt=null;
	    	ResultSet keyResultSet=null;
	    	try{ stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into tipo_elemento(nombre_te,cant_reserva_max, tiempo_limite,dias_anticipacion) " +
					 "values (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
	    		  stmt.setString(1,tipoel.getNombre_TE());
	    		  stmt.setInt(2,tipoel.getCant_reserva_max());
	    		  stmt.setInt(3,tipoel.getTiempo_limite());
	    		  stmt.setInt(4,tipoel.getDias_anticipacion());
	    		  
	    		  stmt.executeUpdate();
	    		  keyResultSet=stmt.getGeneratedKeys();
	    		  if (keyResultSet!=null && keyResultSet.next()){
	    			  tipoel.setId_TE(keyResultSet.getInt(1));
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

	 
	 public ResultSet getResultSet() throws ApplicationException{	
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try
			{
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						"SELECT id_te,nombre_te,cant_reserva_max,tiempo_limite,dias_anticipacion" +
						"FROM  tipo_elemento  where nombre_te=?");		
				rs = stmt.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ApplicationException e) {
				throw e;
			}
			return rs;	
		}

	 public void update(TipoElemento tipoel){
			ResultSet rs=null;
			PreparedStatement stmt=null;	
			try {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement(	
						"UPDATE tipo_elemento SET nombre_te=?, cant_reserva_max=?, tiempo_limite=?,dias_anticipacion=?  WHERE id_te=?");		
				
				stmt.setString(1,tipoel.getNombre_TE());
				stmt.setInt(2,tipoel.getCant_reserva_max());
				stmt.setInt(3,tipoel.getTiempo_limite());
				stmt.setInt(4,tipoel.getDias_anticipacion());
				stmt.setInt(5,tipoel.getId_TE());
				stmt.execute();
			} catch (SQLException e) {			
				e.printStackTrace();
			} catch (ApplicationException e) {			
				e.printStackTrace();
			}
			
		} 

	 public void delete(TipoElemento tipoel){
			PreparedStatement stmt=null;		
			try {
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
						"delete from tipo_elemento where id_te=?");
				stmt.setInt(1,tipoel.getId_TE());
				stmt.execute();
			} catch (SQLException e) {			
				e.printStackTrace();
			} catch (ApplicationException e) {			
				e.printStackTrace();
			} 	
			
}
	 public TipoElemento getById(int id_te) throws Exception{
		 	TipoElemento te = null ; 
		    PreparedStatement stmt= null;
		    ResultSet rs=null;
		    try {
		    		 stmt= FactoryConexion.getInstancia().getConn().prepareStatement( "select id_te,nombre_te,cant_reserva_max, tiempo_limite,dias_anticipacion from tipo_elemento where id_te=? ");
		    		 stmt.setInt(1,id_te);
		    		 rs=stmt.executeQuery();
		    		 if(rs!=null && rs.next()){
		    			 te=new TipoElemento();
		    			 te.setId_TE(rs.getInt("id_te"));
		    			 te.setNombre_TE(rs.getString("nombre_te"));
		    			 te.setCant_reserva_max(rs.getInt("cant_reserva_max"));
		    			 te.setTiempo_limite(rs.getInt("tiempo_limite"));
		    		     te.setDias_anticipacion(rs.getInt("dias_anticipacion"));
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
		    	} return te;
		    	
		    }
}
