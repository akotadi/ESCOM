/*
Implementación Práctica 01: Pruebas a posteriori (Algoritmos de Ordenamiento)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que ordenará por medio de un Árbol Binario de Búsqueda (ABB)
	
Observaciones: 
*/


//LIBRERÍAS
#include "AuxBinarySearchTreeFunctions.h"													// Utilizaremos funciones auxiliares de esta librería para poder manejar el ordenamiento mediante un ABB



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de ordenar los números por medio de un ABB
Recibe: int * Data (arreglo de números a ordenar), int size (cantidad de números)
Devuelve: 
Observaciones: 
*/
void BinarySearchTreeSort(int * Data, int size){
	arbol_bin BinaryTree;															// Declaramos un ABB
	Initialize(&BinaryTree);														// Inicializamos el ABB declarado anteriormente
	int i;																			// Variable de la iteración
	for (i = 0; i < size; ++i)														// Recorreremos el arreglo de números iterativamente
	{
		Insert(&BinaryTree,Data[i]);												// Cada número del arreglo, será añadido al ABB
	}
	InorderTraversal(&BinaryTree, Data, size);										// Realizaremos el recorrido inorden por el ABB para conseguir el orden de los números
	Destroy(&BinaryTree);															// Destruimos el árbol una vez utilizado
	return;
}
