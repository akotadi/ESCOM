/** Intervalo.java 

    CALCULO DEL INTERVALO DE TIEMPO EN NANOSEGUNDOS

2006 07 Jesus Olivares
*/

class Intervalo
{
    public static void main(String args[])
    {
    long inicio ,fin ;
        
        inicio = System.nanoTime();
        for( int i = 0; i < 1000000; i++ )
        ;
        fin = System.nanoTime();
        
        System.out.println("Inicio "+ inicio ); 
        System.out.println("Fin "+ fin ); 
        System.out.println("\nDuracion "+ (fin - inicio) ); 
    }
}