<%@page import="java.util.ArrayList"%>
<%@page import="entity.Reserva"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Listado de Reservas </h2> 
<table>

<tr>
			<td style="width: 110px; ">APELLIDO Y NOMBRE</td>
			<td style="width: 110px; ">TIPO DE ELEMENTO</td>
			<td style="width: 110px; ">ELEMENTO</td>
			<td style="width: 110px; ">FECHA</td>
			<td style="width: 110px; ">HORA</td>
			<td style="width: 110px; ">DETALLE</td>
			<td style="width: 110px; ">ESTADO</td>
			
			
		</tr>
		<%
			ArrayList<Reserva>listaRes= (ArrayList<Reserva>)request.getAttribute("listaReservas");
			for(Reserva r : listaRes){
		%>
		
		<tr>
		    <td><%=r.getPersona().getApellido() %>, <%=r.getPersona().getNombre() %></td>
			<td><%=r.getTipoelemento().getNombre_TE() %></td>
			<td><%=r.getElemento().getNombre_El() %></td>
		    <td><%=r.getFecha() %></td>
			<td><%=r.getHora() %></td>
			<td><%=r.getDetalle() %></td>
			<td><%=r.getEstado()%></td>
			
			
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>