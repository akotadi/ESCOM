#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "TADArbolBin.c"

#define PESOBIT(bpos) 1<<bpos
#define CONSULTARBIT(var,bpos) (*(unsigned*)&var & PESOBIT(bpos))?1:0
#define PONE_1(var,bpos) *(unsigned*)&var |= PESOBIT(bpos)
#define PONE_0(var,bpos) *(unsigned*)&var &= ~(PESOBIT(bpos))
#define CAMBIA(var,bpos) *(unsigned*)&var ^= PESOBIT(bpos)

typedef struct{
	char c;
	char * codificacion;
	int i;
} codificado;

elemento* ReducirFrecuencia (elemento* f, int n, int m){
	elemento* g;
	elemento aux;
	g=(elemento*)malloc(n*sizeof(elemento));
	for (int i = 0; i < 53; ++i)
	{
		if ((f[i].i)!=0&&(f[i].c)!='\0')
		{
			g[m]=f[i];
			m++;
		}
	}
	for (int i = 1; i < n; ++i)
	{
		for (int j = n-1; j >= 0; --j)
		{
			if((g[i].i)!=0){
				if(g[j].i>g[j-1].i)
			{
				aux=g[j];
				g[j]=g[j-1];
				g[j-1]=aux;
			}
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
		p=Root(&letras[i]);
		Initialize(&letras[i]);
		NewRightSon(&letras[i], p, f[i]);
	}
	for (int i = 1; i < n; i++)//reliza el proceso el numero de letras iniciales menos 1
	{
		aux=CombinarArboles(&letras[n-i],&letras[n-1-i]);
		letras[n-1-i]=aux;
		int j=0;
		for(int j=0; j<n-i-1; j++){
			if (ReadNode(&letras[j],Root(&letras[j])).i<ReadNode(&aux,Root(&aux)).i)
			{
				for (int k = 1; k <= j; ++k)
				{
					letras[j+k]=letras[j+k-1];
				}
				letras[j]=aux;
			}	
		}
		/*do{
			if (ReadNode(&letras[j],Root(&letras[j])).i<ReadNode(&aux,Root(&aux)).i)
			{
				for (int k = 1; k <= j; ++k)
				{
					letras[j+k]=letras[j+k-1];
				}
				letras[j]=aux;
			}
			j++;
		}while(j<n-i);*/
	}
	aux=letras[0];
	return aux;
}

void escribir_binario (elemento letra, arbol_bin *arbol, int *binario)
{
	int i,j;
	posicion p;
	//rellenando todo con -1 para tener usarlo como indicador
	for (i = 0; i < 53; i++)
	{
		binario[i]=-1;
	}
	//poniendo el elemento tal como esta en el arbol
 		letra.usado=1;
 	//buscando el elemento letra
 		p = Search(arbol, letra);
 	//obteniendo el binario pero al reves
 	i=0;
 	while(p!=Root(arbol))
 	{
 		binario[i]=Binario (arbol, p);
 		p=Parent(arbol, p);
 		i++;
 	}
}

codificado * CodigoBinario(elemento* f, arbol_bin c, int n,codificado* bin){
	int m;
	posicion p;
	codificado* aux;
	char l;
	for (int i = 0; i < n; ++i)
	{
		bin[i].c = f[i].c;
		bin[i].i = f[i].i;
		bin[i].codificacion = (char*)malloc((f[i].i) * sizeof(char*));
		p = Search(&c, f[i]);
		int j=0;
 		while(p!=Root(&c))
 		{
 			m=p->binario;
 			if (m==1)
 				bin[l].codificacion[j]='1';
 			else
 				bin[l].codificacion[j]='0';
 			p=Parent(&c, p);
 			j++;
 		}
	}
	return aux;
}
	

int main (void)
{
	int n=0,j,binario[53];
	FILE* archivo,*cifrado;
	char c,arch[50],cifr[50];
	arbol_bin cod;
	arbol_bin* letras;
	elemento* f;
	codificado * bin;
	unsigned char resultado;
	f=(elemento*)malloc(53*sizeof(elemento));
 	for (int i = 0; i < 53; i++){
 		(f[i].i)=0;
 		(f[i].c)='\0';
	 }
	bin=(codificado*)malloc(53*sizeof(codificado));
	printf("Archivo que se codificara(con .txt sin espacios): \n");
	scanf("%s",&arch);
	printf("Archivo donde se codificara(con .txt sin espacios): \n");
	scanf("%s",&cifr);
 	archivo = fopen(arch,"r"); 
 	cifrado = fopen(cifr,"w");
 	c=fgetc(archivo);
 	while(c!=EOF)
	{
		if (c>=65&&c<=90)
		{
			(f[c-65].i)++;
			(f[c-65].c)=c;
		}
		else if (c>=97&&c<=122)
		{
			(f[c-97+26].i)++;
			(f[c-97+26].c)=c;
		}
		else if (c==32)
		{
			(f[52].i)++;
			(f[52].c)=c;
		}
		c=fgetc(archivo);
	}
	fclose(archivo);
	for (int i = 0; i < 53; ++i){
		if(f[i].i!=0)	
			n++;
	}
	f=ReducirFrecuencia(f,n,0);
	for(int i=0; i<n;i++){
		printf("\nFrecuencia de %i es: %i",f[i].c,f[i].i);
	}
	letras=(arbol_bin*)malloc(n*sizeof(arbol_bin));
	cod=CrearArbol(letras,f,n);
	printf("\nFrecuencia total: %i\n",ReadNode(&cod,Root(&cod)).i);
	archivo = fopen(arch,"r");
	c=fgetc(archivo);
	bin=CodigoBinario(f,cod,n,bin);
	while(c!=EOF)
	{
		//obtenemos el codigo binario y lo recojemos en un arreglo
		//el arreglo trae el binario de el caracter enviado pero al reves
		for(int i=0;i<53;i++){
			if(f[i].c==c){
				for (int j = 0; j < f[i].i; ++j)
				{
					fwrite(&bin[i].codificacion, sizeof(char), sizeof(bin[c].codificacion), cifrado);
				}
			}
		}
	 c=fgetc(archivo);
	}	
	printf("\n");
	fclose(archivo);
 	fclose(cifrado);
	free(f);
	free(letras);
	return 0;
}

