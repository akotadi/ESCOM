<%@ page import = "Usuario" %>
<%@ page import = "Articulo" %>


<jsp:useBean id="usuario" class="Usuario" scope="session"/>
<jsp:useBean id="articulo" class="Articulo" scope="session"/>

<jsp:setProperty name="base" property="*"/>

<html>
<head><title>Carrito de Compras</title></head>
<body bgcolor="white">
<font size=4 face=Arial>
<%! String user = request.getParameter("usuario"); %>
<%! String pwd = request.getParameter("pwd"); %>


<% if( usuario.valido(user ,pwd) ) 
{

%> 

  <CENTER>
  <B>Hola <%= request.getParameter("usuario") %>   </B>
  </CENTER>
  
  <TABLE>
  <%
  for( i =0; i < articulo.numeroRegs(); i++ )
  {  %>
      <TR>  
      <TD> <%= articulo.getClave(i) %> <BR> </TD>
   
   
  Direccion: <%= base.getDireccion() %> <BR>
  Teléfono: <font size=7 color=#ff0022> <%= base.getTelefono() %> </font>
  <BR>
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
