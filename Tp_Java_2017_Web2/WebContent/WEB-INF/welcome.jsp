<%@page import="java.util.ArrayList"%>
<%@page import="entity.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1>

</head>
<body>
	<ul id=”menu”>
<li><a href=”#”>PERSONA</a>
<ul>
<li><a href=”persona.jsp”>AMBCPersona</a></li>
<li><a href=”listadoPersona.jsp”>Listado Persona</a></li>

</ul>
</li>
<li><a href=”#”>Elemento</a>
<ul>
<li><a href=”#”>ABMCElemento</a></li>
<li><a href=”#”>Listado Elemento</a></li>

</ul>
</li>
<li><a href=”#”>Tipo Elemento</a>
<ul>
<li><a href=”#”>ABMCTipoElemento</a></li>
<li><a href=”#”>Listado Tipo Elemento</a></li>

</ul>
</li>

<li><a href=”#”>Reserva</a>
<ul>
<li><a href=”#”>ABMCReserva</a></li>
<li><a href=”#”>Listado Reserva</a></li>

</ul>
</li>



</body>
</html>