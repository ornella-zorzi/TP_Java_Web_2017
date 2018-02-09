<%@page import="java.util.ArrayList"%>
<%@page import="entity.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="Style/Tabla.css" rel="stylesheet">
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
				padding:20px 50px;
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
		<style>
#customers {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 70%;
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
</head>

<body bgcolor=#D8F6CE>
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

<h2 style="text-align: center;"><br><br><br><br>Listado de Personas </h2> 
<table id="customers" style="margin: 0 auto;">

<tr>
			<th style="width: 110px; ">DNI</th>
			<th style="width: 110px; ">APELLIDO</th>
			<th style="width: 110px; ">NOMBRE</th>
			<th style="width: 110px; ">EMAIL</th>
			<th style="width: 110px; ">CATEGORIA</th>
			
			
		</tr>
		<%
			ArrayList<Persona>listaPers= (ArrayList<Persona>)request.getAttribute("listaPersonas");
			for(Persona p : listaPers){
		%>
		
		<tr>
			<td><%=p.getDni() %></td>
			<td><%=p.getApellido() %></td>
			<td><%=p.getNombre() %></td>
			<td><%=p.getEmail() %></td>
			<td><%=p.getCategoria().getNombre_cat()%></td>
			
			
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>