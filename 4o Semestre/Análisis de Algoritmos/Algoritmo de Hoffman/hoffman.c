#include<stdio.h>
#include<stdlib.h>
#include "TADArbolBin.h"

void main(){
	int cont=0, contadores[50], band=0;
	elemento e, e2;
	posicion raiz, pos;
	arbol_bin arboles[300];
	FILE *archivo;
	char *caracArch;

	archivo=fopen("Leer.txt", "r");
	if(archivo==NULL){
		printf("Error al abrir el archivo");
		exit(1);
	}
	else{
		Initialize(&arboles[0]);
		//Ciclo que repite hasta que se llegue al final del archivo
		while(!feof(archivo)){
			//Se guarda línea por línea en la matriz 
			if((e.c=getc(archivo))!='\n'){
				if(!Empty(&arboles[0])){
					band=0;
					cont=0;
					printf("%c", e.c);
					do{
						raiz=Root(&arboles[cont]);
						e2=ReadNode(&arboles[cont], raiz);
						if(e.c==e2.c){
							e2.n=e2.n+1;
							ReplaceNode(&arboles[cont], raiz, e2);
							printf("%c, %d\n", e2.c, e2.n);
							band=0;
							break;
						}
						else
							band=1;
						cont++;
					}while(!NullNode(&arboles[cont], raiz));
					if(band==1){
						Initialize(&arboles[cont]);
						e.n=1;
						NewRightSon(&arboles[cont], pos, e);
					}
				}
				else{
					e.n=1;
					NewRightSon(&arboles[0], pos, e);
				}
			}
		}
	}
}