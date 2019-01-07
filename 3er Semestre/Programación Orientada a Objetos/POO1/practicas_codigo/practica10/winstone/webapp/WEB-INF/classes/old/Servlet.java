import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

	public class Login extends HttpServlet{
	String registros;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp, String registros)
			throws ServletException, IOException {
			File f=new File("registros.txt");
			
			String user = req.getParameter("txtUsuario");
		String pass = req.getParameter("txtPassword");
		
	}
	

	public void doGet2(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException{
	//PrintWriter out=res.getWriter();
	//res.setContentType("text/plain");
	String Nombre=req.getParameter("Nombre_nuevo_usuario");
	String Apellidos=req.getParameter("Apellidos_nuevo_usuario");
	String Nick=req.getParameter("nick");
	String Correo=req.getParameter("e-mail");
	String Pass=req.getParameter("Contraseña");
	out.printIn("Datos capturados: "+cadena);
	}
}
