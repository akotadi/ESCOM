<HTML>
<!-- Initialize the bean for use in page processing. -->
<%@ page import = "bd.PersonaBean" %>

<jsp:useBean id="persona" class="bd.PersonaBean" scope="request"/>

<!-- Perform the actions on the bean. -->
<%
try
{
 /* Pasa el objeto request y solicita el alta */
 persona.alta(request);
}
catch (Exception e)
{
 System.out.println(e.getMessage());
}
%>
Alta de Persona
</HTML>
