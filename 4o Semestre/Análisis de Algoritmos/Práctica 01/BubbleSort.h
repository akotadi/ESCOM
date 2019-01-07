/*
Implementación Práctica 01: Pruebas a posteriori (Algoritmos de Ordenamiento)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que ordenará por medio del algoritmo de Burbuja, 
  tanto simple como optimizada
  
Observaciones: 
*/



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de ordenar los números por medio del algoritmo de Burbuja Simple
Recibe: int * Data (arreglo de números a ordenar), int size (cantidad de números)
Devuelve: 
Observaciones: 
*/
void SimpleBubbleSort(int * Data,int size){
  for (int i = 0; i < size-1; i++) {                                                            // Se hará una iteración por todo el arreglo de números
    for (int j = 0; j < size-1-i; j++) {                                                        // A partir del inicio, iremos realizando una segunda iteración que recorrerá los elementos -i
      if(Data[j]>Data[j+1]){                                                                    // Si existe un número que sea mayor que su sudecesor, se intercambiarán
        int temp=Data[j+1];
        Data[j+1]=Data[j];
        Data[j]=temp;
      }
    }
  }
}

/*
Descripción: Función encargada de ordenar los números por medio del algoritmo de Burbuja Optimizada
Recibe: int * Data (arreglo de números a ordenar), int size (cantidad de números)
Devuelve: 
Observaciones: 
*/
void OptimizedBubbleSort(int *Data, int size){
  int changes=1;                                                                                // Declaramos una bandera para los cambios realizados
  int i=0;
  while((i<size-1)&&(changes!=0)) {                                                             // Realizaremos una iteración hasta llegar al final del arreglo de números, o bien, 
                                                                                                // hasta que ya no hayamos realizado cambios, lo que indicará que ya trerminamos de ordenar
    changes=0;                                                                                  // Iniciamos el ciclo indicando que no hemos realizado cambios
    for (int j = 0; j < size-1-i; j++) {                                                        // Nuevamente llevaremos la iteración desde el primer elemento hasta los n elementos - i
      if(Data[j]>Data[j+1]){                                                                    // Si existe un elemento mayor que su sucesor, se intercambian
        changes=1;                                                                              // Indicamos que realizamos un cambio en la iteración de i
        int temp=Data[j+1];
        Data[j+1]=Data[j];
        Data[j]=temp;
      }
    }
    i++;
  }
}
