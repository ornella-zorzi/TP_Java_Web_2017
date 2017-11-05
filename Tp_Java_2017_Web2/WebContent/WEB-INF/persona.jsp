<%@page import="controlers.CtrlABMCPersona"%>
<%@page import="entity.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <title>Play JavaCraft!</title>

    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/start.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="style/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	function submitForm(met) {
    		document.myForm.action=met;
    		//document.getElementById("myFrom").submit();
        }
    </script>
    
  </head>
<body>
 <div class="container">
      	 <form class="form-signin" id="myForm" name="myForm" action="" method="post">
     
        <h2 class="form-signin-heading"> Persona</h2>
        ID   <input><br><label for="inputdni" class="sr-only">DNI</label>
        <input name="dni" id="inputdni" class="form-control" placeholder="" required="" autofocus="" type=""> 
        <button class="btn btn-lg " onclick="javascript: submitForm('persona/consulta')">Buscar</button>
        <br><br><label for="inputNombre" class="sr-only">Nombre:</label>
        <input name="nombre" id="inputnombre" class="form-control" placeholder="" required="" type="">
      
        <br><br><label for="inputApellido" class="sr-only">Apellido:</label>
        <input name="apellido" id="inputapellido" class="form-control" placeholder="" required="" type=""><br><br>
     
        <label for="inputEmail" class="sr-only">Email:</label>
        <input name="email" id="inputemail" class="form-control" placeholder="" required="" type="">
      
        <label for="inputCategoria" class="sr-only"><br><br>Categoria:</label> 
        
        <%CtrlABMCPersona ctrl=new CtrlABMCPersona();
			ArrayList<Categoria> cats= new ArrayList<Categoria>();
			cats=ctrl.getCategoria();
		%>
			<select name="categoria" id="inputcategoria">
		<%	for(Categoria c : cats){%>
			<option value="<%=c.getId_cat() %>"><%=c.getNombre_cat() %></option>
		<%
			}
		%> 
		</select>
			<br><br><input type="checkbox" name="habilitado" id="inputhabilitado">
			<label for="inputHabilitado" class="sr-only">Habilitado</label>
		  
        <button class="btn btn-lg " onclick="javascript: submitForm('persona/alta')">Agregar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('persona/modificacion')">Modificar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('persona/baja')">Borrar</button>	
      </form>

    </div> <!-- /container -->  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="style/ie10-viewport-bug-workaround.js"></script>
  

</body></html>