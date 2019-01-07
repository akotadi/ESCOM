import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Llamador extends HttpServlet
{
  public void doGet(HttpServletRequest entrada, HttpServletResponse salida)
    throws IOException, ServletException
    {
        salida.setContentType("text/html");
    PrintWriter sale = salida.getWriter();

        sale.println("<html>");
        sale.println("  <head>");
        sale.println("  <title>Servlet Llamador</title>");
        sale.println("  </head>");
        sale.println("  <body>");
        sale.println("    <h1>Servlet llamador</h1>");
        sale.println("  </body>");
        sale.println("</html>");
        
        RequestDispatcher invoca = entrada.getNamedDispatcher("Invocado");
           invoca.forward(entrada ,salida);
    }
}