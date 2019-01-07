/*
Implementación Práctica 01: Pruebas a posteriori (Algoritmos de Ordenamiento)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que ordenará por medio del algoritmo de Burbuja Optimizada
  
Observaciones: 
*/

//LIBRERÍAS
#include<stdio.h>
#include<stdlib.h>
#include "BubbleSort.h"
#include "tiempo.h"
#include"imprimeTiempos.h"



// FUNCIÓN PRINCIPAL

/*
Variables usadas en el programa:
	int size: variable que tomará el tamaño de la línea de comando
	int* Data: apuntador de entero que será inicializado como arreglo para los datos a ordenar
	double utime0: variable que medirá el tiempo de inicio de ejecución del usuario
	double stime0: variable que medirá el tiempo de inicio de ejecución del sistema
	double wtime0: variable que medirá el tiempo de inicio de ejecución real
	double utime1: variable que medirá el tiempo de finalización de ejecución del usuario 
	double stime1: variable que medirá el tiempo de finalización de ejecución del sistema
	double wtime1: variable que medirá el tiempo de finalización de ejecución real
*/
int main(int argc,char **argv){
	int i;
	int size = atoi(argv[1]);
	int *Data;
	double utime0, stime0, wtime0,utime1, stime1, wtime1; //Variables para medición de tiempos
	Data=calloc(size,sizeof(int));
	for(i=0;i<size;i++){
		scanf("%d",Data+i);
	}
	uswtime(&utime0, &stime0, &wtime0);
	SimpleBubbleSort(Data,size);
	uswtime(&utime1, &stime1, &wtime1);
	imprimeTiempos(size,utime0, stime0, wtime0,utime1, stime1, wtime1);
	free(Data);
}
