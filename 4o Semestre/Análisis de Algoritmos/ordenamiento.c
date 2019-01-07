#include <stdio.h>
#include <stdlib.h>
#define TRUE	1
#define FALSE	0

typedef unsigned int boolean;

int* BurbujaSimple(int * A, int n){
	int aux;
	for (int i = 0; i < n-1; ++i)
	{
		for (int j = 0; j < n-1-i; ++j)
		{
			if (A[j]>A[j+1])
			{
				aux = A[j];
				A[j] = A[j+1];
				A[j+1] = aux;
			}
		}
	}
	return A;
}

int* BurbujaOptimizada(int * A, int n){
	int i = 0, j, aux, cambios = 1; 
	while(i<n-1 && cambios!=0){
		printf("%i\n",i);
		cambios = 0;
		for (j = 0; j < n-1-i; ++j)
		{
			if (A[j]<A[i])
			{
				printf("Cambio 1: %i\nCambio 2: %i\n\n",A[i],A[j]);
				aux = A[j];
				A[j] = A[i];
				A[i] = aux;
				cambios = 1;
			}
		}
		i++;
	}
	return A;
}

int* Insercion(int * A, int n){
	int i, j, temp;
	for (i = 0; i < n; ++i)
	{
		j = i;
		temp = A[i];
		while(j>0 && temp<A[j-1]){
			A[j] = A[j-1];
			j--;
		}
		A[j] = temp;
	}
	return A;
}

int* Seleccion(int * A, int n){
	int k, i, p, temp;
	for (k = 0; k < n-1; ++k)
	{
		p = k;
		for (i = k+1; i < n; ++i)
		{
			if (A[i]<A[p])
			{
				p = i;
			}
		}
		temp = A[p];
		A[p] = A[k];
		A[k] = temp;
	}
	return A;
}

int* Shell(int * A, int n){
	int k, b, i, temp;
	k = n/2;
	while(k>=1){
		b = 1;
		while(b!=0){
			b = 0;
			for (i = k; i < n /*Checar*/; ++i)
			{
				if (A[i-k]>A[i])
				{
					temp = A[i];
					A[i] = A[i-k];
					A[i-k] = temp;
					b++;
				}
			}
		}
		k = k/2;
	}
	return A;
}

int* OrdenaConArbolBinario(int * A, int n){
	for (int i = 0; i < n; ++i)
	{
		Insertar(ArbolBinBusqueda,A[i]);
	}
	return GuardarRecorridoInorden(ArbolBinBusqueda,A);
}

int main(int argc, char const *argv[])
{
	int n, i, m;
	int * A;
	scanf("%i",&n);
	A = (int *)malloc(n*sizeof(int));
	for (i = 0; i < n; ++i)
	{
		scanf("%i",&A[i]);
	}
	for (i = 0; i < n; ++i)
	{
		printf("Número %i: %i\n",i,A[i]);
	}
	printf("\n");
//	scanf("%i",&m);

//	A = BurbujaSimple(A,n);
//	A = BurbujaOptimizada(A,n);
//	A = Insercion(A,n);
//	A = Seleccion(A,n);
	A = Shell(A,n);

	for (i = 0; i < n; ++i)
	{
		printf("Número %i: %i\n",i,A[i]);
	}
	return 0;
}