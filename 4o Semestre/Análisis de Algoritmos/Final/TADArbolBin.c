/*TAD Arbol binario
ESTRUCTURAS DE DATOS 1CM7
ABRIL 2017
*/
#include <stdio.h>
#include <stdlib.h>
#include "TADArbolBin.h"

/*recibe<-árbol(A); 
Initialize (A)
Efecto: Recibe un árbol binario A y lo inicializa para su trabajo normal.
*/
void Initialize(arbol_bin *A){
	*A = NULL;

	return;
}

/*recibe<-árbol(A); 
Destroy (A)
Efecto: Recibe un árbol binario A y lo libera completamente.
*/
void Destroy(arbol_bin *A){
	if(*A == NULL)
		return;
	else
	{
		if((*A)->left != NULL)
			Destroy(&((*A)->left));
		if((*A)->right != NULL)
			Destroy(&((*A)->right));
		free(*A);
		return;
	}
}

/*recibe<-árbol(A); retorna -> posición
Root (A)

Efecto: Recibe un árbol binario A y retorna la posición de la raíz de A, si el árbol es vacío devuelve una posición nula.
*/
posicion Root(arbol_bin *A){
	return *A;
}


/*recibe<-árbol(A), posición(P); retorna -> posición
Parent(A,P)
Efecto: Recibe un árbol binario  A y una posición P, devuelve la posición de padre de p. 
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. Si P es la raíz se devuelve una posición nula.
*/
posicion Parent(arbol_bin *A, posicion p){
	posicion r = NULL;
	if(*A == NULL || *A == p)
		return NULL;
	else if ((*A)->left == p || (*A)->right == p)
		return *A;
	else 
	{
		r = Parent(&((*A)->left), p);
		if(r == NULL)
			r = Parent(&((*A)->right), p);
		return r;
	}
}

/*recibe<-árbol(A), posición(P); retorna -> posición
RightSon(A,P)
Efecto: Recibe un árbol binario A y una posición P, devuelve la posición del hijo derecho de p. 
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. Si P no tiene hijo derecho devuelve una posición nula.
*/
posicion RightSon(arbol_bin *A, posicion P)
{
	posicion r = NULL;
	if(!NullNode(A, P))
		r = P -> right;
	
	return r;
}
/*recibe<-árbol(A), posición(P); retorna -> posición
LeftSon(A,P)
Efecto: Recibe un árbol binario A y una posición P, devuelve la posición del hijo izquierdo de p. 
Requerimientos: El árbol A es no vacío y la posición P es una posición valida. Si P no tiene hijo izquierdo devuelve una posición nula.
*/
posicion LeftSon(arbol_bin *A, posicion P)
{
	posicion r = NULL;
	if(!NullNode(A, P))
		r = P -> left;
	
	return r;
}


/*recibe<-árbol(A), elemento (E); retorna -> posición
Search(A,E)
Efecto: Recibe un árbol binario A y un elemento E, devuelve la posición del elemento E en el árbol  A.
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. Si E no es encontrado devuelve una posición nula.
*/
posicion Search(arbol_bin *A, elemento e)
{
	
	posicion r = NULL;
	if(*A == NULL)
		return NULL;
	else if (memcmp(&((*A)->e),&e,sizeof(elemento))==0)
		return *A;
	else 
	{
		r = Search(&((*A)->left), e);
		if(r == NULL)
			r = Search(&((*A)->right), e);
		return r;
	}

}

/*recibe<-árbol(A); retorna -> booleano
Empty(A)
Efecto: Recibe un árbol binario A y devuelve verdadero en caso de que el árbol A este vacío, devuelve falso en caso contrario.
*/
boolean Empty(arbol_bin *A)
{
	boolean r;
	if(*A == NULL)
		r = TRUE;
	else 
		r = FALSE;
	
	return r;
}

/*recibe<-árbol(A), posición (P); retorna -> booleano
NullNode(A,P)
Efecto: Recibe un árbol binario A y una posición P, devuelve verdadero si la posición P del árbol A es nula o incorrecta y devuelve falso en caso contrario.
*/
boolean NullNode(arbol_bin *A, posicion p)
{
	boolean r;
	if(*A == NULL)
		return TRUE;
	else if (*A == p)
		return FALSE;	
	
	else 
	{
		r = NullNode(&((*A)->left), p);
		if(r == TRUE)
			r = NullNode(&((*A)->right), p);
		return r;
	}
}

/*recibe<-árbol(A), posición (P); retorna -> elemento
ReadNode(A,P)
Efecto: Recibe un árbol binario A y una posición P, devuelve el elemento en la posición P del árbol A.
Requerimientos: El árbol A es no vacío y la posición P es una posición valida..
*/
elemento ReadNode(arbol_bin *A, posicion p)
{
	elemento r;
	
	if(!NullNode(A, p)){
		r = p->e;
	}
	else
	{
		printf("Error: ReadNode");
		exit(1);
	}
	
	return r;
}

/*recibe<-árbol(A), posición (P), elemento E; 
NewRightSon(A,P,E)
Efecto: Recibe un árbol binario A, una posición P y un elemento E, se añade un nodo que contenga E como hijo derecho del nodo con posición P.
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. Si el árbol A es vacío se agrega a un nodo raíz con E. si P ya tiene un hijo derecho, se cancela la operación.
*/
void NewRightSon(arbol_bin *A, posicion p, elemento e)
{
	if(Empty(A))
	{
		*A = malloc(sizeof(node));
		(*A)->e = e;
		(*A)->left = NULL;
		(*A)->right = NULL;
	}
	else if(!NullNode(A, p))
	{
		if(p->right != NULL){
			printf("Error: NewRightSon: ya existe");
			exit(1);
		}
		else
		{
			p->right = malloc(sizeof(node));
			p->right->e = e;
			p->right->left = NULL;
			p->right->right = NULL;
		}
	}else{
		printf("Error: NewRightSon");
		exit(1);
	}
	return;
}

/*recibe<-árbol(A), posición (P), elemento E; 
NewLeftSon(A,P,E)
Efecto: Recibe un árbol binario A, una posición P y un elemento E, se añade un nodo que contenga E como hijo izquierdo del nodo con posición P.
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. Si el árbol A es vacío se agrega a un nodo raíz con E; si P ya tiene un hijo Izquierdo, se cancela la operación.
*/
void NewLeftSon(arbol_bin *A, posicion p, elemento e)
{
	if(Empty(A))
	{
		*A = malloc(sizeof(node));
		(*A)->e = e;
		(*A)->left = NULL;
		(*A)->right = NULL;
	}
	else if(!NullNode(A, p))
	{
		if(p->left != NULL){
			printf("Error: NewLeftSon: ya existe");
			exit(1);
		}
		else
		{
			p->left = malloc(sizeof(node));
			p->left->e = e;
			p->left->left = NULL;
			p->left->right = NULL;
		}
	}else{
		printf("Error: NewLeftSon");
		exit(1);
	}
	return;
}

/*recibe<-árbol(A), posición (P);
DeleteRightSon(A,P)
Efecto: Recibe un árbol binario A y una posición se elimina al hijo derecho y todos sus descendientes del nodo con posición P.
Requerimientos: El árbol A es no vacío y la posición P es una posición valida y tiene un hijo derecho.
*/
void DeleteRightSon(arbol_bin *A, posicion p)
{
	if(!NullNode(A, p))
	{
		Destroy(&(p->right));
	}
	else
	{
		printf("Error: DeleteRightSon");
		exit(1);
	}
	return;
}

/*recibe<-árbol(A), posición (P);
DeleteLeftSon(A,P)
Efecto: Recibe un árbol binario A y una posición se elimina al hijo izquierdo y todos sus descendientes del nodo con posición P.
Requerimientos: El árbol A es no vacío y la posición P es una posición valida y tiene un hijo izquierdo.
*/
void DeleteLeftSon(arbol_bin *A, posicion p)
{
	if(!NullNode(A, p))
	{
	Destroy(&(p->left));
	}
	else
	{
		printf("Error: DeleteLeftSon");
		exit(1);
	}
	return;
}


/*recibe<-árbol(A), posición (P);
DeleteNode(A,P)
Efecto: Recibe un árbol binario A y una posición P, se elimina al nodo con posición  P  y todos sus descendientes.
Requerimientos: El árbol A es no vacío y la posición P es una posición valida. 
*/
void DeleteNode(arbol_bin *A, posicion p)
{
	if(!NullNode(A, p))
	{
		Destroy(&p);
	}
	else
	{
		printf("Error: Delete");
		exit(1);
	}
	return;
}

/*recibe<-árbol(A), posición (P), elemento (E);
ReplaceNode(A,P)
Efecto: Recibe un árbol binario A, una posición P y un elemento E, se remplaza a E del nodo con posición P en A.
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. 
*/
void ReplaceNode(arbol_bin *A, posicion p, elemento e)
{
	if(!NullNode(A, p))
	{
		p->e = e;
	}
	else
	{
		printf("Error: ReplaceNode");
		exit(1);
	}
	return; 
}

/*recibe<-árbol(A), posición (P);
IsLeaf(A,P)
Efecto: Recibe un árbol binario A y una posición P, en caso de ser una hoja, devolverá true.
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. 
*/
boolean IsLeaf (arbol_bin *A,posicion p)
{
	
	if (!NullNode(A,p))
	{
		if (LeftSon(A, p)!=NULL)
		{
			return FALSE;
		}
		if (RightSon(A, p)!=NULL)
		{
			return FALSE;
		}
	}
	else
	{
		printf("ERROR: IsLeaf");
		exit(0);
	}
	return TRUE;

}

/*recibe<-árbol(A), posición (P);
BinaryValue(A,P)
Efecto: Recibe un árbol binario A y una posición P, devolverá el valor binario hasta esa posición.
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. 
*/
int BinaryValue (arbol_bin *A, posicion p)
{
	int bin;
	if (!NullNode(A, p))
	{
		bin=p->nBinario;
	}
	else
	{
		printf("ERROR: BinaryValue");
		//exit(0);
	}
	return bin;
}

//recibe dos arboles por referencia
//une estos dos arboles a un padre con la frecuencia de la suma de estos 2
//al primer arbol le asigna el valor binario 0 y al derecho 1
//pone ambos hijos como usados
arbol_bin MergeFathers (arbol_bin *A, arbol_bin *B)
{
	arbol_bin C;

	if (!NullNode(A, Root(A)) && !NullNode(B, Root(B)))
	{
		C = malloc(sizeof(node)); // creamos nodo
		(C)->e.nFrecuencia = ((*A)->e.nFrecuencia) + ((*B)->e.nFrecuencia); // sumamos frecuencias y se lo asignamos al padre
		(C)->e.cLetra = '?';
		(C)->left = *A;
		(C)->right = *B;
		(C)->right->nBinario = 1;
		(C)->left->nBinario = 0;
		(C)->right->e.nUsado = 1;
		(C)->left->e.nUsado = 1;
	}
	else
	{
		printf("ERROR: MergeFathers");
		exit(0);
	}
	
	return C;
}