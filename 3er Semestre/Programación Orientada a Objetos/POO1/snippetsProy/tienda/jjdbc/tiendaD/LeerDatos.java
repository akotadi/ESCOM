

import java.util.Hashtable;

public class LeerDatos {
	private static final long serialVersionUID = 1L;
	/*ControladorSQL control = new ControladorSQL();*/
	
	public static void main (String []args) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Controlador control = new Controlador();
		DiscoVO d1=new DiscoVO("rolas jorsie","punketo", 22.0f, (short)6);
		//control.insertar(d1) ;
		DiscoVO d2=new DiscoVO("best of madonna","pop",1.0f, (short)1.0);
		//control.insertar(d2) ;
		//control.eliminar("best of maddona");
		
		d1.setGenero("rapero");
		control.actualizar(d1);
		control.consulta("r%");
	}
}
