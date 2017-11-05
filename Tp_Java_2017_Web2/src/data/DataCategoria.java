package data;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Categoria;
import entity.Persona;


public class DataCategoria implements Serializable{
	public ArrayList<Categoria> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Categoria> cats= new ArrayList<Categoria>();
		try{
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("select * from categoria");
			if(rs!=null){
				while(rs.next()){
					Categoria c=new Categoria();
					c.setId_cat(rs.getInt("id_cat"));
					c.setNombre_cat(rs.getString("nombre_cat"));
					cats.add(c);
				}
			}
		} catch (Exception e){
			throw e;
		}
		
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cats;
	}
	public Categoria getById(int id_cat) throws Exception{
		    Categoria c = null ;
		    PreparedStatement stmt= null;
		    ResultSet rs=null;
		    try {
		    		 stmt= FactoryConexion.getInstancia().getConn().prepareStatement( "select id_cat, nombre_cat from categoria where id_cat=? ");
		    		 stmt.setInt(1,id_cat);
		    		 rs=stmt.executeQuery();
		    		 if(rs!=null && rs.next()){
		    			 c=new Categoria();
		    			 c.setId_cat(rs.getInt("id_cat"));
		    			 c.setNombre_cat(rs.getString("nombre_cat"));}
		    		 
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
		    	} return c;
		    	
		    }
	}