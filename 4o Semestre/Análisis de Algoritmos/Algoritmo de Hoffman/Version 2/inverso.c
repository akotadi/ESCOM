#include <stdlib.h>
#include <stdio.h>
#include "TADArbolBin.h"
#define PESOBIT(bpos) 1<<bpos
#define CONSULTARBIT(var,bpos) (*(unsigned*)&var & PESOBIT(bpos))?1:0
#define PONE_1(var,bpos) *(unsigned*)&var |= PESOBIT(bpos)
#define PONE_0(var,bpos) *(unsigned*)&var &= ~(PESOBIT(bpos))
#define CAMBIA(var,bpos) *(unsigned*)&var ^= PESOBIT(bpos)

arbol_bin lista[260];
char nombre[1000], va[1000];
int veces[260];
char actual[20];
int longitud[260];
char codigo[260][20];

void Recorrido(arbol_bin a, posicion p,  int ultimo){
	if(p == NULL) return;
	if(LeftSon(&a,p) == NULL && RightSon(&a,p) == NULL){
		int i ;		
		for(i = 0 ; i < ultimo ; i ++)
			codigo[(unsigned char) (p -> e).c][i] = actual[i];
		longitud[(unsigned char) (p -> e).c] = ultimo;
	} else {
		actual[ultimo] = 0;
		Recorrido(a,LeftSon(&a,p),ultimo+1);
		actual[ultimo] = 1;
		Recorrido(a,RightSon(&a,p),ultimo+1);
	}
}

int main (void)
{
	
	FILE *entrada,*param;
	unsigned char *bytes;

	printf("Leer de : ");
	scanf("%s",nombre);
	printf("Escribir en : ");
	scanf("%s",va);

	entrada = fopen(nombre, "r"); 
	fseek(entrada, 0, SEEK_END);
	int tam = ftell(entrada);       
	fseek(entrada, 0, SEEK_SET);	
	bytes = (unsigned char*)malloc((tam+1)*sizeof(unsigned char));
	fread(bytes, tam, 1, entrada);
	fclose(entrada); 

	param = fopen("parametros.txt","r");
	int original , comp, i;
	fscanf(param,"%d",&original);
	fscanf(param,"%d",&comp);
	for( i = 0 ; i < 256 ; i ++){
		fscanf(param,"%d",&veces[i]);
	}
	unsigned char * completo;
	completo = (unsigned char*)malloc((original)*sizeof(unsigned char));
	fclose(param);

	int buenos = 0;
	for(i = 0 ; i < 256 ; i ++)
	{
		elemento aux;
		aux.c = i;
		if(veces[i] != 0)
		{
			aux.entero = veces[i];
			buenos++;
		}
		else
		{
			aux.entero = tam*2;
		}
		NewRightSon(&lista[i], lista[i] , aux);
	}

	for(i = 0 ; i < buenos-1 ; i ++)
	{
		int menor1 = tam*2 , menor2 = tam*2;
		int pos1 = 0 , pos2 = 0, j;
		for(j = 0 ; j < 256 ; j ++){
			elemento aux = lista[j] -> e;
			if(aux.entero < menor1 ){
				menor2 = menor1;
				pos2 = pos1;
				menor1 = aux.entero;
				pos1 = j;
			} else if(aux.entero < menor2){
				menor2 = aux.entero;
				pos2 = j;
			}
		}

		arbol_bin nuevo;		
		Initialize(&nuevo);
		elemento e;
		e.c = 0;
		e.entero = menor1 + menor2;
		NewRightSon(&nuevo , nuevo , e);
		nuevo -> der = lista[pos1];
		nuevo -> izq = lista[pos2];		
		lista[pos1] = nuevo;
		lista[pos2] = NULL;
		e.c = 0 , e.entero = tam*2;
		NewLeftSon(&lista[pos2], lista[pos2] , e);	
	}

	int menor = tam*2;
	int pos = 0;
	for(i = 0 ; i < 256 ; i ++){
		elemento aux = lista[i] -> e;
		if(aux.entero < menor ){			
			menor = aux.entero;
			pos = i;
		} 
	}

	arbol_bin a = lista[pos];	
	posicion p = a;
	int ultimo = 0;
	int pb = 0;
	int llevo = 0;

	printf("Ejecutando...\n");	

	for(i = 0 ; i < comp ; i ++){
		int aux = CONSULTARBIT(bytes[ultimo],pb);
		if(aux == 0){
			p = LeftSon(&a,p);
		} else {
			p = RightSon(&a,p);
		}
		if(LeftSon(&a,p) == NULL && RightSon(&a,p) == NULL ){
			completo[llevo] = (p -> e).c;			
			llevo++;
			p = a;			
		}
		pb++;
		if(pb == 8){
			ultimo++;
			pb = 0;
		}
	}
	printf("\n");
	

	FILE *salida = fopen(va,"w");
	fwrite(completo,original, 1, salida);
	fseek(salida, 0, SEEK_SET);	
	fclose(salida);
	
	Destroy(&a);
	
	return 0;
}