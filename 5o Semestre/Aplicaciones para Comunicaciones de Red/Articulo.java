import java.io.Serializable;

public class Articulo implements Serializable{
	int id;
	String nombre;
	String descripcion;
	double precio;
	int existencia;
	double promocion;
	File imagen;
}