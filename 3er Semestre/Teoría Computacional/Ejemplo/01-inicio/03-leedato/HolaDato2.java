import java.io.*;

class HolaDato2
{
    public static void main(String args[]) throws IOException
    {
    BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));


        // DESPLEGA UN INDICADOR PARA LEER UN DATO
        System.out.print("> ");

        // LEE UNA cadena DE LA CONSOLA
        String cadena = consola.readLine();

        // DESPLEGA LA cadena LEIDA
        System.out.println("Hola " + cadena);
    }
}
