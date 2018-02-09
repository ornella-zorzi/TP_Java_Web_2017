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
<link href="Style/menu.css" rel="stylesheet">
<link href="Style/Tabla.css" rel="stylesheet">
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
    <style>
#customers {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#customers td, #customers th {
    border: 1px solid #ddd;
    padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #4CAF50;
    color: white;
}
</style>
<style type="text/css">
			
			* {
				margin:0px;
				padding:0px;
			}
			
			#header {
				margin:auto;
				width:1000px;
				font-family:Arial, Helvetica, sans-serif;
			}
			
			ul, ol {
				list-style:none;
			}
			
			.nav > li {
				float:left;
			}
			
			.nav li a {
				background-color:#000000;
				color:#fff;
				text-decoration:none;
				padding:20px 60px;
				display:block;
			}
			
			.nav li a:hover {
				background-color:#4CAF50;
			}
			
			.nav li ul {
				display:none;
				position:absolute;
				min-width:140px;
			}
			
			.nav li:hover > ul {
				display:block;
			}
			
			.nav li ul li {
				position:relative;
			}
			
			.nav li ul li ul {
				right:0px;
				top:0px;
			}
			
			
		</style>
</head>
<body bgcolor=#D8F6CE >
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
<h2 style="text-align: center;"><br><br><br><br>Listado de Reservas Pendientes </h2> 	
<form class="form-signin" id="myForm" name="myForm" action="" method="get">
 
<table id="customers" style="margin: 0 auto;">

<tr>
			<th style="width: 110px; ">ID</td>
			<th style="width: 110px; ">TIPO DE ELEMENTO</td>
			<th style="width: 110px; ">ELEMENTO</td>
			<th style="width: 110px; ">FECHA INICIO</td>
			<th style="width: 110px; ">FECHA FIN</td>
			<th style="width: 110px; ">HORA</td>
			<th style="width: 110px; ">DETALLE</td>
			<th style="width: 110px; "></td>
			
			
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
	</table>
	</form>
	  </div>
</body>
</html>