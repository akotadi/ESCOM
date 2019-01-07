/* Estadistica.java

    CLASE PARA CALCULAR VALORES ESTADISTICOS DE UN ARREGLO DE DATOS
    SE USA LA CLASE Vector QUE TIENE UN NUMERO VARIABLE DE LOCALIDADES
    
2003 07 Jesus Olivares 
*/

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