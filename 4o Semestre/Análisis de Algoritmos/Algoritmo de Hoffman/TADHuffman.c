#include "TADHuffman.h"
#include <stdio.h>
#include <stdlib.h>

//Operaciones de construcción
void InitializeList(lista *l)
{
	l->frente=NULL;
	l->final=NULL;
	l->taml=0;
	return;
}
void DestroyList(lista *l)
{
	posicionl aux;
	while(l->frente!=NULL)
	{
		aux=l->frente->siguiente;
		free(l->frente);
		l->frente=aux;
	}
	l->final=NULL;
	l->taml=0;
	return;
}

//Operaciones de posicionlamiento y busqueda
posicionl Final(lista *l)
{
	return l->final;
}

posicionl First(lista *l)
{
	return l->frente;
}

posicionl Following(lista *l, posicionl p)
{
	if(ValidatePosition(l,p))
	{
		return p->siguiente;
	}
	else
	{
		printf("ERROR: Following (P es INVALIDA)");
		exit(1);
	}
}

posicionl Previous(lista *l, posicionl p)
{
	posicionl aux,anterior=NULL;
	if(ValidatePosition(l,p))
	{
		aux=l->frente;
		while(aux!=NULL)
		{
			if(aux==p)
				return anterior;
			anterior=aux;
			aux=aux->siguiente;	
		}
	}
	else
	{
		printf("ERROR: Previus (P es INVALIDA)");
		exit(1);
	}	
}


posicionl SearchList(lista *l, elementol e)
{
	posicionl aux=l->frente;
	while(aux!=NULL)
	{
		if(memcmp(&e,&(aux->e),sizeof(elementol))==0)
		{
			return aux;
		}
		aux=aux->siguiente;
	}
	return NULL;
}


posicionl SearchCharList(lista *l, elementol e)
{
	posicionl aux=l->frente;
	while(aux!=NULL)
	{
		if(aux->e.c==e.c)
		{
			return aux;
		}
		aux=aux->siguiente;
	}
	return NULL;
}

//Operación de consulta
elementol Position(lista *l, posicionl p)
{
	if(ValidatePosition(l,p))
	{
		return p->e;
	}
	else
	{
		printf("ERROR (Position): La posicionl p es invalida");
		exit(1);
	}
}

boolean ValidatePosition(lista *l, posicionl p)
{
	posicionl aux;
	aux=l->frente;
	boolean b=FALSE;
	while(aux!=NULL&&b==FALSE)
	{
		if(aux==p)
		{
			b=TRUE;
		}
		else
		{
			aux=aux->siguiente;
		}
	}
	return b;
}

elementol Element(lista *l, int n)
{
	int i;
	posicionl aux;
	
	aux=l->frente;
	
	if(n>=1&&n<=Size(l))
	{
		for(i=2;i<=n;i++)
			aux=aux->siguiente;
		return aux->e;
	}
	else
	{
		printf("ERROR (Element): El parametro n es invalido");
		exit(1);
	}
}

posicionl ElementPosition(lista *l, int n)
{
	int i;
	posicionl aux=l->frente;
	if(n>=1&&n<=Size(l))
	{
		for(i=2;i<=n;i++)
			aux=aux->siguiente;
		return aux;
	}
	else
	{
		printf("ERROR (ElementPosition): El parametro n es invalido");
		exit(1);
	}	
	
}
int Size(lista *l)
{
	return l->taml;
}
boolean EmptyList(lista *l)
{
	return (Size(l)==0)?TRUE:FALSE;
}
//Operaciones de modificación
void Insert(lista *l, elementol e, posicionl p,boolean b)
{
	posicionl aux,p_ant;
	aux=malloc(sizeof(nodo));
	if(aux!=NULL)
	{
		aux->e=e;
		if(ValidatePosition(l,p))
		{
			if(b==TRUE)//Se inserta antes de P
			{
				if(p!=l->frente)
				{	
					aux->siguiente=p;
					p_ant=Previous(l,p);
					p_ant->siguiente=aux;
				}
				else
				{
					aux->siguiente=p;
					l->frente=aux;
				}
			}	
			else	//Se inserta despues de P	
			{
				if(p!=l->final)
				{
					aux->siguiente=p->siguiente;
					p->siguiente=aux;
				}
				else
				{
					aux->siguiente=NULL;
					l->final=aux;
				}
			}
		}
		else
		{//Insertar al frente de la lista
			aux->siguiente=l->frente;
			l->frente=aux;
			if(l->final==NULL)
				l->final=aux;
		}
		l->taml++;
	}
	else
	{
		printf("ERROR (Insert): No se pudo crear un nuevo elementol");
		exit(1);
	}
}

void Add(lista *l,elementol e)
{
	posicionl aux;
	aux=malloc(sizeof(nodo));
	aux->e=e;
	if(aux!=NULL)
	{
		//Se insertará al final
		aux->siguiente=NULL;
		if(l->final==NULL)//Si la lista estaba vacia 
		{
			l->frente=aux;
			l->final=aux;
		}
		else //Si no estaba vacia
		{
			l->final->siguiente=aux;
			l->final=aux;
		}
		l->taml++;
	}
	else
	{
		printf("ERROR (Add): No se pudo crear un nuevo elementol");
		exit(1);
	}
}
void Remove(lista *l,posicionl p)
{
	posicionl aux, ant_p;
	if(ValidatePosition(l,p))
	{
		if(p==l->frente)
		{
			aux=p->siguiente;
			free(p);
			l->frente=aux;
			if(p==l->final)
				l->final=NULL;
		}
		else if(p==l->final)
		{
			ant_p=Previous(l,p);
			ant_p->siguiente=NULL;
			l->final=ant_p;
			free(p);
		}
		else
		{
			ant_p=Previous(l,p);
			ant_p->siguiente=p->siguiente;
			free(p);
		}
		l->taml--;
	}
	
	else
	{
		printf("ERROR (Remove): P inexistente");
		exit(1);
	}
}
void Replace(lista *l,posicionl p, elementol e)
{
	if(ValidatePosition(l,p))
	{
		p->e=e;	
	}
	else
	{
		printf("ERROR (Replace): P inexistente");
		exit(1);		
	}
}

void InsertArbol(lista *l, posicion parbol){
	posicionl aux;
	aux=malloc(sizeof(nodol));
	if(aux!=NULL)
	{
			aux->arb=parbol;
			//Se insertará al final
			aux->siguiente=NULL;
			if(l->final==NULL)//Si la lista estaba vacia 
			{
				l->frente=aux;
				l->final=aux;
			}
			else //Si no estaba vacia
			{
				l->final->siguiente=aux;
				l->final=aux;
			}
	}
	else
	{
		printf("ERROR (InsertArbol): No se pudo insertar el arbol en la lista");
		exit(1);
	}
}

posicion ShowArbol(lista *l, posicionl pl){
	if(ValidatePosition(l,pl))
	{
		return pl->arb;
	}
	else
	{
		printf("ERROR: ShowArbol (P es INVALIDA)");
		exit(1);
	}
}








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
		if((*A)->izq != NULL)
			Destroy(&((*A)->izq));
		if((*A)->der != NULL)
			Destroy(&((*A)->der));
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
	else if ((*A)->izq == p || (*A)->der == p)
		return *A;
	else 
	{
		r = Parent(&((*A)->izq), p);
		if(r == NULL)
			r = Parent(&((*A)->der), p);
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
		r = P -> der;
	
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
		r = P -> izq;
	
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
		r = Search(&((*A)->izq), e);
		if(r == NULL)
			r = Search(&((*A)->der), e);
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
		r = NullNode(&((*A)->izq), p);
		if(r == TRUE)
			r = NullNode(&((*A)->der), p);
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
		*A = malloc(sizeof(nodo));
		(*A)->e = e;
		(*A)->izq = NULL;
		(*A)->der = NULL;
	}
	else if(!NullNode(A, p))
	{
		if(p->der != NULL){
			printf("Error: NewRightSon: ya existe");
			exit(1);
		}
		else
		{
			p->der = malloc(sizeof(nodo));
			p->der->e = e;
			p->der->izq = NULL;
			p->der->der = NULL;
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
		*A = malloc(sizeof(nodo));
		(*A)->e = e;
		(*A)->izq = NULL;
		(*A)->der = NULL;
	}
	else if(!NullNode(A, p))
	{
		if(p->izq != NULL){
			printf("Error: NewLeftSon: ya existe");
			exit(1);
		}
		else
		{
			p->izq = malloc(sizeof(nodo));
			p->izq->e = e;
			p->izq->izq = NULL;
			p->izq->der = NULL;
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
		Destroy(&(p->der));
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
	Destroy(&(p->izq));
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

