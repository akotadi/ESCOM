import java.io.*;

class SumaTexto
{
// CONSTANTES (DEBEN SER final Y static)
final static String ARCHIVO_ENTRADA = "entrada.dat";
final static String ARCHIVO_SALIDA = "salida.dat";

    public static void main(String args[]) throws IOException
    {
    BufferedReader entrada = new BufferedReader(new FileReader(ARCHIVO_ENTRADA));
    BufferedWriter salida = new BufferedWriter(new FileWriter(ARCHIVO_SALIDA,true));
    String buffer ;
    int a ,b ;

        System.out.print("Leyendo datos de: " + ARCHIVO_ENTRADA +
          "\nResultado en: " + ARCHIVO_SALIDA);
          
        // LEE UN DATO DEL ARCHIVO DE ENTRADA
        buffer = entrada.readLine();
        a = Integer.parseInt(buffer);

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