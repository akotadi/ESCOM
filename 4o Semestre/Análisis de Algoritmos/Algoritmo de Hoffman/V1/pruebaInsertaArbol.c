#include<stdio.h>
#include<stdlib.h>
#include "TADHuffman.h"

void main(){
	int cont=0, contadores[50], band=0;
	lista l, larch;
	elemento e;
	elementol elem, elem2;
	posicion pos, pa, paux3, paux4;
	posicionl pl, paux, paux2, plarc;
	arbol_bin arboles[300];
	FILE *archivo;
	char *caracArch;

	archivo=fopen("Leer.txt", "r");
	if(archivo==NULL){
		printf("Error al abrir el archivo");
		exit(1);
	}
	else{
		InitializeList(&larch);
		//Ciclo que repite hasta que se llegue al final del archivo
		while(!feof(archivo)){
			//Se guarda línea por línea en la matriz 
			if((elem.c=getc(archivo))!='\n'){
				if(!EmptyList(&larch)){
					band=0;
					cont=1;
					while(cont<=Size(&larch)){
						elem2=Element(&larch, cont);
						paux=ElementPosition(&larch, cont);
						if(elem.c==elem2.c){
							elem2.n=elem2.n+1;
							Replace(&larch, paux, elem2);
							printf("%c, %d     %d\n", elem2.c, elem2.n, Size(&larch));
							band=0;
							break;
						}
						else
							band=1;
						cont++;
					}
					if(band==1){
						elem.n=1;
						Add(&larch, elem);
					}
				}
				else{
					elem.n=1;
					Add(&larch, elem);
				}
			}
		}

	}

	InitializeList(&l);

	for(cont=0; cont<sizeof(arboles); cont++){
		Initialize(&arboles[cont]);
	}
	/*for(cont=1; cont<Size(&larch); cont++){
		elem=Element(&larch, cont);
		switch(elem.c){
			case 'a':
				contadores[0]++;
				break;
			case 'b':
				contadores[1]++;
				break;
			case 'c':
				contadores[2]++;
				break;
			case 'd':
				contadores[3]++;
				break;
			case 'e':
				contadores[4]++;
				break;
			case 'f':
				contadores[5]++;
				break;
			case 'g':
				contadores[6]++;
				break;
			case 'h':
				contadores[7]++;
				break;
			case 'i':
				contadores[8]++;
				break;
			case 'j':
				contadores[9]++;
				break;
			default:
				break;
		}
	}*/
	elem2.c='a';
	paux=SearchCharList(&larch, elem2);
	printf("%d", paux);
	elem2=Position(&larch, paux);
	printf("%c", elem2.c);
	e.c=elem2.c;
	e.n=elem2.n;
	NewRightSon(&a, pos, e);
	pa=Root(&a);
	pl=Final(&l);
	InsertArbol(&l, pa);
	printf("Primer arbol:%d\n", pa);
	e.c='b';
	e.n=contadores[1];
	NewRightSon(&b, pos, e);
	pa=Root(&b);
	pl=Final(&l);
	InsertArbol(&l, pa);
	e.c='c';
	e.n=contadores[2];
	NewRightSon(&c, pos, e);
	pa=Root(&c);
	pl=Final(&l);
	InsertArbol(&l, pa);
	e.c='d';
	e.n=contadores[3];
	NewRightSon(&d, pos, e);
	pa=Root(&d);
	pl=Final(&l);
	InsertArbol(&l, pa);
	e.c='e';
	e.n=contadores[4];
	NewRightSon(&arb_e, pos, e);
	pa=Root(&arb_e);
	pl=Final(&l);
	InsertArbol(&l, pa);
	e.c='f';
	e.n=contadores[5];
	NewRightSon(&f, pos, e);
	pa=Root(&f);
	pl=Final(&l);
	InsertArbol(&l, pa);
	e.c='g';
	e.n=contadores[6];
	NewRightSon(&g, pos, e);
	pa=Root(&g);
	pl=Final(&l);
	InsertArbol(&l, pa);
	e.c='h';
	e.n=contadores[7];
	NewRightSon(&h, pos, e);
	pa=Root(&h);
	pl=Final(&l);
	InsertArbol(&l, pa);
	e.c='i';
	e.n=contadores[8];
	NewRightSon(&i, pos, e);
	pa=Root(&i);
	pl=Final(&l);
	InsertArbol(&l, pa);
	e.c='j';
	e.n=contadores[9];
	NewRightSon(&j, pos, e);
	pa=Root(&j);
	pl=Final(&l);
	InsertArbol(&l, pa);
	printf("Ultimo arbol:%d\n", pa);


	paux=First(&l);
	paux2=Final(&l);
	printf("Lista inicio:%d    Lista fin:%d\n", paux, paux2);
	paux3=ShowArbol(&l, paux);
	paux4=ShowArbol(&l, paux2);

	printf("Arbol inicio:%d    Arbol fin:%d\n", paux3, paux4);
	e=ReadNode(&a, paux3);
	printf("%c, %d", e.c, e.n);
}