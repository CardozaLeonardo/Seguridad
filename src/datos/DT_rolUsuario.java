package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import entidades.RolUsuario;
import entidades.VW_user_rol;


public class DT_rolUsuario {
	
	private PoolConexion pc = PoolConexion.getInstance();
	private Connection cn = PoolConexion.getConnection();
	private ResultSet rs = null;
	
	public ArrayList<RolUsuario> listarRolUsuario() {
        
		ArrayList<RolUsuario> rolesUser = new ArrayList<RolUsuario>();
		
		String sql = "SELECT * FROM tbl_user_rol";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				RolUsuario ru = new RolUsuario();
				ru.setId_User_Rol(rs.getInt("id_user_rol"));
				ru.setId_user(rs.getInt("id_user"));
				ru.setId_rol(rs.getInt("id_rol"));
				
			
				rolesUser.add(ru);
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("Error en listarRolUsuario(): " + e.getMessage());
			e.printStackTrace();
		}
		return rolesUser;
	}
	
	public boolean guardarRolUsuario(RolUsuario ru) {
		
		boolean guardado = false;
		
		try 
		{
			this.listarRolUsuario();
			rs.moveToInsertRow();
			rs.updateInt("id_user", ru.getId_user());
			rs.updateInt("id_rol", ru.getId_rol());
			//rs.updateTimestamp("fechaCreacion", sqlnow);
			//rs.updateInt("estado", 1);
			rs.insertRow();
			rs.moveToCurrentRow();
			guardado = true;
		} 
		catch (Exception e) 
		{
			System.err.println("Error en guardarRolUsuario(): " + e.getMessage());
			e.printStackTrace();
		}
		
		return guardado;
	}
	
   public ArrayList<VW_user_rol> listarRolUsuario(int idUser) {
        
		ArrayList<VW_user_rol> rolesUser = new ArrayList<VW_user_rol>();
		
		String sql = "SELECT * FROM public.vw_user_rol WHERE id_user = ?";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				VW_user_rol ru = new VW_user_rol();
				
				ru.setId_user_rol(rs.getInt("id_user_rol"));
				ru.setId_rol(rs.getInt("id_rol"));
				ru.setId_user(rs.getInt("id_user"));
				ru.setRol_name(rs.getString("rol_name"));
				ru.setEstado(rs.getInt("estado"));
				
			
				rolesUser.add(ru);
			}
			
			return rolesUser;
		} 
		catch (SQLException e) 
		{
			System.err.println("Error en listarRolUsuario(int): " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
   
   
   public ArrayList<VW_user_rol> listarRolUsuario(String username) {
       
		ArrayList<VW_user_rol> rolesUser = new ArrayList<VW_user_rol>();
		
		String sql = "SELECT * FROM public.vw_user_rol WHERE username = ?";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				VW_user_rol ru = new VW_user_rol();
				
				ru.setId_user_rol(rs.getInt("id_user_rol"));
				ru.setId_rol(rs.getInt("id_rol"));
				ru.setUsername(rs.getString("username"));
				ru.setId_user(rs.getInt("id_user"));
				ru.setRol_name(rs.getString("rol_name"));
				ru.setEstado(rs.getInt("estado"));
				
			
				rolesUser.add(ru);
			}
			
			return rolesUser;
		} 
		catch (SQLException e) 
		{
			System.err.println("Error en listarRolUsuario(int): " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
   
   public boolean eliminarUserRol(int idUserRol) {
	   boolean eliminado = false;
		PreparedStatement ps;
		String sql = "DELETE FROM public.tbl_user_rol WHERE id_user_rol = ?";
		
		try 
		{
			ps = cn.prepareStatement(sql);
			ps.setInt(1, idUserRol);
			ps.executeUpdate();
			eliminado = true;
		} 
		catch (Exception e) 
		{
			System.err.println("Error en eliminarUserRol() " + e.getMessage());
			e.printStackTrace();
		}
		
		return eliminado;
   }
	
}
