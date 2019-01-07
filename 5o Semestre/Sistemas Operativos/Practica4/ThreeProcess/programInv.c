#include <stdio.h>
#include <stdlib.h>
#include <time.h>

double** startMatrix(int* nSize){
	int i;

	double** newMatrix = calloc(*nSize, sizeof(double*));
	
	for(i = 0; i < *nSize; ++i)
		newMatrix[i] = calloc(*nSize, sizeof(double));
	
	return newMatrix;
}

double** randomizeMatrix(int* nSize){
	int i,j;
	double** randomMatrix = startMatrix(nSize);

	for(i = 0; i < *nSize; ++i)
		for(j = 0; j < *nSize; ++j){
			randomMatrix[i][j] = -10+(20. * rand())/RAND_MAX;
			while (randomMatrix[i][j] == 0)
			{
				randomMatrix[i][j] = -10+(20. * rand())/RAND_MAX;
			}
		}
	return randomMatrix;
}

void printMatrix(double** matrix, int* nSize){
	int i, j;

	printf("\n");
	for(i = 0; i < *nSize; ++i){
		for(j = 0; j < *nSize; ++j)
			printf("%0.3lf\t", matrix[i][j]);
		printf("\n");
	}
}

void copyMatrix(int* nSize, double** origin, double** destiny){
	int i, j;

	for (i = 0; i < *nSize; ++i)
		for (j = 0; j < *nSize; ++j)
			destiny[i][j] = origin[i][j];
}

double** inverseMatrix(int nSize, double** matrix){
	double **invMatrix, sum, x;
	int i, j, k;

	invMatrix = startMatrix(&nSize);
	copyMatrix(&nSize, matrix, invMatrix);

	if (nSize <= 1) return invMatrix;
	for (i=1; i < nSize; i++) invMatrix[0][i] /= invMatrix[0][0]; 
	for (i=1; i < nSize; i++)  { 
		for (j=i; j < nSize; j++)  {
			sum = 0.0;
			for (k = 0; k < i; k++)  
				sum += invMatrix[j][k] * invMatrix[k][i];
			invMatrix[j][i] -= sum;
		}
		if (i == nSize-1) continue;
		for (j=i+1; j < nSize; j++)  {
			sum = 0.0;
			for (k = 0; k < i; k++)
				sum += invMatrix[i][k]*invMatrix[k][j];
			invMatrix[i][j] = (invMatrix[i][j]-sum) / invMatrix[i][i];
		}
	}
	for ( i = 0; i < nSize; i++ )
		for ( j = i; j < nSize; j++ )  {
			x = 1.0;
			if ( i != j ) {
				x = 0.0;
				for ( k = i; k < j; k++ ) 
					x -= invMatrix[j][k]*invMatrix[k][i];
			}
			invMatrix[j][i] = x / invMatrix[j][j];
		}
	for ( i = 0; i < nSize; i++ )
		for ( j = i; j < nSize; j++ )  {
			if ( i == j ) continue;
			sum = 0.0;
			for ( k = i; k < j; k++ )
				sum += invMatrix[k][j]*( (i==k) ? 1.0 : invMatrix[i][k] );
			invMatrix[i][j] = -sum;
		}
	for ( i = 0; i < nSize; i++ )
		for ( j = 0; j < nSize; j++ )  {
			sum = 0.0;
			for ( k = ((i>j)?i:j); k < nSize; k++ )  
				sum += ((j==k)?1.0:invMatrix[j][k])*invMatrix[k][i];
			invMatrix[j][i] = sum;
		}

	return invMatrix;
}

int main()
{
	srand(time(0));
	int nSize = 10;

	double** matrixA = randomizeMatrix(&nSize);
	printf("\nThe matrixA is:");
	printMatrix(matrixA, &nSize);

	double** matrixB = randomizeMatrix(&nSize);
	printf("\nThe matrixB is:");
	printMatrix(matrixB, &nSize);

	printf("\nThe inverse of matrixA is:");
	printMatrix(inverseMatrix(nSize, matrixA), &nSize);

	printf("\nThe inverse of matrixB is:");
	printMatrix(inverseMatrix(nSize, matrixB), &nSize);

	return 0;
}