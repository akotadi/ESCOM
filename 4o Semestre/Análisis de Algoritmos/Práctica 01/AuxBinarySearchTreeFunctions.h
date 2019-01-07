/*
Implementación Práctica 01: Pruebas a posteriori (Algoritmos de Ordenamiento)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Funciones Auxiliares del Algoritmo de Ordenamiento por medio de un 
	Árbol Binario de Búsqueda (ABB)
	
Observaciones: 
*/


//LIBRERÍAS
#include "stdlib.h"

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
Descripción: Función encargada de hacer el recorrido inorden por el ABB
Recibe: arbol_bin *BinaryTree (apuntador al ABB utilizado por el usuario),
	int* Data (arreglo que contiene los números), int size (cantidad de números)
Devuelve: 
Observaciones: 
*/
void InorderTraversal(arbol_bin *BinaryTree, int* Data, int size){

	position auxPos = *BinaryTree;											// Declaramos un apuntador auxiliar para viajar por el árbol

	node ** Stack = (node**)malloc(size*sizeof(arbol_bin));					// Inicializamos una pila de nodos para guardar valores de nuestro viaje

	int stackTop = -1, i = 0;												// Iniciamos el apuntador del tope de la pila y el índice donde colocaremos los números en el arreglo

	do{																		// Iniciaremos el viaje por el árbol hasta que ya no haya apuntadores por los cuales viajar
		while(auxPos != NULL){												// Haremos un recorrido hasta llegar a la parte más izquierda a partir de cierto nodo
			Stack[++stackTop] = auxPos;										// Iremos colocando en la pila los nodos de la izquierda que vayamos encontrando
			auxPos = auxPos->left;
		}

		if(stackTop >= 0){													// Una vez llegado a la parte más izquierda, verificamos si quedan nodos en la pila
			auxPos = Stack[stackTop--];										// Sacaremos el último nodo de la pila que será la "raíz" de ese subárbol
			Data[i++] = auxPos->number;										// Ese nodo será ingresado al arreglo de números, posteriormente moveremos el índice un lugar más 
			auxPos = auxPos->right;											// Ya que quitamos la "raíz", pasaremos a recorrer el lado derecho del subárbol
		}
	}while(auxPos != NULL || stackTop >= 0);								// Cuando llegamos a un apuntador nulo y no tenemos más nodos que recorrer en la pila, terminamos

	free(Stack);															// Liberamos la memoria reservada a la pila

	return;
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
