<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.RolUsuario, datos.DT_rolUsuario, java.util.ArrayList, entidades.Rol, datos.DT_Rol,
    entidades.Usuario, datos.DT_Usuario, entidades.VW_user_rol"%>
    
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Herbario Nacional - Roles-Usuarios</title>

  <!-- Custom fonts for this template -->
  <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="../css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>
<%
   
 DT_rolUsuario tru = new DT_rolUsuario();
 ArrayList<VW_user_rol> rolesUser = null;
 
 DT_Rol dtr = new DT_Rol();
 ArrayList<Rol> listaRoles = dtr.listarRoles();
 
 DT_Usuario dtus = new DT_Usuario();
 ArrayList<Usuario> usuarios = dtus.listarUsuarios();
 
 
 
 if(request.getParameter("user") != null)
 {
	 int idUser = Integer.parseInt(request.getParameter("user"));
	 rolesUser = tru.listarRolUsuario(idUser);
 }

%>
<body id="page-top">
   <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="../WEB-INF/layouts/sidebar.jsp"></jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <jsp:include page="../WEB-INF/layouts/header.jsp"></jsp:include>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">Roles</h1>
          <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>

            <h2>Aquí debe ir el contenido</h2>
            
            <form role="form" method="POST" class="col-6" action="../SL_asignarRol">
              
            <input type="hidden" id="idUser" name="idUser" value="0">
            <div class="form-group">
		    <label for="listaRoles">Roles: </label>
		    <select name="rolUser" required class="form-control" id="listaRoles">
		      <option value="" selected>Elegir</option>
		     <%
		        for(Rol rol: listaRoles){
		     %>
		      <option value="<%=rol.getId_rol()%>"><%=rol.getRol_name()%></option>
		      <%} %>
		    </select>
		  </div>
		  
		  
            <div class="form-group">
		    <label for="listaRoles">Roles: </label>
		    <select name="user" required class="form-control" id="user">
		      <option value="" selected>Elegir</option>
		     <%
		        for(Usuario user: usuarios){
		     %>
		      <option value="<%=user.getId_user()%>"><%=user.getUsername() +" "+user.getNombre1()+" "+user.getApellido1()%></option>
		      <%} %>
		    </select>
		  </div>
		  
		    <button type="submit" class="btn btn-primary">Agregar</button>
            </form>
            <br>
            
           
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  
                   <thead>
                    <tr>
                      <th>ID</th>
                      <th>Nombres</th>
                      <th>Apellido</th>
                      <th>Username</th>
                      <th>Email</th>
                      <th>Opcion</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>ID</th>
                      <th>Nombres</th>
                      <th>Apellido</th>
                      <th>Username</th>
                      <th>Email</th>
                      <th>Opcion</th>
                    </tr>
                  </tfoot>
                  <tbody>
                    <% for(Usuario user: usuarios) {%>
                    <tr>
                      <td><%=user.getId_user() %></td>
                      <td><%=user.getNombre1() %></td>
                      <td><%=user.getApellido1() %></td>
                      <td><%=user.getUsername() %></td>
                      <td><%=user.getEmail() %></td>
                      <td>
                       <a role="button" href="#" class="btn btn-danger">Remover</a>
                      </td>
                    </tr>
                    <%} %>
                    
                  </tbody>
                </table>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <jsp:include page="../WEB-INF/layouts/footer.jsp"></jsp:include>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
  
  

  <!-- Bootstrap core JavaScript-->
  <script src="../vendor/jquery/jquery.min.js"></script>
  <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="../js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="../vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="../js/demo/datatables-demo.js"></script>
  
  <script>
    
  </script>

</body>
</html>