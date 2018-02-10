<%@page import="controlers.CtrlABMCPersona"%>
<%@page import="entity.Categoria"%>
<%@page import="entity.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="en"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" >
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
	    position: absolute;
		top:50%;
		left:45%;
		width:500px;
		margin-left:-200px;
		height:600px;
		margin-top:-150px;
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
					</ul><br><br>
				</li>
			</ul>
		</div>
<%
   Persona encontrada=null;
   String id="";
   String dni="";
   String nombre="";
   String apellido="";
   String email="";
   Categoria cate = new Categoria();
   String idCat="";
   String nombre_cat="";
   String usuario="";
   String contraseña="";
   boolean habilitado= true;
  String categoria;
   
   
   if(request.getAttribute("encontrada")!=null){
	   encontrada =(Persona)request.getAttribute("encontrada");
	   id=String.valueOf(encontrada.getId_per());
	   dni=encontrada.getDni();
	   nombre=encontrada.getNombre();
	   apellido=encontrada.getApellido();
	   email=encontrada.getEmail();
	   cate = encontrada.getCategoria();     
	   usuario=encontrada.getUsuario();
	   contraseña=encontrada.getContraseña();
	  habilitado=encontrada.isHabilitado();   
   } %>
   <h1 style="text-align: center"><br><br><br>ABMC Personas </h1>
 <div class="container" >
 
      	 <form class="form-signin" id="myForm" name="myForm" action="" method="post" >
      	         <h2 class="form-signin-heading"> Persona</h2>
        
    <div class="row">
     <div class="col-25"><label for="inputid" class="sr-only">ID</label></div>
     <div class="col-75"> <input  name="id" class="form-control" type="text" value=<%=id %> ></div>
    </div>
    <div class="row">
      <div class="col-25"><label for="inputdni" class="sr-only">DNI</label></div>
      <div class="col-75"><input name="dni" id="inputdni"  class="form-control" placeholder="" required="" autofocus="" type="text" value=<%=dni %>></div>
       <input type="submit"  value="Buscar" onclick="javascript: submitForm('persona/consulta')"> 
       
    </div>
     <div class="row">
      <div class="col-25"><label for="inputNombre" class="sr-only">Nombre:</label></div>
      <div class="col-75"><input name="nombre" id="inputnombre"  class="form-control" placeholder="" autofocus="" type="text" value=<%=nombre %>></div>
    </div>
    <div class="row">
      <div class="col-25"><label for="inputApellido" class="sr-only">Apellido:</label></div>
      <div class="col-75"><input name="apellido" id="inputapellido"  class="form-control" placeholder=""  autofocus="" type="text" value=<%=apellido %>></div>
    </div>
     <div class="row">
      <div class="col-25"><label for="inputEmail" class="sr-only">Email:</label></div>
      <div class="col-75"><input name="email" id="inputemail"  class="form-control" placeholder=""  autofocus="" type="text" value=<%=email %>></div>
    </div>
    
     
    
    <div class="row">
      <div class="col-25"><label for="inputCategoria" class="sr-only">Categoria:</label></div>
       <%CtrlABMCPersona ctrl=new CtrlABMCPersona();
			ArrayList<Categoria> cats= new ArrayList<Categoria>();
			cats=ctrl.getCategoria();
		%>
      <div class="col-75"><select name="categoria" required ="id" id="categoria" >
      	
		<%	for(Categoria c : cats){
			if(cate.getId_cat()==c.getId_cat()){
				%><option value="<%=c.getId_cat() %>"selected><%=c.getNombre_cat() %></option>
			<%}
			else{%><option value="<%=c.getId_cat() %>"><%=c.getNombre_cat() %></option>
			 <%  } }%> 

		</select>
		</div>
    </div>
     <div class="row">
      <div class="col-25"><label for="inputUsuario" class="sr-only">Usuario:</label></div>
      <div class="col-75"><input name="usuario" id="inputusuario"  class="form-control" placeholder="" autofocus="" type="text" value=<%=usuario %>></div>
    </div>   
       <div class="row">
      <div class="col-25"><label for="inputContraseña" class="sr-only">Contraseña:</label></div>
      <div class="col-75"><input name="contraseña" id="inputcontraseña"  class="form-control" placeholder="" autofocus="" type="password" value=<%=contraseña %>></div>
    </div>    
     <input type="checkbox" name="habilitado" id="inputhabilitado" value="true" 
			<%if(habilitado){ %>checked<%} %>>
			<label for="inputHabilitado" class="sr-only">Habilitado</label>
      <div class="row">
        <input type="submit"  value="Agregar" onclick="javascript: submitForm('persona/alta')"> 
        <input type="submit"  value="Modificar" onclick="javascript: submitForm('persona/modificacion')"> 
        <input type="submit"  value="Borrar" onclick="javascript: submitForm('persona/baja')"> 
    </div> 
       
		
      </form>
    </div> <!-- /container -->  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="style/ie10-viewport-bug-workaround.js"></script>
  

</body></html>
