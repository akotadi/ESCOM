#include <stdio.h>
#include <stdlib.h>


int checarLados(int n, char** tablero, int a, int b){
	int retornar=0;
	for (int i = 0; i < n; ++i){
		if(tablero[a][i] == '*'){
			retornar =1;
			break;
		}
	}
	return retornar;
} 

int checarDiagonales(int n, char** tablero, int a, int b){
	int retornar = 0;

	//Para abajo
	for(int i=1; a+i<n && b-i>=0 ;i++){
		if(tablero[a+i][b-i] == '*'){
			retornar=1;
		}
	}
	//Para arriba
	for(int i=1; a-i>=0 && b-i>=0 ;i++){
		if(tablero[a-i][b-i] == '*'){
			retornar=1;
		}
	}
	return retornar;
}

void desplegar(int n, char** tablero){
	//Desplegar
	//Liberar la memoria que nos se usa
	printf("\n\n");
	for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < n; ++j)
		{
			if(tablero[i][j]=='*'){
				printf("%c",tablero[i][j]);
				printf(" ");
			}
			else{
				printf("%c",254);
				printf(" ");
			}
		}
		printf("\n");
	}
	for (int i = 0; i < n; ++i)
	{
		free(tablero[i]);
	}
	free(tablero);
	exit(1);
	return ;
}

void mostrar(int n, char** tablero, int a, int b){
	//Desplegar
	//Liberar la memoria que nos se usa
	printf("\n\n");
	for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < n; ++j)
		{
			if(tablero[i][j]=='*'){
				printf("%c",tablero[i][j]);
				printf(" ");
			}
			else if(a==i && b==j){
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

void reinas(int n, char** tablero,int a, int b,int numDamas){

	if(a <n && b<n){
		if(checarDiagonales(n,tablero,a,b) == 0 && checarLados(n,tablero,a,b) == 0 && numDamas!=n){
			mostrar(n,tablero,a,b);
			tablero[a][b]='*';
			numDamas++;
			reinas(n,tablero,0,b+1,numDamas);
			numDamas--;
			tablero[a][b]='-1';
			reinas(n,tablero,a+1,b,numDamas);
		}
		else{
			mostrar(n,tablero,a,b);
			reinas(n,tablero,a+1,b,numDamas);
		}
	}
	else if(numDamas == n)
			desplegar(n,tablero);
	//entonces se restablece el a y el b 
}

int main(){
	int n;
	char** tablero;
	printf("Introducir el tamanio del tablero: ");
	scanf("%i",&n);
	tablero = (char **) malloc(n*sizeof(char *));
	for(int i=0; i<n;i++){
		tablero[i]=(char* )malloc(n*sizeof(char));
	}
	reinas(n,tablero,0,0,0);
	return 1;
}
