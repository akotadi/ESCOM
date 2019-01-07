/*
Implementación Práctica 03: Codificación voraz de Huffman
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que obtiene una codificación prefijo óptima con base al algoritmo de Huffman

Observaciones:

*/

/*
	Estructura que hará la función de un árbol binario
*/
typedef struct arbol{
	unsigned long n;
	char c;
	struct arbol *izq;
	struct arbol *der;
}arbol;


/*
	Estructura que simulará una lista de árboles binarios
*/
typedef struct lista{
	struct arbol *a;
	struct lista *sig;
}lista;

/*
Descripción: Función que unirá dos árboles y mantendrá como valor la suma de ambas frecuencias
Recibe: arbol *a (apuntador a un árbol binario), arbol *b (apuntador a un árbol binario)
Devuelve: arbol* (apuntador al nuevo árbol donde convergen los dos árboles)
Observaciones:
*/
struct arbol* nuevoArbol(struct arbol *a,struct arbol *b);

/*
Descripción: Función que recorrerá el árbol de codificación de manera recursiva guardando las codificaciones 
	en bits para cada caracter encontrado
Recibe: arbol *a (apuntador al nodo donde nos encontremos en el árbol), char c[256][256] (arreglo de caracteres
	que guardará las codificaciones de bits en el caracter respectivo, char *b (arreglo de caracteres que 
	contendrá la codificación en bits temporalmente, int n (nivel del árbol donde nos encontremos)
Devuelve: 
Observaciones:
*/
void recorreArbol(struct arbol *a,char c[256][256],char *b,int n);

/*
Descripción: Función que insertará un árbol al final de la lista
Recibe: lista **l (apuntador al inicio de la lista), arbol *a (árbol que desea añadirse)
Devuelve: 
Observaciones:
*/
void insertaLista(struct lista **l,struct arbol *a);

/*
Descripción: Función que unirá los dos primeros árboles de la lista para posteriormente insertarlo 
	en la lista acorde a su frecuencia
Recibe: lista **l (apuntador al inicio de la lista)
Devuelve: 
Observaciones:
*/
void dosMenoresArboles(struct lista **l);

/*
Descripción: Función que ordenará el arreglo de árboles en orden ascendente de acuerdo al número de apariciones del byte
Recibe: arbol *A (arreglo de árboles a ordenar),int izq (límite inferior),int der (límite superior)
Devuelve: 
Observaciones: La ordenación se realiza por medio de Merge Sort
*/
void ordena(struct arbol *A,int izq,int der);
