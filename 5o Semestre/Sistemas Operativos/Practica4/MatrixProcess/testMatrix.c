#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include "AuxFunctionsMatrix.h"
#include "AddMatrix.h"
#include "SubMatrix.h"
#include "ProdMatrix.h"
#include "TransposeMatrix.h"
#include "DeterminantMatrix.h"
#include "InverseMatrix.h"

void writeFile(double** matrix, char* name, int size){
	FILE * auxFile = fopen(name, "w");

	for(int i = 0; i < size; ++i){
		for(int j = 0; j < size; ++j)
			fprintf(auxFile, "%0.3lf ", matrix[i][j]);
		fprintf(auxFile, "\n");
	}
	fclose(auxFile);
}

double** readFile(char* name, int size){
	double** matrix = startMatrix(&size);
	FILE * auxFile = fopen(name, "r");

	for(int i = 0; i < size; ++i)
		for(int j = 0; j < size; ++j)
			fscanf(auxFile, "%lf", &matrix[i][j]);
	fclose(auxFile);

	return matrix;
}

int main()
{
	srand(time(0));

	double **matrixA, **matrixB, **resultMatrix, **transMatrix, **invMatrix, detMatrix;
	int size, i, j;
	printf("Enter the order of the Matrix: ");
	scanf("%d", &size);

	printf("\nThe matrixA is:");
	matrixA = randomizeMatrix(&size);
	printMatrix(matrixA, &size);

	printf("\nThe matrixB is:");
	matrixB = randomizeMatrix(&size);
	printMatrix(matrixB, &size);

	printf("\nThe addition is:");
	resultMatrix = addMatrix(matrixA, matrixB, size);
	printMatrix(resultMatrix, &size);

	printf("\nThe substraction is:");
	resultMatrix = subMatrix(matrixA, matrixB, size);
	printMatrix(resultMatrix, &size);

	printf("\nThe product is:");
	resultMatrix = prodMatrix(matrixA, matrixB, size);
	printMatrix(resultMatrix, &size);

	detMatrix = determinantMatrix(matrixA, size);
	printf("\nDeterminant of matrixA is: %f\n",detMatrix);

	if (detMatrix == 0)
		printf("\nInverse of Entered Matrix is not possible\n");
	else{

		invMatrix = inverseMatrix(size, matrixA);
		printf("\nThe inverse matrixA is:");
		printMatrix(invMatrix, &size);
	}
	return 0;
}