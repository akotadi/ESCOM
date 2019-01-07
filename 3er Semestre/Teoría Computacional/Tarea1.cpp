#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	
	int nMaxEstado,nSimbolo,nEstado=0;
	
	cout << "Ingresa el numero maximo de simbolos: ";
	cin >> nSimbolo;

 	

 	char* cSimbolos = new char[nSimbolo];

 	cout << "Ingresa el simbolo \n";
 	for (int i = 0; i < nSimbolo; ++i)
 	{
 		cout << i << ": ";
 		cin >> cSimbolos[i];
 	}

 	/*int** nArreglo = (int**)malloc(nMaxEstado*sizeof(int*));
 	for (int i = 0; i < nMaxEstado; ++i)
 	{
 		nArreglo[i] = (int*)malloc(nSimbolo*sizeof(int));
 	}*/

	cout << "Ingresa el numero maximo de estados: ";
	cin >> nMaxEstado;

	int** nArreglo = (int**)new int[nMaxEstado];
 	for (int i = 0; i < nMaxEstado; ++i)
 	{
 		nArreglo[i] = new int[nSimbolo];
 	}
 	
 	for (int i = 0; i < nMaxEstado; ++i)
 	{
 		for (int j = 0; j < nSimbolo; ++j)
 		{
 			cout << "Ingresa la transicion para el estado q" << i << " con el simbolo " << cSimbolos[j] << "(ingresa -1 para marcar error, -2 para terminar): ";
 			cin >> nArreglo[i][j];
 		}
 	}

 	for (int i = 0; i < nMaxEstado; ++i)
 	{
 		for (int j = 0; j < nSimbolo; ++j)
 		{
 			cout << nArreglo[i][j];
 		}
 	}

 	int nMax;

 	cout << "Ingresa el tamanio maximo de la cadena a ingresar: ";
 	cin >> nMax;

 	char* sCadena;
 	sCadena = new char[nMax+1];

 	cout << "Ingresa la cadena: ";
 	for (int i = 0; i < nMax; ++i)
 	{
 		//sCadena[i]=getchar();
 	}
 	cin >> sCadena;
 	printf("%s\n",sCadena);

 	int i=0,k;
 	while(nEstado!=2 || i<strlen(sCadena)){
 		for (int j = 0; j < nSimbolo; ++j)
 		{
 			if (cSimbolos[j]==sCadena[i])
 			{
 				k=j;
 				break;
 			}
 		}
 		nEstado=nArreglo[nEstado][k];
 		i++;
 	}

 	printf("%s: %i\n",sCadena,nEstado);


	return 0;
}