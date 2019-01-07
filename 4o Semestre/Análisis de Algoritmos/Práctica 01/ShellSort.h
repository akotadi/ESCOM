/*
Implementación Práctica 01: Pruebas a posteriori (Algoritmos de Ordenamiento)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que ordenará por medio del algoritmo de Shell
  
Observaciones: 
*/

//LIBRERÍAS
#include "math.h"



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de ordenar los números por medio del algoritmo de Shell
Recibe: int * Data (arreglo de números a ordenar), int size (cantidad de números)
Devuelve: 
Observaciones: 
*/
void ShellSort(int *Data, int size){
  int k =trunc(size/2);                                                           // Declararemos k, colocándola en la mitad del arreglo
  while (k>=1){                                                                   // Hasta llegar al inicio del arreglo, realizaremos una iteración
    int b=1;                                                                      // Declararemos b a modo de bandera para saber si podemos seguir realizando saltos
    while (b!=0) {                                                                // Mnatendremos b hasta llegar al primer elemento del arreglo
      b=0;                                                                        // Iniciaremos suponiendo que el salto no es posible
      for (int i = k; i <= size-1; i++) {                                         // Itereación que irá dando saltos cada vez más chicos a partir de k
        if (Data[i-k]>Data[i]) {                                                  // Si el número en la posición del salto es mayor que el indicado, los intercambiaremos
          int temp=Data[i];
          Data[i]=Data[i-k];
          Data[i-k]=temp;
          b++;                                                                    // En caso de haber cambios, la bandera se colocará en 1 y seguiremos con los salto de este tamaño
        }
      }
    }
    k=trunc(k/2);                                                                 // Reduciremos el tamaño de los saltos a la mitad
  }
}
