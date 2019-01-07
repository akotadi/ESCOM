/*
Practica 06: Codificación de Huffman
Parte B: Decodificación
Implementación de la decodificacion de Huffman
Versión 1.8
29 de Noviembre del 2017
Creadores: Maria Celeste Ponce Mendoza, Guillermo Naranjo Ferrara, Daniel Antonio Hidalgo Badillo.

Compilación: gcc AdHI.c TADArbolBin.c -o AHI.exe
Ejecucion: AH
*/

//Librerías a utilizar
#include <stdlib.h>
#include <stdio.h>
#include "TADArbolBin.h"

//Definiciones para el manejo de bits
#define PESOBIT(bpos) 1<<bpos
#define CONSULTARBIT(var,bpos) (*(unsigned*)&var & PESOBIT(bpos))?1:0
#define PONE_1(var,bpos) *(unsigned*)&var |= PESOBIT(bpos)
#define PONE_0(var,bpos) *(unsigned*)&var &= ~(PESOBIT(bpos))
#define CAMBIA(var,bpos) *(unsigned*)&var ^= PESOBIT(bpos)

arbol_bin lista[260];//Declaración de arboles para cada simbolo existente
char nombre[1000],va[1000];//Declaracion del nombre de los archivos 
int veces[260];//Arreglo para las frecuencias de los simbolos
char actual[20];//Arreglo que mantiene la posicion actual del nodo
int longitud[260];//Determina la longitud de la codificacion de Huffman
char codigo[260][20];//Crea un arreglo con las lineas del texto original

//FUNCIONES A UTILIZAR
/*
    Recorrido 
        Recibe un arbol al cual recorrer, una posicion en dicho arbol, y un entero que será util para 
        identificar la codificación de Huffman
*/
void Recorrido(arbol_bin a, posicion p,  int ultimo);

//CÓDIGO PRINCIPAL
int main (void)
{
	
	FILE *entrada,*param;//Creacion del apuntador al archivo a decodificar y al archivo que contiene los parametros para esto
	unsigned char *bytes;

	printf("Leer de : ");//Petición al usuario del archivo que desea codificar
	scanf("%s",nombre);
	printf("Escribir en : ");//Peticion al usuario del archivo en el que se va a guardar la codificación
	scanf("%s",va);

	entrada = fopen(nombre, "rb"); //Apertura del archivo
	fseek(entrada, 0, SEEK_END);//Lectura del archivo
	int tam = ftell(entrada);//Indica el tamaño de la entrada original 
	fseek(entrada, 0, SEEK_SET);	
	bytes = (unsigned char*)malloc((tam+1)*sizeof(unsigned char));
	fread(bytes, tam, 1, entrada);//entrada de bytes   
	fclose(entrada); 

	param = fopen("parametros.txt","r");//Lectura de parametros para saber como se va a decodificar
	int original , comp;
	fscanf(param,"%d",&original);//Indica la cantidad de bytes en el archivo original
	fscanf(param,"%d",&comp);//Indica el numero de bits dentro de la compresion
    
    int i;
    for(i = 0 ; i < 256 ; i ++)
    {
		fscanf(param,"%d",&veces[i]);
    }
    
	unsigned char * completo;
	completo = (unsigned char*)malloc((original)*sizeof(unsigned char));
	fclose(param);
    
    int buenos = 0;//Determina la cantidad de arboles que se crearan
	for(i = 0 ; i < 256 ; i ++)//Indice igual al num del simbolo al que se esta apuntando
	{
		elemento aux;
        aux.c = i;//Indica el numero de caracter que esta leyendo      
		if(veces[i] != 0)//si es cero ya no sera necesario mantenerlo 
            {
                aux.entero = veces[i];
                buenos++;
            }
		else
            {
                aux.entero = original*2;//original*2 es un numero muy grande para que nunca aparezca al unir a los arboles
            }
		NewRightSon(&lista[i], lista[i] , aux);//creacion del arbol para cada simbolo 
    }

    for(i = 0 ; i < buenos-1 ; i ++)//-1 ya que al unir 2 nodos unicamente queda uno
	{
		int menor1 = tam*2 , menor2 = tam*2;//busqueda de los nodos más pequeños
        int pos1 = 0 , pos2 = 0;//posicion de los menores
        int j;
        for( j = 0 ; j < 256 ; j ++)
        {
            /*
            Se obtiene el contenido del arbol en el que esta posicionado
            Si las repeticion es menor al mas chiquito entonces éste se convierte en el mas pequeño
            */
			elemento aux = lista[j] -> e;
            if(aux.entero < menor1 )
            {   //Búsqueda del numero mas chiquito
				menor2 = menor1;
				pos2 = pos1;
				menor1 = aux.entero;
				pos1 = j;
            } 
            else if(aux.entero < menor2)
            {
				menor2 = aux.entero;
				pos2 = j;
			}
		}
        //En este momento la lista aun no forma parte de la estructura, es necesario introducirlo al arbol principal
		arbol_bin nuevo;		
		Initialize(&nuevo);
		elemento e;
		e.c = 0;//e.c simbolo que importa
		e.entero = menor1 + menor2;//suma de la frecuencia de los simbolos
		NewRightSon(&nuevo , nuevo , e);//creacion de arbol con dicho elemento
		nuevo -> der = lista[pos1];//hijo derecho debe ser el más pequeño 
		nuevo -> izq = lista[pos2];//hijo izq es el segundo mas chiquito
		lista[pos1] = nuevo;//sustitucion del elemento ya que este ultimo queda fuera de la estructura
		lista[pos2] = NULL;//sobra una lista 
		e.c = 0 , e.entero = tam*2;
		NewLeftSon(&lista[pos2], lista[pos2] , e);	
    }
    
	int menor = original*2;
    int pos = 0;
    
    for( i = 0 ; i < 256 ; i ++)
    {
        //busqueda de un arbol mas pequeño
		elemento aux = lista[i] -> e;
        if(aux.entero < menor )
        {			
			menor = aux.entero;
			pos = i;//se guarda el arbol mas pequeño, los demas seran de numero mas grande para que sean ignorados
		} 
    }
    
	//un solo arbol valido

	arbol_bin a = lista[pos];	//a arbol final 
	posicion p = a;//posicion en la que se encuentra
	int ultimo = 0;
	int pb = 0;//Posicion del bit en el que se encuentra la lectura 
	int llevo = 0;//Cuenta la cantidad de bits que se llevan leídos

	printf("Ejecutando...\n");	
//Ciclo que permite conocer el simbolo que se desea encontrar gracias al recorrido almacenado
    for(i = 0 ; i < comp ; i ++)
    {
		int aux = CONSULTARBIT(bytes[ultimo],pb);//bit que se está consultando
        if(aux == 0)
        {
			p = LeftSon(&a,p);
        } else 
        {
			p = RightSon(&a,p);
		}
        if(LeftSon(&a,p) == NULL && RightSon(&a,p) == NULL )
        {//Si ya no hay información guardada en los hijos, se determina que ya se llegó al simbolo que se quiere obtener
			completo[llevo] = (p -> e).c;			
			llevo++;
			p = a;			
		}
		pb++;
        if(pb == 8)
        {
			ultimo++;
			pb = 0;
		}
	}
	printf("\n");
	

	FILE *salida = fopen(va,"wb");//apuntador a archivo en el que se va a guardar
	fwrite(completo,original, 1, salida);//Escritura sobre el archivo que se desea crear
	fseek(salida, 0, SEEK_SET);	
	fclose(salida);
	
	Destroy(&a);//destruccion del arbol
	
	return 0;
}


void Recorrido(arbol_bin a, posicion p,  int ultimo){
	if(p == NULL) return;
    if(LeftSon(&a,p) == NULL && RightSon(&a,p) == NULL)
    {   //cuando es hoja
		int i ;		
        for(i = 0 ; i < ultimo ; i ++)//ultimo te indica donde se va a poner el 1 o 0
		//a raiz del arbol y pos posicion del arbol
		//si los hijos son vacios entonces es una hoja la cual tiene el simbolo y su frecuencia
            codigo[(unsigned char) (p -> e).c][i] = actual[i];
            /*
            Se copia la informacion del arreglo actual al arreglo codigo
            Se hace uso de unsigned para que de unicamente numeros positivos
            */
		//matriz codigo, fila 1 como si fuera un solo arreglo- codificacion para el, codiugo de huffman 
		longitud[(unsigned char) (p -> e).c] = ultimo;
        //arreglo para cada simbolo, guarda la longitud para llegar al dicho simbolo, es decir, 
        //los saltos que tendra que dar para llegar a la hoja del simbolo, guardado en ultimo
	} 
	else //cuando no es hoja 
		{
            actual[ultimo] = 0;
            Recorrido(a,LeftSon(&a,p),ultimo+1);
                //movimiento a la izq se pone 0 en actual, direccion para poder llegar
                //Ya que la posicion cambia por lo que se tiene que mandar al hijo izquierdo
            actual[ultimo] = 1;
            Recorrido(a,RightSon(&a,p),ultimo+1);
		}
}