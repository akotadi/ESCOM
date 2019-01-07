import java.io.*;

class SumaDato
{
    public static void main(String args[]) throws IOException
    {
    StringBuffer cadena ;
    String entrada ;
    char ch ;
    int a ,b ;

        System.out.print("Numero A> ");
        cadena = new StringBuffer(50);
        while( (ch = (char)System.in.read()) != '\n' )
            cadena.append(ch);
        entrada = cadena.toString().substring(0,cadena.length() - 1);
        a = Integer.parseInt(entrada.toString()); 

        System.out.print("Numero B> ");
        cadena = new StringBuffer(50);
        while( (ch = (char)System.in.read()) != '\n' )
            cadena.append(ch);
        entrada = cadena.toString().substring(0,cadena.length() - 1);
        b = Integer.parseInt(entrada.toString()); 

        System.out.println("La Suma es: " + (a + b));
    }
}