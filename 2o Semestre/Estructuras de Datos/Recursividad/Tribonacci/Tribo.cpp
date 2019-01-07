/*
Implementación Práctica 04: Serie de Tribonacci
Por: #TeamYalja
Versión: 1.0

Descripción: En matemáticas, la serie de Tribonacci es una sucesión
infinita de números naturales donde los dos primeros elementos son 0, 
el tercero es 1 y cada elemento restante es la suma de los tres anteriores:
	f(n) = f(n-1) + f(n-2) + f(n-3)
	
Observaciones: Existe otra posibilidad en esta sucesión, donde los primeros
números de la sucesión son 1 y el tercero es 2, sin embargo nos damos cuenta 
que es lo mismo, eliminando los dos primeros elementos la sucesión presentada 
en este programa
*/


//LIBRERÍAS
#include <stdio.h>


//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función recursiva que nos devolverá el n elemento en la sucesión de Tribonacci
Recibe: int n (posición n en la sucesión de Tribonacci)
Devuelve: El valor del número en la posición n de la sucesión de Tribonacci
Observaciones: El caso base nos da los tres primeros elementos de la sucesión
*/
int Tribo (int n){
	if(n==1||n==2)
		return 0;
	if(n==3)
		return 1;
	return Tribo(n-1)+Tribo(n-2)+Tribo(n-3);
}



/*
Variables usadas en el programa:
	int n: Posición del número que buscamos en la sucesión de Tribonacci
*/
int main (void){
	int n;
	printf("Posicion de la sucesion de Tribonacci que se va a calcular: ");
	scanf("%i",&n);
	n=Tribo(n);
	printf("\n\nEl numero es: %i",n);
	return 0;
}
