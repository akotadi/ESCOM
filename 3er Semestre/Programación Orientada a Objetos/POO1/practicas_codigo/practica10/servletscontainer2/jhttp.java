import java.net.*;
import java.io.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
public class jhttp extends Thread {

  Socket theConnection;
  static File docroot;
  static String indexfile = "index.html";
  private HttpRequest request;
  private HttpRequestLine requestLine = new HttpRequestLine(); 
  private HttpResponse response;
  protected StringManager sm =
  StringManager.getManager("ex03.pyrmont.connector.http");

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
    String file="", version = "";
    File theFile;
  
    try {
      PrintStream os = new PrintStream(theConnection.getOutputStream());
      DataInputStream is = new DataInputStream(theConnection.getInputStream());
      SocketInputStream input = new SocketInputStream(
                                         theConnection.getInputStream(), 2048);
      OutputStream output = theConnection.getOutputStream();
        // create HttpRequest object and parse
        request = new HttpRequest(input);
        
        response = new HttpResponse(output);
        response.setRequest(request);
        
        response.setHeader("Server", "Pyrmont Servlet Container");
        parseRequest(input, output);
        //System.out.println("Request.getUri="+request.getRequestURI());
        parseHeaders(input);
        //System.out.println("Request.contentType="+request.getContentType());
        if (request.getRequestURI().startsWith("/servlet/")) {
          //System.out.println("if=("+file+")");
          ServletProcessor processor = new ServletProcessor();
          processor.process(request, response);
          output.close();
          return;
        }
	try {        
          file=request.getRequestURI();
	  file=file.substring(1,file.length());
          //System.out.println("protocolillojillo"+request.getProtocol()+"file "+file);
          ct = guessContentTypeFromName(file);
          theFile = new File(docroot, file);
          FileInputStream fis = new FileInputStream(theFile);
          byte[] theData = new byte[(int) theFile.length()];
          // need to check the number of bytes read here
          fis.read(theData);
          fis.close();
          
          if (request.getProtocol().startsWith("HTTP/")) {  // send a MIME header
             System.out.println("IFIF protocolillo"+request.getProtocol());
            os.print("HTTP/1.0 200 OK\r\n");
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
    catch (Exception e) {    
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
    else if (name.startsWith("/servlet/")) return "/servlet/";
    else return "text/plain";
  }
  private void parseHeaders(SocketInputStream input)
    throws IOException, ServletException {
    while (true) {
      HttpHeader header = new HttpHeader();;
      // Read the next header
      input.readHeader(header);
      if (header.nameEnd == 0) {
        if (header.valueEnd == 0) {
          return;
        }
        else {
          throw new ServletException
            (sm.getString("httpProcessor.parseHeaders.colon"));
        }
      }
      String name = new String(header.name, 0, header.nameEnd);
      String value = new String(header.value, 0, header.valueEnd);
      request.addHeader(name, value);

      // do something for some headers, ignore others.
      if (name.equals("cookie")) {
        Cookie cookies[] = RequestUtil.parseCookieHeader(value);
        for (int i = 0; i < cookies.length; i++) {
          if (cookies[i].getName().equals("jsessionid")) {
            // Override anything requested in the URL
            if (!request.isRequestedSessionIdFromCookie()) {
              // Accept only the first session id cookie
              request.setRequestedSessionId(cookies[i].getValue());
              request.setRequestedSessionCookie(true);
              request.setRequestedSessionURL(false);
            }
          }
          request.addCookie(cookies[i]);
        }
      }
      else if (name.equals("content-length")) {
        int n = -1;
        try {
          n = Integer.parseInt(value);
        }
        catch (Exception e) {
          throw new ServletException(sm.getString("httpProcessor.parseHeaders.contentLength"));
        }
        request.setContentLength(n);
      }
      else if (name.equals("content-type")) {
        request.setContentType(value);
      }
    } //end while
  }
  private void parseRequest(SocketInputStream input, OutputStream output)
    throws IOException, ServletException {
    // Parse the incoming request line
    input.readRequestLine(requestLine);
    String method =
      new String(requestLine.method, 0, requestLine.methodEnd);
    System.out.println("metodo ="+ method);
    String uri = null;
    String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);
    System.out.println("protocol ="+ protocol);
    System.out.println("1request.uri ="+ new String(requestLine.uri));
// Validate the incoming request line
    if (method.length() < 1) {
      throw new ServletException("Missing HTTP request method");
    }
    else if (requestLine.uriEnd < 1) {
      throw new ServletException("Missing HTTP request URI");
    }
// Parse any query parameters out of the request URI
    int question = requestLine.indexOf("?");
    System.out.println("2request.uri ="+ new String(requestLine.uri)+" q= "+question);
    if (question >= 0) {
      request.setQueryString(new String(requestLine.uri, question + 1,
        requestLine.uriEnd - question - 1));
      uri = new String(requestLine.uri, 0, question);
	System.out.println("IF request.uri ="+ new String(requestLine.uri, question + 1,
        requestLine.uriEnd - question - 1));
    }
    else {
      System.out.println("11 ELSE request.uri ="+ new String(requestLine.uri)+"r="+request);
      request.setQueryString(null);
      System.out.println("22** ELSE request.uri ="+ new String(requestLine.uri)+"r="+request);
      uri = new String(requestLine.uri, 0, requestLine.uriEnd);
      
    }
    
    System.out.println("uri ="+ uri);
// Checking for an absolute URI (with the HTTP protocol)
    if (!uri.startsWith("/")) {
      int pos = uri.indexOf("://");
      // Parsing out protocol and host name
      if (pos != -1) {
        pos = uri.indexOf('/', pos + 3);
        if (pos == -1) {
          uri = "";
        }
        else {
          uri = uri.substring(pos);
        }
      }
    }
 // Parse any requested session ID out of the request URI
    String match = ";jsessionid=";
    int semicolon = uri.indexOf(match);
    if (semicolon >= 0) {
      String rest = uri.substring(semicolon + match.length());
      int semicolon2 = rest.indexOf(';');
      if (semicolon2 >= 0) {
        request.setRequestedSessionId(rest.substring(0, semicolon2));
        rest = rest.substring(semicolon2);
      }
      else {
        request.setRequestedSessionId(rest);
        rest = "";
      }
      request.setRequestedSessionURL(true);
      uri = uri.substring(0, semicolon) + rest;
    }
    else {
      request.setRequestedSessionId(null);
      request.setRequestedSessionURL(false);
    }
// Normalize URI (using String operations at the moment)
    String normalizedUri = normalize(uri);
    // Set the corresponding request properties
    ((HttpRequest) request).setMethod(method);
    request.setProtocol(protocol);
    if (normalizedUri != null) {
      ((HttpRequest) request).setRequestURI(normalizedUri);
    }
    else {
      ((HttpRequest) request).setRequestURI(uri);
    }
    if (normalizedUri == null) {
      throw new ServletException("Invalid URI: " + uri + "'");
    }
  }
  protected String normalize(String path) {
    if (path == null)
      return null;
    // Create a place for the normalized path
    String normalized = path;

    // Normalize "/%7E" and "/%7e" at the beginning to "/~"
    if (normalized.startsWith("/%7E") || normalized.startsWith("/%7e"))
      normalized = "/~" + normalized.substring(4);

    // Prevent encoding '%', '/', '.' and '\', which are special reserved
    // characters
    if ((normalized.indexOf("%25") >= 0)
      || (normalized.indexOf("%2F") >= 0)
      || (normalized.indexOf("%2E") >= 0)
      || (normalized.indexOf("%5C") >= 0)
      || (normalized.indexOf("%2f") >= 0)
      || (normalized.indexOf("%2e") >= 0)
      || (normalized.indexOf("%5c") >= 0)) {
      return null;
    }

    if (normalized.equals("/."))
      return "/";

    // Normalize the slashes and add leading slash if necessary
    if (normalized.indexOf('\\') >= 0)
      normalized = normalized.replace('\\', '/');
    if (!normalized.startsWith("/"))
      normalized = "/" + normalized;

    // Resolve occurrences of "//" in the normalized path
    while (true) {
      int index = normalized.indexOf("//");
      if (index < 0)
        break;
      normalized = normalized.substring(0, index) +
        normalized.substring(index + 1);
    }

    // Resolve occurrences of "/./" in the normalized path
    while (true) {
      int index = normalized.indexOf("/./");
      if (index < 0)
        break;
      normalized = normalized.substring(0, index) +
        normalized.substring(index + 2);
    }

    // Resolve occurrences of "/../" in the normalized path
    while (true) {
      int index = normalized.indexOf("/../");
      if (index < 0)
        break;
      if (index == 0)
        return (null);  // Trying to go outside our context
      int index2 = normalized.lastIndexOf('/', index - 1);
      normalized = normalized.substring(0, index2) +
        normalized.substring(index + 3);
    }
    // Declare occurrences of "/..." (three or more dots) to be invalid
    // (on some Windows platforms this walks the directory tree!!!)
    if (normalized.indexOf("/...") >= 0)
      return (null);

    // Return the normalized path that we have completed
    return (normalized);

  }
}
