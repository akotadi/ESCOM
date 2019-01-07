public class quicksort
{
   static void qsort(int a[], int inicio, int ultimo)
   {
   int abajo = inicio;  int arriba = ultimo;  int medio;

      if ( ultimo > inicio)
      {
         // UBICA UN ELEMENTO MEDIO 
		 medio = a[ ( inicio + ultimo ) / 2 ];
         // ITERA HASTA QUE HAYA CRUCE DE INDICES
         while( abajo <= arriba )
         {
             // SALTA LOS ELEMENTOS MENORES QUE EL MEDIO DE ABAJO HACIA ARRIBA
			 while( ( abajo < ultimo ) && ( a[abajo] < medio ) )
               ++abajo;
             // SALTA LOS ELEMENTOS MAYORES QUE EL MEDIO DE ARRIBA HACIA ABAJO
			 while( ( arriba > inicio ) && ( a[arriba] > medio ) )
               --arriba;
			 // SI NO HAY CRUCE DE INDICES INTERCAMBIA LOS ELEMENTOS
			 if( abajo <= arriba ) 
            {
               int tmp = a[abajo]; 
               a[abajo] = a[arriba];
               a[arriba] = tmp;
			   ++abajo;
               --arriba;
            }
         }
         // SI EL INICIO NO HA LLEGADO AL INICIO
		 if( inicio < arriba )
            qsort( a, inicio, arriba );

		 // SI ABAJO NO HA LLEGADO AL ULTIMO
         if( abajo < ultimo )
            qsort( a, abajo, ultimo );
      }
   }

   
public static void main(String[] args)
{
int arreglo[] = new int [100];

    System.out.print("Generando n£meros para ordenar...");
    for( int i = 0; i < arreglo.length ; i++ )
        arreglo[i] = (int)(Math.random() * 100.0);

    System.out.println("listo");
    System.out.println("ANTES DE ORDENAR:");

    for( int i = 0; i < arreglo.length; i++ )
         System.out.print(arreglo[i] + " ");
    
    qsort(arreglo ,0 ,arreglo.length - 1);

    System.out.println("\nPresiona ENTER para ver el resultado"); 
    try
    {
       while( (char)System.in.read() != '\n')
       ;
    }
    catch(Exception e)
    {
    }

    System.out.println("DESPUES DE ORDENAR " + arreglo.length + " DATOS");
    for( int i = 0; i < arreglo.length; i++ )
         System.out.print(arreglo[i] + " ");
    System.out.println("\n\nPresiona ENTER para concluir"); 
    try
    {
       while( (char)System.in.read() != '\n')
       ;
    }
    catch(Exception e)
    {
    }
  }
}
