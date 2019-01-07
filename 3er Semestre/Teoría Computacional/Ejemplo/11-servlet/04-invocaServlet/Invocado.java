import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Invocado extends HttpServlet
{
  public void doGet(HttpServletRequest entrada, HttpServletResponse salida)
    throws IOException, ServletException
    {
        salida.setContentType("text/html");
    PrintWriter sale = salida.getWriter();

        sale.println("<html>");
        sale.println("  <head>");
        sale.println("  <title>Invocado</title>");
        sale.println("  </head>");
        sale.println("  <body>");
        sale.println("    <h1>Servlet Invocado</h1>");
        sale.println("  </body>");
        sale.println("</html>");
    }
}