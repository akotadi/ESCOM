/* Invoca.java
 *
 *EJEMPLO DE INVOCACIÓN DE OTROS PROGRAMAS DEL SISTEMA OPERATIVO
 *enero 1999
*/
import java.lang.*;

public class Invoca
{
    public static void main(String argv[])
    {
    Runtime r = Runtime.getRuntime();

        try
        {
            r.exec("\\windows\\cdplayer.exe");
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
}