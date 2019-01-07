import java.io.*;

class Menu
{
    public static void main(String args[]) throws IOException
    {
    BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
    int opcion = 0; 

        while( opcion != 9 )
        {
       	    System.out.println("MENU\n1 Consulta\n2 Alta\n3 Baja\n4 Cambio\n9 Salir");
            System.out.print("digita tu opcion: ");
        String cadena = consola.readLine();
            opcion = Integer.parseInt(cadena);
            switch(opcion)  
            {
            case 1:
                System.out.println("CONSULTA");
                break;
            case 2:
                System.out.println("ALTA");
                break;
            case 3:
                System.out.println("BAJA");
                break;
            case 4:
                System.out.println("CAMBIO");
                break;
            }

        }
        
    }
}
