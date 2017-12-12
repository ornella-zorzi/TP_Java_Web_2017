<%@page import="controlers.CtrlABMCElemento"%>
<%@page import="entity.Elemento"%>
<%@page import="entity.TipoElemento"%>
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
<%
Elemento encontrado = null;
String id_el ="";
String nombre_el="";
TipoElemento tipoel = new TipoElemento();
String tipoElemento; 
int id_te=0;
String nombre_te=""; 


if(request.getAttribute("encontrado")!=null){
	   encontrado =(Elemento)request.getAttribute("encontrado");
	   id_el=String.valueOf(encontrado.getId_El());
	   nombre_el=encontrado.getNombre_El();
	   id_te=encontrado.getTipoElemento().getId_TE();
	   nombre_te=encontrado.getTipoElemento().getNombre_TE();
}


%>
<div class="container">

      <form class="form-signin" id="myForm" name="myForm" action="" method="post">
      
        <h2 class="form-signin-heading">  Elemento</h2>
			 ID <label for="inputnombre_el" class="sr-only"><input name="id_el" value=<%=id_el %>>           
			 <br><br> Elemento</label>
        <input name="nombre_el" id="inputnombre_el" class="" placeholder=""  type="" value=<%=nombre_el %>>

        <button class="btn btn-lg " onclick="javascript: submitForm('elemento/consulta')">Buscar</button><br><br><label for="inputTipoElemento" class="sr-only">Tipo de elemento</label> 
        <%CtrlABMCElemento ctrl=new CtrlABMCElemento();
			ArrayList<TipoElemento> te= new ArrayList<TipoElemento>();
			te=ctrl.getTipoElemento();
		%>
		<select name="tipoElemento" id="inputtipoelemento">
		<%	for(TipoElemento t : te){ %>
			<option value="<%=t.getId_TE() %>"><%=t.getNombre_TE() %></option> <% } %>
		</select><br><br>
     

        <button class="btn btn-lg " onclick="javascript: submitForm('elemento/alta')">Agregar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('elemento/modificacion')">Modificar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('elemento/baja')">Borrar</button>	
    
      </form>
    </div> <!-- /container -->  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="style/ie10-viewport-bug-workaround.js"></script>
</body>
</html>