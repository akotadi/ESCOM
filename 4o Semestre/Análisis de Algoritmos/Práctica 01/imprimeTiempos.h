/*
Implementación Práctica 01: Pruebas a posteriori (Algoritmos de Ordenamiento)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa secundario que se encargará de imprimir los tiempos de ejecución de los distintos programas
  
Observaciones: 
*/


//LIBRERÍAS
#include "stdio.h"



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de imprimir los tiempos de ejecución de los programas
Recibe: int size (número de datos que se ordenaron), double utime0 (tiempo de inicio del usuario),
	double stime0 (tiempo de inicio del sistema), double wtime0 (tiempo de inicio real), 
	double utime1 (tiempo de finalización del usuario), double stime1 (tiempo de finalización del sistema),
	double wtime1 (tiempo de finalización real)
Devuelve: 
Observaciones: El tiempo se consigue por medio de la diferencia entre el tiempo de inicio y el final, el porcentaje de 
	tiempo dedicado a la ejecución vendría dado por la relación CPU/Wall
*/
void imprimeTiempos(int size,double utime0,double stime0,double wtime0,double utime1,double stime1,double wtime1){
	printf("%15d",size);																						// Imprimimos el número con el que estamos trabajando
	printf("%25.10f",  wtime1 - wtime0);																		// Los respectivos tiempos estarán dados por la diferencia del inicio y el final
	printf("%25.10f",  utime1 - utime0);
	printf("%25.10f",  stime1 - stime0);
	printf("%25.10f\n",100.0 * (utime1 - utime0 + stime1 - stime0) / (wtime1 - wtime0));
}
