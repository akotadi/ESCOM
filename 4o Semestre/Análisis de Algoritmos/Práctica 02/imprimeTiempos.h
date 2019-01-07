/*
Implementación Práctica 02: Análisis temporal y notación de orden (Algoritmos de búsqueda)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa encargado de imprimir los tiempos de ejecuión de los programas
	
Observaciones: 
*/


//LIBRERÍAS
#include <stdio.h>
#include <stdbool.h>



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de imprimir los tiempos de ejecución de los programas
Recibe: 
	bool found (indica si se encontró el número en la búsqueda)
	int keyNumber (número que se buscó)
	int nSize (número de datos sobre los que se realizó la búsqueda)
	int nThreads (número de particiones hilos que se usaron en el programa)
	double SysTime (tiempo del sistema)
	double UserTime (tiempo del usuario)
	double RealTime (tiempo real)
Devuelve:
Observaciones: El tiempo se consigue por medio de la diferencia entre el tiempo de inicio y el final, el porcentaje de
	tiempo dedicado a la ejecución vendría dado por la relación CPU/Wall
*/
void imprimeTiempos(bool found, int keyNumber, int nSize, double RealTime, double UserTime,double SysTime){
	printf("%12d", keyNumber);
	printf("%15d", nSize);
	printf("%25.10f", RealTime);
	printf("%25.10f", UserTime);
	printf("%25.10f", SysTime);
	printf("%10d\n", found);
}

/*void imprimeTiempos(bool found, int keyNumber, int nSize, double RealTime, double UserTime,double SysTime){
	if (found)
	{
		printf("\nKey Number = %i found\n", keyNumber);
	}else{
		printf("\nKey Number = %i NOT found\n", keyNumber);
	}
	printf("With an n = %i\n", nSize);
	printf("Real Time: \t%.10f\n", RealTime);
	printf("User Time: \t%.10f \n", UserTime);
	printf("System Time: \t%.10f\n", SysTime);
}*/
