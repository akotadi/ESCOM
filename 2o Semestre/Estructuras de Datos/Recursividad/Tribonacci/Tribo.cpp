/*
Implementaci�n Pr�ctica 04: Serie de Tribonacci
Por: #TeamYalja
Versi�n: 1.0

Descripci�n: En matem�ticas, la serie de Tribonacci es una sucesi�n
infinita de n�meros naturales donde los dos primeros elementos son 0, 
el tercero es 1 y cada elemento restante es la suma de los tres anteriores:
	f(n) = f(n-1) + f(n-2) + f(n-3)
	
Observaciones: Existe otra posibilidad en esta sucesi�n, donde los primeros
n�meros de la sucesi�n son 1 y el tercero es 2, sin embargo nos damos cuenta 
que es lo mismo, eliminando los dos primeros elementos la sucesi�n presentada 
en este programa
*/


//LIBRER�AS
#include <stdio.h>


//DEFINICI�N DE FUNCIONES

/*
Descripci�n: Funci�n recursiva que nos devolver� el n elemento en la sucesi�n de Tribonacci
Recibe: int n (posici�n n en la sucesi�n de Tribonacci)
Devuelve: El valor del n�mero en la posici�n n de la sucesi�n de Tribonacci
Observaciones: El caso base nos da los tres primeros elementos de la sucesi�n
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
	int n: Posici�n del n�mero que buscamos en la sucesi�n de Tribonacci
*/
int main (void){
	int n;
	printf("Posicion de la sucesion de Tribonacci que se va a calcular: ");
	scanf("%i",&n);
	n=Tribo(n);
	printf("\n\nEl numero es: %i",n);
	return 0;
}
