/*
Implementación Práctica 02: Análisis temporal y notación de orden (Algoritmos de búsqueda)
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que buscará por medio del método Lineal o Secuencial

Observaciones:
*/



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de buscar en los números por medio del algoritmo de Búsqueda Lineal o Secuencial
Recibe: 
Devuelve: 
Observaciones:
*/
void LinealSearch(){
	for (int i = 0; i < nSize; ++i)
	{
		if (Data[i] == keyNumber)
		{
			found = true;
			return;
		}
	}
}