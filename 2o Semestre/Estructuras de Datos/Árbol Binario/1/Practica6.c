#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "TADArbolBin.c"

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
		for (int j = n-1; j >= 0; --j)
		{
			if(g[j].i>g[j-1].i)
			{
				aux=g[j];
				g[j]=g[j-1];
				g[j-1]=aux;
			}
		}
	}
	free(f);
	return g;
}

arbol_bin CrearArbol(arbol_bin *letras,elemento *f, int n){
	posicion p,q;
	arbol_bin aux, *sup;
	for (int i = 0; i < n; ++i)
	{
		Initialize(&letras[i]);
		NewRightSon(&letras[i], p, f[i]);
	}
	for (int i = 1; i < n; i++)//reliza el proceso el numero de letras iniciales menos 1
	{
		aux=CombinarArboles(&letras[n-i],&letras[n-1-i]);
		letras[n-1-i]=aux;
		int j=0;
		do{
			sup=letras;
			if (ReadNode(&letras[j],Root(&letras[j])).i<ReadNode(&aux,Root(&aux)).i)
			{
				sup[j]=aux;
				for (int k = 1; k <= j; ++k)
				{
					sup[j+k]=letras[j+k-1];
				}
			}
			letras=sup;
			j++;
		}while(j<n-i);
	}
	aux=letras[0];
	return aux;
}

char** CodigoBinario(elemento* f, arbol_bin c, int n,char** let){
	int bin[100],m;
	posicion p;
	char** aux;
	for (int i = 0; i < n; ++i)
	{
		aux[i]=f[i].c;
		p = Search(&c, f[i]);
		int j=0;
 		while(p!=Root(&c))
 		{
 			m=p->binario;
 			if (m==1)
 				aux[i][j]='1';
 			else
 				aux[i][j]='0';
 			p=Parent(&c, p);
 			j++;
 		}
	}
	return aux;
}
	

int main (void)
{
	int n=0;
	FILE* archivo,archivo2;
	char c,arch[50],arch2[50];
	char* let;
	arbol_bin cod;
	arbol_bin* letras;
	elemento* f;
	f=(elemento*)malloc(53*sizeof(elemento));
 	for (i = 0; i < 53; i++)
		(f[i].frec)=0;
	printf("Archivo que se codificara(con .txt sin espacios): \n");
	scanf("%s",&arch);
	printf("Archivo donde se codificara(con .txt sin espacios): \n");
	scanf("%s",&arch2);
 	archivo = fopen(arch,"r"); // abrir archivo en modo de lectura(read)
 	do
	{
		c=fgetc(arch);
		if (c>=65&&c<=90)//mayusculas
		{
			(f[c-65].i)++;
			(f[c-65].c)=c;
		}
		else if (c>=97&&c<=122)//minisculas
		{
			(f[c-97+26].i)++;
			(f[c-97+26].c)=c;
		}
		else if (c==32)//espacio
		{
			(f[52].i)++;
			(f[52].c)=c;
		}
	}
	while(c!=EOF);
	fclose(arch);
	for (int i = 0; i < 53; ++i){
		if(f[i].i!=0)	
			n++;
	}
	let = (char **) malloc(n*sizeof(char *)); // Crea el arreglo dinámico que simulará el problema
	for(int i=0; i<n;i++)
		let[i]=(char* )malloc(53*sizeof(char));
	for (int i = 0; i < 53; ++i)
		let[i]='\0'
	f=ReducirFrecuencia(f,n,0);
	letras=(arbol_bin*)malloc(n*sizeof(arbol_bin));
	cod=CrearArbol(letras,f,n);
	let=CodigoBinario(f,c,n,let);
	archivo = fopen(arch,"r");
	archivo2 = fopen(arch2,"w");
	c=fgetc(texto);
	while(c!=EOF)
	{
		for (int i = 0; i < n; ++i)
		{
			if (c==let[i][0].c)
				fwrite(&let[i],sizeof(char),sizeof(&let[i]),archivo2);
		}
	}
	fclose(arch);
	fclose(arch2);
	free(f);
	for (int i = 0; i < n; ++i) // Libera la memoria utilizada por el arreglo dinámico
		free(let[i]);
	free(let);
	free(letras);
	return 0;
}