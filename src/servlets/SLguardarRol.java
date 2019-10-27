package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DT_Rol;
import entidades.Rol;

/**
 * Servlet implementation class SLguardarRol
 */
@WebServlet("/SLguardarRol")
public class SLguardarRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLguardarRol() {
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
		try 
		{
			Rol r = new Rol();
			DT_Rol dtr = new DT_Rol();
			
			//int idrol = 0;
			String descripcionRol = "";
			
			//idrol = Integer.parseInt(request.getParameter("idrol"));
			descripcionRol = request.getParameter("dscrol");
			
			//r.setIdRol(idrol);
			r.setRol_desc(descripcionRol);
			
			if(dtr.guardarRol(r))
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
			System.err.println("SL: ERROR AL GUARDAR ROL " + e.getMessage());
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	
	
	
	
}
