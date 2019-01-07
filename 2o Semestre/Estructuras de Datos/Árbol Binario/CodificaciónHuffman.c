#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "TADArbolBin.c"



elemento* Frecuencia(char c[]){
	elemento* f;
	f=(elemento*)malloc(53*sizeof(elemento));
	for (int i = 0; i < 53; ++i)
	{
		(f[i]).i=0;
	}
	for (int i = 0; i < strlen(c)-1; ++i)
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

/*void IniciarArboles(){

}*/



int main (void)
{
	char c[500];
	elemento* f;
	elemento e,d;
	posicion p,q,aux;
	arbol_bin a[53];
	arbol_bin x;
	arbol_bin* b;
	int n=0,m=0;
	printf("\nIntroduce el texto a codificar: ");
	gets(c);
	f=Frecuencia(c);
	printf("\n");
	for (int i = 0; i < 53; ++i)
	{
		if(f[i].i!=0)
			printf("De %c hay %i elementos\n",f[i].c,f[i].i);
	}
	for (int i = 0; i < 53; ++i)
	{
		//Initialize(&a[i]);
		if ((f[i].i)!=0)
		{
			Initialize(&a[i]);
			NewRightSon(&a[i],p,f[i]);
			n++;
		}
	}
	printf("Hola\n");
	b=(arbol_bin*)malloc(n*sizeof(arbol_bin));
	for (int i = 0; i < 53; ++i)
	{
		p=Root(&a[i]);
		if(p!=NULL)
		{
			b[m]=a[i];
			m++;
		}
	}
	printf("Hola2\n");
	/*for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < n-1; ++j)
		{
			p=Root(&b[j]);
			q=Root(&b[j+1]);
			e=ReadNode(&b[j],p);
			d=ReadNode(&b[j+1],q);
			if(e.i<d.i)
			{
				*aux = *b[j+1];
				*b[j+1] = *b[j];
				*b[j] = *aux;
			}
		}
	}*/
	//printf("Hola3\n");
	printf("%i %i\n",m,n);
	for (int i = 0; i < 53; ++i)
	{
		p=Root(&a[i]);
		if(p!=NULL)
			m++;
	}
	printf("%i %i\n",m,n);
	for (int i = 0; i < 53; ++i)
	{
		Destroy(&a[i]);
		/*if ((f[i].i)!=0)
		{
			Destroy(&a[i]);
		}*/
	}
	printf("Hola3\n");
	for (int i = 0; i < n; ++i)
	{
		Destroy(&b[i]);
	}
	printf("Hola4\n");
	free(b);
	return 0;
}