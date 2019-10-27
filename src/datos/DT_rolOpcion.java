package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import entidades.Tbl_rol_opcion;
import entidades.VW_rol_opcion;
import entidades.VW_user_rol;

public class DT_rolOpcion {
	
	private PoolConexion pc = PoolConexion.getInstance();
	private Connection cn = PoolConexion.getConnection();
	private ResultSet rs = null;
	
	public ArrayList<Tbl_rol_opcion> listarRolOpcion() {
        
		ArrayList<Tbl_rol_opcion> rolesUser = new ArrayList<Tbl_rol_opcion>();
		
		String sql = "SELECT * FROM tbl_rol_opcion";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Tbl_rol_opcion rop = new Tbl_rol_opcion();
				rop.setId_rol_opcion(rs.getInt("id_rol_opcion"));
				rop.setId_rol(rs.getInt("id_rol"));
				rop.setId_opc(rs.getInt("id_opc"));
				
			
				rolesUser.add(rop);
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("Error en listarRolUsuario(): " + e.getMessage());
			e.printStackTrace();
		}
		return rolesUser;
	}
	
	public boolean guardarRolOpcion(Tbl_rol_opcion tro) {
		
		boolean guardado = false;
		
		try 
		{
			this.listarRolOpcion();
			rs.moveToInsertRow();
			rs.updateInt("id_rol", tro.getId_rol());
			rs.updateInt("id_opc", tro.getId_opc());
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
	
   public ArrayList<VW_rol_opcion> listarRolOpcion(int idRol) {
        
		ArrayList<VW_rol_opcion> rolesUser = new ArrayList<VW_rol_opcion>();
		
		String sql = "SELECT * FROM public.vw_rol_opcion WHERE id_rol = ?";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idRol);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				VW_rol_opcion vro = new VW_rol_opcion();
				
				vro.setId_rol_opcion(rs.getInt("id_rol_opcion"));
				vro.setId_rol(rs.getInt("id_rol"));
				vro.setRol_name(rs.getString("rol_name"));
				vro.setId_opc(rs.getInt("id_opc"));
				vro.setOpcion(rs.getString("opcion"));
			
				rolesUser.add(vro);
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
   
   public boolean eliminarRolOpcion(int idRolOpcion) {
	   boolean eliminado = false;
		PreparedStatement ps;
		String sql = "DELETE FROM public.tbl_rol_opcion WHERE id_rol_opcion = ?";
		
		try 
		{
			ps = cn.prepareStatement(sql);
			ps.setInt(1, idRolOpcion);
			ps.executeUpdate();
			eliminado = true;
		} 
		catch (Exception e) 
		{
			System.err.println("Error en eliminarRolOpcion() " + e.getMessage());
			e.printStackTrace();
		}
		
		return eliminado;
   }
	
}
