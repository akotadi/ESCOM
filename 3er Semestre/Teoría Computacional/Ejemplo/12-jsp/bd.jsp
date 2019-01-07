<!--
Ejemplo de una Consulta a una Base de Datos usando JSP

Jesús Manuel Olivares Ceja
-->

<%@ page import = "bd.sbd" %>

<jsp:useBean id="base" class="bd.sbd" scope="session"/>
<jsp:setProperty name="base" property="*"/>

<html>
<head><title>Consulta a Base de Datos</title></head>
<body bgcolor="white">
<font size=4 face=Arial>

<% if( base.hayRespuesta() ) { %>

  <CENTER>
  <B>Resultado de la Consulta</B>
  </CENTER>
  Nombre: <%= base.getNombre() %> <BR>
  Direccion: <%= base.getDireccion() %> <BR>
  <% base.inicializa(); %>
  <BR><BR>
  ¿otra consulta <a href="bd.jsp"> click aquí </a>

<% } else  { %>
  Sistema de Consulta a Base de Datos
 
  <form method=get>
  RFC: <input type=text name=rfc>
  <input type=submit value="Submit">
  </form>
 
<% } %>

</font>
</body>
</html>
