import java.io.*;

class GeneraTabla
{
final static String ARCHIVO_SALIDA = "salida.html";

    public static void main(String args[]) throws IOException
    {
    BufferedWriter salida = new BufferedWriter(new FileWriter(ARCHIVO_SALIDA,false));

        salida.write("<table border=1>" );
        salida.write("<tr>" );
        salida.write("<td>TERMINO 1" ); salida.write("</td>" );
        salida.write("<td>palabra1,palabra2,palabra3" ); salida.write("</td>" );
        salida.write("<td>relacion,objeto;relacion,objeto" ); salida.write("</td>" );
        salida.write("</tr>" );

        salida.write("<tr>" );
        salida.write("<td>TERMINO 2" ); salida.write("</td>" );
        salida.write("<td>palabra1,palabra2,palabra3" ); salida.write("</td>" );
        salida.write("<td>relacion,objeto;relacion,objeto" ); salida.write("</td>" );
        salida.write("</tr>" );

        salida.write("</table>" );




        salida.close();
    }
}