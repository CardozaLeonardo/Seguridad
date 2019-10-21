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
	
	public Usuario obtenerUser(int idUser) {
		Usuario us = null;
		String sql = "SELECT * FROM tbl_user WHERE id_user = ?";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				us = new Usuario();
				us.setId_user(rs.getInt("id_user"));
				us.setUsername(rs.getString("username"));
				us.setNombre1(rs.getString("nombre1"));
				us.setNombre2(rs.getString("nombre2"));
				us.setApellido1(rs.getString("apellido1"));
				us.setApellido2(rs.getString("apellido2"));
				us.setEmail(rs.getString("email"));
				us.setPwd(rs.getString("password"));
				us.setEstado(rs.getInt("estado"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("Error en listarUsuarios(): " + e.getMessage());
			e.printStackTrace();
		}
		return us;
	}
	
	
	public Usuario obtenerUser(String username) {
		Usuario us = null;
		String sql = "SELECT * FROM tbl_user WHERE username = ?";
		
		try 
		{
			PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				us = new Usuario();
				us.setId_user(rs.getInt("id_user"));
				us.setUsername(rs.getString("username"));
				us.setNombre1(rs.getString("nombre1"));
				us.setNombre2(rs.getString("nombre2"));
				us.setApellido1(rs.getString("apellido1"));
				us.setApellido2(rs.getString("apellido2"));
				us.setEmail(rs.getString("email"));
				us.setPwd(rs.getString("password"));
				us.setEstado(rs.getInt("estado"));
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("Error en listarUsuarios(): " + e.getMessage());
			e.printStackTrace();
		}
		return us;
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
			System.err.println("DATOS: ERROR -> Error al guardar Rol " + e.getMessage());
			e.printStackTrace();
		}
		
		return guardado;
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
