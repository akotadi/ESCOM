/*
Implementación Práctica 02: Análisis temporal y notación de orden (Algoritmos de búsqueda)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Funciones Auxiliares del Algoritmo de Búsqueda por medio de un Árbol Binario de Búsqueda (ABB)
	
Observaciones: 
*/


//LIBRERÍAS
#include <stdlib.h>

//DEFINICIONES DE ESTRUCTURAS
typedef struct node															// Nuestro nodo del árbol contendrá hijo izquierdo, derecho y un número entero
{
	struct node *left, *right;
	int number;
}node;

//DEFINICIONES DE SINÓNIMOS
typedef node* position;														// La posición será la dirección hacia un nodo específico
typedef position arbol_bin;													// El árbol binario será simplemente una posición de un nodo, usualmente la raíz del mismo



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de inicializar la estructura del ABB
Recibe: arbol_bin *BinaryTree (apuntador al ABB declarado por el usuario)
Devuelve: 
Observaciones: 
*/
void Initialize(arbol_bin *BinaryTree){
	*BinaryTree = NULL;														// El apuntador enviado por el usuario se coloca en un valor NULL
	return;
}

/*
Descripción: Función encargada de insertar un nuevo elemento en el ABB
Recibe: arbol_bin * BinaryTree (apuntador al ABB utilizado por el usuario), 
	int newNumber (nuevo elemento que se va a incluir en el ABB)
Devuelve: 
Observaciones: 
*/
void Insert(arbol_bin * BinaryTree, int newNumber){
	arbol_bin * aux = BinaryTree; 											// Declaramos un apuntador para recorrer el árbol
	while(*aux != NULL){													// Recorremos el árbol hasta encontrar el espacio libre donde irá el nuevo elemento
		if (newNumber > ((*aux)->number))									// En caso de que el valor sea mayor, iremos a la parte derecha del árbol
		{
			aux = &((*aux)->right);
		}
		else{																// Caso contrario, viajaremos a la parte izquierda del árbol
			aux = &((*aux)->left);
		}
	}
	*aux = (node *)malloc(sizeof(node));									// Una vez ubicados en su lugar, le haremos espacio en memoria al nuevo nodo
	(*aux)->number = newNumber;												// En el nodo colocaremos el número que desea introducir el usuario al árbol
	(*aux)->left = NULL;													// Nos aseguramos de que ambos hijos estén apuntando a un valor NULL para evitar errores
	(*aux)->right = NULL;
	return;
}

/*
Descripción: Función que devolverá los subárboles necesarios para iniciar los hilos
Recibe: position aux[] (arreglo de posiciones donde añadiremos los apuntadores a los subárboles),
	arbol_bin *BinaryTree (apuntador al ABB utilizado por el usuario),
	int n (número de hilos inicial que iremos reduciendo recursivamente)
Devuelve: 
Observaciones: En caso de encontrar el número durante el recorrido, lo indicará y así
	evitaremos que se genere el trabajo en los hilos.
*/
void subTrees(position aux[], arbol_bin * BinaryTree, int n){
	if((*BinaryTree)->number == keyNumber){
		found = true;
		return;
	}
	if(n == 1){
		*aux = *BinaryTree;
		return;
	}
	if((*BinaryTree)->left!=NULL){
		subTrees(aux,&((*BinaryTree)->left),n/2);
	}
	if((*BinaryTree)->right!=NULL){
		subTrees(aux+n/2,&((*BinaryTree)->right),(n-n/2));
	}
}

/*
Descripción: Función recursiva encargada de liberar el espacio ocupado por el ABB
Recibe: arbol_bin *BinaryTree (apuntador al ABB utilizado por el usuario)
Devuelve: 
Observaciones: 
*/
void Destroy(arbol_bin *BinaryTree){
	if(*BinaryTree == NULL)													// Veirficamos no estar apuntando a un valor nulo en el árbol enviado
		return;
	else
	{
		if((*BinaryTree)->left != NULL)										// Verificamos si el árbol izquierdo existe, para eliminarlo primero
			Destroy(&((*BinaryTree)->left));								// Llamamos recursivamente la función por el lado izquierdo
		if((*BinaryTree)->right != NULL)									// Posteriormente eliminamos el lado derecho del árbol verificando que existe
			Destroy(&((*BinaryTree)->right));								// Llamamos recursivamente la función por el lado derecho
		free(*BinaryTree);													// Liberamos el nodo una vez que ya no tiene hijos
		return;
	}
}
