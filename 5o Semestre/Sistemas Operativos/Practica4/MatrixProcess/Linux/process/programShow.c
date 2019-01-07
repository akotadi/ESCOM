#include <stdio.h>
#include <stdlib.h>
#include "AuxFunctionsMatrix.h"

double** readFile(char* name, int size){
	double** matrix = startMatrix(&size);
	FILE * auxFile = fopen(name, "r");

	for(int i = 0; i < size; ++i)
		for(int j = 0; j < size; ++j)
			fscanf(auxFile, "%lf", &matrix[i][j]);
	fclose(auxFile);

	return matrix;
}

int main(int argc, char const *argv[])
{
	int size = atoi(argv[1]);

	printf("\nThe matrixA is:");
	printMatrix(readFile("matrixA.txt", size), &size);

	printf("\nThe matrixB is:");
	printMatrix(readFile("matrixB.txt", size), &size);

	printf("\nThe addition is:");
	printMatrix(readFile("addMatrix.txt", size), &size);

	printf("\nThe substraction is:");
	printMatrix(readFile("subMatrix.txt", size), &size);

	printf("\nThe product is:");
	printMatrix(readFile("prodMatrix.txt", size), &size);

	printf("\nThe transpose of matrixA is:");
	printMatrix(readFile("transMatrixA.txt", size), &size);

	printf("\nThe transpose of matrixB is:");
	printMatrix(readFile("transMatrixB.txt", size), &size);

	printf("\nThe inverse of matrixA is:");
	printMatrix(readFile("invMatrixA.txt", size), &size);

	printf("\nThe inverse of matrixB is:");
	printMatrix(readFile("invMatrixB.txt", size), &size);

	return 0;
}