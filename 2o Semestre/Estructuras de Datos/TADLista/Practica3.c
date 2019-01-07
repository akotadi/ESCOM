/*
Implementación Práctica 02: Simulación 1 - Supermercado
Por: #TeamYalja
Versión: 1.0

Descripción: Simular la atención de clientes en un supermercado, el cuál
deberá de atender al menos 100 clientes por día para no tener
perdidas, por lo que una vez que ya se atendieron a más de
100 personas y no hay gente formada en las cajas puede
cerrar la tienda. Mientras no se cierre la tienda, las personas
podrán seguir llegando con productos a las cajas.
	
Observaciones: 
*/


//LIBRERÍAS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <math.h>
#include "TADListaDobleLigada.c"



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Funcion que recibe un caracter. Si este es especial entonces lo cambia a su representacion en ASCII.
Recibe: char c (carácter especial)
Devuelve: char (carácter cambiado para su facilidad de uso)
Observaciones: 
*/
char cambioCaracter (char c)
{
	if (c==-15)//ñ
		c=-92;
	if (c==-31)//á
		c=-96;
	if (c==-23)//é
		c=-126;
	if (c==-19)//í
		c=-95;
	if (c==-13)//ó
		c=-94;
	if (c==-6)//ú
		c=-93;
	if (c==-63)//Á
		c=-75;
	if (c==-55)//É
		c=-112;
	if (c==-51)//Í
		c=-42;
	if (c==-45)//Ó
		c=-32;
	if (c==-38)//Ú
		c=-23;
	if (c==-47)//Ñ
		c=-91;
	return c;
}

/*
Descripción: Funcion que genera el Hash de la palabra ingresada.
Recibe: char palabra[] (palabra a la que se le efectuará el Hash), int tamanio (Tamaño máximo que será de 1000)
Devuelve: int (llave que nos indicará el lugar donde se guardará la palabra)
Observaciones: 
	El hash que se usa es el Hash Polinomial
	Sustrae cada caracter de la palabra y lo multiplica por un numero primo elevado a la potencia del contador+1 en cada caso.
	Ejemplo:
	a0 * primo+ a1 * primo^2 + a2 *primo^3 + ... + a(n-1) * primo^(n) = hash
	En cada suma que realiza tambien hace un modulo con respecto al tamanio de la tabla para que no se pase del limite del tipo int
*/
int hashPoli(char palabra[], int tamanio){
	double suma=0;
	int final=0;
	int llave,aux,digitos,longitud=0;

	longitud = strlen(palabra);

	for (int i = 0; i < longitud; ++i){
		if(palabra[i] > 90)
			aux=palabra[i]-96;
		else
			aux=palabra[i]-64;
		if(aux < 0){
			aux = (-1)*aux;
		}
		suma = suma + (aux*(pow(29,longitud-(i+1))));
		llave=(int) suma % tamanio;
	}
	if(suma > 2147483647 && llave < 0){ // Ese es número es el tope que acepta el tipo int
		digitos = floor(log10(suma)) + 1;
		suma = floor(suma*pow(10,-(digitos-8))); // Si sobrepasa el limite, entonces recorre los numeros n veces a la izquierda y despues le quita los decimales
		final=(int)suma;
		llave=final % tamanio;
	}
	return llave;
}


/*
Descripción: Funcion que recibe 2 cadenas y checa si son iguales caracter a caracter
Recibe: char pelemento[] (Palabra candidata a ser la buscada), char palabra[] (Palabra que está siendo buscada)
Devuelve: int (En caso de ser cero, las palabras son distintas en caso de ser 1, son diferentes)
Observaciones: 
*/
int sonIguales(char pelemento[], char palabra[]){
	int resultado = 0;
	if(strlen(pelemento) == strlen(palabra)){
		for (int i = 0; i < strlen(palabra); ++i)
		{
			if(pelemento[i] == palabra[i])
				resultado = 1;
			else{
				resultado= 0;
				break;
			}
		}
	}
	return resultado;
}


/*
Descripción: Función que busca en la lista el elemento que tiene a esa palabra, cuando lo encuentra regresa la posicion en donde lo encontró
Recibe: lista* buscar (Lista donde se va a buscar), char palabra[] (Palabra que se está buscando)
Devuelve: int (En caso de ser 0, no está la palabra, de ser distinto, indicado la posición de la lista donde se encuentra)
Observaciones: 
*/
int busquedaPos(lista* buscar, char palabra[]){
	int cont=1,checar=0;
	posicion p;
	elemento e;
	while(cont <= Size(buscar)){
		e=Element(buscar,cont);
		checar=sonIguales(e.p,palabra);
		if (checar == 0)
			cont++;
		else
			break;
	}
	if(cont > Size(buscar)){
		printf("La palabra no esta en el diccionario\n");
		cont = 0;
	}
	return cont;
}


/*
Descripción: Funcion que realiza una busqueda en la tabla hash
Recibe: lista* tabla(Lista donde se está buscando la palabra),int tamanio (Tamaño máximo de la tabla) ,char palabra[] (Palabra buscada)
Devuelve: int (Posicición donde se encuentra la palabra buscada)
Observaciones: Recibe una palabra y le hace un hash. Despues va a la posicion, que consiguio mediante el hash, de la tabla y realiza la busqueda en la lista.
*/
int buscarPalabra(lista* tabla,int tamanio,char palabra[]){
	int llave,contBus;
	elemento e;

	llave=hashPoli(palabra,tamanio);

	if(Size(&tabla[llave]) > 1)
		contBus=busquedaPos(&tabla[llave],palabra);

	if(contBus != 0){
		e=Element(&tabla[llave],contBus);
		printf("Palabra: %s\n",e.p);
		printf("Definicion: %s\n",e.d );
		printf("La llave es: %i\n",llave);
		printf("Numero de saltos requeridos: %i\n",contBus);
	}
	
	return contBus;
}


/*
Descripción: Funcion que pide la definicion y agrega la nueva palabra al diccionario.
Recibe: lista* tabla (Lista que contiene las palabras),int tamanio (Tamaño máximo de la tabla), char palabra[] (Palabra a ser agregada)
Devuelve: 
Observaciones: 
*/
void agregarPalabra(lista* tabla,int tamanio,char palabra[]){
	char definicion[250];
	elemento e;
	int llave=0,longitud=0;
	if(buscarPalabra(tabla,tamanio, palabra) == 0){
		strcpy(e.p,palabra); 
		printf("Introduce la definicion: \n");
		scanf("%s",&definicion);
		strcpy(e.d,definicion);
		llave=hashPoli(palabra,tamanio);
		Add(&tabla[llave],e);
		printf("La llave es: %i\n",llave);
	}
	else
		printf("La palabra ya esta en el diccionario\n");
	return ;
} 


/*
Descripción: Función que recibe una palabra y le cambia su definicion
Recibe: lista* tabla (Lista que contiene las palabras, int tamanio (Tamaña máximo de la tabla), char palabra[] (Palabra a modificar)
Devuelve: 
Observaciones: 
*/
void modificarDef(lista* tabla,int tamanio,char palabra[]){
	char definicion[250];
	int llave=0,pos;
	posicion p;
	elemento e;
	llave=hashPoli(palabra,tamanio);
	pos=buscarPalabra(tabla,tamanio,palabra);
	strcpy(e.p,palabra); 
	printf("Introduce la definicion: \n");
	scanf("%s",&definicion);
	strcpy(e.d,definicion);
	p = ElementPosition(&tabla[llave],pos);
	Replace(&tabla[llave],p,e);
	return ;
}


/*
Descripción: Función que recibe una palabra y la elimina
Recibe: lista* tabla (Lista que contiene las palabras, int tamanio (Tamaña máximo de la tabla), char palabra[] (Palabra a eliminar)
Devuelve: 
Observaciones: 
*/
void eliminar(lista* tabla,int tamanio,char palabra[]){
	int llave=0,pos;
	posicion p;
	llave=hashPoli(palabra,tamanio);
	pos=buscarPalabra(tabla,tamanio,palabra);
	p=ElementPosition(&tabla[llave],pos);
	Remove(&tabla[llave],p);
	return ;
}


/*
Descripción: Funcion que carga un archivo nuevo
Recibe: lista* tabla (Lista que contendrá las palabras, int tam_tabla (Tamaño máximo de la tabla)
Devuelve: 
Observaciones: 
*/
void cargarArchivo(lista* tabla, int tam_tabla){
	FILE* archivo;
	char arch[50];
	printf("Archivo que se abrira(con .txt): \n");
	scanf("%s",&arch);
 	archivo = fopen(arch,"r"); // abrir archivo en modo de lectura(read)
 	int i,llave;
 	char c;
 	elemento e;

 	if (archivo == NULL){
 		printf("El archivo no existe \n");
 		return ;
 	}
 	
 	while(!feof(archivo) ){ // Mientras no sea el fin del archivo
 		
 		i=0;
		do
	    { // Lee caracter a caracter hasta que tope con un : que divide a la palabra de la definicion
	      c=fgetc(archivo);//Lee un caracter
	      c=cambioCaracter(c);//Checa si es especial
	      e.p[i]=c;//Lo mete en la cadena p que es la palabra en el struct elemento
	      i++;
	    }
	    while(c!=':');
	    e.p[i-1]='\0';//quita el ultimo elemento ya que toma los ":"

	    i=0;
	    do
	    {//Lee la descripcion
	      c=fgetc(archivo);
	      c=cambioCaracter(c);
	      e.d[i]=c;
	      i++;
	    }
	    while(c!='\n'&&c!=EOF);	
	    e.d[i-1]='\0';
  		
  		llave=hashPoli(e.p,tam_tabla);//Hace hash a cada palabra que entra
  		Add(&tabla[llave],e);//Mete la palabra a la tabla 

  		llave=0;
 	}
    fclose(archivo);

    return ;
}



/*
Variables usadas en el programa:
	char nombre[45]: Cadena de texto que contendrá el nombre del supermercado
	int cant: Entero que indicará la cantidad de cajeras disponibles
	int t_cliente: Entero que indicará cada cuanto tiempo llegará un cliente
	int* t_cajeras: Apuntador a entero que contendrá el tiempo que tarda cada cajera en atender
	int* ejecucion: Apuntador a entero que contendrá un arreglo que guardará el tiempo que lleva atendiendo cada cajera
	cola* cola_cajeras: Apuntador de cola que simulará la cola de cada caja en el supermercado.
*/
int main(){
	char cadena[450];
	char palabra[50];
	char definicion[250];
 	char c;
 	int tam_tabla=1000,llave=0,longitud=0,cont=0,prov=0,opcion=0,mayor=0,vacias=0,a=0,colisiones = 0;

 	elemento e;

 	
	lista* tabla;
	tabla=(lista*)malloc(tam_tabla*sizeof(lista));
	for (int i = 0; i < tam_tabla; ++i){
		Initialize(&tabla[i]);
	}
	
	
	while(opcion != 6){
		opcion = 0;
		vacias =0;
		mayor =0;
		colisiones=0;
		printf("********************************************************************\n");
		printf("               M              E            N          U\n");
		printf("********************************************************************\n");
		printf("1.-Cargar archivo con definiciones.\n");
		printf("2.- Agregar una palabra y su definicion\n");
		printf("3.-Buscar una palabra y su definicion\n");
		printf("4.-Modificar una definicion\n");
		printf("5.-Eliminar una palabra\n");
		printf("6.-Salir\n");
		scanf("%i",&opcion);

		if(opcion < 6){
			
			if(opcion == 1)
				cargarArchivo(tabla,tam_tabla);
			else{
				printf("Introduce la palabra: \n");
				//gets(palabra);
				scanf("%s",palabra);
				printf("\n");
				if(opcion == 2)
					agregarPalabra(tabla, tam_tabla,palabra);
					//printf("hola\n");
				else{
					if(opcion == 3)
						a=buscarPalabra(tabla, tam_tabla,palabra);
					else{
						if(opcion == 4)
							modificarDef(tabla, tam_tabla,palabra);
						else{
							if(opcion == 5)
								eliminar(tabla, tam_tabla,palabra);
						}
					}
				}
			}
		    for (int i = 0; i < tam_tabla; ++i){
		    	if (Size(&tabla[i]) > mayor)
		    	{
		    		mayor = Size(&tabla[i]);
		    	}
		    	if(Size(&tabla[i]) == 0){
		    		vacias++;
		    	}
		    	if (Size(&tabla[i]) > 1)
		    	{
		    		colisiones += Size(&tabla[i]) -1;
		    	}
			}
			printf("\n");
			printf("******************* E S T A D I S T I C A S ************************\n");
			printf("El mayor es: %i \n",mayor);
			printf("Hay vacias: %i \n",vacias);
			printf("Colisiones: %i\n",colisiones );
			printf("Tamanio de la tabla: %i\n",tam_tabla );
			printf("********************************************************************\n");
			printf("\n");
		}
	}
	
	for (int i = 0; i < tam_tabla; ++i)
	{
		Destroy(&tabla[i]);
	}
	return 0;
}