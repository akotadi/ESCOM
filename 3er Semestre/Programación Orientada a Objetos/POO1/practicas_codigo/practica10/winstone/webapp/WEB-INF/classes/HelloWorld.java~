import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//http://localhost:5050/servlets/servlet/Hello
public class HelloWorld extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    out.println("<HTML>");
    out.println("<HEAD><TITLE>Hello World</TITLE></HEAD>");
    out.println("<BODY>");
    out.println("<BIG>Hola pianola</BIG>");
    out.println("</BODY></HTML>");
  }
}
