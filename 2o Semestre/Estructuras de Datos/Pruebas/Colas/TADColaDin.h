/*
LIBRERIA: Cabecera de el TAD COLA DIN�MICA
AUTOR: Edgardo Adri�n Franco Mart�nez (C) Marzo 2017
VERSI�N: 1.6
DESCRIPCI�N: TAD cola o Queue.
Estructura de datos en la que se cumple:
Los elementos se insertan en un extremo (el posterior) y 
la supresiones tienen lugar en el otro extremo (frente).
OBSERVACIONES: Hablamos de una Estructura de datos din�mica 
cuando se le asigna memoria a medida que es necesitada, 
durante la ejecuci�n del programa.  
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
	int id;
	
} elemento;

//Definir un nodo que ser� utilizado para almacenar una posici�n de la cola (Nodo), lo que incluira a un elemento y a un apuntador al siguiente nodo
typedef struct nodo
{
	//Elemento a almacenar en cada nodo de la cola	
	elemento e;	
	//Apuntador al elemento siguiente (Requerido por ser una implementaci�n din�mica -Usuario: No modificar)
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

//DECLARACI�N DE FUNCIONES
void Initialize(cola *c);			//Inicializar cola (Initialize): Recibe una cola y la inicializa para su trabajo normal.
void Queue(cola *c, elemento e);	//Encolar (Queue): Recibe una cola y agrega un elemento al final de ella. 
elemento Dequeue(cola *c);			//Desencolar (Dequeue): Recibe una cola y remueve el elemento del frente retorn�ndolo.
boolean Empty(cola *c);				//Es vac�a (Empty): Recibe la cola y devuelve verdadero si esta esta vac�a.
elemento Front(cola *c);			//Frente (Front): Recibe una cola y retorna el elemento del frente.	
elemento Final(cola *c);			//Final (Final): Recibe una cola y retorna el elemento del final.
elemento Element(cola *c, int i); 	// Recibe una cola y un n�mero de elemento de 1 al tama�o de la cola y retorna el elemento de esa posici�n.
int Size(cola *c);					//Tama�o (Size): Retorna el tama�o de la cola 	
void Destroy(cola *c);				//Eliminar cola (Destroy): Recibe una cola y la libera completamente.
