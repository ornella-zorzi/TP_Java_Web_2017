<%@page import="java.util.ArrayList"%>
<%@page import="servlet.abmcPerServlet" %>
<%@page import="servlet.abmcResServet" %>
<%@page import="entity.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>Menu Desplegable</title>
		
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="Style/menu.css" rel="stylesheet">
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
	<div id="header" >
	<form class="form-signin" id="myForm" name="myForm" action="" method="post">
			<ul class="nav">
				<li><a class=active href="Start">Home </a> </li>
				<li><a href="#">Personas </a>
				   <ul>
						<li><a href="persona/per">ABMC Persona</a></li>
						<li><a href="listadoPer/lp">Listado de Personas</a></li>
					</ul>
				</li>
				<li><a href="#">Elementos</a>
					<ul>
						<li><a href="elemento/el">ABMC Elemento</a></li>
						<li><a href="listadoEl/le">Listado de Elementos</a></li>
					</ul>
				</li>
				<li><a href="#">Tipo de Elementos</a>
					<ul>
						<li><a href="tipoElemento/te">ABMC Tipo de Elemento</a></li>
						<li><a href="listadoTe/lte">Listado de Tipo de Elementos</a></li>
					</ul>
				</li>
				<li><a href="#">Reservas</a>
				     <ul>
						<li><a href="reserva/re">ABMC Reserva</a></li>
						<li><a href="listadoRe/listado">Listado de Reservas</a></li>
					</ul>
				</li>
	</form>		</ul>
	</div>
</body>

<div>
<h1 style="text-align: center;"><br><br><br><br>Su reserva se ha registrado exitosamente. </h1>
<h1 style="text-align: center;"><br>Se le ha enviado un mail a su casilla con los datos de la reserva. </h1>
</html>