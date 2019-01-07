//CABECERA DEL ARBOL BINARIO
#ifndef ArbolBin_H
#define ArbolBin_H

//BIBLIOTECA PARA EL MANEJO DE MEMORIA
#include <stdlib.h>

//Valores de verdadero y falso para el boolean
#define TRUE 1
#define FALSE 0

//Definimos un boolean
typedef unsigned char boolean;

/*Definimos un char sin signo, es más flexible a la hora
de realizar operaciones de bits*/
typedef unsigned char Char;

/*
Definimos al nodo del árbol binario, el cuál contendrá dos
apuntadores a otro nodo: al de la izquierda y al de la derecha;
además de un Char c, que es lo único que necesitaremos para
la codificación de Huffman
*/
typedef struct nodo{
	struct nodo * izq;
	struct nodo * der;
	Char c;
} nodo;

/*
Por mayor comodidad a la hora de usar punteros, definimos un
árbol binario como una estructura cuyo único elemento será
un apuntador a nodo, que será su raíz
*/
typedef struct{
	nodo * raiz;
} arbol_bin;

//Renombramos a un apuntador a nodo
typedef nodo* posicion;

//FUNCIONES MÍNIMAS REQUERIDAS PARA EL ALGORITMO DE CODIFICACIÓN DE HUFFMAN

//Devuelve un apuntador a arbol_bin listo para usar
arbol_bin * NewArbolBin();

/*Devuelve un apuntador a un nuevo nodo listo para usar, inicializándolo
con el Char c*/
posicion NewNode(Char c);

/*Dada una posición, devuelve TRUE si es una hoja, es decir, si tanto su
hijo izquierdo como derecho son nulos; en caso contrario devuelve FALSE*/
boolean IsLeaf(posicion p);

/*Dados dos apuntadores a árboles binarios, esta función los junta en uno
nuevo. Así, dichos árboles serán los hijos izquierdo y derecho de la raíz
de este nuevo árbol. Finalmente, devolvemos el apuntador al nuevo árbol
*/
arbol_bin * MergeTrees(arbol_bin *A, arbol_bin *B);

#endif