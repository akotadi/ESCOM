/*
Practica 06: Codificación de Huffman
Parte A: Codificación
Implementación de la codificacion de Huffman
Versión 2.1
29 de Noviembre del 2017
Creadores: Maria Celeste Ponce Mendoza, Guillermo Naranjo Ferrara, Daniel Antonio Hidalgo Badillo.

Compilación: gcc AdH.c TADArbolBin.c -o AH.exe
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
	FILE *entrada;//Creacion del apuntador al archivo a leer 
	unsigned char *bytes;

	printf("Leer de : ");//Petición al usuario del archivo que desea codificar
	scanf("%s",nombre);
	printf("Escribir en : ");//Peticion al usuario del archivo en el que se va a guardar la codificación
	scanf("%s",va);

	entrada = fopen(nombre, "r");//Apertura del archivo
	fseek(entrada, 0, SEEK_END);//Lectura del archivo
	int tam = ftell(entrada); //Indica el tamaño de la entrada original 
	fseek(entrada, 0, SEEK_SET);	
	bytes = (unsigned char*)malloc((tam+1)*sizeof(unsigned char));
    fread(bytes, tam, 1, entrada);//entrada de bytes     
    bytes [tam] = 0;
	fclose(entrada); 
    //conteo de los caracteres
	int i;
    for(i = 0 ; i < tam ; i ++)
        {
            veces[bytes[i]]++;
        }	

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
                aux.entero = tam*2;//tam*2 es un numero muy grande para que nunca aparezca al unir a los arboles
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

	int menor = tam*2;
	int pos = 0;
    for( i = 0 ; i < 256 ; i ++)
    {
        //busqueda de un arbol mas pequeño
		elemento aux = lista[i] -> e;
		if(aux.entero < menor ){			
			menor = aux.entero;
			pos = i;//se guarda el arbol mas pequeño, los demas seran de numero mas grande para que sean ignorados
		} 
	}
	//un solo arbol valido

	arbol_bin a = lista[pos];	//a arbol final 

	Recorrido(a,a,0);

	int total = 0 ;//conocer tamaño del archivo compreso 
	//longitud conocer el tamaño de nla codificacion de hoffman
	for(i = 0 ; i < tam ; i ++)
	{
        total += longitud[bytes[i]];//longitud de la salida de huffman en bits
	}
	int original = total;//original dice en bits la salida, salida es necesaria conocerla en bytes, por ende se divide en bytes
	total = (total/8)+1;//+1 para asegurar que el byte se acomplete

//se tienen 1 y 0 como elementos de un arreglo 
	unsigned char *comp;//comp arreglo de char, salida pero compresa con codificacion de huffman
	comp = (unsigned char*)malloc(total*sizeof(unsigned char));	//lo que se imprime en el codigo de salida
	int ultimo = 0; //ultimo indica el byte en el que se esta escribiendo
	int pb = 0;//pb es la posicion del bit dentro del byte
	FILE *printbits = fopen("En bits.txt","w");//archivo n l qu s imprimn los bits
	for(i = 0 ; i < tam ;  i++)
    {
		int j = 0 ;
        for(j = 0 ; j < longitud[bytes[i]]; j++)//pregunta por la longitud de la codificacion del caracter sobre el que esta, 
        {
			if(codigo[bytes[i]][j] == 1){//si es un uno sobre el que esta parado se manda un 1 a la posicion en la que está
				PONE_1(comp[ultimo],pb);
				fprintf(printbits,"%d",1);
			} else {
				PONE_0(comp[ultimo],pb);
				fprintf(printbits,"%d",0);
			}
			pb++;	
			if(pb == 8){//se inicia con el nuevo byte
				ultimo++;
				pb = 0;
			}
		}
	}

	FILE *salida = fopen(va,"w");//apuntador a archivo en el que se va a guardar 
	fwrite(comp,total, 1, salida);//Escritura sobre el archivo que se desea crear
	fseek(salida, 0, SEEK_SET);	
	fclose(salida);



	salida = fopen("parametros.txt","w");//impresion de parametros para la decodificacion
	fprintf(salida,"%d\n",tam);//longitud en bytes del archivo original 
	fprintf(salida,"%d\n",original);//tamaño en bits de lo comprimido
	for(i = 0 ; i< 256 ; i ++)//imprime frecuencia del caracter para que en la decodificacion se pueda reconstruir
		{
			fprintf(salida,"%d\n", veces[i]);
		}
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