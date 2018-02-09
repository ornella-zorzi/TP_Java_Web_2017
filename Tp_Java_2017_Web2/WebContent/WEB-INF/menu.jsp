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
<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1>

</head>
<body bgcolor=#D8F6CE >
	<div id="header" >
	<form class="form-signin" id="myForm" name="myForm" action="" method="post">
			<ul class="nav">
				<li><a class=active href="Start">Home </a> </li>
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
	</form>		</ul>
	</div>
</body>
</html>