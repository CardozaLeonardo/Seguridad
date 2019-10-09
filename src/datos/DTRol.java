package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Rol;

public class DTRol 
{
	PoolConexion pc = PoolConexion.getInstance();
	Connection cn = PoolConexion.getConnection();
	ResultSet rs = null;
	
	public ArrayList<Rol> listarRol()
	{
		ArrayList<Rol> roles = new ArrayList<Rol>();
		String sql = "SELECT * from public.rol where estado <> 3";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Rol r = new Rol();
				r.setIdRol(rs.getInt("idRol"));
				r.setDescripcion(rs.getString("descripcion"));
				roles.add(r);
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: ERROR AL OBTENER ROLES");
			e.printStackTrace();
		}
		return roles;
	}
	
	public boolean guardarRol(Rol r)
	{
		boolean guardado = false;
		
		try 
		{
			this.listarRol();
			rs.moveToInsertRow();
			rs.updateInt("idRol", r.getIdRol());
			rs.updateString("descripcion", r.getDescripcion());
			rs.insertRow();
			rs.moveToCurrentRow();
			guardado = true;
		} 
		catch (Exception e) 
		{
			System.err.println("DATOS: ERROR -> Error al guardar Rol " + e.getMessage());
			e.printStackTrace();
		}
		
		return guardado;
	}
	
	public boolean eliminarRol(Rol r)
	{
		/*
		 * Estados
		 * 1 - Agregado
		 * 2 - Modificado
		 * 3 - Eliminado
		 *  */
		
		boolean eliminado = false;
		PreparedStatement ps;
		String sql = "Update public.rol set estado=3 where \"idRol\" = ?";
		
		try 
		{
			ps = cn.prepareStatement(sql);
			ps.setInt(1, r.getIdRol());
			ps.executeUpdate();
			eliminado = true;
		} 
		catch (Exception e) 
		{
			System.err.println("DATOS: ERROR -> Error al eliminar Rol " + e.getMessage());
			e.printStackTrace();
		}
		
		return eliminado;
	}
	
	public boolean modificarRol(Rol r)
	{
		boolean modificado = false;
		PreparedStatement ps;
		String sql = "Update public.rol set descripcion= ?, estado = 2 where \"idRol\" = ?";
				
		try 
		{
			ps = cn.prepareStatement(sql);
			ps.setString(1, r.getDescripcion());
			ps.setInt(2, r.getIdRol());
			ps.executeUpdate();
			modificado = true;
		} 
		catch (Exception e) 
		{
			System.err.println("DATOS: ERROR-> Error al modificar Rol " +e.getMessage());
			e.printStackTrace();
		}
		return modificado;
	}
}















