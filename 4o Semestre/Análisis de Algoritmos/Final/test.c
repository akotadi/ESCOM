#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "TADArbolBin.h"


//version 3.
//camambio y consulta de bits 
#define PESOBIT(bpos) 1<<bpos
#define CONSULTARBIT(var,bpos) (*(unsigned*)&var & PESOBIT(bpos))?1:0
#define PONE_1(var,bpos) *(unsigned*)&var |= PESOBIT(bpos)
#define PONE_0(var,bpos) *(unsigned*)&var &= ~(PESOBIT(bpos))
#define CAMBIA(var,bpos) *(unsigned*)&var ^= PESOBIT(bpos)

void ShellSort(arbol_bin * Data, int size){
	elemento e1, e2;
	posicion p1, p2;

	int k = trunc(size / 2);
	while(k >= 1){
		int b = 1;
		while(b != 0){
			b = 0;
			for (int i = k; i <= size-1; ++i)
			{
				p1 = Root(&Data[i-k]);
				e1 = ReadNode(&Data[i-k],p1);
				p2 = Root(&Data[i-k]);
				e2 = ReadNode(&Data[i-k],p2);
				if (e1.nFrecuencia > e2.nFrecuencia)
				{
					arbol_bin aux;
					Initialize(&aux);
					aux = Data[i];
					Data[i] = Data[i-k];
					Data[i-k] = aux;
					b++;
				}
			}
		}
		k = trunc(k / 2);
	}
}


//recibe un arreglo donde se almacenaran los arboles y las frecuencias de el las letras
//aplica hoffman a las frecuencias
//retorna un arbol binario al que se la aplico hoffman
arbol_bin generar_arbol (arbol_bin *letras,elemento frecuencias[])
{
	//declaracion de variables
	int i,j,k,l,num_letras;
	int min1,min2;
	int a[2];
	arbol_bin aux;
	elemento e;
	posicion p;
	//quitando frecuencias con 0 e introduciendo las que no son 0 en arbol como su raiz.
	num_letras=0;//el numero de ltras difenetes de cero
	for(i=0;i<255;i++)
	{
		if (frecuencias[i].nFrecuencia != 0)
		{
			p=Root(&letras[num_letras]);
			NewRightSon(&letras[num_letras], p, frecuencias[i]);
			num_letras++;
		}
	}
	j=num_letras;

	ShellSort(letras,num_letras);

	//buscar los más pequeños 
	//por selccion 
	for (k = 0; k < num_letras-1; k++)//reliza el proceso el numero de letras iniciales menos 1
	{
			p=Root(&letras[j-1]);//lee el ultimo elemento para asegurar que no haya sido usado
			e=ReadNode(&letras[j-1], p);
			min1=e.nFrecuencia;//lo marca como el mninimo
			min2=min1+1;
			for (i = 0; i < j; i++)
			{
				p=Root(&letras[i]);
				e=ReadNode(&letras[i], p);
				if (e.nFrecuencia!=0 && e.nUsado!=1)//comprueba que este arbol no se haya usado
				{
					if (e.nFrecuencia<min1)//busca el más pequeño
					{
						min1=e.nFrecuencia;
					}
					else if (e.nFrecuencia<=min2 && e.nFrecuencia>min1)//busca el que sea más pequeño pero más grande que min1
					{
						min2=e.nFrecuencia;
					}
				}
			}
		//buscar el arbol con la frecuencia pequeña 1
			l=0;
			for (i = 0; i < j; i++)
			{
				p=Root(&letras[i]);
				e=ReadNode(&letras[i], p);
				if (e.nFrecuencia==min1 && e.nUsado!=1)
				{
					a[l]=i;
					l++;
				}
			}
			//buscar el arbol con la frecuencia pequeña 2
			for (i = 0; i < j; i++)
			{
				p=Root(&letras[i]);
				e=ReadNode(&letras[i], p);
				if (e.nFrecuencia==min2 && l<2 && e.nUsado!=1)
				{
					a[l]=i;
					l++;
				}
			}
			//unir arboles
			aux = MergeFathers(&letras[a[0]], &letras[a[1]]);
			//agregar arbol a la lista de busqueda
			letras[j]=aux;
			j++;
	}
	return aux;
		
}

void GetFrequency (FILE ** texto, elemento * frecuencias){

	char c;
	int i;

	//abriendo archivos
	*texto = fopen("texto.txt","r");
	
	//obteniendo frecuencias recorriendo el archivo y según el caracter 
	//sumarlo en la frecuencia del arreglo corresponidiente
	i=0;
	do
	{
		c = fgetc(*texto);
		if (c >= ' ' && c <= 165)
		{
			(frecuencias[c].nFrecuencia)++;
			(frecuencias[c].cLetra) = c;
		}
		i++;
	}	while(c!=EOF);
	//cierra el archivo para regresar el puntero al inicio
	fclose(*texto);
}

/*
Descripción: Función encargada de obtener el valor binario de una letra recibida para aplicárselo a una variable unsigned char
Recibe: elemento letra (frecuencia de la letra en cuestión), arbol_bin *arbol (apuntador al árbol final), 
	int *binario (arreglo donde escribiremos el binario del número)
Devuelve: 
Observaciones: Hará la modificación sobre la variable recibida pero con los bits modificados
*/
void WriteBinary (elemento letra, arbol_bin *arbol, int *binario)
{
	int i, j;
	posicion p;
	
	//rellenando todo con -1 para tener usarlo como indicador
	for (i = 0; i < 255; i++)
	{
		binario[i]=-1;
	}
	
	//poniendo el elemento tal como esta en el arbol
 	letra.nUsado = 1;
 	
 	//buscando el elemento letra
 	p = Search(arbol, letra);

 	//obteniendo el binario pero al reves
 	i=0;
 	while( p != Root(arbol) )
 	{
 		binario[i] = BinaryValue(arbol, p);
 		p = Parent(arbol, p);
 		i++;
 	}
}




int main (void)
{
	elemento e;
	elemento frecuencias[255];
	arbol_bin final;
	arbol_bin letras[105];
	int i,j,k;
	int binario[255];
	posicion p;
	unsigned char resultado;
	char c;
	FILE *cifrado,*texto;

	//limpiando arreglos e inicializando arbol
	for (i = 0; i < 255; i++)
	{
		(frecuencias[i].nFrecuencia)=0;
		(frecuencias[i].cLetra)='\0';
		Initialize(&letras[i]);
	}

	printf("-----------------CIFRADOR-------------------\n");


	cifrado = fopen("mensaje_cifrado.txt","w");

	GetFrequency(&texto, frecuencias);	

	//generando el arbol para la codificacion 
	final = generar_arbol (letras,frecuencias);//regresa el arbol final

	//Escirbiendo el binario en una variable para imprimir lo en archivo 
	j=0;
	texto=fopen("texto.txt","r");
	c=fgetc(texto);
	printf("Mensaje Cifrado: \n");
 	while(c!=EOF)
	{
		//obtenemos el codigo binario y lo recojemos en un arreglo
		//el arreglo trae el binario de el caracter enviado pero al reves
		WriteBinary((frecuencias[c]), &final,binario);
		//recorriendo la cadena al reves para introducirlo en la variable resultante
	 	//pone el binario correspondiente al la variable resultante
	 	for (k = 52; k >=0; k--)
	 	{
	 		if (binario[k]!=-1)
	 		{
	 			if (binario[k]==1)//
	 			{
	 				PONE_1(resultado,j); 
	 			}
	 			else if (binario[k]==0)
	 			{
	 				PONE_0(resultado,j);
	 			}
	 			j++;
	 			if (j==8)//si ha recorrido los 8 bits la imprime y resetea esta variable pra volver a usarla
	 			{
	 				printf("%c",resultado);
	 				fputc(resultado,cifrado);
	 				j=0;
	 			}
	 		}
	 	}
	 c=fgetc(texto);
	}
	
	//agrega los bits faltantes e imprime el numero de bits no utlizados
	if (j>0)
	{
		printf("%c",resultado);
		fputc(resultado,cifrado);
		fputs("\n",cifrado);
		fprintf(cifrado, "%d",8-j);
	}
	//imprimiendo frecuencia
	//tabla de frecuencias
	fputs("\n",cifrado);
	for (i = 0; i < 255; i++)
	{
		fprintf(cifrado, "%d\n",frecuencias[i].nFrecuencia);//imprime las frecuencias en le archivo
	}
	printf("\n");
	fclose(texto);
 	fclose(cifrado);
	system ("PAUSE");
	return 0;
}