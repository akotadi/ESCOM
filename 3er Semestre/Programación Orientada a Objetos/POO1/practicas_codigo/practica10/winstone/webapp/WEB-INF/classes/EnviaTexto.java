

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




//import java.util.*;

//http://localhost:5050/servlets/servlet/TEXTO
public class EnviaTexto extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {

       
    response.setContentType("text/plain");

        //String root = getInitParameter("root");

    // Set content size
    //fileName="/servlets/images/"+fileName;
    String fileName="/servlets/images/mensaje.txt";

    ServletContext sc = getServletContext();
    String filename = sc.getRealPath(fileName);
    File file = new File(filename);
    response.setContentLength((int)file.length());

    // Open the file and output streams
    FileInputStream in = new FileInputStream(file);
    OutputStream out = response.getOutputStream();
    //out.println("<html>");
    //out.println("<head><title>Mensaje de el Dia</title></head>");
    //out.println("<body><h1>Mensaje de el Dia</h1>");
    // Copy the contents of the file to the output stream
    byte[] buf = new byte[1024];
    int count = 0;
    while ((count = in.read(buf)) >= 0) {
        out.write(buf, 0, count);
    }
    //out.println("</body></html>");
    in.close();
    out.close();

/*
    } else {
            //out.println("No Parameters, Please enter some");
    }*/

/*
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
	out.println("<html>");
        out.println("<body>");
        out.println("<head>");
        out.println("<title>Mp3 envio</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
    	out.println("<P>");
        out.print("<form action=\"");
        out.print("HelloWorldExample\" ");
        out.println("method=POST>");
        out.println("File:");
        out.println("<input type=text size=20 name=file>");
        out.println("<br>");
        out.println("<input type=submit>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");*/
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        doGet(request, response);
    }
}



