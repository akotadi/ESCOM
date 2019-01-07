#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <windows.h>
#include "AuxFunctionsMatrix.h"
#include "AddMatrix.h"
#include "SubMatrix.h"
#include "ProdMatrix.h"
#include "TransposeMatrix.h"
#include "InverseMatrix.h"

void writeFile(double** matrix, char* name, int nSize){
	int i, j;
	FILE * auxFile = fopen(name, "w");

	for(i = 0; i < nSize; ++i){
		for(j = 0; j < nSize; ++j)
			fprintf(auxFile, "%0.3lf ", matrix[i][j]);
		fprintf(auxFile, "\n");
	}
	fclose(auxFile);
}

double** readFile(char* name, int nSize){
	int i, j;
	double** matrix = startMatrix(&nSize);
	FILE * auxFile = fopen(name, "r");

	for(i = 0; i < nSize; ++i)
		for(j = 0; j < nSize; ++j)
			fscanf(auxFile, "%lf", &matrix[i][j]);
	fclose(auxFile);

	return matrix;
}

int main()
{
	srand(time(0));
	clock_t startTime = clock();

	double **matrixA, **matrixB;
	int nSize;

	printf("Enter the order of the Matrix: ");
	scanf("%d", &nSize);

	matrixA = randomizeMatrix(&nSize);
	writeFile(matrixA, "matrixA.txt", nSize);

	matrixB = randomizeMatrix(&nSize);
	writeFile(matrixB, "matrixB.txt", nSize);

	writeFile(addMatrix(matrixA, matrixB, nSize), "addMatrix.txt", nSize);

	writeFile(subMatrix(matrixA, matrixB, nSize), "subMatrix.txt", nSize);

	writeFile(prodMatrix(matrixA, matrixB, nSize), "prodMatrix.txt", nSize);

	writeFile(transposeMatrix(matrixA, nSize), "transMatrixA.txt", nSize);

	writeFile(transposeMatrix(matrixB, nSize), "transMatrixB.txt", nSize);

	writeFile(inverseMatrix(nSize, matrixA), "invMatrixA.txt", nSize);

	writeFile(inverseMatrix(nSize, matrixB), "invMatrixB.txt", nSize);

	printf("\nThe matrixA is:");
	printMatrix(readFile("matrixA.txt", nSize), &nSize);

	printf("\nThe matrixB is:");
	printMatrix(readFile("matrixB.txt", nSize), &nSize);

	printf("\nThe addition is:");
	printMatrix(readFile("addMatrix.txt", nSize), &nSize);

	printf("\nThe substraction is:");
	printMatrix(readFile("subMatrix.txt", nSize), &nSize);

	printf("\nThe product is:");
	printMatrix(readFile("prodMatrix.txt", nSize), &nSize);

	printf("\nThe transpose of matrixA is:");
	printMatrix(readFile("transMatrixA.txt", nSize), &nSize);

	printf("\nThe transpose of matrixB is:");
	printMatrix(readFile("transMatrixB.txt", nSize), &nSize);

	printf("\nThe inverse of matrixA is:");
	printMatrix(readFile("invMatrixA.txt", nSize), &nSize);

	printf("\nThe inverse of matrixB is:");
	printMatrix(readFile("invMatrixB.txt", nSize), &nSize);

	clock_t finishTime = clock();
	printf("\nTime: %0.3fs\n", (double)(finishTime - startTime) / CLOCKS_PER_SEC);

	return 0;
}