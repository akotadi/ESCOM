/** Formato.java 

    FORMATEO DE NUMEROS

2003 08 Jesus Olivares
*/
import java.text.*;

public class Formato
{
    public static void main(String args[])
    {
        System.out.println("Ejemplo del uso de la clase NumberFormat");
    NumberFormat precision4 = NumberFormat.getInstance();
        precision4.setMaximumFractionDigits(4);
    String dato = precision4.format(3.1415926);
        System.out.println(dato);
        dato = precision4.format(3.14);
        System.out.println(dato);
        precision4.setMinimumFractionDigits(4);
        dato = precision4.format(3.1415926);
        System.out.println(dato);
        dato = precision4.format(3.14);
        System.out.println(dato);
		//System.out.println(""+formatoNum(14,3)+"    "+formatoNum(14,5) );
		//   String formatoNum(long numero ,int enteros) {
        //String formateado = String.format("%0"+enteros+"d", numero);
        //return formateado;
        //}
    }
}
