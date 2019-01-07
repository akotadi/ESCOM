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
//http://localhost:5050/Detalle?key=manchas
//import java.util.*;
public class ObtenImagen extends HttpServlet {
    String conexionUrl = "jdbc:mysql://localhost:3306/Perracos";
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException {
    BufferedImage bufferedImage;
    ServletOutputStream out = response.getOutputStream();
    String name = request.getParameter("nombre");
    String tabla = request.getParameter("tabla");
    System.out.println("====SELECT imagen FROM ("+tabla+") where nombre='"+name+"'");
    Blob photo = null;
    try { 
        Class.forName("com.mysql.jdbc.Driver");          		
        Connection conex=DriverManager.getConnection(conexionUrl, "root", "" );
        Statement statement=conex.createStatement();
        ResultSet rs=statement.executeQuery(
            "SELECT imagen FROM "+tabla+" where nombre='"+name+"'");
        if(rs.next()) {
            byte barray[] = rs.getBytes(1);
            response.setContentType("image/jpg");
            out=response.getOutputStream();
            out.write(barray);
            out.flush(); out.close();
        }
    } catch(Exception fnfe1){
         System.out.println(fnfe1);
    }   
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        doGet(request, response);
    }
}



