<%@page import="java.util.ArrayList"%>
<%@page import="entity.TipoElemento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table>
		<%
			ArrayList<TipoElemento>listaTels= (ArrayList<TipoElemento>)request.getAttribute("listaTipoElementos");
			for(TipoElemento te : listaTels){
		%>
		<tr>
			<td><%=te.getNombre_TE() %></td>
			<td><%=te.getTiempo_limite() %></td>
			<td><%=te.getCant_reserva_max() %></td>
			<td><%=te.getDias_anticipacion() %></td>
			
			
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>