<%@page import="controlers.CtrlABMCReserva"%>
<%@page import="controlers.CtrlABMCTipoElemento"%>
<%@page import="controlers.CtrlABMCElemento"%>
<%@page import="controlers.CtrlABMCPersona"%>
<%@page import="java.util.*"%>
<%@page import="entity.TipoElemento"%>
<%@page import="entity.Persona"%>
<%@page import="entity.Reserva"%>
<%@page import="entity.Elemento"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Time"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <title>Play JavaCraft!</title>

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

 <%ArrayList<Elemento> eles=new ArrayList(); %>
 <div class="container">
      	 <form class="form-signin" id="myForm" name="myForm" action="" method="post">
     
        <h2 class="form-signin-heading"> Reserva</h2>
			ID <input><br>Tipo Elemento<%CtrlABMCTipoElemento ctrl=new CtrlABMCTipoElemento();
			ArrayList<TipoElemento> tels= new ArrayList<TipoElemento>();
			int id=((Persona)session.getAttribute("user")).getCategoria().getId_cat();
			if(id==2){
				tels=ctrl.getAll();
			}
			else {tels=ctrl.getPublico();}
		%>
			<select name="tipo_elemento" style="width: 107px; ">
		<%	if(request.getAttribute("elementos_tipo")!=null){
		int id_el=Integer.parseInt(request.getParameter("tipo_elemento"));
		TipoElemento te=new TipoElemento();
		te=ctrl.getById(id_el);
		%>
		<option value="<%=te.getId_TE() %>"><%=te.getNombre_TE() %></option>
	<%	}
		else{ for(TipoElemento t : tels){%>
			<option value="<%=t.getId_TE() %>"><%=t.getNombre_TE() %></option>
		<% 	}
		}
		%> 
		</select>
		<button class="btn btn-lg " onclick="javascript: submitForm('reserva/consulta')">Buscar</button><br><br>Elemento  
		
        	<select name="elemento" style="width: 113px; ">
		<%	 
		if(request.getAttribute("elementos_tipo")!=null){
		eles=(ArrayList<Elemento>)request.getAttribute("elementos_tipo");
		
		for(Elemento e : eles){%>
			<option value="<%=e.getId_El() %>"><%=e.getNombre_El() %></option>
		<%}} %> 
		</select><br>
		<br><label for="inputFecha" class="sr-only">Fecha</label>
        <input name="fecha" id="inputFecha" class="form-control" placeholder="aaaa-mm-dd" type="date" >
       
       <br>
		<br><br><label for="inputHora_inicio" class="sr-only">Hora de Inicio</label>
        <input name="hora_inicio" id="inputHora_inicio" class="form-control" placeholder="hh:mm:ss" type="time" >
        
        <br><br><label for="inputHora_fin" class="sr-only">Hora de Finalizacion</label>
        <input name="hora_fin" id="inputHora_fin" class="form-control" placeholder="hh:mm:ss" type="time" >
       
        <br><br><label for="inputDetalle" class="sr-only">Detalle</label>
        <input name="detalle" id="inputDetalle" class="form-control" placeholder="" type="" ><br><br>
           
       <button class="btn btn-lg " onclick="javascript: submitForm('reserva/alta')" style="width: 169px; ">Agregar</button>
        
        	
      </form>

    </div> <!-- /container -->
    
</body>
</html>