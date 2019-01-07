import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendPdf extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String fileName = "/servlets/oscar.pdf";
    ServletOutputStream stream = null;
    BufferedInputStream buf = null;
    try {
      stream = response.getOutputStream();
      ServletContext sc = getServletContext();
      String filename = sc.getRealPath(fileName);
      File pdf = new File(filename);
      response.setContentLength((int) pdf.length());

      response.setContentType("application/pdf");
      response.addHeader("Content-Disposition", "attachment; filename="
          + fileName);
		// setting some extra response headers to sooth browser issues
      response.setHeader("Expires", "0");
      response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
      response.setHeader("Pragma", "public");
     
      FileInputStream input = new FileInputStream(pdf);
      buf = new BufferedInputStream(input);
      int readBytes = 0;

      while ((readBytes = buf.read()) != -1)
          stream.write(readBytes);
    } catch (IOException ioe) {
      throw new ServletException(ioe.getMessage());
    } finally {
      if (stream != null)
        stream.close();
      if (buf != null)
        buf.close();
    }
  }
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
