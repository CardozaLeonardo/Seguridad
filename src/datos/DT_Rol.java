package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Rol;

public class DT_Rol 
{
	PoolConexion pc = PoolConexion.getInstance();
	Connection cn = PoolConexion.getConnection();
	ResultSet rs = null;
	
	public ArrayList<Rol> listarRoles()
	{
		ArrayList<Rol> roles = new ArrayList<Rol>();
		String sql = "SELECT * FROM tbl_rol where estado <> 3";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Rol rol = new Rol();
				rol.setId_rol(rs.getInt("id_rol"));
				rol.setRol_name(rs.getString("rol_name"));
				rol.setRol_desc(rs.getString("rol_desc"));
				rol.setEstado(rs.getInt("estado"));
			
				roles.add(rol);
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: ERROR AL OBTENER ROLES");
			e.printStackTrace();
		}
		return roles;
	}
	
	public boolean guardarRol(Rol rol)
	{
		boolean guardado = false;
		
		try 
		{
			this.listarRoles();
			rs.moveToInsertRow();
			//rs.updateInt("idRol", r.getIdRol());
			rs.updateString("rol_name", rol.getRol_name());
			rs.updateString("rol_desc", rol.getRol_desc());
			rs.updateInt("estado", 1);
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
	
	public boolean eliminarRol(Rol rol)
	{
		/*
		 * Estados
		 * 1 - Agregado
		 * 2 - Modificado
		 * 3 - Eliminado
		 *  */
		
		boolean eliminado = false;
		PreparedStatement ps;
		String sql = "UPDATE tbl_rol SET estado = 3 WHERE id_rol = ?";
		
		try 
		{
			ps = cn.prepareStatement(sql);
			ps.setInt(1, rol.getId_rol());
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
	
	/*public boolean modificarRol(Rol r)
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
	}*/
	
	public boolean modificarRol(Rol rol) {
        boolean modificado = false;
		
		try
		{
			this.listarRoles();
			rs.beforeFirst();
			
			while(rs.next())
			{
				if(rs.getInt(1)==rol.getId_rol())
				{
					
					rs.updateString("rol_name", rol.getRol_name());
					rs.updateString("rol_desc", rol.getRol_desc());
					rs.updateInt("estado",2);
					rs.updateRow();
					modificado = true;
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.err.println("ERROR modificarRol():" + e.getMessage());
			e.printStackTrace();
		}
		
		return modificado;
	}
}


