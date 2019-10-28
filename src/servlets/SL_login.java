package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DT_Usuario;
import entidades.Usuario;
import negocio.NG_login;
import entidades.VW_user_opciones;
import datos.DT_rolOpcion;

/**
 * Servlet implementation class SL_login
 */
@WebServlet("/SL_login")
public class SL_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SL_login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if(request.getParameter("logout") != null) {
			String logout = request.getParameter("logout");
			
			try {
				int log = Integer.parseInt(logout);
				
				if(log == 1) {
					request.getSession().invalidate();
					response.sendRedirect("login.jsp");
					return;
				}else {
					response.sendRedirect("index.jsp?error=1");
					return;
				}
			}catch(NumberFormatException e) {
				e.printStackTrace();
				response.sendRedirect("index.jsp?error=2");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			Usuario u = new Usuario();
			DT_Usuario dtu = new DT_Usuario();
			NG_login ngl = new NG_login();
			ArrayList<VW_user_opciones> vus = null;
			DT_rolOpcion dro = new DT_rolOpcion();
			
			String usuario, clave = "";
			int idRol = Integer.parseInt(request.getParameter("selectRol"));
			
			usuario = request.getParameter("username");
			clave = request.getParameter("password");
			
			u.setUsername(usuario);
			u.setPwd(clave);
			
			if (dtu.LoginUsuario(u)) 
			{
				System.out.println("EL USUARIO ES CORRECTO");
				
				// Verificar si cuenta con los permisos para acceder con el rol ...
				// seleccionado.
				
				if(!ngl.tieneRol(u.getUsername(), idRol)) {
					response.sendRedirect("./accesoDenegado.jsp?type=1");
					return;
				}
				
				
				HttpSession hts = request.getSession(true);
				hts.setAttribute("login", usuario);
				hts.setAttribute("id_rol", idRol);
				vus = dro.listarOpcionesUsuario(usuario);
				hts.setAttribute("opciones", vus);
				System.out.println(hts);
				response.sendRedirect("index.jsp");
			}
			else
			{
				System.err.println("ERROR AL AUTENTICAR EL USUARIO");
				response.sendRedirect("login.jsp?status=1");
				//request.setAttribute("failLogin", 1);
				//RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				//rd.forward(request, response);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("ERROR EN SL_LOGIN: "+e.getMessage());
			
		}
	}
	

}
