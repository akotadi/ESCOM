class HiloThread extends Thread
{
String nombre;

     HiloThread(String _nombre)
     {
         nombre = _nombre;
     }
     public void run()
     {
         for(int i=0; i < 4; i++)
         {
             System.out.println(nombre + "  " +i);
             yield(); // LIBERA VOLUNTARIAMENTE EL PROCESADOR
         }
     }
}
class HiloRunnable implements Runnable
{
String nombre ;

     HiloRunnable(String _nombre)
     {
         nombre = _nombre;
     }
     public void run()
     {
         for(int i=0; i < 4; i++)
         {
             System.out.println(nombre + "  " +i);
             Thread.yield(); // LIBERA VOLUNTARIAMENTE EL PROCESADOR
         }
     }
}

class Prioridad
{
    public static void main(String args[] )
    {
    HiloThread procesoA = new HiloThread("A");
    HiloThread procesoB = new HiloThread("B");
    Thread procesoC = new Thread(new HiloRunnable("C"));
    Thread procesoD = new Thread(new HiloRunnable("D"));

        // ASIGNA PRIORIDADES, procesoC TIENE PRIORIDAD = 5 POR OMISION
        // SE ATIENDE PRIMERO A LA PRIORIDAD 10
        procesoA.setPriority(1);
        procesoB.setPriority(10);
        procesoD.setPriority(8);

        procesoA.start();
        procesoB.start();
        procesoC.start();
        procesoD.start();
    }
}
