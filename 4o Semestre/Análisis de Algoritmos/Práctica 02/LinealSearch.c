/*
Implementación Práctica 02: Análisis temporal y notación de orden (Algoritmos de búsqueda)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que buscará por medio del método Lineal o Secuencial

Observaciones:

Compilación:

	gcc -o LinealSearch LinealSearch.c

	./LinealSearch n k < SortedNumbers.txt >> Lineal.txt

	donde:
		n es el tamaño de la búsqueda
		k es el valor a buscar
*/


//LIBRERÍAS
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "time.h"
#include "imprimeTiempos.h"

//VARIABLES GLOBALES
bool found = false;
int* Data;
int nSize = 0, keyNumber = 0;

#include "LinealSearch.h"



// FUNCIÓN PRINCIPAL

/*
Variables usadas en el programa:
	bool found: variable que indicará si se encontró el número en la búsqueda
	int* Data: apuntador de entero que será inicializado como arreglo para los datos donde se buscará
	int nSize: variable que tomará el tamaño de la línea de comando
	int keyNumber: variable que indicará el número a buscar
	double utime0: variable que medirá el tiempo de inicio de ejecución del usuario
	double stime0: variable que medirá el tiempo de inicio de ejecución del sistema
	double wtime0: variable que medirá el tiempo de inicio de ejecución real
	double utime1: variable que medirá el tiempo de finalización de ejecución del usuario
	double stime1: variable que medirá el tiempo de finalización de ejecución del sistema
	double wtime1: variable que medirá el tiempo de finalización de ejecución real
	
*/
int main(int argc, char const *argv[])
{
	if (argc < 3) exit(0);	// Verificación sencilla
	
	nSize = atoi(argv[1]);	// Identifica el número de datos sobre los que se va a trabajar
	keyNumber = atoi(argv[2]);	// Asigna el número que se va a buscar

	double utime0, stime0, wtime0; 	// Tiempos de inicio
	double utime1, stime1, wtime1;	// Tiempos de finalización

	Data = (int*)calloc(nSize,sizeof(int));	// Inicialización del arreglo para los números

	for (int i = 0; i < nSize; ++i){
		scanf("%d", Data+i);			// Insertamos los números en el arreglo
	}

	uswtime(&utime0, &stime0, &wtime0);		// Iniciamos los contadores de tiempo

	LinealSearch(); // Función que realizará la búsqueda

	uswtime(&utime1, &stime1, &wtime1);		// Finalizamos los contadores de tiempo
	
	double RealTime = wtime1 - wtime0;	// Asignamos el tiempo real del proceso desde su inicio hasta su finalización
	double UserTime = utime1 - utime0;	// Asignamos el tiempo que la CPU se ha dedicado exclusivamente a la computación del programa
	double SysTime  = stime1 - stime0;	// Asignamos el tiempo que la CPU se ha dedicado a dar servicio al sistema operativo por necesidades del programa

	imprimeTiempos(found, keyNumber, nSize, RealTime, UserTime, SysTime); // Función que mostrará los resultados

	free(Data);	// Liberamos el arreglo de números

	return 0;
}