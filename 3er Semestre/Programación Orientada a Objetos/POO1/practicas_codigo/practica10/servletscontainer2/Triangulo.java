import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Triangulo implements Servlet {
  public void init(ServletConfig config) throws ServletException {
    System.out.println("init");
  }
  public void service(ServletRequest req, ServletResponse res)
                               throws ServletException, IOException {

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    String n = req.getParameter("renglones");
    int reng = 10; 
    System.out.println("Triangulo  reng ="+reng);
    if(n != null) 
	reng =Integer.parseInt(n);
    out.println("<HTML>");
    out.println("<HEAD><TITLE>Triangulo</TITLE></HEAD>");
    out.println("<BODY>");
    out.println("<H1>Triangulo</H1>");
    for(int i=0; i< reng ; i++){
	for(int j=0; j< i+1; j++)
    		out.println("*");
	out.println("<BR>");
    }
    out.println("</BODY></HTML>");
  }
  public void destroy() {
    System.out.println("destroy");
  }
  public String getServletInfo() {
    return null;
  }
  public ServletConfig getServletConfig() {
    return null;
  }
}
