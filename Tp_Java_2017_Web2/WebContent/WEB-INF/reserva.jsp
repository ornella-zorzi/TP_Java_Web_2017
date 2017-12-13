<%@page import="controlers.CtrlABMCReserva"%>
<%@page import="controlers.CtrlABMCTipoElemento"%>
<%@page import="controlers.CtrlABMCElemento"%>
<%@page import="java.util.*"%>
<%@page import="entity.TipoElemento"%>
<%@page import="entity.Reserva"%>
<%@page import="entity.Elemento"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Time"%>
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
 <%ArrayList<Elemento> eles=new ArrayList(); %>
 <div class="container">
      	 <form class="form-signin" id="myForm" name="myForm" action="" method="post">
     
        <h2 class="form-signin-heading"> Reserva</h2>
			ID <input><br>Tipo Elemento<%CtrlABMCTipoElemento ctrl=new CtrlABMCTipoElemento();
			ArrayList<TipoElemento> tels= new ArrayList<TipoElemento>();
			tels=ctrl.getAll();
		%>
			<select name="tipo_elemento">
		<%	for(TipoElemento t : tels){%>
			<option value="<%=t.getId_TE() %>"><%=t.getNombre_TE() %></option>
		
		<% 	}
		%> 
		</select>
		<button class="btn btn-lg " onclick="javascript: submitForm('reserva/consulta')">Buscar</button>
		
        	<select name="elemento">
		<%	 
		CtrlABMCElemento ctrl2=new CtrlABMCElemento();
		eles=ctrl2.getAll();
		
		for(Elemento e : eles){%>
			<option value="<%=e.getId_El() %>"><%=e.getNombre_El() %></option>
		<%} %> 
		</select><br>
		<br><label for="inputFecha" class="sr-only">fecha</label>
        <input name="fecha" id="inputFecha" class="form-control" placeholder="aaaa/mm/dd" required="" type="">
       
        <br><br><label for="inputHora" class="sr-only">Hora</label>
        <input name="hora" id="inputHora" class="form-control" placeholder="hh:mm:ss" required="" type="">
       
        <br><br><label for="inputDetalle" class="sr-only">Detalle</label>
        <input name="detalle" id="inputDetalle" class="form-control" placeholder="" required="" type=""><br><br>
        <label for="inputEstado" class="sr-only">Estado</label>
        <input name="estado" id="inputEstado" class="form-control" placeholder="" required="" type=""><br><br>
           
       <button class="btn btn-lg " onclick="javascript: submitForm('reserva/alta')">Agregar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('reserva/modificacion')">Modificar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('reserva/baja')">Borrar</button>	
      </form>

    </div> <!-- /container -->
    
</body>
</html>