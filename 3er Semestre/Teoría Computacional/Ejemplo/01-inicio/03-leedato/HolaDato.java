/* HolaDato.java


2003 07 Jesus Olivares
*/

import java.io.*;

class HolaDato
{
    public static void main(String args[]) throws IOException
    {
    StringBuffer cadena ;
    char ch ;

        // DESPLEGA UN INDICADOR DE DATO
        System.out.print("> ");

        // INICIALIZA cadena
        cadena = new StringBuffer(50);

        // LEE CARACTER POR CARACTER LA cadena
        while( (ch = (char)System.in.read()) != '\n' )
            cadena.append(ch);

        // DESPLEGA EL CONTENIDO DE cadena
        System.out.println("Hola " + cadena);
    }
}
