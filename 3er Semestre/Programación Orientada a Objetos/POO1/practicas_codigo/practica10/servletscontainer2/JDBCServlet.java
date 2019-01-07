

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.IOException;
import javax.imageio.ImageIO; 
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.sql.*; 
import java.awt.*;
import javax.servlet.*;
import javax.servlet.http.*;

//import java.util.*;
public class JDBCServlet extends GenericServlet {

    public void init(ServletConfig config) throws ServletException {
    System.out.println("init");
    }
    public void service(ServletRequest request, ServletResponse response)
        throws IOException, ServletException {
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "classicmodels";
    BufferedImage bufferedImage;
    Connection conex=null;
    PrintWriter out1 = response.getWriter();
    String name = request.getParameter("nombre");
    Blob photo = null;
	try { 
		ResultSet rs;
		Class.forName("com.mysql.jdbc.Driver");          		
		conex=DriverManager.getConnection(url+dbName, "root", "gatito" );
		Statement statement=conex.createStatement();
		if(name!=null)
			rs=statement.executeQuery("SELECT * FROM PerroImg where nombre='"+name+"'");
		else
            		rs=statement.executeQuery("SELECT * FROM PerroImg");
            
            //while(rs.next())  
            //{ 
            rs.next();
            System.out.println("Cons SELECT * FROM PerroImg where nombre='"+name+"'  bd="+rs.getString(1));
            //out1.println(rs.getString(1));
            photo = rs.getBlob(5);
            
	    response.setContentType("image/jpg");
            //
            InputStream in = photo.getBinaryStream();
            int length = (int) photo.length();
	    response.setContentLength((int)length);
            int bufferSize = 1024;
            int c=0;
            while ((c = in.read()) != -1) {
                out1.write(c);
            }
            in.close();
            out1.flush();
	    //}
	    	conex.close( );
	    } catch(Exception fnfe1){
                System.out.println("JDBC "+fnfe1);
                fnfe1.printStackTrace();
            }
	   
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



