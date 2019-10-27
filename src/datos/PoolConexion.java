package datos;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import org.apache.commons.dbcp.BasicDataSource;

public class PoolConexion 
{
	private static PoolConexion INSTANCE = null;
	private static Connection con = null;
	private static DataSource dataSource;
	//DATOS DE CONEXION
	private static String db = "HR";
	private static String url = "jdbc:postgresql://localhost:5432/"+db;
	private static String user = "postgres";
	private static String pass = "Usuario123#.";
	
	private PoolConexion()
	{
		inicializaDataSource();
	}
	
	private synchronized static void createInstance()
	{
		if(INSTANCE==null)
		{
			INSTANCE = new PoolConexion();
		}
	}
	
	public static PoolConexion getInstance()
	{
		if(INSTANCE == null)
		{
			createInstance();
		}
		return INSTANCE;
	}
	
	public final void inicializaDataSource()
    {

	 	BasicDataSource basicDataSource = new org.apache.commons.dbcp.BasicDataSource();
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(pass);
        basicDataSource.setUrl(url);
        basicDataSource.setMaxActive(50);
        dataSource = basicDataSource;
    }
	
	public static boolean EstaConectado()
    {
    	boolean resp = false;
    	try
    	{
    		con = PoolConexion.dataSource.getConnection();
    		if ((con==null) || (con.isClosed()))
    			resp = false;
    		else
    			resp = true;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println(e.getMessage());
    	}
    	
    	return resp;
    }
	
	 public static Connection getConnection() 
	    {	
		   if (EstaConectado()==false) 
			   {
			   		try 
			   		{
						con = PoolConexion.dataSource.getConnection();
					} 
			   		catch (SQLException e) 
			   		{
						// TODO Auto-generated catch block
			   			e.printStackTrace();
			   			System.out.println(e.getMessage());
					}
			   }
		   return con;
	    }
	 
	 public static void main(String[] args) throws SQLException 
		{
			PoolConexion.getInstance();
			Connection con = null;
	        
	        try 
	        {
		    	con = PoolConexion.getConnection();
		    	if(con!=null)
		    	{
		    		JOptionPane.showMessageDialog(null, "Conectado a " + db +" !");
		    		System.out.println("Conectado a "+db+" !!!");
		    	}
		    	else
		    	{
		    		JOptionPane.showMessageDialog(null, "Error al Conectar a "+db+" !!!");
		    		System.out.println("Error al Conectar a "+db+" !!!");
		    	}
	        }
	        finally
	        {
	            try 
	            {
	               con.close();
	               System.out.println("Se desconect� de "+db+"!!!");
	            } 
	            catch (SQLException ex) 
	            {
	            	ex.printStackTrace();
	                System.out.println(ex.getMessage());
	            }
	        }

		}
	    
}
