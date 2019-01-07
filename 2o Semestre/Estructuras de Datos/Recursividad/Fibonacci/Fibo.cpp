/*
Implementaci�n Pr�ctica 04: Serie de Fibonacci
Por: #TeamYalja
Versi�n: 1.0

Descripci�n: En matem�ticas, la serie de Fibonacci es una sucesi�n
infinita de n�meros naturales donde el primer elemento es 0, el segundo
es 1 y cada elemento restante es la suma de los dos anteriores:
	f(n) = f(n-1) + f(n-2)
	
Observaciones: 
*/


//LIBRER�AS
#include <stdio.h>


//DEFINICI�N DE FUNCIONES

/*
Descripci�n: Funci�n recursiva que nos devolver� el n elemento en la sucesi�n de Fibonacci
Recibe: int n (posici�n n en la sucesi�n de Fibonacci)
Devuelve: El valor del n�mero en la posici�n n de la sucesi�n de Fibonacci
Observaciones: El caso base nos da los dos primeros elementos de la sucesi�n
*/
int Fibo (int n){
	if(n==1)
		return 0;
	if(n==2)
		return 1;
	return Fibo(n-1)+Fibo(n-2);
}



/*
Variables usadas en el programa:
	int n: Posici�n del n�mero que buscamos en la sucesi�n de Fibonacci
*/
int main (void){
	int n;
	printf("Posicion de la sucesion de Fibonacci que se va a calcular: ");
	scanf("%i",&n);
	n=Fibo(n);
	printf("\n\nEl numero es: %i",n);
	return 0;
}
