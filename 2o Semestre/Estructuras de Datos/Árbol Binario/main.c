#include <stdio.h>
#include "TADArbolBin.c"

void PreOrden(arbol_bin *a,posicion p);
void InOrden(arbol_bin *a,posicion p);
void PostOrden(arbol_bin *a,posicion p);

int main (void)
{
	arbol_bin a;
	elemento e;
	posicion p;
	
	Initialize(&a);		
	e.c='A';
	NewRightSon(&a,p,e);
	
	p=Root(&a);
	
	e.c='C';
	NewRightSon(&a,p,e);
		
	e.c='B';
	NewLeftSon(&a,p,e);
		
	p=RightSon(&a,p);	
	e.c='D';
	NewLeftSon(&a,p,e);
	e.c='E';
	NewRightSon(&a,p,e);
	
	p=Root(&a);
	PreOrden(&a,p);
	printf("\n");
	InOrden(&a,p);
	printf("\n");
	PostOrden(&a,p);
	
	Destroy(&a);
	
	return 0;
}

void PreOrden(arbol_bin *a,posicion p)
{
	elemento e;
	if(!NullNode(a,p))
	{
		e=ReadNode(a,p);
		printf("\t%c",e.c);
		PreOrden(a,LeftSon(a,p));
		PreOrden(a,RightSon(a,p));
	}
	return;
}

void InOrden(arbol_bin *a,posicion p)
{
	elemento e;
	if(!NullNode(a,p))
	{
		InOrden(a,LeftSon(a,p));
		e=ReadNode(a,p);
		printf("\t%c",e.c);
		InOrden(a,RightSon(a,p));
	}
	return;
}

void PostOrden(arbol_bin *a,posicion p)
{
	elemento e;
	if(!NullNode(a,p))
	{
		PostOrden(a,LeftSon(a,p));
		PostOrden(a,RightSon(a,p));
		e=ReadNode(a,p);
		printf("\t%c",e.c);
	}
	return;
}
