<%@page import="controlers.*"%>
<%@page import="entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div class="container">

      <form class="form-signin" name="signin" action="start" method="post" style="height: 259px; ">
     
        <h2 class="form-signin-heading"> Reserva</h2>
        ID <input><br>Tipo Elemento<%CtrlABMCTipoElemento ctrl=new CtrlABMCTipoElemento();
			ArrayList<TipoElemento> tels= new ArrayList<TipoElemento>();
			tels=ctrl.getAll();
		%>
			<select name="elementos">
		<%	for(TipoElemento te : tels){%>
			<option value="<%=te.getNombre_TE() %>"><%=te.getNombre_TE() %></option>
		<%
			}
		%> 
		</select>
        <br>
        <br>Elemento<%
		%>
		<select></select><br>
		<br><label for="inputFecha" class="sr-only">fecha</label>
        <input name="fecha" id="inputFecha" class="form-control" placeholder="aaaa/mm/dd" required="" type="">
       
        <br><br><label for="inputHora" class="sr-only">Hora</label>
        <input name="hora" id="inputHora" class="form-control" placeholder="hh:mm:ss" required="" type="">
       
        <br><br><label for="inputDetalle" class="sr-only">Detalle</label>
        <input name="detalle" id="inputDetalle" class="form-control" placeholder="" required="" type=""><br><br>
        <label for="inputEstado" class="sr-only">Estado</label>
        <input name="estado" id="inputEstado" class="form-control" placeholder="" required="" type=""><br><br>
       
        <button class="btn btn-agregar btn-primary btn-block" type="submit">Agregar</button>
        <button class="btn btn-modificar btn-primary btn-block" type="submit">Modificar</button>
        <button class="btn btn-borrar btn-primary btn-block" type="submit">Borrar</button>
       
      </form>

    </div> <!-- /container -->
    
</body>
</html>