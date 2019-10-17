package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entidades.Usuario;

public class DTUsuario {

	PoolConexion pc = PoolConexion.getInstance();
	Connection cn = PoolConexion.getConnection();
	ResultSet rs = null;
	
	public ArrayList<Usuario> listarUsuario()
	{
		ArrayList<Usuario> roles = new ArrayList<Usuario>();
		String sql = "SELECT * from public.usuario ";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Usuario r = new Usuario();
				r.setIdUsuario(rs.getInt("idUsuario"));
				r.setPrimerNombre(rs.getString("primerNombre"));
				r.setSegundoNombre(rs.getString("segundoNombre"));
				r.setPrimerApellido(rs.getString("primerApellido"));
				r.setSegundoApellido(rs.getString("segundoApellido"));
				r.setUsuario(rs.getString("usuario"));
				r.setPwd(rs.getString("pwd"));
				r.setFechaCreacion(rs.getDate("fechaCreacion"));
				
			
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
	public ArrayList<Usuario> permisosUsuario(String usuario)
	{
		ArrayList<Usuario> roles = new ArrayList<Usuario>();
		String sql = "SELECT * from public.usuario where usuario = ?";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Usuario r = new Usuario();
				r.setIdUsuario(rs.getInt("idUsuario"));
				r.setPrimerNombre(rs.getString("primerNombre"));
				r.setSegundoNombre(rs.getString("segundoNombre"));
				r.setPrimerApellido(rs.getString("primerApellido"));
				r.setSegundoApellido(rs.getString("segundoApellido"));
				r.setUsuario(rs.getString("usuario"));
				r.setPwd(rs.getString("pwd"));
				r.setFechaCreacion(rs.getDate("fechaCreacion"));
				
				
				roles.add(r);
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: ERROR AL OBTENER DATOS DEL USUARIO");
			e.printStackTrace();
		}
		return roles;
	}
	
	public boolean guardarUsuario(Usuario u)
	{
		boolean guardado = false;
		LocalDateTime now = LocalDateTime.now();
		Timestamp sqlnow = Timestamp.valueOf(now);
		try 
		{
			this.listarUsuario();
			rs.moveToInsertRow();
			rs.updateString("primerNombre", u.getPrimerNombre());
			rs.updateString("segundoNombre", u.getSegundoNombre());
			rs.updateString("primerApellido", u.getPrimerApellido());
			rs.updateString("segundoApellido", u.getSegundoApellido());
			rs.updateString("usuario", u.getUsuario());
			rs.updateString("pwd", u.getPwd());
			rs.updateTimestamp("fechaCreacion", sqlnow);
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
	
	public boolean LoginUsuario(Usuario u)
	{
		boolean encontrado = false;
		
		PreparedStatement ps;
		String sql = ("SELECT * FROM public.usuario WHERE usuario = ? AND pwd = ?");
		try 
		{
			ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, u.getUsuario());
			ps.setString(2, u.getPwd());
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				encontrado = true;
			}
			else
			{
				encontrado = false;
			}
			
		} 
		catch (Exception e) 
		{
			System.out.println("DATOS: ERROR AL VERIFICAR EL LOGIN "+ e.getMessage());
			e.printStackTrace();
		}
		
		return encontrado;
	}
	
//	public boolean eliminarRol(Rol r)
//	{
//		/*
//		 * Estados
//		 * 1 - Agregado
//		 * 2 - Modificado
//		 * 3 - Eliminado
//		 *  */
//		
//		boolean eliminado = false;
//		PreparedStatement ps;
//		String sql = "Update public.rol set estado=3 where \"idRol\" = ?";
//		
//		try 
//		{
//			ps = cn.prepareStatement(sql);
//			ps.setInt(1, r.getIdRol());
//			ps.executeUpdate();
//			eliminado = true;
//		} 
//		catch (Exception e) 
//		{
//			System.err.println("DATOS: ERROR -> Error al eliminar Rol " + e.getMessage());
//			e.printStackTrace();
//		}
//		
//		return eliminado;
//	}
//	
//	public boolean modificarRol(Rol r)
//	{
//		boolean modificado = false;
//		PreparedStatement ps;
//		String sql = "Update public.rol set descripcion= ?, estado = 2 where \"idRol\" = ?";
//				
//		try 
//		{
//			ps = cn.prepareStatement(sql);
//			ps.setString(1, r.getDescripcion());
//			ps.setInt(2, r.getIdRol());
//			ps.executeUpdate();
//			modificado = true;
//		} 
//		catch (Exception e) 
//		{
//			System.err.println("DATOS: ERROR-> Error al modificar Rol " +e.getMessage());
//			e.printStackTrace();
//		}
//		return modificado;
//	}

}
