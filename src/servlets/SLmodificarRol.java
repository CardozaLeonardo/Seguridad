package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTRol;
import entidades.Rol;

/**
 * Servlet implementation class SLmodificarRol
 */
@WebServlet("/SLmodificarRol")
public class SLmodificarRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLmodificarRol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idRol = "";
		String rol = "";
		
		try 
		{
			DTRol dtr = new DTRol();
			Rol r = new Rol();
			
			idRol = request.getParameter("midrol");
			rol = request.getParameter("mdscrol");
			
			r.setIdRol(Integer.parseInt(idRol));
			r.setDescripcion(rol);
			
			if(dtr.modificarRol(r))
			{
				response.sendRedirect("rol.jsp");
			}
			else
			{
				response.sendRedirect("rol.jsp?error");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL: Error al modificar rol " + e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
	}

}
