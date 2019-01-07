/*
Implementación Práctica 02: Análisis temporal y notación de orden (Algoritmos de búsqueda)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que buscará por medio del método Binario o Dicotómico con hilos

Observaciones:
*/



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de buscar en los números por medio del algoritmo de Búsqueda Binaria o Dicotómica
Recibe: void * id (Será el id del número de hilo que se esté manejando)
Devuelve: 
Observaciones:
*/
void * BinarySearch(void *id){

	int nLeft,nRight,nMid; // Tres variables que nos permitirán movernos por el arreglo

	nLeft = (int)id*(nSize / nThreads);	// Indicamos el lado izquierdo de la sección a manejar
	nRight = ((int)id+1)*(nSize / nThreads);	// Indicamos el lado derecho de la sección a manejar

	while(nRight>=nLeft && !found){	// Iteración que se detendrá cuando los límites se superen o se encuentre el número en los demás hilos
		nMid=(nLeft+nRight)/2;	// Buscamos la mitad de la sección
		if(Data[nMid]==keyNumber){	// En caso de encontrar el número en la mitad
			found=true;	// Indicamos que ya se encontró
		}
		if(Data[nMid]<keyNumber){	// Si el número del medio es menor al buscado
			nLeft=nMid+1;	// Continuamos la búsqueda del lado derecho de la partición
		}
		else{	// En caso contrario
			nRight=nMid-1;	// Continuamos la búsqueda del lado izquierdo de la partición
		}
	}
	pthread_exit(NULL);
}
