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
   <link href="Style/menu.css" rel="stylesheet">
   <link href="Style/form.css" rel="stylesheet">

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
    <style>
* {
    box-sizing: border-box;
}
	
input[type=text],[type=date], select, textarea{
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    resize: vertical;
}

label {
    padding: 12px 12px 12px 0;
    display: inline-block;
}

input[type=submit] {
    background-color: #4CAF50;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    float: right;
}

input[type=submit]:hover {
    background-color: #45a049;
}

.container {
		top:50%;
		left:45%;
		width:500px;
		margin-left:520px;
		height:520px;
		margin-top:50px;
		padding:40px;
		border:1px solid #808080;
		border-radius: 5px;
        background-color: #f2f2f2;
}
.col-25 {
    float: left;
    width: 25%;
    margin-top: 6px;
}

.col-75 {
    float: left;
    width: 75%;
    margin-top: 6px;
}

/* Clear floats after the columns */
.row:after {
    content: "";
    display: table;
    clear: both;
}

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media (max-width: 600px) {
    .col-25, .col-75, input[type=submit] {
        width: 100%;
        margin-top: 0;
    }
}
</style>
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
 <h1 style="text-align: center"><br><br><br>ABMC Reserva </h1>
 <div class="container">
      	 <form class="form-signin" id="myForm" name="myForm" action="" method="post">
     
        <h2 class="form-signin-heading"> Reserva</h2>
        
    <div class="row">
     <div class="col-25"><label for="inputid" class="sr-only">ID</label></div>
     <div class="col-75"> <input  name="id" class="form-control" type="text" value="--" readonly="readonly" ></div>
    </div>
    
      <div class="row">
     <div class="col-25"><label for="inputTipoElemento" class="sr-only">Tipo de elemento</label></div>
      <%CtrlABMCTipoElemento ctrl=new CtrlABMCTipoElemento();
			ArrayList<TipoElemento> tels= new ArrayList<TipoElemento>();
			int id=((Persona)session.getAttribute("user")).getCategoria().getId_cat();
			if(id==2){
				tels=ctrl.getAll();
			}
			else {tels=ctrl.getPublico();}
		%>
		<div class="col-75">	<select name="tipo_elemento" >
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
		</div>	
		 </div>   
		 
   <div class="row">
   <div class="col-25"><label for="inputElemento" class="sr-only">Elemento</label></div>
     <select name="elemento" style="width: 170px; ">
		<%	 
		if(request.getAttribute("elementos_tipo")!=null){
		eles=(ArrayList<Elemento>)request.getAttribute("elementos_tipo");
		
		for(Elemento e : eles){%>
			<option value="<%=e.getId_El() %>"><%=e.getNombre_El() %></option>
		<%}} %> 
		</select>
		<input type="submit"  value="Buscar Elemento" onclick="javascript: submitForm('reserva/consulta')">
    </div>   
		
	<div class="row">
      <div class="col-25"><label for="inputFecha" class="sr-only">Fecha:</label></div>
      <div class="col-75"><input name="fecha" id="inputFecha"  class="form-control" placeholder="aaaa-mm-dd" autofocus="" type="date" ></div>
    </div>
    
    <div class="row">
      <div class="col-25"><label for="inputHora_inicio" class="sr-only">Hora de Inicio:</label></div>
      <div class="col-75"><input name="hora_inicio" id="inputHora_inicio"  class="form-control" placeholder="hh:mm:ss" autofocus="" type="text" ></div>
    </div>	
	
	<div class="row">
      <div class="col-25"><label for="inputHora_fin" class="sr-only">Hora de Finalizacion:</label></div>
      <div class="col-75"><input name="hora_fin" id="inputHora_fin"  class="form-control" placeholder="hh:mm:ss" autofocus="" type="text" ></div>
    </div>
            
    <div class="row">
      <div class="col-25"><label for="inputDetalle" class="sr-only">Detalle:</label></div>
      <div class="col-75"><input name="detalle" id="inputDetalle"  class="form-control" placeholder="" autofocus="" type="text" ></div>
    </div>
 
 	<input type="submit"  value="Agregar" onclick="javascript: submitForm('reserva/alta')"> 
           
      
        
        	
      </form>

    </div> <!-- /container -->
    <script src="style/ie10-viewport-bug-workaround.js"></script>
</body>
</html>