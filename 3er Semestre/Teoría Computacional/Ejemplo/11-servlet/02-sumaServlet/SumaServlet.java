import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SumaServlet extends HttpServlet
{
  public void doGet(HttpServletRequest entrada, HttpServletResponse salida)
    throws IOException, ServletException
    {
        salida.setContentType("text/html");
    PrintWriter sale = salida.getWriter();

    int numeroA = Integer.parseInt(entrada.getParameter("NumeroA"));
    int numeroB = Integer.parseInt(entrada.getParameter("NumeroB"));
        sale.println("<html>");
        sale.println("  <head>");
        sale.println("  <title>Suma Numeros</title>");
        sale.println("  </head>");
        sale.println("  <body>");
        sale.println(
"    <h1>" + numeroA + " + " + numeroB + " = " + (numeroA + numeroB) + "</h3>");
        sale.println("  </body>");
        sale.println("</html>");
    }
    
    public void service(HttpServletRequest entrada, HttpServletResponse salida)
    throws IOException, ServletException
    {
        salida.setContentType("text/html");
    PrintWriter sale = salida.getWriter();

    int numeroA = Integer.parseInt(entrada.getParameter("NumeroA"));
    int numeroB = Integer.parseInt(entrada.getParameter("NumeroB"));
        sale.println("<html>");
        sale.println("  <head>");
        sale.println("  <title>SOY SERVICE</title>");
        sale.println("  </head>");
        sale.println("  <body>");
        sale.println(
"    <h1>SERVICE " + numeroA + " + " + numeroB + " = " + (numeroA + numeroB) + "</h3>");
        sale.println("  </body>");
        sale.println("</html>");
    }
}