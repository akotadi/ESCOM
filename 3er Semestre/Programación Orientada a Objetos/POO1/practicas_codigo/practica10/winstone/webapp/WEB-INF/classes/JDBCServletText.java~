

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.IOException;
import java.sql.*; 
import java.awt.*;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import java.util.*;


public class JDBCServletText extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {

    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "classicmodels";
    Connection conex=null;
   ServletOutputStream out = response.getOutputStream();
    Blob photo = null;
	try { 
		Class.forName("com.mysql.jdbc.Driver");          		
		conex=DriverManager.getConnection(url+dbName, "root", "gatito" );
		Statement statement=conex.createStatement();
		ResultSet rs=statement.executeQuery("SELECT * FROM PerroImg");
                response.setContentType("text/html");
             	out.println("<HTML>");
    	    	out.println("<HEAD><TITLE>Perros </TITLE></HEAD>");
    		out.println("<BODY>");
                while(rs.next()) 
            {
            
	    
            //
            String dato1=rs.getString(1);
	    String dato2=rs.getString(2);
            String dato3=rs.getString(3);
	    String dato4=rs.getString(4);
            out.println(dato1+"|"+dato2+"|"+dato3+"|"+dato4+"<BR>");
            out.println("<img src= \"http://localhost:8080/servlets/servlet/JDBC?nombre="+dato1+"\"></img>");
            out.println("<A HREF=\"http://localhost:8080/servlets/servlet/Galleta?nombre="+dato1+"\">\n" +
    "<CODE>ShowCookies</CODE>"+ dato1 +"</A>");
	    out.println("|"+dato2+"|"+dato3+"|"+dato4);
            out.flush();
	    }
		out.println("</BODY></HTML>");
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



