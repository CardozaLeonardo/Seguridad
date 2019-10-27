package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Tbl_opcion;

public class DT_opcion 
{
	PoolConexion pc = PoolConexion.getInstance();
	Connection cn = PoolConexion.getConnection();
	ResultSet rs = null;
	
	public ArrayList<Tbl_opcion> listarOpciones()
	{
		ArrayList<Tbl_opcion> roles = new ArrayList<Tbl_opcion>();
		String sql = "SELECT * FROM tbl_opcion where estado <> 3";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Tbl_opcion opc = new Tbl_opcion();
				opc.setId_opcion(rs.getInt("id_opcion"));
				opc.setOpcion(rs.getString("opcion"));
				opc.setOpcion_desc(rs.getString("opcion_desc"));
				opc.setEstado(rs.getInt("estado"));
			
				roles.add(opc);
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: ERROR AL OBTENER OPCIONES");
			e.printStackTrace();
		}
		return roles;
	}
	
	public boolean guardarOpcion(Tbl_opcion opc)
	{
		boolean guardado = false;
		
		try 
		{
			this.listarOpciones();
			rs.moveToInsertRow();
			
			rs.updateString("opcion", opc.getOpcion());
			rs.updateString("opcion_desc", opc.getOpcion_desc());
			rs.updateInt("estado", 1);
			rs.insertRow();
			rs.moveToCurrentRow();
			guardado = true;
		} 
		catch (Exception e) 
		{
			System.err.println("DATOS: ERROR -> Error al guardar Opcion " + e.getMessage());
			e.printStackTrace();
		}
		
		return guardado;
	}
	
	public boolean eliminarOpcion(int idOpcion)
	{
		
		boolean eliminado = false;
		PreparedStatement ps;
		String sql = "UPDATE tbl_opcion SET estado = 3 WHERE id_rol = ?";
		
		try 
		{
			ps = cn.prepareStatement(sql);
			ps.setInt(1, idOpcion);
			ps.executeUpdate();
			eliminado = true;
		} 
		catch (Exception e) 
		{
			System.err.println("DATOS: ERROR -> Error al eliminar Opcion " + e.getMessage());
			e.printStackTrace();
		}
		
		return eliminado;
	}
	
	
	
	public boolean modificarOpcion(Tbl_opcion opc) {
        boolean modificado = false;
		
		try
		{
			this.listarOpciones();
			rs.beforeFirst();
			
			while(rs.next())
			{
				if(rs.getInt(1)==opc.getId_opcion())
				{
					
					rs.updateString("opcion", opc.getOpcion());
					rs.updateString("opcion_desc", opc.getOpcion_desc());
					rs.updateInt("estado",2);
					rs.updateRow();
					modificado = true;
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.err.println("ERROR modificarOpcion():" + e.getMessage());
			e.printStackTrace();
		}
		
		return modificado;
	}
}


