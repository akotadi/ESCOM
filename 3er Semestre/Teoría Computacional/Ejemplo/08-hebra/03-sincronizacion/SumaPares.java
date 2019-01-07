class Generador extends Thread
{
// SE USA PRIVATE PARA EVITAR QUE SE ACCESEN DIRECTAMENTE
private Monitor monitor;
private int canal ;

  public Generador(Monitor _monitor ,int _canal )
  {
      monitor = _monitor;
      canal = _canal;
  }

  public void run()
  {
  int numero ;

      // GENERA 10 NUMEROS PARA DEPOSITARLOS EN EL CANAL
      for( int i=0; i < 6; i++ )
      {
           numero = (int)Math.floor(Math.random() * 50);
           System.out.println("->" + canal + " numero " + numero);
           while( monitor.guardaNumero(numero ,canal) == false)
           {
               // MIENTRAS NO SE GUARDE ITERA
           }

           // GENERA UN RETRASO EN EL HILO ACTUAL
           try
           {
               sleep((int)(Math.random() * 2000));
           }
           catch(InterruptedException e)
           {  }
       }

       // GUARDA -1 PARA INDICAR FIN
       while( monitor.guardaNumero(-1 ,canal) == false )
           ;
  }
}

class Sumador extends Thread
{
private Monitor monitor;
private int canal0 ,canal1 ;

  public Sumador( Monitor _monitor )
  {
      monitor = _monitor;
  }

  public void run()
  {
  boolean hayDatos = true;

      while(hayDatos)
      {
          canal0 = -2;
          canal1 = -2;
          do
          {
              if( canal0 == -2 )
                  canal0 = monitor.obtenNumero(0);
              if( canal1 == -2 )
                  canal1 = monitor.obtenNumero(1);
          } while( (canal1 == -2) || (canal0 == -2));
          if( canal0 < 0 || canal1 < 0 )
              hayDatos = false;
          else
              System.out.println("SUMA " + canal0 + " + " + canal1 + " = "
                + (canal0 + canal1));
      }
  }
}

class Monitor
{
private int canal0 ;
private int canal1 ;
private boolean hay0 = false;
private boolean hay1 = false;

  public synchronized int obtenNumero(int _canal)
  {
      if( _canal == 0 )
      {
          if( hay0 == false )
              return -2;
          else
          {
              hay0 = false;
              return canal0;
          }
      }
      if( _canal == 1 )
      {
          if( hay1 == false )
              return -2;
          else
          {
              hay1 = false;
              return canal1;
          }
      }
      return -2;
  }

  public synchronized boolean guardaNumero(int _numero ,int _canal)
  {
      if( _canal == 0 && hay0 == false )
      {
          canal0 = _numero;
          hay0 = true;
          return true;
      }
      else
          if( _canal == 1 && hay1 == false )
          {
              canal1 = _numero;
              hay1 = true;
              return true;
          }
      notify();
      return false;
  }
}

class SumaPares
{
  public static void main( String args[])
  {
  Monitor monitor = new Monitor();
  Generador gUno = new Generador(monitor ,0);
  Generador gDos = new Generador(monitor ,1);
  Sumador sumador = new Sumador(monitor);

        sumador.start();
        gUno.start();
        gDos.start();
   }
}