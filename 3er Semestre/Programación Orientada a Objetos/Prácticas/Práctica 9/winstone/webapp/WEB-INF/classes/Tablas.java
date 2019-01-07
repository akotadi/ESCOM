import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.*;

public class Tablas extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException {
		
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<head><title>Practica 9</title></head>");
		out.println("<body>");
		for (int i = 1; i < 11 ; i++) {
			out.println("Tabla del " + i + "<br>");
			for (int j = 1; j < 11 ; j++) {
				out.println("" + i + " x " + j + " = " + (i * j));
				out.println("<br>");
			}
			out.println("<br>");
		}
		out.println("</body></html>");
	
	}

}
