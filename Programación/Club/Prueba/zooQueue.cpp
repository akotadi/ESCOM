#include <iostream>
#include <stdio.h>
#include <stdlib.h>


using namespace std;

#define TRUE	1
#define FALSE	0

typedef unsigned char boolean;

typedef struct elemento
{
	
	string s;
	
} elemento;

typedef struct nodo
{
	elemento e;	
	struct nodo *siguiente;
}nodo;

typedef struct cola
{
	nodo *frente;
	nodo *final;
	int num_elem;
} cola;

void Initialize(cola * c)
{
	c->frente = NULL;
	c->final = NULL;
	c->num_elem=0;
	return;
}

void Queue(cola * c, elemento e)
{
	nodo * aux; 
	aux=(nodo*)malloc(sizeof(nodo));
	if(aux==NULL)
	{
		printf("\nERROR: Desbordamiento de cola");
		exit(1);		
	}
	
	aux->e = e;
	aux->siguiente = NULL;
	if (c->num_elem==0)
	{
		c->frente = aux;
		c->final = aux;
	}
	else
	{
		c->final->siguiente = aux;
		c->final = aux;
	}
	c->num_elem++;
	
	return;
}

elemento Dequeue(cola * c)
{
	elemento e;
	nodo *aux;
	
	if(c->num_elem==0)
	{
		printf("\nERROR: Subdesbordamiento de cola");
		exit(1);
	}
	else
	{
		e = c->frente->e;
		aux=c->frente->siguiente;
		free(c->frente);	
		c->num_elem--;
		c->frente = aux;
		if(c->num_elem==0) 
		{
			Initialize(c);
		}
	}
	
	return e;
}

boolean Empty(cola * c)
{
	return (c->num_elem==0) ? TRUE : FALSE;
}

elemento Front(cola * c)
{
	return c->frente->e;
}

elemento Final(cola * c)
{
	return c->final->e;
}

int Size(cola * c)
{
	return c->num_elem;
}

elemento Element(cola * c, int i)
{
	elemento r;
	nodo *n;
	int j;
	if (i>0&&i<=Size(c))
	{
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

void Destroy(cola * c)
{
	nodo * aux;	
	
	while(c->frente != NULL)
	{
		aux = c->frente;
		c->frente = c->frente->siguiente;
		free(aux);
	}
	
	c->num_elem=0;
	
	c->final = NULL;
}


int main(int argc, char const *argv[])
{
	char c;
	cola z;
	Initialize(&z);
	int x,y=0;
	elemento e;
	while(cin>>c){
		switch(c){
			case 'N':
				cin>>e.s;
				Queue(&z,e);
				break;
			case 'C':
				cin>>x;
				y+=x;
				break;
			case 'S':
				if (!Empty(&z))
				{
					e=Dequeue(&z);
					cout<<e.s<<endl;
				}
				break;
			case 'A':
				if (y!=0)
				{
					cout<<y/Size(&z);
					y=0;
				}
				else
					while(!Empty(&z)){
						e=Dequeue(&z);
						cout<<e.s<<endl;
					}
				break;
			case 'F':
				cout<<Size(&z)<<endl;
				break;
		}
	}
	return 0;
}