package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DT_rolOpcion;
import entidades.Tbl_rol_opcion;

/**
 * Servlet implementation class SL_asignarOpciones
 */
@WebServlet("/SL_asignarOpciones")
public class SL_asignarOpciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SL_asignarOpciones() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DT_rolOpcion dtr = new DT_rolOpcion();
		
		try {
			String opcion = request.getParameter("delete");
			String id_rol = request.getParameter("idRol");
			int id_rol_opcion = 0;
			int idRol = 0;
			id_rol_opcion = Integer.parseInt(opcion);
			idRol = Integer.parseInt(id_rol);
			if(dtr.eliminarRolOpcion(id_rol_opcion)) {
				response.sendRedirect("./seguridad/rolesOpciones.jsp?rol="+idRol);
			}
		}
		catch(Exception e)
		{
			System.err.println("Error en SL_asignarOpciones: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rolId = request.getParameter("idRol");
		String opcId = request.getParameter("listaOpciones");
		Tbl_rol_opcion tro = new Tbl_rol_opcion();
		DT_rolOpcion dro = new DT_rolOpcion();
		
		try
		{
			int rol = Integer.parseInt(rolId);
			int opc = Integer.parseInt(opcId);
			
			tro.setId_rol(rol);
			tro.setId_opc(opc);
			
			if(dro.guardarRolOpcion(tro)) {
				response.sendRedirect("./seguridad/rolesOpciones.jsp?rol=" + rol+"&saved=1");
			}
			
		}catch(Exception e) {
			System.err.println("Error en sevlet SL_asignarOpciones: ");
			e.printStackTrace();
		}
	}

}
