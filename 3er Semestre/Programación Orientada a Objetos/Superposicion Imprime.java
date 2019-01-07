// Superposición de métodos

/* Se le dice superposición de métodos porque el método de la subclase oculta al método de la superclase.
Para que haya superposición basta con que dos métodos tengan el mismo nombre.
*/

public class Imprime{
	int x=0;
	int y=1;
	public void print(){
		System.out.println("x es "+x);
		System.out.println("y es "+y);
	}

	/*
	public void print(){
	super.print();
	System.out.println("z es "+z);
	}

	Necesitamos "super" (palabra reservada) o se volvería recursiva, con esto cambiamos el orden de búsqueda.
	Super ayuda con la resolución de colisión de nombres.
	*/
}

public class SubclaseImprime1 extends Imprime{
	int z=2;
	public static void main(String[] args) {
		SubclaseImprime1 obj = new SubclaseImprime1();
		obj.print();
	}
}

public class SubclaseImprime2 extends Imprime{
	int z=2;
	public static void main(String[] args) {
		System.out.println("x es "+x)
		System.out.println("y es "+y)
		System.out.println("z es "+z)
	}
}

public static void main(String[] args) {
	SubclaseImprime2 obj = new SubclaseImprime2():
	obj.print();
}



// Clases Abstractas

/* Clase abstracta es una clase de la que no se pueden crear instancias directas (definición POO).
En Java se utiliza la palabra reservada abstract.
Una clase es abstracta si contiene al menos un método abstracto.
Un método abstracto es aquel que no tiene implementación.

Los métodos abstractos de las clases abstractas son implementados por alguna subclase no abstracta.

Programación por contratos
Implementar un método abstracto es una obligación de las subclases no abstractas
*/

public abstract class MiClaseAbstracta{
	int unavariable;
	public abstract int unaSubclaseNoAbstracaDebeImplementarse(); // Método abstracto
	public void hasAlgo(){
		// ...
	}
}

public class UnaSubclaseConcreta extends MiClaseAbstracta{
	public int unaSubclaseNoAbstracaDebeImplementarde(){ // La subclase implementa el método abstracto. 
			// La subclase es abstracto por herencia, pero puede evitar ser abstracta implementando el método
		// ...
	}
}



// Interfaces en Java
/* Antes de Java 8 las interfaces sólo podía contener constantes simbólicas y métodos abstractos.
Una interfaz debe ser pública, igual que su contenido.

Los métodos abstractos de las interfaces son implementados
*/
public interface UnaInterfaz{
	public static final int laRespuesta = 42; // final indica que éste es el último valor de una variable, es decir, es una constante simbólica
	public abstract int laVidaEnElUniverso(); // es un método abstracto
	long contaddorDelBingBang = 0; // dado que es interfaz, es una constante simbólica
	long edadDelUniverso(); // método abstracto
	protected int unaConstante; // Es un error
	private int obtenUnEntero(); // Otro error
}


// Antes de Java 8 hay que implementar todos los métodos abstractos de la interfaz
interface Imprimible{
	void imprime();
}

class Lunes implements Imprimible{
	void Imprimible(){
		System.out.println("Lunes");
	}
}



/*
interface Coprofagia{
	void comer();
}

public class Pelotero implement{
	coproFagia{
	public void comer(){
	System.out.println("YOMI YOMI");
	}
	}
}
*/