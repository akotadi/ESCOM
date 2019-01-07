#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "TADArbolBin.h"
//version 3.
//camambio y consulta de bits 
#define PESOBIT(bpos) 1<<bpos
#define CONSULTARBIT(var,bpos) (*(unsigned*)&var & PESOBIT(bpos))?1:0
#define PONE_1(var,bpos) *(unsigned*)&var |= PESOBIT(bpos)
#define PONE_0(var,bpos) *(unsigned*)&var &= ~(PESOBIT(bpos))
#define CAMBIA(var,bpos) *(unsigned*)&var ^= PESOBIT(bpos)
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
	for(i=0;i<53;i++)
	{
		if (frecuencias[i].frec != 0)
		{
			p=Root(&letras[num_letras]);
			NewRightSon(&letras[num_letras], p, frecuencias[i]);
			num_letras++;
		}
	}
	j=num_letras;
	//buscar los más pequeños 
	//por selccion 
	for (k = 0; k < num_letras-1; k++)//reliza el proceso el numero de letras iniciales menos 1
	{
			p=Root(&letras[j-1]);//lee el ultimo elemento para asegurar que no haya sido usado
			e=ReadNode(&letras[j-1], p);
			min1=e.frec;//lo marca como el mninimo
			min2=min1+1;
			for (i = 0; i < j; i++)
			{
				p=Root(&letras[i]);
				e=ReadNode(&letras[i], p);
				if (e.frec!=0 && e.usado!=1)//comprueba que este arbol no se haya usado
				{
					if (e.frec<min1)//busca el más pequeño
					{
						min1=e.frec;
					}
					else if (e.frec<=min2 && e.frec>min1)//busca el que sea más pequeño pero más grande que min1
					{
						min2=e.frec;
					}
				}
			}
		//buscar el arbol con la frecuencia pequeña 1
			l=0;
			for (i = 0; i < j; i++)
			{
				p=Root(&letras[i]);
				e=ReadNode(&letras[i], p);
				if (e.frec==min1 && e.usado!=1)
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
				if (e.frec==min2 && l<2 && e.usado!=1)
				{
					a[l]=i;
					l++;
				}
			}
			//unir arboles
			aux = unirpadre (&letras[a[0]], &letras[a[1]]);
			//agregar arbol a la lista de busqueda
			letras[j]=aux;
			j++;
	}
	return aux;
		
}
//recibe un elemento con el cracter a buscar y su frecuencia, el arbol generado de hoffman
//obtine el valor binario de la letra y se lo aplica a una variable unsigned char
//retorna la variable con los bits modificados 
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
 		binario[i]=valorbinario (arbol, p);
 		p=Parent(arbol, p);
 		i++;
 	}
}
int main (void)
{
	elemento frecuencias[53];
	elemento e;
	arbol_bin final;
	arbol_bin letras[105];
	int binario[53];
	posicion p;
	int i,j,k;
	unsigned char resultado;
	char c;
	FILE *cifrado,*texto;
	//limpiando arreglos e inicializando arbol
	
	for (i = 0; i < 53; i++)
	{
		(frecuencias[i].frec)=0;
		(frecuencias[i].caracter)='\0';
		Initialize(&letras[i]);
	}
	printf("-----------------CIFRADOR-------------------\n");
	//abriendo archivos
	cifrado=fopen("mensaje_cifrado.txt","w");
	texto=fopen("texto.txt","r");
	
	//obteniendo frefuencias recorriendo el archivo y segun el carcater 
	//sumarlo en la frecuencia del arreglo corresponidiente
	i=0;
	do
	{
		c=fgetc(texto);
		if (c>=65&&c<=90)//mayusculas
		{
			(frecuencias[c-65].frec)++;
			(frecuencias[c-65].caracter)=c;
		}
		else if (c>=97&&c<=122)//minisculas
		{
			(frecuencias[c-97+26].frec)++;
			(frecuencias[c-97+26].caracter)=c;
		}
		else if (c==32)//espacio
		{
			(frecuencias[52].frec)++;
			(frecuencias[52].caracter)=c;
		}
		i++;
	}
	while(c!=EOF);
	fclose(texto);//cierra el archivo para regresar el puntero al inicio
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
		if (c>=65&&c<=90)//mayusculas
		{
			escribir_binario ((frecuencias[c-65]), &final,binario);
		}
		else if (c>=97&&c<=122)//minisculas
		{
			escribir_binario ((frecuencias[c-97+26]),&final, binario);
		}
		else if (c==32)//espacio
		{
			escribir_binario (frecuencias[52],&final, binario);
		}
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
	for (i = 0; i < 53; i++)
	{
		fprintf(cifrado, "%d\n",frecuencias[i].frec);//imprime las frecuencias en le archivo
	}
	printf("\n");
	fclose(texto);
 	fclose(cifrado);
	system ("PAUSE");
	return 0;
}