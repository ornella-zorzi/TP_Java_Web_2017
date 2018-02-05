package data;
import entity.*;
import util.ApplicationException;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.Level;
import java.security.KeyStore.ProtectionParameter;


public class DataPersona implements Serializable{
	
	public ArrayList<Persona> getAll() throws ApplicationException{
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Persona> pers = new ArrayList<Persona>();
		try{ 
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("Select  *  from persona  inner join categoria  on persona.id_cat=categoria.id_cat ");
			if (rs!= null ){
				while(rs.next()){
					Persona p = new Persona();
					p.setCategoria(new Categoria());
					p.setId_per(rs.getInt("id_per"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setDni(rs.getString("dni"));
					p.setEmail(rs.getString("email"));
					p.setUsuario(rs.getString("usuario"));
					p.setContraseña(rs.getString("contraseña"));
					p.setHabilitado(rs.getBoolean("habilitado"));
					p.getCategoria().setId_cat(rs.getInt("id_cat"));
					p.getCategoria().setNombre_cat(rs.getString("nombre_cat"));
					pers.add(p);
						
				}
			}
			
		} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error al recuperar listado de Personas.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (ApplicationException ade){
			throw ade;
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return pers;
		
	}

    public Persona getByDni(Persona per) throws Exception{
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
					p.setDni(rs.getString("dni"));
					p.setEmail(rs.getString("email"));
					p.setUsuario(rs.getString("usuario"));
					p.setContraseña(rs.getString("contraseña"));
					p.setHabilitado(rs.getBoolean("habilitado"));
					p.getCategoria().setId_cat(rs.getInt("id_cat"));
					p.getCategoria().setNombre_cat(rs.getString("nombre_cat"));
    		 }
    		 
    	} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error al recuperar Persona.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (ApplicationException ade){
			throw ade;
		}finally {
    		try{
    			if(rs!=null)rs.close();
    			if (stmt!=null)stmt.close();
    			FactoryConexion.getInstancia().releaseConn();
    		}catch (SQLException e ){
    			throw e;
    		}
    	} return p;
    	
    }
    
    public void add (Persona p ) throws Exception{
    	PreparedStatement stmt=null;
    	ResultSet keyResultSet=null;
    	try{ stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
				"insert into persona(dni, nombre, apellido, email, usuario, contraseña,  habilitado, id_cat) " +
				 "values (?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
    		  stmt.setString(1,p.getDni());
    		  stmt.setString(2,p.getNombre());
    		  stmt.setString(3,p.getApellido());
    		  stmt.setString(4,p.getEmail());
    		  stmt.setString(5,p.getUsuario());
    		  stmt.setString(6,p.getContraseña());
    		  stmt.setBoolean(7,p.isHabilitado());
    		  stmt.setInt(8,p.getCategoria().getId_cat());
    		  stmt.executeUpdate();
    		  keyResultSet=stmt.getGeneratedKeys();
    		  if (keyResultSet!=null && keyResultSet.next()){
    			  p.setId_per(keyResultSet.getInt(1));
    		  }
    		
    	} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error al agregar persona.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (ApplicationException ade){
			throw ade;
		}
    	try { 
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
					"SELECT id_per, dni, nombre, apellido, email, usuario, contraseña,  habilitado, id_cat " +
					"FROM persona  inner join categoria  on persona.id_cat=categoria.id_cat where dni=?");			
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			throw e;
		}

		return rs;
		
	}



public void update(Persona p) throws ApplicationException{
	PreparedStatement stmt=null;	
	try {
		stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
				"UPDATE persona SET dni=?,nombre=?,apellido=?,email=?,usuario=?,contraseña=?,habilitado=?,id_cat=? WHERE id_per=?");	
		
		 stmt.setString(1,p.getDni());
		  stmt.setString(2,p.getNombre());
		  stmt.setString(3,p.getApellido());
		  stmt.setString(4,p.getEmail());
		  stmt.setString(5,p.getUsuario());
		  stmt.setString(6,p.getContraseña());
		  stmt.setBoolean(7,p.isHabilitado());
		  stmt.setInt(8,p.getCategoria().getId_cat());
		  stmt.setInt(9, p.getId_per());
		  
		  stmt.execute();
		
	}  catch (SQLException e) {
		ApplicationException ade=new ApplicationException(e, "Error al modificar persona.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
		throw ade;
	} catch (ApplicationException ade){
		throw ade;
	}
	

}

public void delete(Persona p) throws ApplicationException{
	PreparedStatement stmt=null;		
	try {
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
				"delete from persona where id_per=?");
		stmt.setInt(1,p.getId_per());
		stmt.execute();
	}  catch (SQLException e) {
		ApplicationException ade=new ApplicationException(e, "Error al eliminar persona.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
		throw ade;
	} catch (ApplicationException ade){
		throw ade;
	}
	
	
}
public Persona getValidacionUsario(Persona per) throws Exception{ 
	Persona u = null ;
	PreparedStatement stmt= null;
	ResultSet rs=null;
	try {
		 stmt= FactoryConexion.getInstancia().getConn().prepareStatement( "select p.id_per, p.dni, p.nombre, p.apellido, p.email, p.usuario, p.contraseña, p.habilitado, p.id_cat, c.nombre_cat from persona p  inner join categoria c  on p.id_cat=c.id_cat where  p.usuario= ? and p.contraseña=? ");
		 stmt.setString(1,per.getUsuario());
		 stmt.setString(2,per.getContraseña());
		 rs=stmt.executeQuery();
		 if(rs!=null && rs.next()){
			 u= new Persona();
			 u.setCategoria(new Categoria());
			 u.setId_per(rs.getInt("id_per"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
				u.setDni(rs.getString("dni"));
				u.setEmail(rs.getString("email"));
				//u.setUsuario(rs.getString("usuario"));
				//u.setContraseña(rs.getString("contraseña"));
				u.setHabilitado(rs.getBoolean("habilitado"));
				u.getCategoria().setId_cat(rs.getInt("id_cat"));
				u.getCategoria().setNombre_cat(rs.getString("nombre_cat"));
		 } 
	} catch (SQLException e) {
		ApplicationException ade=new ApplicationException(e, "Error al validar persona.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
		throw ade;
	} catch (ApplicationException ade){
		throw ade;
	}
	 finally {
	    		try{
	    			if(rs!=null)rs.close();
	    			if (stmt!=null)stmt.close();
	    			FactoryConexion.getInstancia().releaseConn();
	    		}catch (SQLException e ){
	    			throw e;
	    		}
	    	} return u;
	} 

}