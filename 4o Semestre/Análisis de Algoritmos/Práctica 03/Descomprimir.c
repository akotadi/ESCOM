/*
Implementaci�n Pr�ctica 03: Codificaci�n voraz de Huffman
Por: Git Gud (Equipo Arbol)
Versi�n: 1.0

Descripci�n: Programa que obtiene una codificaci�n prefijo �ptima con base al algoritmo de Huffman

Observaciones:

Compilaci�n:

	gcc -o Descomprimir Descomprimir.c -lm

	./Descomprimir

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
	int k: �ndice para saber qu� bit deber� ser manipulado
	int l: entero auxiliar para manipular la decodificaci�n
	char c[11]: arreglo que contendr� en el primer valor el caracter y en los siguientes el valor de su frecuencia
	char d: caracter auxiliar con el que tomaremos los valores a decodificar
	char s[100]: arreglo de caracteres que contendr� el nombre del archivo a codificar
	unsigned long i: variable que servir� para iteraciones
	unsigned long j: valor que contendr� el n�mero de caracteres que han sido le�dos del archivo
	arbol *a: nodo auxiliar que mantendr� la ra�z del �rbol de frecuencias
	arbol A[256]: arreglo de �rboles con los posibles caracteres
	FILE *f: apuntador al archivo que se va a abrir
	FILE *f2: apuntador al archivo que se va a escribir
	lista *L: apuntador que funcionar� como una lista din�mica donde se a�adir�n los �rboles
	int prev:  variable que contendr� el tama�o parcial del archivo
	unsigned long sz: variable que contendr� el tama�o total del archivo
	char *entrada: apuntador que har� la funci�n de un arreglo din�mico para guardar el contenido del archivo 
	arbol *aux: nodo que nos ayudar� a recorrer el �rbol de frecuencias
	
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

	// En caso de no ser v�lido, terminamos el programa.
	if(f==NULL){
		printf("No existe el archivo.");
		return -1;
	}

	// Recorremos el caracter NULL para eliminar la extensi�n inclu�da en la codificaci�n
	s[strlen(s)-4]='\0'; 
	
	// Abrimos el archivo donde decodificaremos
	f2=fopen(s,"wb");
	
	// Medimos el tama�o del archivo
	int prev=ftell(f);
    fseek(f,0L,SEEK_END);
    unsigned long sz=(long)ftell(f);
    fseek(f,prev,SEEK_SET);
    printf("El archivo a leer mide %ld bytes\n",sz);	
	
	// Se pasa el contenido del archivo a un arreglo de caracteres
	char *entrada=(char*)malloc(sizeof(char)*sz);
	fread(entrada,sizeof(char),sz,f);
	
	// Se cargan los datos al arreglo de �rboles
	for(i=0;i<256;i++){
		A[i].c = entrada[i*11]; // Guardamos el caracter acorde a la posici�n
		strncpy(c,entrada+i*11+1,10); // Los siguientes 10 caracteres nos dir�n su frecuencia
		A[i].n = atol(c); // Convertimos el caracter a entero
		A[i].der=A[i].izq = NULL; // Iniciamos los apuntadores a los hijos en NULL
	}
	
	// Se ponen los �rboles en una lista
	for(i=0;i<256;i++){
		insertaLista(&L,A+i);
	}
	
	// Se van juntando los �rbole hasta que quede uno solo
	while(L->sig!=NULL){
		dosMenoresArboles(&L);
	}
	
	/*Se va recorriendo bit a bit el archivo fuente mientras 
	se va restaurando el original*/
	a=L->a; // Copiamos el primer �rbol de la lista
	arbol *aux=a; // Colocamos el apuntador a ese �rbol

	// Iniciamos en el caracter a partir del cual terminamos de tomar los valores de codificaci�n
	// Concluiremos cuando ya hayamos llegado al valor total de frecuencias
	for(i=2816,j=0; j < a->n; i++){ 
		d=entrada[i];
		for(k=7;k>-1;k--){ // Analizaremos los 8 bits de cada caracter
			l=(d>>k)&1; // Por medio de una m�scara extraemos el bit a analizar
			if(!l){ // Si el bit es 0, bajaremos a la izquierda
				aux=aux->izq;
			}
			else{ // Si es 1, bajaremos a la derecha
				aux=aux->der;
			}
			if(!(aux->izq)){ // En caso de llegar a una hoja
				j++; // Aumentamos n indicando que ya le�mos una letra
				fprintf(f2,"%c",aux->c); // Imprimimos el caracter encontrado
				aux=a; // Volvemos al nodo ra�z
			}
		}
	}
	
	// Cerramos ambos archivos utilizados.
	fclose(f);
	fclose(f2);

	// Liberamos la memoria din�mica solicitada
	free(entrada);
	return 0;
}
