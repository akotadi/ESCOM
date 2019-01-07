/*
Implementación Práctica 03: Codificación voraz de Huffman
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que obtiene una codificación prefijo óptima con base al algoritmo de Huffman

Observaciones:

Compilación:

	gcc -o Comprimir Comprimir.c -lm

	./Comprimir

*/


//LIBRERÍAS
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>
#include "FuncionesAuxiliares.c"
#include "Estructuras.c"



// FUNCIÓN PRINCIPAL

/*
Variables usadas en el programa:
	int j: variable que servirá para iteraciones
	int k: variable que recibirá valores binarios convertidos a entero
	int l: índice para saber qué bit deberá ser prendido
	char c: caracter que guardará la codificación a anotar en el archivo
	char s[100]: arreglo de caracteres que contendrá el nombre del archivo a codificar
	unsigned long i: variable que servirá para iteraciones
	arbol A[256]: arreglo de árboles con los posibles caracteres
	char cod[256][256]: arreglo que guardará la codificación binaria de un caracter de manera vertical
	char b[256]: arreglo de caracteres auxiliar que guardará temporalmente el recorrido binario del árbol
	FILE *f: apuntador al archivo que se va a abrir
	FILE *f2: apuntador al archivo que se va a escribir
	lista *L: apuntador que funcionará como una lista dinámica donde se añadirán los árboles
	int prev:  variable que contendrá el tamaño parcial del archivo
	unsigned long sz: variable que contendrá el tamaño total del archivo
	char *entrada: apuntador que hará la función de un arreglo dinámico para guardar el contenido del archivo
	
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

	// En caso de no ser válido, terminamos el programa.
	if(f==NULL){
		printf("No existe el archivo.");
		return -1;
	}

	// Abrimos el archivo codificado en modo de escritura concatenándole .ggf como extensión de identificación
	strcat(s,".ggf");
	f2=fopen(s,"wb");
	
	// Medimos el tamaño del archivo
	int prev = ftell(f);
    fseek(f,0L,SEEK_END);
    unsigned long sz=(long)ftell(f);
    fseek(f,prev,SEEK_SET);
    printf("El archivo mide %ld bytes\n",sz);
    
    // Inicialización del arreglo de árboles
	for(i=0;i<256;i++){
		A[i].c = decBin(i); // Colocamos el número en binario como caracter de la estructura
		A[i].n = 0; // Número de apariciones en 0
		A[i].der = A[i].izq = NULL; // Apuntadores a hijos en NULL
	}
	
	// Se pasa el contenido del archivo a un arreglo de caracteres
	char *entrada=(char*)malloc(sizeof(char)*sz);
	fread(entrada,sizeof(char),sz,f);
	
	// Se hace el conteo de cada byte posible
	for(i=0;i<sz;i++){
		A[binDec(entrada[i])].n++; // Convertimos el caracter recibido a entero y aumentamos la frecuencia de ese árbol
	}
	
	// Se ordena el arreglo de arboles en orden ascendente de acuerdo al numero de apariciones del byte
	ordena(A,0,255);
	
	// Se añade la tabla de codificación al inicio del archivo
	for(i=0;i<256;i++){
		fprintf(f2,"%c%10ld",A[i].c,A[i].n);
	}
	
	// Se incertan los árboles a una lista
	for(i=0;i<256;i++){
		insertaLista(&L,A+i);
	}
	
	// Se van juntando los árbole hasta que quede uno solo
	while(L->sig!=NULL){
		dosMenoresArboles(&L);
	}
	
	// Se recorre el árbol creando la tabla de codificación
	recorreArbol(L->a,cod,b,0);
	
	// Por cada Byte del archivo fuente se va añadiendo su codificación al archivo destino
	c=0; // Caracter que guardará la codificación a anotar en el archivo
	l=7; // Índice para saber qué bit deberá ser prendido
	for(i=0;i<sz;i++){
		k=binDec(entrada[i]); // Convertimos el caracter a entero para saber el índice a buscar
		for(j=0;cod[k][j]!=-1;j++,l--){ // Recorreremos la posición indicada del arreglo de codificaciones 
			if(l==-1){ // En caso de que hayamos terminado los bits disponibles del caracter
				l=7; // Reiniciamos el índice
				fprintf(f2,"%c",c); // Imprimimos el caracter en el archivo
				c=0; // Reiniciamos el caracter
			}

			// Si todavía hay espacio en el caracter, prenderemos o mantendremos apagado el bit en el caracter
			c=c|(cod[k][j]<<l); 
		}
	}
	
	// Se añade el último byte al archivo destino
	fprintf(f2,"%c",c);
	
	// Cerramos ambos archivos utilizados.
	fclose(f);
	fclose(f2);

	// Liberamos la memoria dinámica solicitada
	free(entrada);
	return 0;
}