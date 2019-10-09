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
 * Servlet implementation class SLeliminarRol
 */
@WebServlet("/SLeliminarRol")
public class SLeliminarRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLeliminarRol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idRol = "";
		idRol = request.getParameter("id");
		idRol = idRol == null?"0":idRol;
		
		try 
		{
			DTRol dtr = new DTRol();
			Rol r = new Rol();
			r.setIdRol(Integer.parseInt(idRol));
			
			if(dtr.eliminarRol(r))
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
			System.err.println("SL:Error no eliminó el ROL " +e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
