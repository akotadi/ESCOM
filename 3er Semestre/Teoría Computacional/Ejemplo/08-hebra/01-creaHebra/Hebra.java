class HebraThread extends Thread
{
String nombre;

     HebraThread(String _nombre)
     {
         nombre = _nombre;
     }
     public void run()
     {
         for(int i=0; i < 4; i++)
         {
             System.out.println(nombre + "  " +i);
             try
             {
                 sleep((int)Math.floor(Math.random() * 2000) + 100);
             }
             catch (InterruptedException e)
             { }
         }
     }
}
class HebraRunnable implements Runnable
{
String nombre ;

     HebraRunnable(String _nombre)
     {
         nombre = _nombre;
     }
     public void run()
     {
         for(int i=0; i < 4; i++)
         {
             System.out.println(nombre + "  " +i);
             try
             {
                 Thread.sleep((int)Math.floor(Math.random() * 500) + 100);
             }
             catch (InterruptedException e)
             { }
         }
     }
}

class Hebra
{
    public static void main(String args[] )
    {
    HebraThread procesoA = new HebraThread("A");
    HebraThread procesoB = new HebraThread("B");
    Thread procesoC = new Thread(new HebraRunnable("C"));
    Thread procesoD = new Thread(new HebraRunnable("D"));

        procesoA.start();
        procesoB.start();
        procesoC.start();
        procesoD.start();
    }
}
