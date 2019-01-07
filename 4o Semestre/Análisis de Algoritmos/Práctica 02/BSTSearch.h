/*
Implementación Práctica 02: Análisis temporal y notación de orden (Algoritmos de búsqueda)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que buscará por medio de un Árbol Binario de Búsqueda

Observaciones:
*/



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de buscar en los números por medio de un Árbol Binario de Búsqueda
Recibe:
Devuelve: 
Observaciones:
*/
void BSTSearch(){

	position aux = mainTree;	// Posición auxiliar que nos permitirá movernos en el ABB

	while(aux != NULL){	// Iteración que durará hasta que ya no haya elementos donde buscar

		if(aux->number == keyNumber){	// En caso de encontrar el número
			found = true;	// Indicamos que fue encontrado
			return;	// Terminamos la función
		}

		if(aux->number > keyNumber){	// En caso de que el número en el que estamos sea mayor que el buscado
			aux = aux->left;	// Nos moveremos al subárbol izquierdo
		}
		else{	// En caso contrario
			aux = aux->right;	// Nos movemos al subárbol derecho
		}
	}
}
