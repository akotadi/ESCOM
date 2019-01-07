import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//http://localhost:5050/Detalle?key=manchas
public class PerroImgDetails extends HttpServlet {
    String conexionUrl = "jdbc:mysql://localhost:3306/Perracos";
    protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>" +
                    "<head><title>Descripcion del Perro</title>"+
               "<link href='css/estilos.css' rel='stylesheet' type='text/css'/>"+
                    "</head>" +
                    "<body  bgcolor=\"#FFFFFF\">");
        String key = request.getParameter("key");
	try {      		
        if (key != null) {
           Class.forName("com.mysql.jdbc.Driver");          		
           Connection conex=DriverManager.getConnection(conexionUrl, "root", "" );
           Statement statement=conex.createStatement();
           ResultSet rs=statement.executeQuery(
            "SELECT * FROM PerroImg where nombre='"+key+"'");
            rs.next();//poner if
            ResultSetMetaData rmeta = rs.getMetaData();
            int numCols = rmeta.getColumnCount();
            //int edad=Integer.parseInt(rs.getString("edad"));
         
        out.println("<div class='content'>");
        out.println("     <div class='foto'>");
        out.println("         <center>");
        out.println("            <img src= \"http://localhost:5050/obtenimagen?nombre="+rs.getString("nombre")+"&tabla=PerroImg\"/>");
        out.println("        </center>");
        out.println("     <div class='fotoname'>");   
        out.println("     "+ rs.getString("nombre"));
        out.println("     </div>");   
        out.println("     </div>");
        out.println("<br> &nbsp; <br>");
        out.println("<h4>________ </h4>");
                         
          for(int i = 1;  i <= numCols; ++i){	    
               String name = rmeta.getColumnName( i );
	       Object value = rs.getObject(i);
               if(!rmeta.getColumnTypeName(i).equals("BLOB") &&
                  !rmeta.getColumnTypeName(i).equals("LONGBLOB") &&
                  !name.equals("nombre")){
                  String s=value.toString().trim();  
                  out.println(s+ ", &nbsp; &nbsp;");   
               } 
             }
          out.println("<h4>Edad: " +rs.getInt("Edad") + "</h4></p>");
        }
        out.println("</div>");
        out.println("</body></html>");
        out.close();
        } catch(Exception fnfe1){ System.out.println(fnfe1); }	   
    }
}
