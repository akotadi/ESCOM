import java.io.*;
import java.util.*;

class SumaTexto
{
// CONSTANTES (DEBEN SER final Y static)
final static String ARCHIVO_ENTRADA = "entrada.dat";
final static String ARCHIVO_SALIDA = "salida.dat";

    public static void main(String args[]) throws IOException
    {
    BufferedReader entrada = new BufferedReader(new FileReader(ARCHIVO_ENTRADA));
    BufferedWriter salida = new BufferedWriter(new FileWriter(ARCHIVO_SALIDA,false));
    // false es para borrar cualquier contenido anterior en un archivo de texto
    String buffer ;
    int a = 0,b ;

        System.out.print("Leyendo datos de: " + ARCHIVO_ENTRADA +
          "\nResultado en: " + ARCHIVO_SALIDA);

        // LEE UN DATO DEL ARCHIVO DE ENTRADA
        buffer = entrada.readLine();
        StringTokenizer campo = new StringTokenizer(buffer,",");
        int n=0;

        while (campo.hasMoreElements())
        {
                   System.out.println("\n CAMPO " + ++n +": "+campo.nextElement());
                if( n == 0 )
                   a = Integer.parseInt(buffer);

        }


        // LEE EL SEGUNDO DATO DEL ARCHIVO DE ENTRADA
        buffer = entrada.readLine();
        b = Integer.parseInt(buffer);

        // ESCRIBE EL RESULTADO DE LA SUMA EN EL ARCHIVO DE SALIDA
        salida.write("La Suma es: " + (a + b));

        // CIERRA LOS ARCHIVOS
        entrada.close();
        salida.close();
    }
}