/*
IMPLEMENTACION DE LA LIBRERIA DEL TAD COLA ESTATICA (TADColaEst.h)
AUTOR: Edgardo Adri�n Franco Mart�nez (C) Febrero 2017
VERSI�N: 1.6
DESCRIPCI�N: TAD cola o Queue.
Estructura de datos en la que se cumple:
Los elementos se insertan en un extremo (el posterior) y 
la supresiones tienen lugar en el otro extremo (frente).
OBSERVACIONES: Hablamos de una Estructura de datos din�mica 
cuando se le asigna memoria a medida que es necesitada, 
durante la ejecuci�n del programa. 
COMPILACI�N PARA GENERAR EL C�DIGO OBJETO: gcc -c TADColaDin.c
*/

//LIBRERAS
#include "TADColaDin.h"
#include <stdio.h>
#include <stdlib.h>

/*
void Initialize(cola *c);
Descripci�n: Inicializar cola (Iniciar una cola para su uso)
Recibe: cola *c (Referencia a la cola "c" a operar)
Devuelve:
Observaciones: El usuario a creado una cola y c tiene la referencia a ella, 
si esto no ha pasado se ocasionara un error.
*/
void Initialize(cola * c)
{
	c->frente = NULL;
	c->final = NULL;
	c->num_elem=0;
	return;
}

/*
void Queue(cola * c, elemento e);
Descripci�n: Recibe una cola y agrega un elemento al final de ella.
Recibe: cola *c (Referencia a la cola "c" a operar) elemento e (Elemento a encolar)
Devuelve:
Observaciones: El usuario a creado una cola y ha sido inicializada y c tiene la referencia a ella, 
si esto no ha pasado se ocasionara un error.
*/
void Queue(cola * c, elemento e)
{
	//Apuntador a elemento 
	nodo * aux; 
	
	//Crear un bloque de memoria para un elemento y mantener su referencia en aux	
	aux=(nodo *)malloc(sizeof(nodo));
	
	//Si malloc no pudo devolver un bloque de memoria indicar el error
	if(aux==NULL)
	{
		printf("\nERROR: Desbordamiento de cola");
		exit(1);		
	}
	
	//Introducir el elemento al bloque referenciado por aux
	aux->e = e;
	
	//Colocar el NULL a el apuntador siguiente del nuevo elemento
	aux->siguiente = NULL;
	
	//Si la cola esta vacia, los apuntadores de la cola apuntar�n al nuevo elemento
	if (c->num_elem==0)
	{
		c->frente = aux;
		c->final = aux;
	}
	//Si la cola ya tiene elementos
	else
	{
		//El elemento del final apuntar� al nuevo elemento 		
		c->final->siguiente = aux;
		//El final de la cola apuntar� al nuevo elemento
		c->final = aux;
	}
	//Incrementar el n�mero de elementos en la cola 
	c->num_elem++;
	
	return;
}

/*
elemento Dequeue(cola * c);
Descripci�n: Recibe una cola y devuelve el elemento que se encuentra al 
frente de esta, quit�ndolo de la cola.
Recibe: cola *c (Referencia a la cola "c" a operar)
Devuelve: elemento (Elemento desencolado)
Observaciones: El usuario a creado una cola y la cola tiene elementos, si no 
se genera un error y se termina el programa.
*/
elemento Dequeue(cola * c)
{
	elemento e;	//Elemento a retornar
	nodo *aux; //Apuntador auxiliar 
	
	//Si la cola esta vacia (Subdesbordamiento de cola)
	if(c->num_elem==0)
	{
		printf("\nERROR: Subdesbordamiento de cola");
		exit(1);
	}
	//Si la cola tiene elementos
	else
	{
		//Almacenar el elemento a retornar
		e = c->frente->e;
		
		//Guardar la direcci�n del siguiente nodo
		aux=c->frente->siguiente;
		
		//Destruir el bloque de memoria del elemento al frente
		free(c->frente);	
		
		//Decrementar el n�mero de eleme en la cola
		c->num_elem--;

		//El nuevo frente de la cola es aux (Siguiente del frente)
		c->frente = aux;
		
		//Si la cola ha quedado vacia se inicializa (c->frente=NULL, c->final=NULL)
		if(c->num_elem==0) 
		{
			Initialize(c);
		}
	}
	
	//Retornar al elemento desencolado
	return e;
}


/*
boolean Empty(cola * c);
Descripci�n: Recibe una cola y TRUE si la cola esta vacia y FALSE en caso contrario
Recibe: cola *c (Referencia a la cola "c" a verificar)
Devuelve: boolean TRUE O FALSE
Observaciones: El usuario a creado una cola y la cola fue correctamente inicializada
*/
boolean Empty(cola * c)
{
	return (c->num_elem==0) ? TRUE : FALSE;
}

/*
elemento Front(cola * c);
Descripci�n: Recibe una cola y devuelve el elemento que se encuentra al frente de esta.
Recibe: cola *c (Referencia a la cola "c")
Devuelve: elemento del frente de la cola
Observaciones: El usuario a creado una cola,la cola fue correctamente inicializada, esta 
tiene elementos de lo contrario devolvera ERROR. *No se modifica el TAD
*/
elemento Front(cola * c)
{
	return c->frente->e;
}

/*
elemento Final(cola * c);
Descripci�n: Recibe una cola y devuelve el elemento que se encuentra al final de esta.
Recibe: cola *c (Referencia a la cola "c")
Devuelve: elemento del final de la cola
Observaciones: El usuario a creado una cola,la cola fue correctamente inicializada, esta 
tiene elementos de lo contrario devolvera ERROR. *No se modifica el TAD
*/
elemento Final(cola * c)
{
	return c->final->e;
}

/*
int Size(cola * c);
Descripci�n: Recibe una cola y devuelve el n�mero de elemento que se encuentran en esta.
Recibe: cola *c (Referencia a la cola "c")
Devuelve: int (Tama�o de la cola)
Observaciones: El usuario a creado una cola,la cola fue correctamente inicializada, esta 
*No se modifica el TAD
*/
int Size(cola * c)
{
	return c->num_elem;
}

/*
void Element(cola * c, int i);
Descripci�n: Recibe una cola y un n�mero de elemento de 1 al tama�o de la cola y retorna el elemento de esa posici�n.
Devuelve: elemento en la posicion i de la cola
Observaciones: El n�mero i debera estar entre 1 y el tama�o de la cola, si esta es vacia o mas peque�a se provoca un error.
*/
elemento Element(cola * c, int i)
{
	elemento r;
	nodo *n;
	int j;
	//Si el elemento solicitado esta entre 1 y el tama�o de la cola
	if (i>0&&i<=Size(c))
	{
		//Obtener el elemento en la posici�n i
		n=c->frente;
		for(j=1;j<i;j++)
			n=n->siguiente;
		r=n->e;
	}
	else
	{
		printf("\nERROR: (Element) Se intenta acceder a elemento inexistente");
		exit(1);
	}
	return r;
}

/*
void Destroy(cola * c);
Descripci�n: Recibe una cola y la libera completamente.
Recibe: cola *c (Referencia a la cola "c" a operar)
Devuelve:
Observaciones: La cola se destruye y se inicializa.
*/
void Destroy(cola * c)
{
	nodo * aux;	//Apuntador auxiliar a elemento	
	
	//Mientras el apuntador del frente de la cola no sea NULL
	while(c->frente != NULL)
	{
		//Guardar la referencia al frente
		aux = c->frente;
		
		//El nuevo frente es el siguiente
		c->frente = c->frente->siguiente;
		
		//Liberar el antiguo frente de memoria
		free(aux);
	}
	
	//El n�mero de elementos en la cola es 0
	c->num_elem=0;
	
	//Colocar el frente (ya quedo en NULL seg�n como se fue destruyendo) y final inicializado
	c->final = NULL;
}
