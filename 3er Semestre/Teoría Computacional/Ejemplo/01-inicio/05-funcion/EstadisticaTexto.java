// Jesus M. Olivares Ceja

import java.io.*;

class Estadistica
{
int arreglo[] = new int [11] ;

   public void leeDatos()
   {
   StringBuffer cadena;
   char ch ;
       System.out.println("Escribe 10 numeros enteros");
       for( int i = 0; i < 11; i++ )
       {
           try
           {
           System.out.print(">");
           cadena = new StringBuffer(50);
           while( (ch = (char)System.in.read()) != '\n' )
              cadena.append(ch);
           String salida = cadena.toString().substring(0,cadena.length() - 1);
           arreglo[i] = Integer.parseInt(salida);
           }
           catch( Exception e)
           {}
       }
   }

   float media()
   {
   float suma ,_media ;

       suma = 0;
       for( int i = 0; i < 11; i++ )
          suma += arreglo[i];
       _media = suma / (float)11.0;
       return _media;
   }

   int moda()
   {
   int i, j, repite ,_moda ,frecuencia,candidato ;

       _moda = 0;
       repite = 0;
       for( i = 0; i < 11; i++ )
       {
           candidato = arreglo[i];
           frecuencia = 1;
           for( j = 0; j < 11; j++ )
           {
               if( i != j )
                   if( candidato == arreglo[j] )
                       frecuencia++;
           }
           if( frecuencia > repite )
           {
               repite = frecuencia;
               _moda = candidato;
           }
       }
       return _moda;
   }
   int mediana()
   {
   int i, j, auxiliar ,_mediana ;

       for( i = 0; i < 11; i++ )
       {
           for( j = i + 1; j < 11; j++ )
           {
               if( arreglo[i] > arreglo[j] )
               {
                   auxiliar = arreglo[i];
                   arreglo[i] = arreglo[j];
                   arreglo[j] = auxiliar;
               }
           }
       }
       _mediana = arreglo[5];
       for( j = 0; j < 11; j++ )
           System.out.print(arreglo[j] + " ");
       return _mediana;
   }
   float varianza()
   {
   float suma ,_media ,_varianza ,factor ,diferencia ;
   int i ;

       suma = 0;
       for( i = 0; i < 11; i++ )
          suma += arreglo[i];
       _media = suma / (float)11.0;

       suma = 0;
       for( i = 0; i < 11; i++ )
       {
          diferencia = arreglo[i] - _media;
          factor = diferencia * diferencia;
          suma += factor;
       }
       _varianza = suma / (float)10.0;
       return _varianza;
   }
}

class stadisticaTexto
{
    public static void main(String args[])
    {
    Estadistica es = new Estadistica();

        es.leeDatos();
        System.out.println("La media es: " + es.media());
        System.out.println("La moda es: " + es.moda());
        System.out.println("La mediana es: " + es.mediana());
        System.out.println("La varianza es: " + es.varianza());
    }
}





