import java.util.Hashtable;

public class LeerDatos {
private static final long serialVersionUID = 1L;
	
public static void main (String []args) throws Exception{
		
	Controlador control = new Controlador();
	PerroBean p1=new 
PerroBean("Solovino", "Criollo", 2, "M");
	//control.insertar(p1) ;
	PerroBean p2=new 
PerroBean("Capulin", "Boxer", 3, "M");
	//control.insertar(p2) ;
	//control.eliminar("Capulin");
		
	p1.setRaza("Chihuahua");
	control.actualizar(p1);
	control.consulta("Solo%");
	}
}
