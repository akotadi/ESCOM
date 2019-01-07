

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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


public class JDBCServlet0 extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {

    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "classicmodels";
    BufferedImage bufferedImage;
    Connection conex=null;
	try { 
		Class.forName("com.mysql.jdbc.Driver");          		
		conex=DriverManager.getConnection(url+dbName, "root", "gatito" );
		Statement statement=conex.createStatement();
		ResultSet rs=statement.executeQuery("SELECT * FROM MyPictures1");
                while(rs.next()) 
            {
            bufferedImage = new BufferedImage(650, 550, 
			                            BufferedImage.TYPE_INT_RGB);
	    Graphics g = bufferedImage.getGraphics();
            g.setColor(Color.red);
            g.fillOval(0, 0, 199,199);
            byte[] imagedata = rs.getBytes("photo") ;
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
	    response.setContentType("image/jpg");
            //response.setContentLength((int)file.length());
            //g.drawImage(650, 550, img);
            ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
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



