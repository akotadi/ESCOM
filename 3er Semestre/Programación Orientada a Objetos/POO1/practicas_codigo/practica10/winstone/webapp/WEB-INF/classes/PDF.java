import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PDF extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("application/pdf");
	response.setContentType("text/plain");
/*
        response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
        response.setHeader("Cache-Control", "max-age=0");
        response.setHeader("Content-disposition",
                "attachment; filename=oscar.pdf");*/
        //ServletOutputStream stream = response.getOutputStream();
String fileName="/servlets/images/mensaje.txt";

    ServletContext sc = getServletContext();
    String filename = sc.getRealPath(fileName);
    File file = new File(filename);
    response.setContentLength((int)file.length());

    FileInputStream in = new FileInputStream(file);
    OutputStream out = response.getOutputStream();
    
    byte[] buf = new byte[1024];
    int count = 0;
    while ((count = in.read(buf)) >= 0) {
        out.write(buf, 0, count);
    }
    in.close();
    out.close();


/*
        OutputStream out= response.getOutputStream();
        FileInputStream input = new FileInputStream("/home/rtecla/Escritorio/POO1/practicas/practica10/webapp/WEB-INF/classes/oscar.pdf");
        
        BufferedInputStream buf = new BufferedInputStream(input);
        byte[] buffer = new byte[100];
        int sizeRead = 0;
        while ((sizeRead = input.read(buffer)) >= 0) { 
            out.write(buffer, 0, sizeRead); 
        }*/
       
/*
    FileInputStream in = new FileInputStream(file);
    OutputStream out = response.getOutputStream();
    byte[] buf = new byte[1024];
    int count = 0;
    while ((count = in.read(buf)) >= 0) {
        out.write(buf, 0, count);
    }
    in.close();
    out.close();*/
/*
        int readBytes = 0;
        while ((readBytes = buf.read()) != -1) {
            out.write(readBytes);
        }*/
/*
         InputStream in = connection.getInputStream(); //conectando para descargar
        OutputStream out = new DataOutputStream(response.getOutputStream());
        byte[] buffer = new byte[BUFFER_SIZE];
        int sizeRead = 0;
        while ((sizeRead = in.read(buffer)) >= 0) { //leyendo del host
            out.write(buffer, 0, sizeRead); //escribiendo para el navegador
        }*/

        out.flush();
        out.close();
  }
}
