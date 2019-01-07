import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

	public class Login extends HttpServlet{
	String registros;
        String loginv="Albert", passdv="Einstein";
	protected void doGet(HttpServletRequest req, HttpServletResponse resp/*, String registros*/)
			throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/plain");
                  File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
 
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("registro.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;
         if((linea=br.readLine())!=null){
                System.out.println(linea);
		out.println(linea);
                StringTokenizer st = new StringTokenizer(linea,",");
      		loginv = st.nextToken();
                passdv = st.nextToken();
                out.println(loginv);
		out.println(passdv);
                
		String user = req.getParameter("txtUsuario");
		String pass = req.getParameter("txtPassword");
                out.println(user);
		out.println(pass);
		if(loginv.equals(user) && passdv.equals(pass))
			out.println("Aceptado");
		else
			out.println("denegado");
	}
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }		
	}
}
