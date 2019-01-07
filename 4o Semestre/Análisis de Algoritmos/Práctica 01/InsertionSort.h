/*
Implementación Práctica 01: Pruebas a posteriori (Algoritmos de Ordenamiento)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que ordenará por medio del algoritmo de Inserción
  
Observaciones: 
*/



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de ordenar los números por medio del algoritmo de Inserción
Recibe: int * Data (arreglo de números a ordenar), int size (cantidad de números)
Devuelve: 
Observaciones: 
*/
void insertion(int *Data,int size){	
	int i,j;																			// Variables usadas para las iteraciones
	int aux;																			// Variable usada como auxiliar para llevar control del número a insetar en el arreglo
	for(i=0;i<size;i++){																// Iteración a través de todo el arreglo
		aux=Data[i];																	// Ṕosicionamos el auxiliar en el primer dato a revisar
		for(j=i;j>0&&aux<Data[j-1];j--){												// Iteración que buscará la posición donde debemos colocar el número entre sus predecesores
			Data[j]=Data[j-1];															// Hasta encontrar su lugar, iremos recorriendo a sus predecesores
		}
		Data[j]=aux;																	// Al llegar a su posición, colocaremos el número
	}
}
