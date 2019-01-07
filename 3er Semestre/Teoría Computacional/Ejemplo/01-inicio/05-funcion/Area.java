import java.io.*;

class Area
{
    public static void main(String args[]) throws IOException
    {
    BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
    double radio ;

        System.out.print("radio: ");
    String cadena = consola.readLine();
        radio = Integer.parseInt(cadena);
        System.out.println("El Area del circulo es: " + (Math.PI * radio * radio));
    }
}
