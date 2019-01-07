/*
Implementación Práctica 01: Pruebas a posteriori (Algoritmos de Ordenamiento)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que ordenará por medio del algoritmo de Selección
  
Observaciones: 
*/



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de ordenar los números por medio del algoritmo de Selección
Recibe: int * Data (arreglo de números a ordenar), int size (cantidad de números)
Devuelve: 
Observaciones: 
*/
void SelectionSort(int *Data, int size){
  for (int k = 0; k < size-1; k++) {                                                // Iteración que recorrerá todo el arreglo de números, hasta la penúltima posición
    int p=k;                                                                        // Colocaremos un índice auxiliar en la posición k
    for (int i=k+1; i < size; i++) {                                                // Iteración que recorrerá el arreglo desde una posición delante de su predecesora hasta el final
      if (Data[i]<Data[p]) {                                                        // Si el número es menor que el que se encuentra en nuestro índice auxiliar, 
        p=i;                                                                        // Cambiaremos el índice auxiliar para llevar control del menor número disponible para esa posición
      }
    }
    int aux=Data[p];                                                                // Una vez recorrido el resto de números, intercambiaremos los números para colocar el menor disponible en la posición k
    Data[p]=Data[k];
    Data[k]=aux;
  }
}
