package entidades;


public class Usuario 
{
	//ATRIBUTOS
	private int id_user;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private String username;
	private String email;
	private String pwd;
	private String pwd_tmp;
	private int estado;
	//private Date fechaCreacion;
	
	//M�TODOS
	
	
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNombre1() {
		return nombre1;
	}
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	public String getNombre2() {
		return nombre2;
	}
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getPwd_tmp() {
		return pwd_tmp;
	}
	public void setPwd_tmp(String pwd_tmp) {
		this.pwd_tmp = pwd_tmp;
	}
	
	

	
}
