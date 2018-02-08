<%@page import="java.util.ArrayList"%>
<%@page import="entity.TipoElemento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="Style/Tabla.css" rel="stylesheet">
<link href="Style/menu.css" rel="stylesheet">
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
		
<h2 style="text-align: center;"><br><br><br><br>Listado de Tipo de Elementos </h2> 		
<table  id="customers" style="margin: 0 auto;">
		 <tr>

			<th style="width: 110px; ">NOMBRE TIPO ELEMENTO</td>
			
			<th style="width: 110px; ">CANTIDAD DE RESERVA MAX</td>
			<th style="width: 110px; ">TIEMPO LIMITE</td>
			<th style="width: 110px; ">DIAS DE ANTICIPACION</td>
			
			
			
		</tr>
		<%
			ArrayList<TipoElemento>listaTels= (ArrayList<TipoElemento>)request.getAttribute("listaTipoElementos");
			for(TipoElemento te : listaTels){
		%>
		<tr>
			<td><%=te.getNombre_TE() %></td>
			
			<td><%=te.getCant_reserva_max() %></td>
			<td><%=te.getTiempo_limite() %></td>
			<td><%=te.getDias_anticipacion() %></td>
			
			
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>