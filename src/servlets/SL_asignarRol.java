package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datos.DT_rolUsuario;
import entidades.RolUsuario;

/**
 * Servlet implementation class SL_asignarRol
 */
@WebServlet("/SL_asignarRol")
public class SL_asignarRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SL_asignarRol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String userId = request.getParameter("userId");
		String rolId = request.getParameter("rolId");
		RolUsuario rs = new RolUsuario();
		DT_rolUsuario drs = new DT_rolUsuario();
		
		try
		{
			int user = Integer.parseInt(userId);
			int rol = Integer.parseInt(rolId);
			
			rs.setId_user(user);
			rs.setId_rol(rol);
			
			if(drs.guardarRolUsuario(rs)) {
				
				response.sendRedirect("./asignarRol.jsp?user=" + user);
			}
			
		}catch(Exception e) {
			System.err.println("Error en sevlet SL_asignarRol: ");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.getWriter().append("Su rol es:" + request.getParameter("rolUser"));
	}

}
