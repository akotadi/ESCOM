import java.net.*;
import java.io.*;
import java.util.*;

public class jhttp extends Thread {

  Socket theConnection;
  static File docroot;
  static String indexfile = "index.html";
  
  public jhttp(Socket s) {
    theConnection = s;
  }

  public static void main(String[] args) {

    int thePort;
    ServerSocket ss;

    // get the Document root
    try {
      docroot = new File(args[0]);
    }
    catch (Exception e) {
      docroot = new File(".");
    }
    
    // set the port to listen on
    try {
      thePort = Integer.parseInt(args[1]);
      if (thePort < 0 || thePort > 65535) thePort = 80;
    }  
    catch (Exception e) {
      thePort = 8080;
    }  
                
    try {
      ss = new ServerSocket(thePort);
      System.out.println("Accepting connections on port " 
        + ss.getLocalPort());
      System.out.println("Document Root:" + docroot);
      while (true) {
        jhttp j = new jhttp(ss.accept());
        j.start();
      }
    }
    catch (IOException e) {
      System.err.println("Server aborted prematurely");
    }
  
  }

  public void run() {
  
    String method;
    String ct;
    String version = "";
    File theFile;
  
    try {
      PrintStream os = new PrintStream(theConnection.getOutputStream());
      DataInputStream is = new DataInputStream(theConnection.getInputStream());
      String get = is.readLine();
      StringTokenizer st = new StringTokenizer(get);
      method = st.nextToken();
      if (method.equals("GET")) {
        String file = st.nextToken();
        if (file.endsWith("/")) file += indexfile;
        ct = guessContentTypeFromName(file);
        if (st.hasMoreTokens()) {
          version = st.nextToken();
        }
        // loop through the rest of the input lines 
        
        while ((get = is.readLine()) != null) {
          if (get.trim().equals("")) break;        
        }

        try {
          theFile = new File(docroot, file.substring(1,file.length()));
          FileInputStream fis = new FileInputStream(theFile);
          byte[] theData = new byte[(int) theFile.length()];
          // need to check the number of bytes read here
          fis.read(theData);
          fis.close();

          if (version.startsWith("HTTP/")) {  // send a MIME header
            os.print("HTTP("+new Date()+")\r\n");
            Date now = new Date();
            os.print("Date: " + now + "\r\n");
            os.print("Server: jhttp 1.0\r\n");
            os.print("Content-length: " + theData.length + "\r\n");
            os.print("Content-type: " + ct + "\r\n\r\n");
          }  // end try
          
          // send the file
          os.write(theData);
          os.close();
        }  // end try
        catch (IOException e) {  // can't find the file
          if (version.startsWith("HTTP/")) {  // send a MIME header
            os.print("HTTP/1.0 404 File Not Found\r\n");
            Date now = new Date();
            os.print("Date: " + now + "\r\n");
            os.print("Server: jhttp 1.0\r\n");
            os.print("Content-type: text/html" + "\r\n\r\n");
          } 
          os.println("<HTML><HEAD><TITLE>File Not Found</TITLE></HEAD>");
          os.println("<BODY><H1>HTTP Error 404: Archivo Not Found</H1></BODY></HTML>");
          os.close();
        }
      }
      else {  // method does not equal "GET"
        if (version.startsWith("HTTP/")) {  // send a MIME header
          os.print("HTTP/1.0 501 Not Implemented\r\n");
          Date now = new Date();
          os.print("Date: " + now + "\r\n");
          os.print("Server: jhttp 1.0\r\n");
          os.print("Content-type: text/html" + "\r\n\r\n"); 
        }       
        os.println("<HTML><HEAD><TITLE>Not Implemented</TITLE></HEAD>");
        os.println("<BODY><H1>HTTP Error 501: Not Implemented</H1></BODY></HTML>");
        os.close();
      }
    }
    catch (IOException e) {
    
    }
    try {
      theConnection.close();
    }
    catch (IOException e) {
    }

  }
  
  public String guessContentTypeFromName(String name) {
    if (name.endsWith(".html") || name.endsWith(".htm")) return "text/html";
    else if (name.endsWith(".txt") || name.endsWith(".java")) return "text/plain";
    else if (name.endsWith(".gif") ) return "image/gif";
    else if (name.endsWith(".class") ) return "application/octet-stream";
    else if (name.endsWith(".jpg") || name.endsWith(".jpeg")) return "image/jpeg";
    else return "text/plain";
  }

}
