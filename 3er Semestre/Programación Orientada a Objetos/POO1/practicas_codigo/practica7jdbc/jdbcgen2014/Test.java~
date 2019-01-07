

import java.util.Hashtable;

public class Test {
private static final long serialVersionUID = 1L;
	
public static void main (String []args) throws Exception{
		
	Control control = new Control("com.mysql.jdbc.Driver", 
        "jdbc:mysql://localhost:3306/test", "root", "gatito");
	PerroBean p1=new 
PerroBean("Beethoven", "Cooker", 2, "M");
	control.insertar(p1, "perro") ;
	PerroBean p2=new 
PerroBean("Pulgoso", "Sharpei", 3, "M");
	control.insertar(p2, "perro") ;
	//control.eliminar("Capulin");
		
	p1.setRaza("Pomeranio");
        //p1.setNombre("solovino");
	control.actualizar(p1, "nombre","perro");
        //control.eliminar(args[0],args[1], "perro");
	control.consulta("*");
	}
}
