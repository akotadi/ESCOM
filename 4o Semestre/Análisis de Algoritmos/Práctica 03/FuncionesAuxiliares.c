/*
Implementación Práctica 03: Codificación voraz de Huffman
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que obtiene una codificación prefijo óptima con base al algoritmo de Huffman

Observaciones:

*/


//CABECERA
#include "FuncionesAuxiliares.h"

/*
Descripción: Función que convertirá un número entero a su equivalente binario
Recibe: int n (número a convertir)
Devuelve: char (binario del número)
Observaciones:
*/
char decBin(int n){
	char c=0;
	int i;
	for(i=128;i>0;i/=2){
		if(n>=i){
			c=c|i;
			n-=i;
		}
	}
	return c;
}

/*
Descripción: Función que convertirá un número binario a su equivalente decimal
Recibe: char c (variable que contiene el binario del número)
Devuelve: unsigned int (binario en su representación de entero)
Observaciones:
*/
unsigned int binDec(char c){
	int i;
	unsigned n=0;
	for(i=0;i<8;i++){
		if((c>>i)&1){
			n+=pow(2,i);
		}
	}
	return n;
}

/*
Descripción: Función que imprimirá un valor binario en el documento
Recibe: char n (representación binaria a imprimir)
Devuelve: 
Observaciones:
*/
void imprimeBinario(char n){
	int i;
	for(i=7;i>=0;i--){
		printf("%d",(n>>i)&1);
	}
}

