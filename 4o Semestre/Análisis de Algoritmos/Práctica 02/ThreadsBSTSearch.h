/*
Implementación Práctica 02: Análisis temporal y notación de orden (Algoritmos de búsqueda)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que buscará por medio de un Árbol Binario de Búsqueda con t hilos

Observaciones:

*/

//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de buscar en los números por medio de un Árbol Binario de Búsqueda
Recibe: void * root (Será el apuntador a la raíz sobre la que vamos a buscar)
Devuelve: 
Observaciones:
*/
void * BSTSearch(void *root){

	position aux = (position)root;	// Posición auxiliar que nos permitirá movernos en el ABB

	while(aux != NULL && !found){ // Iteración que durará hasta que ya no haya elementos donde buscar o se haya encontrado el número en otro hilo

		if(aux->number == keyNumber){	// En caso de encontrar el número
			found = true;	// Indicamos que fue encontrado
		}

		if(aux->number > keyNumber){	// En caso de que el número en el que estamos sea mayor que el buscado
			aux = aux->left;	// Nos moveremos al subárbol izquierdo
		}
		else{	// En caso contrario
			aux = aux->right;	// Nos movemos al subárbol derecho
		}
	}
	pthread_exit(NULL);	// Salimos del hilo
}