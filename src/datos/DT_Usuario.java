package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;


import entidades.Usuario;



public class DT_Usuario {

	PoolConexion pc = PoolConexion.getInstance();
	Connection cn = PoolConexion.getConnection();
	ResultSet rs = null;
	
	public ArrayList<Usuario> listarUsuarios()
	{
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "SELECT * FROM tbl_user WHERE estado <> 3";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Usuario us = new Usuario();
				us.setId_user(rs.getInt("id_user"));
				us.setUsername(rs.getString("username"));
				us.setNombre1(rs.getString("nombre1"));
				us.setNombre2(rs.getString("nombre2"));
				us.setApellido1(rs.getString("apellido1"));
				us.setApellido2(rs.getString("apellido2"));
				us.setEmail(rs.getString("email"));
				us.setPwd(rs.getString("password"));
				us.setEstado(rs.getInt("estado"));
				
			
				usuarios.add(us);
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("Error en listarUsuarios(): " + e.getMessage());
			e.printStackTrace();
		}
		return usuarios;
	}
	
	/*public ArrayList<Usuario> permisosUsuario(String usuario)
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
	} */
	
	public Usuario obtenerUser(int idUser)
	{
		Usuario us  = new Usuario();
		try
		{
			PreparedStatement ps = cn.prepareStatement("SELECT * from tbl_user where id_user = ? and estado<>3", 
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, 
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			if(rs.next())
			{
				us.setId_user(rs.getInt("id_user"));
				us.setNombre1(rs.getString("nombre1"));
				us.setNombre2(rs.getString("nombre2"));
				us.setApellido1(rs.getString("apellido1"));
				us.setApellido2(rs.getString("apellido2"));
				us.setUsername(rs.getString("username"));
				us.setPwd(rs.getString("password"));
				us.setEmail(rs.getString("email"));
				us.setPwd_tmp(rs.getString("pwd_tmp"));
				us.setEstado(rs.getInt("estado"));
			}
		}
		catch (Exception e)
		{
			System.out.println("DATOS: ERROR en obtenerUser() "+ e.getMessage());
			e.printStackTrace();
		}
		
		return us;
	}
	
	public Usuario obtenerUser(String user)
	{
		Usuario us  = null;
		try
		{
			PreparedStatement ps = cn.prepareStatement("SELECT * from tbl_user where username = ? and estado<>3", 
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, 
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, user);
			rs = ps.executeQuery();
			if(rs.next())
			{
				us  = new Usuario();
				us.setId_user(rs.getInt("id_user"));
				us.setNombre1(rs.getString("nombre1"));
				us.setNombre2(rs.getString("nombre2"));
				us.setApellido1(rs.getString("apellido1"));
				us.setApellido2(rs.getString("apellido2"));
				us.setUsername(rs.getString("username"));
				us.setPwd(rs.getString("password"));
				us.setEmail(rs.getString("email"));
				us.setPwd_tmp(rs.getString("pwd_tmp"));
				us.setEstado(rs.getInt("estado"));
			}
		}
		catch (Exception e)
		{
			System.out.println("DATOS: ERROR en obtenerUser() "+ e.getMessage());
			e.printStackTrace();
		}
		
		return us;
	}
	
	public boolean guardarUsuario(Usuario user)
	{
		boolean guardado = false;
		//LocalDateTime now = LocalDateTime.now();
		//Timestamp sqlnow = Timestamp.valueOf(now);
		try 
		{
			this.listarUsuarios();
			rs.moveToInsertRow();
			rs.updateString("username", user.getUsername());
			rs.updateString("nombre1", user.getNombre1());
			rs.updateString("nombre2", user.getNombre2());
			rs.updateString("apellido1", user.getApellido1());
			rs.updateString("apellido2", user.getApellido2());
			rs.updateString("email", user.getEmail());
			rs.updateString("password", user.getPwd());
			rs.updateInt("estado", 1);
			rs.moveToCurrentRow();
			guardado = true;
		} 
		catch (Exception e) 
		{
			System.err.println("DATOS: ERROR -> Error al guardar Usuario " + e.getMessage());
			e.printStackTrace();
		}
		
		return guardado;
	}
	
	
	public boolean modificarUser(Usuario u)
	{
		boolean modificado=false;	
		try
		{
			this.listarUsuarios();
			rs.beforeFirst();
			while (rs.next())
			{
				if(rs.getInt(1)==u.getId_user())
				{
					rs.updateString("nombre1", u.getNombre1());
					rs.updateString("nombre2", u.getNombre2());
					rs.updateString("apellido1", u.getApellido1());
					rs.updateString("apellido2", u.getApellido2());
					rs.updateString("password", u.getPwd());
					rs.updateString("email", u.getEmail());
					rs.updateInt("estado", 2);
					rs.updateRow();
					modificado=true;
					break;
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("ERROR modificarUser() "+e.getMessage());
			e.printStackTrace();
		}
		return modificado;
		
	}
	
	public boolean eliminarUser(Usuario u)
	{
		boolean eliminado=false;	
		try
		{
			this.listarUsuarios();
			rs.beforeFirst();
			while (rs.next())
			{
				if(rs.getInt(1)==u.getId_user())
				{
					rs.updateInt("estado",3);
					rs.updateRow();
					eliminado=true;
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.err.println("ERROR eliminarUser() "+e.getMessage());
			e.printStackTrace();
		}
		return eliminado;
	}
	
	public boolean LoginUsuario(Usuario u)
	{
		boolean encontrado = false;
		
		PreparedStatement ps;
		String sql = ("SELECT * FROM tbl_user WHERE (username = ? AND password = ?) AND estado <> 3");
		try 
		{
			ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, u.getUsername());
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
	


}
