package entidades;

public class VW_user_rol {
	
	private int id_user_rol;
	private int id_user;
	private String username;
	private int id_rol;
	private String rol_name;
	private int estado;
	
	public int getId_user_rol() {
		return id_user_rol;
	}
	public void setId_user_rol(int id_user_rol) {
		this.id_user_rol = id_user_rol;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public String getRol_name() {
		return rol_name;
	}
	public void setRol_name(String rol_name) {
		this.rol_name = rol_name;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
