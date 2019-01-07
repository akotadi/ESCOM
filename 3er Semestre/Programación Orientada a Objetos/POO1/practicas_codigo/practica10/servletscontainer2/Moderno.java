import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class  Moderno implements Servlet {

  public void init(ServletConfig config) throws ServletException {
    System.out.println("init");
  }

  public void service(ServletRequest request, ServletResponse response)
    throws ServletException, IOException {
    System.out.println("desde service");
    PrintWriter out = response.getWriter();
    out.println("Hello. Roses are red.");
    out.println("======Violets are blue.");
    out.println("parametro = "+request.getParameter("Texto"));
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
