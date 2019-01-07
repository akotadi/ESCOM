
import java.io.*;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import org.eclipse.jetty.server.Request;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

@MultipartConfig
public class SubeImagServ extends HttpServlet {
   private static final MultipartConfigElement MULTI_PART_CONFIG = new       MultipartConfigElement(System.getProperty("java.io.tmpdir")); 
   String conexionUrl = "jdbc:mysql://localhost:3306/Perracos";
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      InputStream is=null;
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      if (request.getContentType() != null && 
         request.getContentType().startsWith("multipart/form-data")) {
         request.setAttribute(
                 Request.__MULTIPART_CONFIG_ELEMENT, MULTI_PART_CONFIG);
	 System.out.println("IFIFcontenttype "+request.getContentType());
      }     
      try {        
         Class.forName("com.mysql.jdbc.Driver");          		
         Connection conex=DriverManager.getConnection(conexionUrl, "root", "" );
         String sql="INSERT INTO fotoPerro (foto) values(?)";
         PreparedStatement ps=conex.prepareStatement(sql);  
         Part p = request.getPart("imagen");
         if(p!=null) is=p.getInputStream();
         if(is!=null) ps.setBlob(1, is); 
         int row=ps.executeUpdate();
         if(row==0) out.println("No se insertó la imagen");
         else out.println("Se insertó la imagen");
      } catch(Exception fnfe1){ System.out.println(fnfe1); }	          
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    static private String extractFileName(Part part) {
	String contentDisp = part.getHeader("content-disposition");
	String[] items = contentDisp.split(";");
	for (String s : items) {
		if (s.trim().startsWith("filename")) {
	            return s.substring(s.indexOf("=") + 2, s.length()-1);
	        }
	}
	return "";
    }
}
