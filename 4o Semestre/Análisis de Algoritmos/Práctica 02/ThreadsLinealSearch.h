/*
Implementación Práctica 02: Análisis temporal y notación de orden (Algoritmos de búsqueda)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que buscará por medio del método Lineal o Secuencial con hilos

Observaciones:

*/



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de buscar en los números por medio del algoritmo de Búsqueda Lineal o Secuencial
Recibe: void * id (Será el id del número de hilo que se esté manejando)
Devuelve: 
Observaciones:
*/
void* LinealSearch(void* id){

	int start = (int)id*(nSize / nThreads); 	// Indica la posición desde la que se va a iniciar la búsqueda
	int end = ((int)id+1)*(nSize / nThreads);	// Indica la posición hasta la que se va a concluir la búsqueda

	int i = start;								// Posicionamos el indicador en el inicio de la sección
	while(i < end && !found){					// Iteración que recorrerá toda la sección mientras no se haya encontrado el número en los demás hilos
		if (Data[i] == keyNumber)				// En caso de encontrarlo
		{
			found = true;						// Indicamos que se encontró el número para romper la iteración
		}
		i++;
	}
	pthread_exit(NULL);							// Salida del hilo
}