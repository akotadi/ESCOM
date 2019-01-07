import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Hello extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    String name = req.getParameter("name");
    out.println("<HTML>");
    out.println("<HEAD><TITLE>Hola holilla, " + name + "</TITLE></HEAD>");
    out.println("<BODY>");
    out.println("Hola holilla, <h1>"+ name +"</h1>");
    out.println("<img src= \"http://localhost:5050/obtenimagen?nombre=manchas&tabla=PerroImg\"></img>");
    out.println("</BODY></HTML>");
  }

  public String getServletInfo() {
    return "A servlet that knows the name of the person to whom it's" + 
           "saying hello";
  }
}
