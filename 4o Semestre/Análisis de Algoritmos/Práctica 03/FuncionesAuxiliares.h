/*
Implementación Práctica 03: Codificación voraz de Huffman
Por: Git Gud (Equipo Arbol)
Versión: 1.0

Descripción: Programa que obtiene una codificación prefijo óptima con base al algoritmo de Huffman

Observaciones:

*/

/*
Descripción: Función que convertirá un número entero a su equivalente binario
Recibe: int n (número a convertir)
Devuelve: char (binario del número)
Observaciones:
*/
char decBin(int n);

/*
Descripción: Función que convertirá un número binario a su equivalente decimal
Recibe: char c (variable que contiene el binario del número)
Devuelve: unsigned int (binario en su representación de entero)
Observaciones:
*/
unsigned int binDec(char c);

/*
Descripción: Función que imprimirá un valor binario en el documento
Recibe: char n (representación binaria a imprimir)
Devuelve: 
Observaciones:
*/
void imprimeBinario(char n);
