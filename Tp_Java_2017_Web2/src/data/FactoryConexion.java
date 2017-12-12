package data;

import java.io.Serializable;
import java.sql.*;

import util.*;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;

public class FactoryConexion implements Serializable{
	
	private String driver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="root";
	private String password="37815875";
	private  String db="java2017";
	//private String dbType="mysql";
	//static String url="jdbc:mysql://localhost:3307/"+db;
   // private String password="root";
   // private String db="tp_java_2017";
	private static FactoryConexion instancia;
	
	private FactoryConexion(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static FactoryConexion getInstancia(){
		if (FactoryConexion.instancia == null){		
			FactoryConexion.instancia=new FactoryConexion();
		}
		return FactoryConexion.instancia;
		
	}
	
	private Connection conn;
	private int cantConn=0;
	public Connection getConn() throws SQLException,ApplicationException{
		try {
			if(conn==null || conn.isClosed()){	
				conn = DriverManager.getConnection(
			        "jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password);
			}
		} catch (SQLException e) {
			throw new ApplicationException(e, "Error al conectar a la base de datos");
		}
		cantConn++;
		return conn;
	}
	
	public void releaseConn() throws SQLException{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			throw e;
		}
	}
	

}
