#include <stdio.h>
#include <stdlib.h>
#include "AuxFunctionsMatrix.h"
#include "TransposeMatrix.h"

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

int main(int argc, char const *argv[])
{
	int size = atoi(argv[1]);

	double** matrixA = readFile("matrixA.txt", size);
	double** matrixB = readFile("matrixB.txt", size);

	writeFile(transposeMatrix(matrixA, size), "transMatrixA.txt", size);
	writeFile(transposeMatrix(matrixB, size), "transMatrixB.txt", size);

	return 0;
}