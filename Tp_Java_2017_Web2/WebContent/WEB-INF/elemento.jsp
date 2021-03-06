<%@page import="controlers.CtrlABMCElemento"%>
<%@page import="entity.Elemento"%>
<%@page import="entity.TipoElemento"%>
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
	
input[type=text], select, textarea{
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
		height:350px;
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

<%
Elemento encontrado = null;
String id_el ="";
String nombre_el="";
TipoElemento tipo_el = new TipoElemento();
String tipoElemento; 
int id_te=0;
String nombre_te=""; 


if(request.getAttribute("encontrado")!=null){
	   encontrado =(Elemento)request.getAttribute("encontrado");
	   id_el=String.valueOf(encontrado.getId_El());
	   nombre_el=encontrado.getNombre_El();
	   tipo_el=encontrado.getTipoElemento();
}


%>
<h1 style="text-align: center"><br><br><br>ABMC Elemento </h1>
<div class="container">

      <form class="form-signin" id="myForm" name="myForm" action="" method="post">
      
        <h2 class="form-signin-heading">  Elemento</h2>
        
        <div class="row">
     <div class="col-25"><label for="inputid" class="sr-only">ID</label></div>
     <div class="col-75"> <input  name="id_el" class="form-control" type="text" readonly="readonly" value=<%=id_el %> ></div>
    </div>
    <div class="row">
     <div class="col-25"><label for="inputnombre_el" class="sr-only">Elemento</label></div>
      <div class="col-75"><input name="nombre_el" id="inputnombre_el"  class="form-control" placeholder="" required="" autofocus="" type="text" value=<%=nombre_el %>></div>
      <input type="submit"  value="Buscar" onclick="javascript: submitForm('elemento/consulta')"> 
    </div>
    
    <div class="row">
     <div class="col-25"><label for="inputTipoElemento" class="sr-only">Tipo de elemento</label></div>
      <%CtrlABMCElemento ctrl=new CtrlABMCElemento();
			ArrayList<TipoElemento> te= new ArrayList<TipoElemento>();
			te=ctrl.getTipoElemento();
		%>
      <div class="col-75"><select name="tipoElemento" id="inputtipoelemento"><%	for(TipoElemento t : te){ 
		if(tipo_el.getId_TE()==t.getId_TE()){
				%><option value="<%=t.getId_TE() %>"selected><%=t.getNombre_TE() %></option>
			<%}
			else{%><option value="<%=t.getId_TE() %>"><%=t.getNombre_TE() %></option>
			 <%  } }%>
		</select> </div>
     
    </div>
    
    
   <div class="row">
        <input type="submit"  value="Agregar" onclick="javascript: submitForm('elemento/alta')"> 
        <input type="submit"  value="Modificar" onclick="javascript: submitForm('elemento/modificacion')" > 
        <input type="submit"  value="Borrar" onclick="javascript: submitForm('elemento/baja')"> 
    </div> 

      </form>
    </div> <!-- /container -->  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="style/ie10-viewport-bug-workaround.js"></script>
</body>
</html>