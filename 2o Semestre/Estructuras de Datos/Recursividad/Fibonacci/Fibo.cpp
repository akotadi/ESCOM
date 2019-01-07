/*
Implementación Práctica 04: Serie de Fibonacci
Por: #TeamYalja
Versión: 1.0

Descripción: En matemáticas, la serie de Fibonacci es una sucesión
infinita de números naturales donde el primer elemento es 0, el segundo
es 1 y cada elemento restante es la suma de los dos anteriores:
	f(n) = f(n-1) + f(n-2)
	
Observaciones: 
*/


//LIBRERÍAS
#include <stdio.h>


//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función recursiva que nos devolverá el n elemento en la sucesión de Fibonacci
Recibe: int n (posición n en la sucesión de Fibonacci)
Devuelve: El valor del número en la posición n de la sucesión de Fibonacci
Observaciones: El caso base nos da los dos primeros elementos de la sucesión
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
	int n: Posición del número que buscamos en la sucesión de Fibonacci
*/
int main (void){
	int n;
	printf("Posicion de la sucesion de Fibonacci que se va a calcular: ");
	scanf("%i",&n);
	n=Fibo(n);
	printf("\n\nEl numero es: %i",n);
	return 0;
}
