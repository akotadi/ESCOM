/*
IMPLEMENTACIONES DE LA LIBRERIA DEL TAD LISTA (TADLista.h)
AUTOR: Edgardo Adrián Franco Martínez (C) Marzo 2017
VERSIÓN: 1.1
DESCRIPCIÓN: TAD lista o (list)
Estructura de datos en la que se cumple:
Los elementos se consultan, añaden y se remueven con base en posiciones 
dentro de un arreglo lineal el cual cuenta con un frente o cabeza 
y un final o cola.
OBSERVACIONES: TADLista por definición es una Estructura de Datos dinámica. 
La implementación del presente código se realiza mediante el principo de "Lista Doblemente Ligada" i.e. nodos que contienen un elemento pero se encuentran ligados hacia el frente y atras de estos.
Frente                                                       Final
      ******    ******    ******    ******    ******    ******
NULL<-*    * <- *    * <- *    * <- *    * <- *    * <- *    *
      * N1 *    * N2 *    * N3 *    * N4 *    * N5 *    * N6 *
      *    * -> *    * -> *    * -> *    * -> *    * -> *    * -> NULL
      ******    ******    ******    ******    ******    ******    
COMPILACIÓN PARA GENERAR EL CÓDIGO OBJETO: gcc TADLista.c -c 
*/

//LIBRERAS
#include <stdio.h>
#include <stdlib.h>
#include "TADLista.h"

//DEFINICIÓN DE FUNCIONES

/***************************************************
Operaciones de construcción
***************************************************/
/*
void Initialize(lista *l)
Descripción: Inicializar lista (Iniciar una lista para su uso)
Recibe: lista *l (Referencia a la lista "l" a operar)
Devuelve:
Observaciones: El usuario a creado una lista y l tiene la referencia a ella, 
si esto no ha pasado se ocasionara un error.
*/
void Initialize(lista *l)
{
	l->frente = NULL;
	l -> final = NULL;
	l->tam = 0;
	return;
}

/*
void Destroy(lista *l)
Descripción: Destruir una lista (Recibe una lista L y la libera completamente)
Recibe: lista *l (Referencia a la lista "l" a operar)
Devuelve:
Observaciones: El usuario a creado una lista y l tiene la referencia a ella, 
si esto no ha pasado se ocasionara un error.
*/
void Destroy(lista *l)
{
	posicion aux,aux_siguiente;;
	aux=l->frente;
	//Destruir los nodos
	while(aux!=NULL)
	{
		aux_siguiente=aux->atras;
		free(aux);
		aux=aux_siguiente;
	}
	//Inicializar la lista
	Initialize(l);
}

/***************************************************
Operaciones de posicionamiento y busqueda
***************************************************/
/*
posicion Final(lista *l)
Descripción: Recibe una lista L y regresa la posición del final (Recibe una 
lista L y retorna la posición del elemento al final de esta.)
Recibe: lista *l (Referencia a la lista "l" a operar)
Devuelve: posición del nodo que contiene al elemeto final de la lista
Observaciones: El usuario a creado una lista y l tiene la referencia a ella, 
si esto no ha pasado se ocasionara un error.
*/
posicion Final(lista *l)
{
	return l->final;
}

/*
posicion First(lista *l)
Descripción: Recibe una lista L y regresa la posición del frente (Recibe una 
lista L y retorna la posición del elemento al frente de esta.)
Recibe: lista *l (Referencia a la lista "l" a operar)
Devuelve: posición del nodo que contiene al elemento del frente de la lista
Observaciones: El usuario a creado una lista y l tiene la referencia a ella, 
si esto no ha pasado se ocasionara un error.
*/
posicion First(lista *l)
{
	return l->frente;
}

/*
posicion Following(lista *l, posicion p)
Descripción: Recibe una lista L, una posición P y devuelve la posición del 
elemento siguiente de P
Recibe: lista *l y posicion p (Referencia a la lista "l" a operar y posición valida de la lista)
Devuelve: posición del nodo siguiente a la posicion dada
Observaciones: El usuario a creado una lista y l tiene la referencia a ella, p es una posicion valida de la lista, si esto no ha pasado se ocasionara un error.
*/
posicion Following(lista *l, posicion p)
{
	if(ValidatePosition(l,p))
	{
		return p->atras;
	}
	else
	{
		printf("\nERROR (Following): Posicion no valida o  lista vacia");		
		exit(1);
	}	
}

/*
posicion Previus(lista *l, posicion p)
Descripción: Recibe una lista L, una posición P y devuelve la posición del 
elemento anterior de P
Recibe: lista *l y posicion p (Referencia a la lista "l" a operar y posición valida de la lista)
Devuelve: posición del nodo anterior a la posicion dada
Observaciones: El usuario a creado una lista y l tiene la referencia a ella, p es una posicion valida de la lista, si esto no ha pasado se ocasionara un error.
*/
posicion Previus(lista *l, posicion p)
{
	
	if(ValidatePosition(l,p))
	{
		return p->adelante;
	}
	else
	{
		printf("ERROR (Previus): Posicion no valida o  lista vacia");		
		exit(1);
	}
}

/*
posicion Search(lista *l, elemento e)
Descripción: Recibe una lista L y un elemento e, regresa la posición que coincida exactamente con el elemento e.
Recibe: lista *l y un elemento e (Referencia a la lista "l" a operar y elemento a buscar en la lista)
Devuelve: posición del elemento en caso de ser encontrado, si no se encuentra se devuelve una posicion invalida
Observaciones: El usuario a creado una lista y l tiene la referencia a ella el elemento a buscar se compara directamente a nivel de bytes.
*/
posicion Search(lista *l, elemento e)
{
	posicion r=NULL,aux;
	
	if (l->tam>0)
	{
		aux=l->frente;
		while (aux!=NULL&&r==NULL)
		{
			if(memcmp(&e,&aux->e,sizeof(elemento))==0)
			{
				r=aux;
			}
			else 
			{
				aux=aux->atras;
			}
		}
	}
	return r;
}

//***************************************************
//Operaciónes de consulta
//***************************************************
/*
elemento Position(lista *l, posicion p)
Descripción: Recibe una lista L, una posición P y devuelve el elemento en dicha posición. 
Recibe: lista *l y una posicion p(Referencia a la lista "l" a operar posicion valida en la lista)
Devuelve: Elemento en la posicion dada, si la posicion es invalida se genera error.
Observaciones: La lista L es no vacía y la posición P es una posición valida.
*/
elemento Position(lista *l, posicion p)
{
	if(ValidatePosition(l,p))
	{
		return p->e;
	}
	else
	{
		printf("ERROR: Position(La poscion es invalida)");
		exit(1);
	}
}

/*
boolean ValidatePosition(lista *l, posicion p)
Descripción: Recibe una lista L, una posición P y devuelve TRUE si la posición es una posición P valida en la lista L y FALSE en caso contrario.
Recibe: lista *l y una posicion p(Referencia a la lista "l" a operar y una posicion)
Devuelve: Booleano 
Observaciones:
*/
boolean ValidatePosition(lista *l, posicion p)
{
	boolean r = FALSE;
	posicion aux;
	aux = l->frente;
	while (aux!=NULL && r!=TRUE)
	{
		if (aux==p)
			r = TRUE;
		
		aux = aux->atras;
	}
	return r;
}

/*
elemento Element(lista *l, int n)
Descripción: Recibe una lista y un índice (entre 1 y el tamaño de la lista) y devuelve el elemento que se encuentra en la lista en ese índice partiendo del frente de este =1 hacia atrás.
Recibe: lista *l y una entero
Devuelve: elemento 
Observaciones: Si la cola esta vacía o el índice se encuentra fuera del tamaño de la lista se produce error.
*/
elemento Element(lista *l, int n)
{
	elemento r;
	nodo *aux;
	int i;
	//Si el elemento solicitado esta entre 1 y el tamaño de la lista
	if (n>0&&n<=Size(l))
	{
		//Obtener el elemento en la posición n
		aux=l->frente;
		for(i=1;i<n;i++)
			aux=aux->atras;
		r=aux->e;
	}
	else
	{
		printf("\nERROR (Element): Se intenta acceder a elemento %d inexistente",n);
		exit(1);		
	}
	return r;	
}

/*
posicion ElementPosition(lista *l, int n)
Descripción: Recibe una lista y un índice (entre 1 y el tamaño de la lista) y devuelve la posicion del elemento que se encuentra en la lista en ese índice partiendo del frente de este =1 hacia atrás.
Recibe: lista *l y una entero
Devuelve: posicion 
Observaciones: Si la cola esta vacía o el índice se encuentra fuera del tamaño de la lista se produce error.
*/
posicion ElementPosition(lista *l, int n)
{
	posicion aux=NULL;
	int i;
	//Si el elemento solicitado esta entre 1 y el tamaño de la lista
	if (n>0&&n<=Size(l))
	{
		//Obtener el elemento en la posición n
		aux=l->frente;
		for(i=1;i<n;i++)
			aux=aux->atras;
	}
	return aux;		
}


/*
int Size(lista * l);
Descripción: Recibe una lista y devuelve el número de elemento que se encuentran en esta.
Recibe: lista *l (Referencia a la lista "l")
Devuelve: int (Tamaño de la lista)
Observaciones: El usuario a creado una lista,la lista fue correctamente inicializada.
*/
int Size(lista *l)
{
	return l->tam;
}

boolean Empty(lista *l)
{
	return (l->tam == 0) ? TRUE : FALSE;	
}

//***************************************************
//Operaciones de modificación
//***************************************************
void Insert(lista *l, elemento e, posicion p,boolean b)
{
	posicion aux;
	aux = malloc(sizeof(nodo));
		if (aux==NULL)
		{
			printf("ERROR (Insert): No se pudo crear un nuevo nodo");
			exit(1);
		}
		aux->e = e;
	
	//Si la posición es invalida se inserta el nodo al frente de la lista 
	if (!ValidatePosition (l,p))
	{
		aux->atras=l->frente;
		aux->adelante=NULL;
		l->frente=aux;
		if (l->tam==0) //Si no habia elementos en la lista 
			l->final=aux; //El elemento nuevo es el unico y el final de la lista apuntará también a el
		else
			aux->atras->adelante = aux;
	}
	else
	{
		//Si el parametro b es TRUE, se agrega el nodo antes de p
		if (b==TRUE)
		{
			aux->atras = p;
			aux->adelante = p->adelante;
			p->adelante = aux;
			if (p==l->frente) //Si p era el frente de la lista
				l->frente = aux; //aux es el nuevo frente
			else
				aux->adelante->atras = aux; //Reconectar el nodo de adelante de aux con el
		}
		//Si el parametro b es FALSO, se agrega el nodo despues de p
		else
		{
			aux->adelante = p;
			aux->atras = p->atras;
			p->atras = aux;
			if (p==l->final) //Si p era el elemento del final de la lista
				l->final = aux; //aux es el nuevo final
			else
				aux->atras->adelante = aux; //Reconectar el nodo de atras de aux con el
		}
	}
	//Incrementar el tamaño de la lista
	l->tam++;
}

void Add(lista *l,elemento e)
{
	posicion aux;
	aux = malloc(sizeof(nodo));
	if (aux==NULL)
	{
		printf("ERROR (Add): No se pudo crear un nuevo nodo");
		exit(1);
	}
	
	aux->e=e;	
	aux->atras=NULL;
	aux->adelante=l->final;
	l->final=aux;
	if (l->tam==0)
		l->frente=aux;
	else
		l->final->adelante->atras=l->final;
	l->tam++;	
}

void Remove(lista *l,posicion p)
{	
	if(ValidatePosition(l,p))
	{
		posicion adelante_p, atras_p;
		adelante_p=p->adelante;
		atras_p=p->atras;
		free(p);
		
		//Si se no remueve el elemento del frente
		if(adelante_p!=NULL)
			adelante_p->atras=atras_p; //El elemento del frente del removido en su parte de atras apuntará a el que estaba atras de p
		else
			l->frente=atras_p; //Si se removio el del frente de la lista entonces este cambia
			
		
		//Si se no remueve el elemento del final
		if(atras_p!=NULL)
			atras_p->adelante=adelante_p;//El elemento de atras del removido en su parte de adelante apuntará al que estaba delante de p
		else
			l->final=adelante_p; //Si se removio el del final de la lista entonces este cambia
		
		//Restar en uno el tamaño de la lista
		l->tam--;
	}
	else
	{
		printf("ERROR (Remove): La poscion es invalida");
		exit(1);
	}	
}

void Replace(lista *l,posicion p, elemento e)
{
	if(ValidatePosition(l,p))
	{
		p->e=e;
	}
	else
	{
		printf("ERROR (Replace): La poscion es invalida");
		exit(1);
	}		
}