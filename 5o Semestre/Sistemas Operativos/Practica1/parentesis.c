/*
DESCRIPCION: Programa que evalua si una expresion tiene parentesis, llaves o corchetes balanceados
OJO: Se usa TADPilaEst.h
PARA COMPILAR: 	gcc -o par Parentesis.c TADPilaEst.o (Si se tiene el objeto de la implementación)
				gcc -o par Parentesis.c TADPilaEst.c (Si se tiene el fuente de la implementación)
PARA EJECUTAR: par.exe (En Windows) - ./par (En Linux)
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "TADPilaEst.c"

#define TAM_CADENA 100 //Tamaño maximo el caracter de final de cad

int main(void)
{
	int tam_cadena;
	int i,j;
	char cadena[TAM_CADENA];
	pila mi_pila;
	elemento e1;
	Initialize(&mi_pila);

	scanf("%s",cadena);
	tam_cadena=strlen(cadena);

	//Recorrer cad
	for(i=0;i<tam_cadena;i++)
	{
		//Si el caracter es '(' o '{' o '[' entra a la pila
		if(cadena[i]=='(' )
			{
				e1.c='(';
				Push(&mi_pila,e1);
			}
			
		if(cadena[i]=='[' )
			{
				e1.c='[';
				Push(&mi_pila,e1);
			}
			
		if(cadena[i]=='{' )
			{
				e1.c='{';
				Push(&mi_pila,e1);
			}
			
		//Si el caracter es ) hacer un Pop a la pila
			else if(cadena[i]==')'||cadena[i]==']'||cadena[i]=='}')
			{
				//Si se intenta extraer un elemento y la pila esta vacia
				if(Empty(&mi_pila))
				{
					printf("NO BALANCEADA");
					exit(1);
				}
				e1=Pop(&mi_pila);
			}
	}

	//Si termina revision y aún hay elementos en la pila
	if(!Empty(&mi_pila))
	{
		printf("NO BALANCEADA");
		exit(1); 
	}

	printf("BALANCEADA");

	Destroy(&mi_pila);
	exit(0);
}
