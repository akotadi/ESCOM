/*
Implementación Práctica 02: Análisis temporal y notación de orden (Algoritmos de búsqueda)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que buscará por medio de un Árbol Binario de Búsqueda con hilos

Observaciones:

Compilación:

	gcc -o ThreadsBSTSearch ThreadsBSTSearch.c

	./ThreadsBSTSearch n t k < UnsortedNumbers.txt >> BST.txt

	donde:
		n es el tamaño de la búsqueda
		t es el número de hilos
		k es el valor a buscar
*/


//LIBRERÍAS
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <pthread.h>
#include "time.h"
#include "imprimeTiempos.h"

//VARIABLES GLOBALES
bool found = false;
int* Data;
int nSize = 0, nThreads = 0, keyNumber = 0;

#include "AuxBSTFunctions.h"
arbol_bin mainTree;

#include "ThreadsBSTSearch.h"



// FUNCIÓN PRINCIPAL

/*
Variables usadas en el programa:
	bool found: variable que indicará si se encontró el número en la búsqueda
	int* Data: apuntador de entero que será inicializado como arreglo para los datos donde se buscará
	int nSize: variable que tomará el tamaño de la línea de comando
	int nThreads: variable que tomará el número de hilos
	int keyNumber: variable que indicará el número a buscar
	arbol_bin mainTree: estructura que simulará el ABB usado
	double utime0: variable que medirá el tiempo de inicio de ejecución del usuario
	double stime0: variable que medirá el tiempo de inicio de ejecución del sistema
	double wtime0: variable que medirá el tiempo de inicio de ejecución real
	double utime1: variable que medirá el tiempo de finalización de ejecución del usuario
	double stime1: variable que medirá el tiempo de finalización de ejecución del sistema
	double wtime1: variable que medirá el tiempo de finalización de ejecución real
	pthread_t * aThreads: arreglo para la identificacion de los distintos hilos
	
*/
int main(int argc, char const *argv[])
{
	if (argc < 4) exit(0);	// Verificación sencilla
	
	nSize = atoi(argv[1]);	// Identifica el número de datos sobre los que se va a trabajar
	nThreads = atoi(argv[2]);	// Toma el número de hilos a trabajar
	keyNumber = atoi(argv[3]);	// Asigna el número que se va a buscar

	double utime0, stime0, wtime0; 	// Tiempos de inicio
	double utime1, stime1, wtime1;	// Tiempos de finalización

	Data = (int*)calloc(nSize,sizeof(int));	// Inicialización del arreglo para los números

	for (int i = 0; i < nSize; ++i){
		scanf("%d", Data+i);			// Insertamos los números en el arreglo
	}

	Initialize(&mainTree);	// Iniciamos nuestro ABB para usarlo

	for(int i = 0; i < nSize; ++i){
		Insert(&mainTree, Data[i]);	// Insertamos los datos en el ABB
	}

	uswtime(&utime0, &stime0, &wtime0);		// Iniciamos los contadores de tiempo

	position aux[nThreads];	// Arreglo de posiciones a partir de las cuales se realizará la búsqueda
	for (int i = 0; i < nThreads; ++i)
	{
		aux[i] = NULL;
	}

	subTrees(aux, &mainTree, nThreads);

	/*switch(nThreads){	// Switch-Case con base al número de hilos

		case 2:	// En caso de 2 hilos
		// Iniciaremos la búsqueda en los dos subárboles de la raíz
			aux[0] = mainTree->left;
			aux[1] = mainTree->right;
			break;

		case 3:	// En caso de 3 hilos
		// Los dos primeros hilos iniciarán en los dos subárboles del subárbol izquierdo
			aux[0] = (mainTree->left)->left;
			aux[1] = (mainTree->left)->right;
		// El último hilo iniciará en el subárbol derecho
			aux[2] = mainTree->right;
			break;

		case 4:	// En caso de 4 hilos
		// Iniciaremos la búsqueda en los subárboles de los dos hijos de la raíz
			aux[0] = (mainTree->left)->left;
			aux[1] = (mainTree->left)->right;
			aux[2] = (mainTree->right)->left;
			aux[3] = (mainTree->right)->right;
			break;

	}*/

	// En caso de que el número se encuentre en la raíz o en sus dos hijos, no iniciamos las búsquedas
	if(!found){	
		pthread_t *aThreads;	// Declaramos un arreglo de hilos
		aThreads = (pthread_t*) malloc(nThreads*sizeof(pthread_t));	// Inicialización del arreglo de hilos

		for (int i = 0; i < nThreads; ++i)
		{
			pthread_create(&aThreads[i], NULL, BSTSearch, (void*)aux[i]);	// Crear los hilos con el comportamiento "segmentar"
		}

		for (int i = 0; i < nThreads; ++i)
		{
			pthread_join(aThreads[i], NULL);	// Se verifica la finalización de todos los hilos
		}

		free(aThreads);	// Liberamos el arreglo de hilos
	}

	uswtime(&utime1, &stime1, &wtime1);		// Finalizamos los contadores de tiempo
	
	double RealTime = wtime1 - wtime0;	// Asignamos el tiempo real del proceso desde su inicio hasta su finalización
	double UserTime = utime1 - utime0;	// Asignamos el tiempo que la CPU se ha dedicado exclusivamente a la computación del programa
	double SysTime  = stime1 - stime0;	// Asignamos el tiempo que la CPU se ha dedicado a dar servicio al sistema operativo por necesidades del programa

	imprimeTiempos(found, keyNumber, nSize, RealTime, UserTime, SysTime); // Función que mostrará los resultados

	free(Data);	// Liberamos el arreglo de números

	Destroy(&mainTree);	// Destruimos el ABB usado

	return 0;
}