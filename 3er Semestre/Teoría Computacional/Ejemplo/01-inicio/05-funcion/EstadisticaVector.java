/* EstatVector.java

    SE UTILIZA UN Vector PARA MANEJAR ARREGLOS DE LONGITUD VARIABLE

2003 07 Jesus Olivares */

import java.util.*;
import java.io.*;

class Estadistica
{
Vector arreglo ;

   public Estadistica(Vector _arreglo)
   {
       arreglo = _arreglo;
   }
   double media()
   {
   double suma ,_media ;

       suma = 0;
       for( int i = 0; i < arreglo.size(); i++ )
       {
           Double dato = new Double(arreglo.get(i).toString());
           suma += dato.doubleValue();
       }
       _media = suma / arreglo.size();
       return _media;
   }
   double varianza()
   {
   double suma ,_media ,_varianza ,factor ,diferencia ;
   int i ;

       _media = this.media();
       suma = 0;
       for( i = 0; i < arreglo.size(); i++ )
       {
           Double dato = new Double(arreglo.get(i).toString());
           diferencia = dato.doubleValue() - _media;
           factor = diferencia * diferencia;
           suma += factor;
       }
       _varianza = suma / arreglo.size();
       return _varianza;
   }
}

class EstadisticaVector
{
   public static void main(String args[]) throws IOException
   {
   StringBuffer cadena;
   char ch ;
   int max ;
   Vector v = new Vector(0); // EL VECTOR INICIA SIN ELEMENTOS
   boolean sigue = true;
   Integer dato ;

       System.out.println("Digite sus elementos (termine con ENTER)");
       for( int i = 0; sigue; i++ )
       {
           System.out.print(">");
           cadena = new StringBuffer(50);
           while( (ch = (char)System.in.read()) != '\n' )
              cadena.append(ch);
           if( cadena.length() == 1 )
           {
               sigue = false;
               continue;
           }
           String salida = cadena.toString().substring(0,cadena.length() - 1);
           v.add((Object)new Integer(salida));

       }
       Estadistica estadistica = new Estadistica(v);
       System.out.println("Media = " + estadistica.media());
       System.out.println("Varianza = " + estadistica.varianza());
   }
}