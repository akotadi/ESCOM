//IMPLEMETNACIÓN DE LAS FUNCIONES MÍNIMAS DEL ÁRBOL BINARIO
#include "ArbolBin.h"

//Devuelve un apuntador a arbol_bin listo para usar
arbol_bin * NewArbolBin(){
	/*Usando calloc, a diferencia de malloc, automáticamente
	la memoria se inicializa con el tipo predeterminado de cada dato*/
	arbol_bin * A = calloc(1, sizeof(arbol_bin));
	return A;
}

/*Devuelve un apuntador a un nuevo nodo listo para usar, inicializándolo
con el Char c*/
posicion NewNode(Char c){
	posicion nuevo = calloc(1, sizeof(nodo));
	nuevo->c = c;
	return nuevo;
}

/*Dada una posición, devuelve TRUE si es una hoja, es decir, si tanto su
hijo izquierdo como derecho son nulos; en caso contrario devuelve FALSE*/
boolean IsLeaf(posicion p){
	return p->izq == NULL && p->der == NULL;
}

/*Dados dos apuntadores a árboles binarios, esta función los junta en uno
nuevo. Así, dichos árboles serán los hijos izquierdo y derecho de la raíz
de este nuevo árbol. Finalmente, devolvemos el apuntador al nuevo árbol
*/
arbol_bin * MergeTrees(arbol_bin *A, arbol_bin *B){
	arbol_bin * C = NewArbolBin();
	//Hacemos que la raíz tenga por default el Char 0
	C->raiz = NewNode(0);
	C->raiz->izq = A->raiz;
	C->raiz->der = B->raiz;
	return C;
}