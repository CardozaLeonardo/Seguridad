<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Usuario</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Custom fonts for this template -->
  <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="../css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
  
  <!-- jAlert css  -->
  <link rel="stylesheet" href="../vendor/jAlert/dist/jAlert.css" /> 
  
<!-- Font Awesome -->
<!-- <link rel="stylesheet" href="../../plugins/fontawesome-free/css/all.min.css">
Ionicons
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
Theme style
<link rel="stylesheet" href="../../dist/css/adminlte.min.css">
Google Font: Source Sans Pro
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
jAlert css 
<link rel="stylesheet" href="../../plugins/jAlert/dist/jAlert.css" /> -->


<%
/* RECUPERAMOS EL VALOR DE LA VARIABLE MSJ */
String mensaje = "";
mensaje = request.getParameter("msj");
mensaje = mensaje==null?"":mensaje;

%>


</head>
<body class="hold-transition sidebar-mini">

<div id="wrapper">
	
	<!-- SIDEBAR -->
	  	<jsp:include page="../WEB-INF/layouts/sidebar.jsp"></jsp:include>
	<!-- SIDEBAR -->
	
	  <!-- Content Wrapper. Contains page content -->
	  <div id="content-wrapper" class="d-flex flex-column">
	    <!-- Content Header (Page header) -->
	    
	    <section class="content-header">
	<!-- Navbar -->
	  	<jsp:include page="/WEB-INF/layouts/header.jsp"></jsp:include>
	<!-- /.navbar -->
	
	      <div class="container-fluid">
	        <div class="row mb-2">
	          <div class="col-sm-6">
	            <h1>Registro [Nuevo Usuario]</h1>
	          </div>
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <li class="breadcrumb-item"><a href="usuarios.jsp">Seguridad</a></li>
	              <li class="breadcrumb-item active">Nuevo Usuario</li>
	            </ol>
	          </div>
	        </div>
	      </div><!-- /.container-fluid -->
	    </section>
	
	<!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <!-- left column -->
          <div class="col-md-12">
            <!-- general form elements -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Nuevo Usuario</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form role="form" action="../SLguardarUsuario" method="post">
                <div class="card-body">
                  <input name="opc" id="opc" type="hidden" value="1"> <!-- ESTE INPUT ES UTILIZADO PARA EL CASE DEL SERVLET -->
                  <div class="form-group">
                    <label for="exampleInputEmail1">Nombre de Usuario:</label>
                    <input type="text" id="username" name="username" class="form-control" 
                    placeholder="Nombre de Usuario" required>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Contrase�a: </label>
                    <input type="password" id="password" name="password" class="form-control" 
                    title="Recuerde usar teclas may�sculas, min�sculas, n�meros y caracteres especiales..." 
                    placeholder="Ingrese su Contrase�a" required>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Confirmar Contrase�a: </label>
                    <input type="password" id="password2" name="password2" class="form-control" 
                    title="Recuerde usar teclas may�sculas, min�sculas, n�meros y caracteres especiales..." 
                    placeholder="Ingrese nuevamente su Contrase�a" required>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Primer Nombre:</label>
                    <input type="text" id="nombre1" name="nombre1" class="form-control" 
                    placeholder="Primer Nombre"  pattern="[A-Za-z]{4-16}" required>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Segundo Nombre:</label>
                    <input type="text" id="nombre2" name="nombre2" class="form-control"  pattern="[A-Za-z]{4-16}"  placeholder="Nombre de Usuario">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Primer Apellido:</label>
                    <input type="text" id="apellido1" name="apellido1" class="form-control" 
                    placeholder="Primer Apellido"  pattern="[A-Za-z]{4-16}" required>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Segundo Apellido:</label>
                    <input type="text" id="apellido2" name="apellido2" class="form-control" 
                    placeholder="Segundo Apellido"  pattern="[A-Za-z]{4-16}">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" 
                    placeholder="Ingrese una cuenta de correo electr�nico v�lida, Ejemplo: ejemplo@ejemplo.com" required>
                  </div>

                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Registrar</button>
                  <!-- <button type="reset" class="btn btn-danger">Cancelar</button> -->
                  
                  <a href="usuarios.jsp" class="btn btn-danger btn-icon-split">
                    <span class="text">Cancelar</span>
                  </a>
                  
                </div>
              </form>
            </div>
            <!-- /.card -->
           </div>
          </div>
         </div>       
    </section>
    <!-- /.content -->
	
	<!-- Footer -->
  		<jsp:include page="/WEB-INF/layouts/footer.jsp"></jsp:include>
  	<!-- ./Footer -->
	</div>
  </div>

  <!-- jQuery -->
  <script src="../vendor/jquery/jquery.min.js"></script>
  <!-- <script src="../../plugins/jquery/jquery.min.js"></script> -->

 <!-- jAlert js -->
  <script src="../vendor/jAlert/dist/jAlert.min.js"></script>
  <script src="../vendor/jAlert/dist/jAlert-functions.min.js"> </script>
  
  
  
  <script>
    $(document).ready(function ()
    {
     
      /////////// VARIABLES DE CONTROL MSJ ///////////
      var nuevo = 0;
      nuevo = "<%=mensaje%>";

      if(nuevo == "1")
      {
        successAlert('�xito', 'El nuevo registro ha sido almacenado!!!');
      }
      if(nuevo == "2")
      {
        errorAlert('Error', 'Revise los datos e intente nuevamente!!!');
      }
    
      

    });
    </script>

</body>
</html>