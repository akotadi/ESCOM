/*
Implementaci�n Pr�ctica 03: Codificaci�n voraz de Huffman
Por: Git Gud (Equipo Arbol)
Versi�n: 1.0

Descripci�n: Programa que obtiene una codificaci�n prefijo �ptima con base al algoritmo de Huffman

Observaciones:

Compilaci�n:

	gcc -o Comprimir Comprimir.c -lm

	./Comprimir

*/


//LIBRER�AS
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>
#include "FuncionesAuxiliares.c"
#include "Estructuras.c"



// FUNCI�N PRINCIPAL

/*
Variables usadas en el programa:
	int j: variable que servir� para iteraciones
	int k: variable que recibir� valores binarios convertidos a entero
	int l: �ndice para saber qu� bit deber� ser prendido
	char c: caracter que guardar� la codificaci�n a anotar en el archivo
	char s[100]: arreglo de caracteres que contendr� el nombre del archivo a codificar
	unsigned long i: variable que servir� para iteraciones
	arbol A[256]: arreglo de �rboles con los posibles caracteres
	char cod[256][256]: arreglo que guardar� la codificaci�n binaria de un caracter de manera vertical
	char b[256]: arreglo de caracteres auxiliar que guardar� temporalmente el recorrido binario del �rbol
	FILE *f: apuntador al archivo que se va a abrir
	FILE *f2: apuntador al archivo que se va a escribir
	lista *L: apuntador que funcionar� como una lista din�mica donde se a�adir�n los �rboles
	int prev:  variable que contendr� el tama�o parcial del archivo
	unsigned long sz: variable que contendr� el tama�o total del archivo
	char *entrada: apuntador que har� la funci�n de un arreglo din�mico para guardar el contenido del archivo
	
*/
int main(){
	int j,k,l;
	char c, s[100];
	unsigned long i; 
	arbol A[256];
	char cod[256][256];
	char b[256];
	FILE *f=NULL;
	FILE *f2=NULL;
	lista *L;
	L=NULL;
	
	// Se lee el nombre del archivo desde consola.
	printf("Nombre del archivo (maximo 100 caracteres):\n");
	scanf("%s",s); 

	// Abrimos el archivo en modo lectura.
	f=fopen(s,"rb");

	// En caso de no ser v�lido, terminamos el programa.
	if(f==NULL){
		printf("No existe el archivo.");
		return -1;
	}

	// Abrimos el archivo codificado en modo de escritura concaten�ndole .ggf como extensi�n de identificaci�n
	strcat(s,".ggf");
	f2=fopen(s,"wb");
	
	// Medimos el tama�o del archivo
	int prev = ftell(f);
    fseek(f,0L,SEEK_END);
    unsigned long sz=(long)ftell(f);
    fseek(f,prev,SEEK_SET);
    printf("El archivo mide %ld bytes\n",sz);
    
    // Inicializaci�n del arreglo de �rboles
	for(i=0;i<256;i++){
		A[i].c = decBin(i); // Colocamos el n�mero en binario como caracter de la estructura
		A[i].n = 0; // N�mero de apariciones en 0
		A[i].der = A[i].izq = NULL; // Apuntadores a hijos en NULL
	}
	
	// Se pasa el contenido del archivo a un arreglo de caracteres
	char *entrada=(char*)malloc(sizeof(char)*sz);
	fread(entrada,sizeof(char),sz,f);
	
	// Se hace el conteo de cada byte posible
	for(i=0;i<sz;i++){
		A[binDec(entrada[i])].n++; // Convertimos el caracter recibido a entero y aumentamos la frecuencia de ese �rbol
	}
	
	// Se ordena el arreglo de arboles en orden ascendente de acuerdo al numero de apariciones del byte
	ordena(A,0,255);
	
	// Se a�ade la tabla de codificaci�n al inicio del archivo
	for(i=0;i<256;i++){
		fprintf(f2,"%c%10ld",A[i].c,A[i].n);
	}
	
	// Se incertan los �rboles a una lista
	for(i=0;i<256;i++){
		insertaLista(&L,A+i);
	}
	
	// Se van juntando los �rbole hasta que quede uno solo
	while(L->sig!=NULL){
		dosMenoresArboles(&L);
	}
	
	// Se recorre el �rbol creando la tabla de codificaci�n
	recorreArbol(L->a,cod,b,0);
	
	// Por cada Byte del archivo fuente se va a�adiendo su codificaci�n al archivo destino
	c=0; // Caracter que guardar� la codificaci�n a anotar en el archivo
	l=7; // �ndice para saber qu� bit deber� ser prendido
	for(i=0;i<sz;i++){
		k=binDec(entrada[i]); // Convertimos el caracter a entero para saber el �ndice a buscar
		for(j=0;cod[k][j]!=-1;j++,l--){ // Recorreremos la posici�n indicada del arreglo de codificaciones 
			if(l==-1){ // En caso de que hayamos terminado los bits disponibles del caracter
				l=7; // Reiniciamos el �ndice
				fprintf(f2,"%c",c); // Imprimimos el caracter en el archivo
				c=0; // Reiniciamos el caracter
			}

			// Si todav�a hay espacio en el caracter, prenderemos o mantendremos apagado el bit en el caracter
			c=c|(cod[k][j]<<l); 
		}
	}
	
	// Se a�ade el �ltimo byte al archivo destino
	fprintf(f2,"%c",c);
	
	// Cerramos ambos archivos utilizados.
	fclose(f);
	fclose(f2);

	// Liberamos la memoria din�mica solicitada
	free(entrada);
	return 0;
}