import java.io.Serializable;

public class Articulo implements Serializable{
	int id;
	String nombre;
	String descripcion;
	double precio;
	int existencia;
	double promocion;
	String imagen;

	void Imprime(){
		System.out.println("Articulo: " + id);
		System.out.println("\t" + nombre);
		System.out.println("\t" + descripcion);
		System.out.println("\t" + precio);
		System.out.println("\t" + existencia);
		System.out.println("\t" + promocion);
		System.out.println("\t" + imagen);
	}

}