class HebraThreadDemonio extends Thread
{
String nombre;

     HebraThreadDemonio(String _nombre)
     {
         nombre = _nombre;
         setDaemon(true); // INDICA QUE SE TRATA DE UN PROCESO Daemon
     }
     public void run()
     {
         for(int i=0; i < 5; i++)
         {
             System.out.println(nombre + "  " +i);
             try
             {
                 sleep((int)Math.floor(Math.random() * 3000) + 100);
             }
             catch (InterruptedException e)
             { }
         }
     }
}

class HebraDemonio
{
    public static void main(String args[] )
    {
    HebraThreadDemonio procesoA = new HebraThreadDemonio("A");
    HebraThreadDemonio procesoB = new HebraThreadDemonio("b");
    HebraThreadDemonio procesoC = new HebraThreadDemonio("c");
    HebraThreadDemonio procesoD = new HebraThreadDemonio("D");

        procesoA.start();
        procesoB.start();
        procesoC.start();
        procesoD.start();

        // LOS DEMONIOS EXISTEN MIENTRAS EXISTE EL PROCESO QUE LOS LANZA
        try
        {
            System.out.println("Actividad de demonios durante 5 seguntos");
            Thread.sleep(5000);
        }
        catch(Exception e)
        {}
        // LOS DEMONIOS PUEDEN TERMINAR ANTES DEL LANZADOR
        // PERO SI EL LANZADOR TERMINA, ENTONCES LOS DEMONIOS DEBEN TERMINAR
    }
}
