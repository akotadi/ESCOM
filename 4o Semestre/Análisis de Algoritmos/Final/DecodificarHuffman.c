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
	num_letras=0;//numero de letras nos indicara el numero de letras usadas en el texto introducido
	for(i=0;i<53;i++)
	{
		if (frecuencias[i].nFrecuencia != 0)
		{
			p=Root(&letras[num_letras]);
			NewRightSon(&letras[num_letras], p, frecuencias[i]);
			num_letras++;
		}
	}
	j=num_letras;
	//buscar los más pequeños 
	//la busqueda se realioza por seleccion
	for (k = 0; k < num_letras-1; k++)
	{
			p=Root(&letras[j-1]);
			e=ReadNode(&letras[j-1], p);
			min1=e.nFrecuencia;//toma a el más pequeño como el ultmi de la cadena para asegurarnos que no se haya usado
			min2=min1+1;
			for (i = 0; i < j; i++)
			{
				p=Root(&letras[i]);
				e=ReadNode(&letras[i], p);
				if (e.nFrecuencia!=0 && e.nUsado!=1)
				{
					if (e.nFrecuencia<min1)//se toma el más pqueño
					{
						min1=e.nFrecuencia;
					}
					else if (e.nFrecuencia<=min2 && e.nFrecuencia>min1)//se toma el más pequeño pero mayor que m1
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
			aux = MergeFathers (&letras[a[0]], &letras[a[1]]);
			//agregar arbol a la lista de busqueda
			letras[j]=aux;
			j++;
	}
	return aux;
		
}
//recibe un caracter unsigned char
//obtine el valor binario de la letra y lo guarda en un arreglo int
void leer_binario (unsigned char c, int *binario)
{
	int i;
	//rellenando todo con -1 para tener usarlo como indicador
	for (i = 0; i < 8; i++)
	{
		binario[i]=CONSULTARBIT(c,i);
	}
	return;
}

int main (void)
{
	elemento frecuencias[53];
	elemento e;
	arbol_bin final;
	arbol_bin letras[105];
	posicion p;
	int binario[8];
	int i;
	int ignorados;
	unsigned char c,cLetra;

	FILE *descifrado,*texto;
	//limpiando arreglos e inicializando arbol
	
	for (i = 0; i < 53; i++)
	{
		(frecuencias[i].nFrecuencia)=0;
		(frecuencias[i].cLetra)='\0';
		Initialize(&letras[i]);
	}
	printf("-----------------DESCIFRADOR-------------------\n");
	//abriendo archivos
	descifrado=fopen("mensaje_descifrado.txt","w");
	texto=fopen("mensaje_cifrado.txt","r");
	//obteniendo frefuencias del archivo
	do
	{
		c=fgetc(texto);
	}
	while(c!='\n'); //avanza un renglo del archivo para saltar la parte que tiene el texto y no las frecuencias
	fscanf(texto,"%d\n",&ignorados);//recoge el numero de bits ignorados
	for (i = 0; i < 53; i++)
	{
		fscanf(texto,"%d\n",&(frecuencias[i].nFrecuencia));//se toma la frecuencia del archivo
		if (i>=0&&i<=25)//mayusculas
		{
			(frecuencias[i].cLetra)=i+65;//se genera el cracter correspondiente 
		}
		else if (i>=26&&i<=51)//minisculas
		{
			(frecuencias[i].cLetra)=i+97-26;//se genera el cracter correspondiente 
		}
		else if (i==52)//espacio
		{
			(frecuencias[i].cLetra)=32;//se genera el cracter correspondiente 
		}
	}
	//generando el arbol para la decodificacion 
	final = generar_arbol (letras,frecuencias);//regresa el arbol final
	fclose(texto);
	texto=fopen("mensaje_cifrado.txt","r");//reiniciando el puntero del archivo
	//obteniendo el binario
	printf("Mensaje Descifrado: \n");
	//recoge el texto a decifrar
	c=fgetc(texto);
	p=Root(&final);
	while(c!='\n') 
	{
		leer_binario (c, binario);//regresa el binario en el arreglo llmado binario
		c=fgetc(texto);
		//generando caracter a partir de arbol y binario
		if (c!='\n')//si no es el ultimo caracter del mensaje
		{	
			for (i = 0; i < 8; i++)
			{
				if(binario[i])//si es 1 se mueve a la derecha
				{
					p=RightSon(&final, p);
				}
				if (!binario[i])
				{
					p=LeftSon(&final, p);//si es 0 se mueva a la izquierda
				}
				if(IsLeaf (&final,p))//cuando encuentre una hoja imprime el caracter y regresa a la raiz
				{
					e=ReadNode(&final, p);
					printf("%c",e.cLetra);
					fputc(e.cLetra,descifrado);
					p=Root(&final);
				}
			}
		}
		else//si es el ultmimo cracter 
		{
			for (i = 0; i < 8-ignorados; i++)
			{
				if(binario[i]==1)//si es 1 se muevae haci la derecha
				{
					p=RightSon(&final, p);
				}
				if (binario[i]==0)
				{
					p=LeftSon(&final, p);//si es 0 se mueve a la izquierda
				}
				if(IsLeaf (&final,p))//si encuentra una hoja imprime el caracter y regresa la posicion a la raiz del arbol
				{
					e=ReadNode(&final, p);
					printf("%c",e.cLetra);
					fputc(e.cLetra,descifrado);
					p=Root(&final);
				}
			}
		}
	}
	printf("\n");
	fclose(texto);
 	fclose(descifrado);
	system ("PAUSE");
	return 0;
}