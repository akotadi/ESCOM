import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HolaServlet extends HttpServlet
{
  public void doGet(HttpServletRequest entrada, 
                    HttpServletResponse salida)
    throws IOException, ServletException
    {
        salida.setContentType("text/html");
    PrintWriter sale = salida.getWriter();

        sale.println("<html>");
        sale.println("  <head>");
        sale.println("  <title>Hola Soy Jesús</title>");
        sale.println("  </head>");
        sale.println("  <body>");
        sale.println("    <h1>Hola Soy Jesús</h1>");
        sale.println("  </body>");
        sale.println("</html>");
    }
}