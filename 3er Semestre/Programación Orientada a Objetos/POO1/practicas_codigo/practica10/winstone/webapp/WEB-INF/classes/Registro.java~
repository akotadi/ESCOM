import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

	public class Registro extends HttpServlet{
	String registros;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException{
	PrintWriter out=res.getWriter();
	res.setContentType("text/plain");
	String Nombre=req.getParameter("Nombre_nuevo_usuario");
	String Apellidos=req.getParameter("Apellidos_nuevo_usuario");
	String Nick=req.getParameter("nick");
	String Correo=req.getParameter("e-mail");
	String Pass=req.getParameter("Contraseña");
        registros=Nombre+Apellidos+Nick+Correo+Pass;
	out.println("Datos capturados: "+registros);
        if(!Nombre.equals("") && !Apellidos.equals("") && !Nick.equals("") && 
           !Correo.equals("") && !Pass.equals("")){
         	FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("registro.txt");
            pw = new PrintWriter(fichero);
            pw.println(Nick+","+Pass);
            out.println(Nick+","+Pass);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
			out.println("Exitoso");
       }
		else
			out.println("Fallido");
	}
}
