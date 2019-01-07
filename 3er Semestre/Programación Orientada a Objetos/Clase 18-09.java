/*El bloque try debe escribirse en un mundo ideal, los errores deben aparecer casi nunca
En catch se pone el manejo del error.
La excepción recorre la pila de llamadas en sentido inverso hasta que encuentra un manejador de excepciones.
Cuando hay menos cpu's que procesos, debe simularse la concurrencia (esto es la multitarea).
A cada proceso se le da un quantum (unidad de tiempo para el proceso), normalmente hay un planificador,
cuando se le quita el cpu a un proceso para dárselo a otro, se le llama cambio de contexto.
El cuerpo del hilo es lo que está dentro del método Runnable.
La clase Printer debe implementar el método Runnable, la clase que lo implementa debe describir el método abstracto run()

public interface Runnable{
	void run();
}

No programan Orientado a Objetos si no hay abstracción (generalización) y 
*/

public class Printer implements
Runnable{
	Thread hilo;
	string string;
	int count;
	int sleepTime;
	public Printer(String s, int howMany, int sleep){
		count = howMany;
		String = s;
		sleepTime = sleep;
		hilo = new Thread(this);
		hilo.start();
	}

	/*El constructor de Printer inicializa tres variables de instancia con los parámetros recibidos,
	mientras que Thread (hilo de control) se crea, en este momento está en el estado (lo que está haciendo y lo que puede hacer)
	recién nacido. Sólo son eligibles los hilos que están en el estado listo (método start).
	Debemos pasarle un objeto de "una" (generalización) clase que implementa la interfaz Runnable, al constructor de Thread */

	public void run(){
		while(count->0){
			System.out.println(String);
			try{
				hilo.sleep(sleepTime); // esta llamada puede fallar
			}
			catch(Exception e){
				return; // muerte violenta del hilo
			}
		}
	} // muerte natural del run

	public static void main(String[] args) {
		// Creamos dos objetos tipo Printer
		new Printer("Ping",5,500); // tiempo dado en ms
		new Printer("Pong",5,300); // no podemos hacer suposiciones sobre las velocidades, para sincronizarlos deberíamos de programar más
	}
}

/* Los programas en java serán multihilos salvo que sea muy básico.

Java es totalmente orientado a objetos, es multihilos, es gráfico (GUI), se puede programar en red (socket, RMI, sevlets), JDBC (base de datos), 3D (J3D y Jogl)
	Por ello Java es un lenguaje y una tecnología.
*/