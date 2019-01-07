	/*
Implementación Práctica 04: Torres de Hanoi
Por: #TeamYalja
Versión: 1.1

Descripción: Tenemos tres astas y un conjunto de aros, todos de distintos 
tamaños. El propósito del enigma es lograr apilar los aros, en el mismo 
orden, pero en el asta C iniciando en el asta A. 
	
Observaciones: Tiene dos restricciones.-
	-Solo puede mover el aro superior de cualquiera de las astas.
	-Un aro más grande nunca puede estar encima de uno más pequeño
*/


//LIBRERÍAS
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <windows.h>


//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de mostrar en pantalla una simulación de las astas
Recibe: int n (número total de aros), char* A (arreglo que contiene un asta)
Devuelve: 
Observaciones: 
*/
void Dibujar (int n, char* A){
	int d=0;
	for(int i=0;A[i]!=-1&&i<n;i++) // Iteración que cuenta el número de aros
		d++;
	printf("\n");
	for(int i=0;i<=n-d;i++){ // Iteración que dibuja la zona sin aros
		for(int j=0;j<n;j++)
			printf("%c",32); // Caracter de espacio
		printf("%c",186); // Caracter que simula el asta
		printf("\n");
	}
	for(int i=0;i<d;i++){
		for(int j=0;j<n-A[i];j++)
			printf("%c",32);
		for(int j=0;j<A[i];j++) 
			printf("%c",254); // Caracter que simula el aro
		for(int j=0;j<A[i];j++)
			printf("%c",254);
		printf("%c",254);
		printf("\n");
	}
	for(int i=0;i<n;i++){
		printf("%c",205); //  Caracter que simula la base del asta
		printf("%c",205);
	}
	printf("%c",205);
	printf("\n");
}

/*
Descripción: Función que mostrará las tres astas
Recibe: int n (número de aros), char* A (arreglo que simula una de las astas), 
	char* B (arreglo que simula una de las astas), char* C (arreglo que simula 
	una de las astas)
Devuelve: 
Observaciones: El último caracter de cada arreglo indica cual de las astas se trata,
	el 1 es el asta de origen, el 2 el asta auxiliar y el 3 es el asta final
*/
void Mostrar (int n, char* A, char* B, char* C){
	system("cls");
	for(int i=0;i<3;i++){
		if(i==0)
			printf("\nTorre  Inicial");
		if(i==1)
			printf("\nTorre  Auxiliar");
		if(i==2)
			printf("\nTorre  Final");
		if(A[n]==i+1)
			Dibujar(n,A);
		if(B[n]==i+1)
			Dibujar(n,B);
		if(C[n]==i+1)
			Dibujar(n,C);
	}
	Sleep(100);
	//system("pause");
	return;
}

/*
Descripción: Función que moverá, de un arreglo A a otro B, los números que simulan los aros 
Recibe: int n (número de aros), char* A (arreglo al que se le quitará el aro), 
	char* B (arreglo al que se le agregará el aro),char* C
Devuelve: 
Observaciones: Caso especial con un solo aro
*/
void Mover (int n, char* A, char* B,char* C){
	for(int i=n-1;i>0;i--){ // Iteración para hacer espacio en el arreglo para el nuevo aro
		B[i]=B[i-1];
	}
	B[0]=A[0]; // Se mueve el aro
	if(n==1)
		A[0]=-1; // -1 Indica que no hay aro en esa posición
	for(int i=0;i<n-1;i++){ // Iteración para recorrer los aros en el arreglo al que se le retiró
		A[i]=A[i+1];
		A[i+1]=-1;
	}
	Mostrar(n,A,C,B); // Hecho el movimiento, lo simulamos en pantalla
	return;
}

/*
Descripción: Función recursiva que resuelve el problema de las Torres de Hanoi
Recibe: int n (número de aros), int d (número que nos ayudará a encontrar el caso base), 
	char* TorreInicial (arreglo que simula una de las astas), 
	char* TorreAuxiliar (arreglo que simula una de las astas), 
	char* TorreFinal (arreglo que simula una de las astas)
Devuelve: 
Observaciones: La recursividad toma el caso base donde solo debemos mover un aro desde la 
	torre inicial hasta la torre final
*/
void TorresHanoi (int n, int d, char* TorreInicial, char* TorreAuxiliar, char* TorreFinal){
	if(d==1) // Caso base donde movemos nuestro aro "solo" desde la torre inicial hasta la final
    {
        Mover(n,TorreInicial,TorreFinal,TorreAuxiliar);
    }
    else
    {
        TorresHanoi(n,d-1,TorreInicial,TorreFinal,TorreAuxiliar); // Movemos los n-1 aros desde la torre inicial hasta la auxiliar
        Mover(n,TorreInicial,TorreFinal,TorreAuxiliar);
		TorresHanoi(n,d-1,TorreAuxiliar,TorreInicial,TorreFinal); // Movemos los n-1 aros desde la torre auxiliar hasta la final
    }
    return;
}



/*
Variables usadas en el programa:
	int n: Número de aros
	int d: Auxiliar que simulará la recursividad de los aros
	int mov: Número de movimientos necesarios
	char* TorreInicial: Arreglo dinámico que simulará la torre inicial
	char* TorreAuxiliar: Arreglo dinámico que simulará la torre auxiliar
	char* TorreFinal: Arreglo dinámico que simulará la torre final
*/
int main (void){
	int n, d, mov;
	char* TorreInicial;
	char* TorreAuxiliar;
	char* TorreFinal;
	printf("Introduzca el numero de discos: ");
	scanf("%i",&n);
	TorreInicial = (char*)malloc((n+1)*sizeof(int)); // Creamos los arreglos de forma dinámica
	TorreAuxiliar = (char*)malloc((n+1)*sizeof(int));
	TorreFinal = (char*)malloc((n+1)*sizeof(int));
	for(int i=0;i<n;i++){ // Llenamos las torres donde -1 indicará que está vacío
		TorreInicial[i]=i+1;
		TorreAuxiliar[i]=-1;
		TorreFinal[i]=-1;
	}
	TorreInicial[n]=1; // Número que nos permitirá conocer de qué torre se trata durante la ejecución
	TorreAuxiliar[n]=2;
	TorreFinal[n]=3;
	Mostrar(n,TorreInicial,TorreAuxiliar,TorreFinal);
	TorresHanoi(n,d=n,TorreInicial,TorreAuxiliar,TorreFinal);
	printf("\nEl numero de movimientos necesarios son: %i\n",mov=pow(2,n)-1);
	free(TorreInicial); // Liberamos la memoria solicitada de forma dinámica
	free(TorreAuxiliar);
	free(TorreFinal);
	return 0;
}
