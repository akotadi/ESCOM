

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
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




//import java.util.*;


public class JDBCServlet extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {

    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "classicmodels";
    BufferedImage bufferedImage;
    Connection conex=null;
   ServletOutputStream out = response.getOutputStream();
    String name = request.getParameter("nombre");
    Blob photo = null;
	try { 
		Class.forName("com.mysql.jdbc.Driver");          		
		conex=DriverManager.getConnection(url+dbName, "root", "gatito" );
		Statement statement=conex.createStatement();
		ResultSet rs=statement.executeQuery("SELECT * FROM PerroImg where nombre='"+name+"'");
                while(rs.next())  
            {
            photo = rs.getBlob(5);
            
	    response.setContentType("image/jpg");
            //
            InputStream in = photo.getBinaryStream();
            int length = (int) photo.length();
	    response.setContentLength((int)length);
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];

      while ((length = in.read(buffer)) != -1) {
        System.out.println("writing " + length + " bytes");
        out.write(buffer, 0, length);
      }

            in.close();
            out.flush();
	    }
	    	conex.close( );
	    } catch(Exception fnfe1){
                System.out.println(fnfe1);
            }
	   
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        doGet(request, response);
    }
}



