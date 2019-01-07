/*
Implementación Práctica 03: Codificación voraz de Huffman
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que obtiene una codificación prefijo óptima con base al algoritmo de Huffman

Observaciones:

Compilación:

	gcc -o Descomprimir Descomprimir.c -lm

	./Descomprimir

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
	int k: índice para saber qué bit deberá ser manipulado
	int l: entero auxiliar para manipular la decodificación
	char c[11]: arreglo que contendrá en el primer valor el caracter y en los siguientes el valor de su frecuencia
	char d: caracter auxiliar con el que tomaremos los valores a decodificar
	char s[100]: arreglo de caracteres que contendrá el nombre del archivo a codificar
	unsigned long i: variable que servirá para iteraciones
	unsigned long j: valor que contendrá el número de caracteres que han sido leídos del archivo
	arbol *a: nodo auxiliar que mantendrá la raíz del árbol de frecuencias
	arbol A[256]: arreglo de árboles con los posibles caracteres
	FILE *f: apuntador al archivo que se va a abrir
	FILE *f2: apuntador al archivo que se va a escribir
	lista *L: apuntador que funcionará como una lista dinámica donde se añadirán los árboles
	int prev:  variable que contendrá el tamaño parcial del archivo
	unsigned long sz: variable que contendrá el tamaño total del archivo
	char *entrada: apuntador que hará la función de un arreglo dinámico para guardar el contenido del archivo 
	arbol *aux: nodo que nos ayudará a recorrer el árbol de frecuencias
	
*/
int main(){
	int k,l;
	char c[11],d,s[100];
	unsigned long i,j; 
	arbol *a; 
	arbol A[256];
	FILE *f=NULL;
	FILE *f2=NULL;
	lista *L;
	L=NULL;

	// Se lee el nombre del archivo desde consola.
	printf("Nombre del archivo:\n");
	scanf("%s",s);
	
	// Abrimos el archivo en modo lectura.
	f=fopen(s,"rb");

	// En caso de no ser válido, terminamos el programa.
	if(f==NULL){
		printf("No existe el archivo.");
		return -1;
	}

	// Recorremos el caracter NULL para eliminar la extensión incluída en la codificación
	s[strlen(s)-4]='\0'; 
	
	// Abrimos el archivo donde decodificaremos
	f2=fopen(s,"wb");
	
	// Medimos el tamaño del archivo
	int prev=ftell(f);
    fseek(f,0L,SEEK_END);
    unsigned long sz=(long)ftell(f);
    fseek(f,prev,SEEK_SET);
    printf("El archivo a leer mide %ld bytes\n",sz);	
	
	// Se pasa el contenido del archivo a un arreglo de caracteres
	char *entrada=(char*)malloc(sizeof(char)*sz);
	fread(entrada,sizeof(char),sz,f);
	
	// Se cargan los datos al arreglo de árboles
	for(i=0;i<256;i++){
		A[i].c = entrada[i*11]; // Guardamos el caracter acorde a la posición
		strncpy(c,entrada+i*11+1,10); // Los siguientes 10 caracteres nos dirán su frecuencia
		A[i].n = atol(c); // Convertimos el caracter a entero
		A[i].der=A[i].izq = NULL; // Iniciamos los apuntadores a los hijos en NULL
	}
	
	// Se ponen los árboles en una lista
	for(i=0;i<256;i++){
		insertaLista(&L,A+i);
	}
	
	// Se van juntando los árbole hasta que quede uno solo
	while(L->sig!=NULL){
		dosMenoresArboles(&L);
	}
	
	/*Se va recorriendo bit a bit el archivo fuente mientras 
	se va restaurando el original*/
	a=L->a; // Copiamos el primer árbol de la lista
	arbol *aux=a; // Colocamos el apuntador a ese árbol

	// Iniciamos en el caracter a partir del cual terminamos de tomar los valores de codificación
	// Concluiremos cuando ya hayamos llegado al valor total de frecuencias
	for(i=2816,j=0; j < a->n; i++){ 
		d=entrada[i];
		for(k=7;k>-1;k--){ // Analizaremos los 8 bits de cada caracter
			l=(d>>k)&1; // Por medio de una máscara extraemos el bit a analizar
			if(!l){ // Si el bit es 0, bajaremos a la izquierda
				aux=aux->izq;
			}
			else{ // Si es 1, bajaremos a la derecha
				aux=aux->der;
			}
			if(!(aux->izq)){ // En caso de llegar a una hoja
				j++; // Aumentamos n indicando que ya leímos una letra
				fprintf(f2,"%c",aux->c); // Imprimimos el caracter encontrado
				aux=a; // Volvemos al nodo raíz
			}
		}
	}
	
	// Cerramos ambos archivos utilizados.
	fclose(f);
	fclose(f2);

	// Liberamos la memoria dinámica solicitada
	free(entrada);
	return 0;
}
