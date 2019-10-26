package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DT_Usuario;
import entidades.Usuario;

/**
 * Servlet implementation class SLguardarUsuario
 */
@WebServlet("/SLguardarUsuario")
public class SLguardarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLguardarUsuario() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String opc = request.getParameter("opc");
		int opcion = 0;

		opc = opc==null?"0":opc;
		System.out.println("opc: "+opc);
		opcion = Integer.parseInt(opc);
		System.out.println("opcion: "+opcion);
		
		Usuario u = new Usuario();
		DT_Usuario dtus = new DT_Usuario();
		
		switch(opcion)
		{
			case 1:
			{
				try
				{
					u.setUsername(request.getParameter("username"));
					u.setPwd(request.getParameter("password"));
					u.setNombre1(request.getParameter("nombre1"));
					u.setNombre2(request.getParameter("nombre2"));
					u.setApellido1(request.getParameter("apellido1"));
					u.setApellido2(request.getParameter("apellido2"));
					u.setEmail(request.getParameter("email"));
					
					if(dtus.guardarUsuario(u))
					{
						response.sendRedirect("./pages/seguridad/newUser.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("./pages/seguridad/newUser.jsp?msj=2");
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("Servlet: Error al guardar el Usuario!!!");
				}
				break;
			}
			case 2:
			{
				try
				{
					u.setId_user(Integer.parseInt(request.getParameter("IdUser")));
					u.setUsername(request.getParameter("username"));
					u.setPwd(request.getParameter("password"));
					u.setNombre1(request.getParameter("nombre1"));
					u.setNombre2(request.getParameter("nombre2"));
					u.setApellido1(request.getParameter("apellido1"));
					u.setApellido2(request.getParameter("apellido2"));
					u.setEmail(request.getParameter("email"));
					
					if(dtus.modificarUser(u))
					{
						response.sendRedirect("./pages/seguridad/tblusuarios.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("./pages/seguridad/tblusuarios.jsp?msj=2");
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("Servlet: Error al editar el Usuario!!!");
				}
				break;
			}
			
			default:
			{
				response.sendRedirect("../seguridad/newUser.jsp?msj=ERROR");
			}
		}
		
		
	}

}
