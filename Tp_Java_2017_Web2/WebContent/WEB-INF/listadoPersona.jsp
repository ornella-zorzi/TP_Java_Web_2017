<%@page import="java.util.ArrayList"%>
<%@page import="entity.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Listado de Personas </h2> 
<table>

<tr>
			<td style="width: 110px; ">DNI</td>
			<td style="width: 110px; ">APELLIDO</td>
			<td style="width: 110px; ">NOMBRE</td>
			<td style="width: 110px; ">EMAIL</td>
			<td style="width: 110px; ">CATEGORIA</td>
			
			
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