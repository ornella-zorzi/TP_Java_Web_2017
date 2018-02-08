<%@page import="java.util.ArrayList"%>
<%@page import="entity.Reserva"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	<div id="header">
			<ul class="nav">
				<li><a href="Start">Home </a> </li>
				<li><a href="#">Personas </a>
				   <ul>
						<li><a href="persona">ABMC Persona</a></li>
						<li><a href="listadoPer">Listado de Personas</a></li>
					</ul>
				</li>
				<li><a href="#">Elementos</a>
					<ul>
						<li><a href="elemento">ABMC Elemento</a></li>
						<li><a href="listadoEl">Listado de Elementos</a></li>
					</ul>
				</li>
				<li><a href="#">Tipo de Elementos</a>
					<ul>
						<li><a href="tipoElemento">ABMC Tipo de Elemento</a></li>
						<li><a href="listadoTe">Listado de Tipo de Elementos</a></li>
					</ul>
				</li>
				<li><a href="#">Reservas</a>
				     <ul>
						<li><a href="reserva">ABMC Reserva</a></li>
						<li><a href="listadoRe/listado">Listado de Reservas</a></li>
					</ul>
				</li>
			</ul>
		</div>

<div class="container">
<form class="form-signin" id="myForm" name="myForm" action="" method="get">
      
<h2>Listado de Reservas Pendientes</h2> 
<table>

<tr>
			<td style="width: 110px; ">ID</td>
			<td style="width: 110px; ">TIPO DE ELEMENTO</td>
			<td style="width: 110px; ">ELEMENTO</td>
			<td style="width: 110px; ">FECHA INICIO</td>
			<td style="width: 110px; ">FECHA FIN</td>
			<td style="width: 110px; ">HORA</td>
			<td style="width: 110px; ">DETALLE</td>
			
			
		</tr>
		
		<%
			ArrayList<Reserva>listaRes= (ArrayList<Reserva>)request.getAttribute("listaReservas");
			for(Reserva r : listaRes){
				
		%>
		<tr>
		   <td><%=r.getId_res()%></td>
			<td><%=r.getTipoelemento().getNombre_TE() %></td>
			<td><%=r.getElemento().getNombre_El() %></td>
		    <td><%=r.getFecha() %></td>
			<td><%=r.getHora_inicio() %></td>
			<td><%=r.getHora_fin() %></td>
			<td><%=r.getDetalle() %></td>
			<td><button type="submit" onclick="javascript: submitForm('listado/eliminar')"value="<%=r.getId_res()%>" id="cancelar" name="cancelar">Cancelar reserva</button></td>
			 </tr>
		<%
			}
		%>
		
    </div> <!-- /container -->  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="style/ie10-viewport-bug-workaround.js"></script>
	</table>
	</form>
</body>
</html>