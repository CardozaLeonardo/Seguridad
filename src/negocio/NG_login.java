package negocio;

import java.util.ArrayList;

import datos.DT_rolUsuario;
import entidades.VW_user_rol;

public class NG_login {
	
	
	public boolean tieneRol(String username, int rolId) {
		
		DT_rolUsuario dtrol = new DT_rolUsuario();
		ArrayList<VW_user_rol> roles = dtrol.listarRolUsuario(username);
		
		if(roles == null) {
			return false;
		}
		
		for(VW_user_rol vrol: roles) {
			if(vrol.getId_rol() == rolId) {
				return true;
			}
		}
		
		return false;
	}
}
