<%@page import="controlers.CtrlABMCTipoElemento"%>
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
<% TipoElemento encontrado=null;
String id_te="";
String nombre_te="";
String cant_reserva_max="";
String tiempo_limite="";
String dias_anticipacion ="" ;

if(request.getAttribute("encontrada")!=null){
	   encontrado =(TipoElemento)request.getAttribute("encontrado");
	   id_te=String.valueOf(encontrado.getId_TE()); 
       nombre_te=encontrado.getNombre_TE();
       cant_reserva_max=String.valueOf(encontrado.getCant_reserva_max());
       tiempo_limite=String.valueOf(encontrado.getTiempo_limite());
       dias_anticipacion=String.valueOf(encontrado.getDias_anticipacion()); 
}
%>
 <div class="container">
  <form class="form-signin" id="myForm" name="myForm" action="" method="post">
     

     
        <h2 class="form-signin-heading"> Tipo de Elemento</h2>
        ID <input  name="id" value=<%=id_te%> ><br><br>
        
 <label for="inputnombre_te" class="sr-only">Tipo de elemento</label>
        <input name="nombre_te" id="inputnombre_te" class="form-control" placeholder=""  type="" value=<%=nombre_te %> >
        <button class="btn btn-lg " onclick="javascript: submitForm('tipoElemento/consulta')">Buscar</button>
        <br><br><label for="inputcant_reserva_max" class="sr-only">Cant. de reserva maxima</label>
        <input name="cant_reserva_max" id="inputcant_reserva_max" class="" placeholder=""  type="" value=<%=cant_reserva_max %>>
       
        <br><br><label for="inputtiempo_limite" class="sr-only">Tiempo Limite</label>
        <input name="tiempo_limite" id="inputtiempo_limite" class="" placeholder="" type="" value=<%=tiempo_limite%>>
       
        <br><br><label for="inputdias_anticipacion" class="sr-only">Dias Anticipacion</label>
        <input name="dias_anticipacion" id="inputdias_anticipacion" class="" placeholder=""  type="" value=<%=dias_anticipacion %>><br><br>


        <button class="btn btn-lg " onclick="javascript: submitForm('tipoElemento/alta')">Agregar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('tipoElemento/modificacion')">Modificar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('tipoElemento/baja')">Borrar</button>	
       
      </form>

    </div> <!-- /container -->
    </body>
</html>