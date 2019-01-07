	/*
Implementaci�n Pr�ctica 04: Torres de Hanoi
Por: #TeamYalja
Versi�n: 1.1

Descripci�n: Tenemos tres astas y un conjunto de aros, todos de distintos 
tama�os. El prop�sito del enigma es lograr apilar los aros, en el mismo 
orden, pero en el asta C iniciando en el asta A. 
	
Observaciones: Tiene dos restricciones.-
	-Solo puede mover el aro superior de cualquiera de las astas.
	-Un aro m�s grande nunca puede estar encima de uno m�s peque�o
*/


//LIBRER�AS
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <windows.h>


//DEFINICI�N DE FUNCIONES

/*
Descripci�n: Funci�n encargada de mostrar en pantalla una simulaci�n de las astas
Recibe: int n (n�mero total de aros), char* A (arreglo que contiene un asta)
Devuelve: 
Observaciones: 
*/
void Dibujar (int n, char* A){
	int d=0;
	for(int i=0;A[i]!=-1&&i<n;i++) // Iteraci�n que cuenta el n�mero de aros
		d++;
	printf("\n");
	for(int i=0;i<=n-d;i++){ // Iteraci�n que dibuja la zona sin aros
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
Descripci�n: Funci�n que mostrar� las tres astas
Recibe: int n (n�mero de aros), char* A (arreglo que simula una de las astas), 
	char* B (arreglo que simula una de las astas), char* C (arreglo que simula 
	una de las astas)
Devuelve: 
Observaciones: El �ltimo caracter de cada arreglo indica cual de las astas se trata,
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
Descripci�n: Funci�n que mover�, de un arreglo A a otro B, los n�meros que simulan los aros 
Recibe: int n (n�mero de aros), char* A (arreglo al que se le quitar� el aro), 
	char* B (arreglo al que se le agregar� el aro),char* C
Devuelve: 
Observaciones: Caso especial con un solo aro
*/
void Mover (int n, char* A, char* B,char* C){
	for(int i=n-1;i>0;i--){ // Iteraci�n para hacer espacio en el arreglo para el nuevo aro
		B[i]=B[i-1];
	}
	B[0]=A[0]; // Se mueve el aro
	if(n==1)
		A[0]=-1; // -1 Indica que no hay aro en esa posici�n
	for(int i=0;i<n-1;i++){ // Iteraci�n para recorrer los aros en el arreglo al que se le retir�
		A[i]=A[i+1];
		A[i+1]=-1;
	}
	Mostrar(n,A,C,B); // Hecho el movimiento, lo simulamos en pantalla
	return;
}

/*
Descripci�n: Funci�n recursiva que resuelve el problema de las Torres de Hanoi
Recibe: int n (n�mero de aros), int d (n�mero que nos ayudar� a encontrar el caso base), 
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
	int n: N�mero de aros
	int d: Auxiliar que simular� la recursividad de los aros
	int mov: N�mero de movimientos necesarios
	char* TorreInicial: Arreglo din�mico que simular� la torre inicial
	char* TorreAuxiliar: Arreglo din�mico que simular� la torre auxiliar
	char* TorreFinal: Arreglo din�mico que simular� la torre final
*/
int main (void){
	int n, d, mov;
	char* TorreInicial;
	char* TorreAuxiliar;
	char* TorreFinal;
	printf("Introduzca el numero de discos: ");
	scanf("%i",&n);
	TorreInicial = (char*)malloc((n+1)*sizeof(int)); // Creamos los arreglos de forma din�mica
	TorreAuxiliar = (char*)malloc((n+1)*sizeof(int));
	TorreFinal = (char*)malloc((n+1)*sizeof(int));
	for(int i=0;i<n;i++){ // Llenamos las torres donde -1 indicar� que est� vac�o
		TorreInicial[i]=i+1;
		TorreAuxiliar[i]=-1;
		TorreFinal[i]=-1;
	}
	TorreInicial[n]=1; // N�mero que nos permitir� conocer de qu� torre se trata durante la ejecuci�n
	TorreAuxiliar[n]=2;
	TorreFinal[n]=3;
	Mostrar(n,TorreInicial,TorreAuxiliar,TorreFinal);
	TorresHanoi(n,d=n,TorreInicial,TorreAuxiliar,TorreFinal);
	printf("\nEl numero de movimientos necesarios son: %i\n",mov=pow(2,n)-1);
	free(TorreInicial); // Liberamos la memoria solicitada de forma din�mica
	free(TorreAuxiliar);
	free(TorreFinal);
	return 0;
}
