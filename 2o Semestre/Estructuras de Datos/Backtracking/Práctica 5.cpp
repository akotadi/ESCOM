/*
Implementaci�n Pr�ctica 05: El problema de las N-Reinas.
Por: #TeamYalja
Versi�n: 1.3

Descripci�n: El problema de las N-Reinas consiste en colocar n reinas en
un tablero de ajedrez de tama�o N*N de forma la reinas no se amenacen seg�n 
las normas del ajedrez. Se busca encontrar una soluci�n o todas las soluciones 
posibles utilizando un esquema de backtracking.
	
Observaciones: 
*/


//LIBRER�AS
#include <stdio.h>
#include <stdlib.h>

//DEFINICI�N DE FUNCIONES

/*
Descripci�n: Funci�n que se encarga de revisar que las reinas no se amenacen de forma horizontal
Recibe: int n (dimensi�n del problema), char** tablero (arreglo din�mico que simula el tablero de ajedrez),
	int a (fila de la reina actual), int b (columna de la reina actual)
Devuelve: int (iniciar� como 0 suponiendo que no haya amenaza, en caso de presentarse, devuelve 1)
Observaciones: 
*/
int checarLados(int n, char** tablero, int a, int b){
	int retornar=0;
	for (int i = 0; i < b; ++i){ // Iteraci�n que comienza en la fila donde se encuentra la posible reina y termina en �sta u otra reina
		if(tablero[a][i] == '*'){
			retornar =1;
			break;
		}
	}
	return retornar;
} 

/*
Descripci�n: Funci�n que se encarga de revisar que las reinas no se amenacen por medio de sus diagonales
Recibe: int n (dimensi�n del problema), char** tablero (arreglo din�mico que simula el tablero de ajedrez),
	int a (fila de la reina actual), int b (columna de la reina actual)
Devuelve: int (iniciar� como 0 suponiendo que no haya amenaza, en caso de presentarse, devuelve 1)
Observaciones: En ambos casos utilizaremos la i como 
*/
int checarDiagonales(int n, char** tablero, int a, int b){
	int retornar = 0;

	//Diagonal hacia abajo
	for(int i=1; a+i<n && b-i>=0 ;i++){ // Iteraci�n que durar� mientras la diagonal est� dentro del tablero
		if(tablero[a+i][b-i] == '*'){
			retornar=1;
		}
	}
	//Diagonal hacia arriba
	for(int i=1; a-i>=0 && b-i>=0 ;i++){ // Iteraci�n que durar� mientras la diagonal est� dentro del tablero
		if(tablero[a-i][b-i] == '*'){
			retornar=1;
		}
	}
	return retornar;
}

/*
Descripci�n: Funci�n encargada de mostrar la simulaci�n del tablero en pantalla
Recibe: int n (dimensi�n del problema), char** tablero (arreglo din�mico que simula el tablero de ajedrez),
	int a (fila de la reina actual), int b (columna de la reina actual)
Devuelve:
Observaciones: 
*/
void desplegar(int n, char** tablero, int a, int b){
	for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < n; ++j)
		{
			if(tablero[i][j]=='*'){ // Reinas actualmente en el tablero
				printf("%c",tablero[i][j]);
				printf(" ");
			}
			else if(a==i && b==j){ // Reina actual
				printf("*");
				printf(" ");
			}
			else{
				printf("%c",254);
				printf(" ");
			}
		}
		printf("\n");
	}
	return ;
}

/*
Descripci�n: Funci�n recursiva que utilizar� el backtracking para probar todas las posibilidades de tablero
Recibe: int n (dimensi�n del problema), char** tablero (arreglo din�mico que simula el tablero de ajedrez),
	int a (fila de la reina actual), int b (columna de la reina actual), int numDamas (contador de reinas colocadadas 
	sobre el tablero que no se amenazan), int c (contador de soluciones del problema)
Devuelve: int (contador de posibles soluciones del problema)
Observaciones: Se fijar� la primer columna como base, sobre ella se mover� la reina principal y a partir de ella
	hacia la derecha se realizar� el backtracking, el caso base supone que la columna principal lleg� a la �ltima fila
	al igual que la columna subsecuente
*/
int reinas(int n, char** tablero,int a, int b,int numDamas, int c){
	if (numDamas==n){ // Si el n�mero de damas es igual a la dimensi�n, significa que tenemos una soluci�n
		printf("\n\n");
		printf("--------------------------------------------\n");
		printf("Solucion %i: {",c+1); // Muestra la soluci�n de forma lineal
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(tablero[i][j]=='*' && i+1!=n)
					printf("%i, ",j+1);
				else if(tablero[i][j]=='*' && i+1==n)
					printf("%i",j+1);
			}
		}
		printf("}\n");
		c++; // Aumentamos el contador de soluciones
		desplegar(n,tablero,a,b);
		printf("--------------------------------------------");
	}
	if(a<n&&b<n){ // Condici�n de existencia dentro de los l�mites del tablero
		printf("\n\n");
		desplegar(n,tablero,a,b);
		if(checarDiagonales(n,tablero,a,b) == 0 && checarLados(n,tablero,a,b) == 0){ // Buscamos que la posible reina no se amenace con la anteriores
		tablero[a][b]='*'; // Suponemos que esta reina es correcta
		numDamas++;
		c=reinas(n,tablero,0,b+1,numDamas,c); // Pasamos a la siguiente columna
		numDamas--; // La reina supuesta o fue correcta y se despleg� la soluci�n, o fue incorrecta y no pudo llegar a la �ltima columna
		tablero[a][b]='|';
		c=reinas(n,tablero,a+1,b,numDamas,c); // Pasamos a la siguiente fila
		}
		else // Implica que la posible reina se amenazaba con las anteriores en esta fila, por tanto lo intentaremos en la siguiente fila
		c=reinas(n,tablero,a+1,b,numDamas,c);
	}
	return c;
}


/*
Variables usadas en el programa:
	int n: Dimensi�n sobre la que se resolver� el problema
	int s: Contador de soluciones
	char** tablero: Doble apuntador que utilizaremos para simular el tablero por medio de un arreglo din�mico
*/
int main(){
	int n; 
	int s; 
	char** tablero; 
	printf("Introducir el tamanio del tablero: ");
	scanf("%i",&n);
	tablero = (char **) malloc(n*sizeof(char *)); // Crea el arreglo din�mico que simular� el problema
	for(int i=0; i<n;i++){
		tablero[i]=(char* )malloc(n*sizeof(char));
	}
	for (int i = 0; i < n; ++i) // Se llena el tablero con un mismo s�mbolo
	{
		for (int j = 0; j < n; ++j)
		{
			tablero[i][j]='|';
		}
	}
	s=reinas(n,tablero,0,0,0,0); // Se llama a la funci�n recursiva
	printf("\nHay %i posibles soluciones\n",s);
	for (int i = 0; i < n; ++i) // Libera la memoria utilizada por el arreglo din�mico
	{
		free(tablero[i]);
	}
	free(tablero);
	return 1;
}
