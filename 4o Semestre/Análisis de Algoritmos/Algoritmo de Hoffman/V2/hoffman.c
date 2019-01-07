#include<stdio.h>
#include<stdlib.h>
#include "TADArbolBin.h"

void main(){
	int cont=0, contadores[50], band=0, cont2, tam=0, cont3;
	elemento e, e2, e3;
	posicion raiz, raiz2, raiz3, raizaux, raizaux2, pos;
	arbol_bin arboles[300], arbaux, arbaux2;
	FILE *archivo;
	char *caracArch;

	//Se lee el archivo de texto
	archivo=fopen("Leer.txt", "r");
	if(archivo==NULL){
		printf("Error al abrir el archivo");
		exit(1);
	}
	else{
		//Se inicializan todos los árboles
		for(cont=0; cont<300; cont++)
			Initialize(&arboles[cont]);
		//Ciclo que repite hasta que se llegue al final del archivo
		while(!feof(archivo)){
			//Se lee línea por línea el archivo
			if((e.c=getc(archivo))!='\n'){
				//En caso de que el arbol en posición 0 no esté vacío
				if(!Empty(&arboles[0])){
					band=0;
					cont=0;
					//Ciclo en caso de que el arbol correspondiente al caracter no esté vacío
					do{
						//Se obtiene el elemento del árbol correspondiente al caracter y se incrementa su frecuencia
						raiz=Root(&arboles[cont]);
						e2=ReadNode(&arboles[cont], raiz);
						if(e.c==e2.c){
							e2.n=e2.n+1;
							ReplaceNode(&arboles[cont], raiz, e2);
							band=0;
							break;
						}
						else
							band=1;
						cont++;
					}while(!Empty(&arboles[cont]));
					//En caso de que se encuentre un caracter del cual todavía no hay árbol, se crea un nuevo árbol
					if(band==1){
						e.n=1;
						NewRightSon(&arboles[cont], pos, e);
					}
				}
				//En caso de que el árbol en la posición 0 del arreglo esté vacío
				else{
					e.n=1;
					NewRightSon(&arboles[0], pos, e);
				}
			}
		}

		cont=0;
		//Obtenemos la cantidad de árboles que no están vacíos
		do{
			raiz=Root(&arboles[cont]);
			e=ReadNode(&arboles[cont], raiz);
			printf("%c  %d\n", e.c, e.n);
			tam++;
			cont++;
		}while(!Empty(&arboles[cont]));
		printf("\n\n");
		/*Initialize(&arbaux);
		NewRightSon(&arbaux, pos, e);
		raizaux=Root(&arbaux);*/
		cont=0;
		band=0;
		do{
			raiz=Root(&arboles[cont]);
			e=ReadNode(&arboles[cont], raiz);
			cont2=cont;
			if(cont<tam){
				do{
					raiz2=Root(&arboles[cont2]);
					e2=ReadNode(&arboles[cont2], raiz2);
					if(cont2<tam-1){
						raiz3=Root(&arboles[cont2+1]);
						e3=ReadNode(&arboles[cont2+1], raiz3);
						if(e3.n<=e2.n){
							ReplaceNode(&arboles[cont2], raiz2, e3);
							ReplaceNode(&arboles[cont2+1], raiz3, e2);
						}
					}
					cont2++;
				}while(!Empty(&arboles[cont2]));
			}
			cont++;
		}while(!Empty(&arboles[cont]));

		cont=0;
		do{
			raiz=Root(&arboles[cont]);
			e=ReadNode(&arboles[cont], raiz);
			printf("%c  %d\n", e.c, e.n);
			cont++;
		}while(!Empty(&arboles[cont]));
	}
}