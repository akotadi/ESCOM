#include <stdio.h>
#include <stdlib.h>

int checarLados(int n, char** tablero, int a, int b){
	int retornar=0;
	for (int i = 0; i < b; ++i){
		if(tablero[a][i] == '*'){
			retornar =1;
			break;
		}
	}
	return retornar;
} 

int checarDiagonales(int n, char** tablero, int a, int b){
	int retornar = 0;

	//Diagonal hacia abajo
	for(int i=1; a+i<n && b-i>=0 ;i++){
		if(tablero[a+i][b-i] == '*'){
			retornar=1;
		}
	}
	//Diagonal hacia arriba
	for(int i=1; a-i>=0 && b-i>=0 ;i++){
		if(tablero[a-i][b-i] == '*'){
			retornar=1;
		}
	}
	return retornar;
}

void desplegar(int n, char** tablero, int a, int b){
	//Desplegar
	//Liberar la memoria que nos se usa
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
	printf("\n\n");
	return ;
}

int reinas(int n, char** tablero,int a, int b,int numDamas, int c){
	if (numDamas==n){
		printf("Solucion %i: {",c+1);
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(tablero[i][j]=='*' && i+1!=n)
					printf("%i, ",j+1);
				else if(tablero[i][j]=='*' && i+1==n)
					printf("%i",j+1);
			}
		}
		printf("}\n");
		c++;
		desplegar(n,tablero,a,b);
	}
	if(a<n&&b<n){
		desplegar(n,tablero,a,b);
		if(checarDiagonales(n,tablero,a,b) == 0 && checarLados(n,tablero,a,b) == 0){
		tablero[a][b]='*';
		numDamas++;
		c=reinas(n,tablero,0,b+1,numDamas,c);
		numDamas--;
		tablero[a][b]='-1';
		c=reinas(n,tablero,a+1,b,numDamas,c);
	}
	else
		c=reinas(n,tablero,a+1,b,numDamas,c);
	}
	return c;
}

int main(){
	int n; // Cantidad n del problema
	int s; // Contador de soluciones
	char** tablero;
	printf("Introducir el tamanio del tablero: ");
	scanf("%i",&n);
	tablero = (char **) malloc(n*sizeof(char *));
	for(int i=0; i<n;i++){
		tablero[i]=(char* )malloc(n*sizeof(char));
	}
	for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < n; ++j)
		{
			tablero[i][j]='|';
		}
	}
	printf("\n");
	s=reinas(n,tablero,0,0,0,0);
	printf("Hay %i posibles soluciones\n",s);
	for (int i = 0; i < n; ++i)
	{
		free(tablero[i]);
	}
	free(tablero);
	return 1;
}
