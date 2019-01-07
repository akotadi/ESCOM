/* Directorio.java
 *
 * LISTA UN DIRECTORIO CON SUS ARCHIVOS
 *
 * 14 abril 2005
*/
import java.io.*;
import java.util.*;

public class Directorio
{
    public static String getUnidad(long _talla)
    {
    String unidad = " bytes";

        if( _talla > 1023)
        {
            _talla = _talla / 1024;
            unidad = " Kb";
            if( _talla > 1023)
            {
                _talla = _talla / 1024;
                unidad = " Mb";
            }
        }
        return "" + _talla + unidad;
    }
    public static void revisaDir(String _dir)
    {
    File subDir = new File(_dir ,".");
    String subLista[] = subDir.list();

        for( int i=0; i < subLista.length; i++ )
        {
        File f = new File(_dir ,subLista[i]);
            if( f.isDirectory())
            {
                System.out.println("DIR " + subLista[i]);
                revisaDir(f.getAbsolutePath());
                System.out.println(" ");
            }
            else
            {
                System.out.println(""+ new Date(f.lastModified())+ " " +
                    getUnidad(f.length()) + " " +f.getName());
            }
        }
    }

    public static void main(String argv[])
    {
    BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
    String nomDir = ".";

        System.out.print("Directorio> ");
        try {
        nomDir = consola.readLine();
        }
        catch(IOException e) {System.out.println("ERROR");}
    File raiz = new File(nomDir ,".");
    String lista[] = raiz.list();

        for( int i=0; i < lista.length; i++ )
        {
        File f = new File(nomDir ,lista[i]);
            if( f.isDirectory())
            {
                System.out.println("DIR " + lista[i]);
                revisaDir(f.getAbsolutePath());
                System.out.println(" ");
            }
            else
            {
                System.out.println(""+ new Date(f.lastModified())+ " " +
                getUnidad(f.length()) + " " +f.getName());
            }
        }
    }
}