/*
LIBRERIA: Cabecera de el TAD COLA DINÁMICA
AUTOR: Edgardo Adrián Franco Martínez (C) Marzo 2017
VERSIÓN: 1.6

DESCRIPCIÓN: TAD cola o Queue.
Estructura de datos en la que se cumple:
Los elementos se insertan en un extremo (el posterior) y 
la supresiones tienen lugar en el otro extremo (frente).

OBSERVACIONES: Hablamos de una Estructura de datos dinámica 
cuando se le asigna memoria a medida que es necesitada, 
durante la ejecución del programa.  
*/

//DEFINICIONES DE CONSTANTES
#define TRUE	1
#define FALSE	0
//DEFINICIONES DE TIPOS DE DATO

//Definir un boolean (Se modela con un "char")
typedef unsigned char boolean;

//Definir un elemento (Se modela con una estructura "elemento")
typedef struct elemento
{
	//Variables de la estructura "elemento" (El usuario puede modificar)
	int n;
	char nombre[45];
	char actividad[200];
	char id[45];
	int tiempo_ejecucion;
	char status;
	
} elemento;

//Definir un nodo que será utilizado para almacenar una posición de la cola (Nodo), lo que incluira a un elemento y a un apuntador al siguiente nodo
typedef struct nodo
{
	//Elemento a almacenar en cada nodo de la cola	
	elemento e;	
	//Apuntador al elemento siguiente (Requerido por ser una implementación dinámica -Usuario: No modificar)
	struct nodo *siguiente;
}nodo;

//Definir una cola (Se modela con una estructura que incluye unicamente 
//dos apuntadores a elementos, para controlar el frente y final dela cola)
typedef struct cola
{
	nodo *frente;
	nodo *final;
	int num_elem;
} cola;

//DECLARACIÓN DE FUNCIONES
void Initialize(cola *c);			//Inicializar cola (Initialize): Recibe una cola y la inicializa para su trabajo normal.
void Queue(cola *c, elemento e);	//Encolar (Queue): Recibe una cola y agrega un elemento al final de ella. 
elemento Dequeue(cola *c);			//Desencolar (Dequeue): Recibe una cola y remueve el elemento del frente retornándolo.
boolean Empty(cola *c);				//Es vacía (Empty): Recibe la cola y devuelve verdadero si esta esta vacía.
elemento Front(cola *c);			//Frente (Front): Recibe una cola y retorna el elemento del frente.	
elemento Final(cola *c);			//Final (Final): Recibe una cola y retorna el elemento del final.
elemento Element(cola *c, int i); 	// Recibe una cola y un número de elemento de 1 al tamaño de la cola y retorna el elemento de esa posición.
int Size(cola *c);					//Tamaño (Size): Retorna el tamaño de la cola 	
void Destroy(cola *c);				//Eliminar cola (Destroy): Recibe una cola y la libera completamente.
