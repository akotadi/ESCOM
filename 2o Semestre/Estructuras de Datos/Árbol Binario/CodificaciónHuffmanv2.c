#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "TADArbolBin.c"

arbol_bin* Huffman(arbol_bin h, arbol_bin* g, posicion p, int n){
	for (int i = 0; i < n; ++i)
	{
		if()
	}
	return g;
}

elemento* Frecuencia(char c[]){
	elemento* f;
	f=(elemento*)malloc(53*sizeof(elemento));
	for (int i = 0; i < 53; ++i)
	{
		(f[i]).i=0;
	}
	for (int i = 0; i < strlen(c); ++i)
	{
		if (c[i]==32)
		{
			(f[0].c) = ' ';
			(f[0].i)++;
		}
		else if (c[i]>62 && c[i]<93)
		{
			(f[c[i]-64].c) = c[i];
			(f[c[i]-64].i)++;
		}
		else if (c[i]>93)
		{
			(f[c[i]-70].c) = c[i];
			(f[c[i]-70].i)++;
		}
	}
	return f;
}


elemento* ReducirFrecuencia (elemento* f, int n, int m){
	elemento* g;
	elemento aux;
	g=(elemento*)malloc(n*sizeof(elemento));
	for (int i = 0; i < 53; ++i)
	{
		if ((f[i].i)!=0)
		{
			g[m]=f[i];
			m++;
		}
	}
	for (int i = 1; i < n; ++i)
	{
		for (int j = 0; j < n-1; ++j)
		{
			if(g[j].i>g[j+1].i)
			{
				aux=g[j];
				g[j]=g[j+1];
				g[j+1]=aux;
			}
		}
	}
	free(f);
	return g;
}

arbol_bin* CrearArboles(arbol_bin* a, elemento* f, int n){
	posicion p;
	a=(arbol_bin*)malloc(n*sizeof(arbol_bin));
	for (int i = 0; i < n; ++i)
	{
		Initialize(&a[i]);
		NewRightSon(&a[i],p,f[i]);
	}
	return a;
}

arbol_bin* Reacomodo(int n, arbol_bin* a, arbol_bin b){
	for (int i = 0; i < n-2; ++i)
	{
		if((ReadNode(&a[i+2],Root(&a[i+2])).i)<(ReadNode(&b,Root(&b)).i)){
			a[i]=a[i+2];
			a[i+1]=b;
		}
		else{
			a[i]=b;
			for (int j = i+1; j < n-1; ++j){
				a[j]=a[j+1];
			}
			break;
		}
	}

	/*for (int i = 0; i < n-1; ++i)
	{
		printf("Hay %i elementos\n",(a[i]->e.i));
	}*/
	return a;
}



int main (void)
{
	char c[500];
	elemento e,x,y;
	elemento* f,g;
	arbol_bin h,aux;
	arbol_bin* a;
	posicion p,q;
	int n=0;
	printf("\nIntroduce el texto a codificar: ");
	gets(c);
	f=Frecuencia(c);
	printf("\n");
	for (int i = 0; i < 53; ++i)
	{
		if(f[i].i!=0)
		{			
			printf("De %c hay %i elementos\n",f[i].c,f[i].i);
			n++;
		}
	}
	f=ReducirFrecuencia(f,n,0);
	printf("\n");
	g=(elemento*)malloc(n*sizeof(elemento));
	for (int i = 0; i < n; ++i)
	{
		printf("De %c hay %i elementos\n",f[i].c,f[i].i);
		
	}
	printf("\n");
	a=CrearArboles(a,f,n);
	Initialize(&h);

	for (int i = 0; i < n-1; ++i)
	{
		e.i=ReadNode(&a[0],Root(&a[0])).i+ReadNode(&a[1],Root(&a[1])).i;
		//printf("La frecuencia total es: %i\n",e.i);
		Initialize(&aux);
		NewRightSon(&aux,p,e);
		p=Root(&aux);
		NewTreeLeftSon(&aux,p,&a[0]);
		NewTreeRightSon(&aux,p,&a[1]);
		a=Reacomodo(n-i,a,aux);
		h=aux;
	}
	printf("La frecuencia total es: %i\n",ReadNode(&h,Root(&h)).i);
	g=(elemento*)malloc(n*sizeof(elemento));
	g=Huffman(h,g,p,n);
	
	//for (int i = 0; i < n; ++i)
		//Destroy(&a[i]);
	//Destroy(&aux);
	//Destroy(&h);
	free(a);
	free(f);
	return 0;
}