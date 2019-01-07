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

typedef struct Informacion{
	int nOption;
	int nSize;
} info;

void writeFile(double** matrix, char* name, int nSize){
	FILE * auxFile = fopen(name, "w");

	for(int i = 0; i < nSize; ++i){
		for(int j = 0; j < nSize; ++j)
			fprintf(auxFile, "%0.3lf ", matrix[i][j]);
		fprintf(auxFile, "\n");
	}
	fclose(auxFile);
}

double** readFile(char* name, int nSize){
	double** matrix = startMatrix(&nSize);
	FILE * auxFile = fopen(name, "r");

	for(int i = 0; i < nSize; ++i)
		for(int j = 0; j < nSize; ++j)
			fscanf(auxFile, "%lf", &matrix[i][j]);
	fclose(auxFile);

	return matrix;
}

DWORD WINAPI executeThread(LPVOID lpParam){
	info *data = (info *)lpParam;
	double** matrixA = readFile("matrixA.txt", (data->nSize));
	double** matrixB = readFile("matrixB.txt", (data->nSize));
	switch(data->nOption){
		case 0:
			writeFile(addMatrix(matrixA, matrixB, (data->nSize)), "addMatrix.txt", (data->nSize));
			break;
		case 1:
			writeFile(subMatrix(matrixA, matrixB, (data->nSize)), "subMatrix.txt", (data->nSize));
			break;
		case 2:
			writeFile(prodMatrix(matrixA, matrixB, (data->nSize)), "prodMatrix.txt", (data->nSize));
			break;
		case 3:
			writeFile(transposeMatrix(matrixA, (data->nSize)), "transMatrixA.txt", (data->nSize));
			writeFile(transposeMatrix(matrixB, (data->nSize)), "transMatrixB.txt", (data->nSize));
			break;
		case 4:
			writeFile(inverseMatrix((data->nSize), matrixA), "invMatrixA.txt", (data->nSize));
			writeFile(inverseMatrix((data->nSize), matrixB), "invMatrixB.txt", (data->nSize));
			break;
		case 5:
			printf("\nThe matrixA is:");
			printMatrix(readFile("matrixA.txt", (data->nSize)), &(data->nSize));

			printf("\nThe matrixB is:");
			printMatrix(readFile("matrixB.txt", (data->nSize)), &(data->nSize));

			printf("\nThe addition is:");
			printMatrix(readFile("addMatrix.txt", (data->nSize)), &(data->nSize));

			printf("\nThe substraction is:");
			printMatrix(readFile("subMatrix.txt", (data->nSize)), &(data->nSize));

			printf("\nThe product is:");
			printMatrix(readFile("prodMatrix.txt", (data->nSize)), &(data->nSize));

			printf("\nThe transpose of matrixA is:");
			printMatrix(readFile("transMatrixA.txt", (data->nSize)), &(data->nSize));

			printf("\nThe transpose of matrixB is:");
			printMatrix(readFile("transMatrixB.txt", (data->nSize)), &(data->nSize));

			printf("\nThe inverse of matrixA is:");
			printMatrix(readFile("invMatrixA.txt", (data->nSize)), &(data->nSize));

			printf("\nThe inverse of matrixB is:");
			printMatrix(readFile("invMatrixB.txt", (data->nSize)), &(data->nSize));
			break;
		default:
			exit(0);
	}
	return 0;
}

int main()
{
	srand(time(0));
	clock_t startTime = clock();

	double **matrixA, **matrixB;
	int i, nSize;

	printf("Enter the order of the Matrix: ");
	scanf("%d", &nSize);

	matrixA = randomizeMatrix(&nSize);
	writeFile(matrixA, "matrixA.txt", nSize);

	matrixB = randomizeMatrix(&nSize);
	writeFile(matrixB, "matrixB.txt", nSize);
	
	DWORD idHilo[6];
	HANDLE aThread[6];
	info data[6];
	for(i = 0; i < 5; ++i){
		data[i].nSize = nSize;
		data[i].nOption = i;
		aThread[i] = CreateThread(NULL, 0, executeThread, &data[i], 0, &idHilo[i]);
		SetThreadPriority(aThread[i], THREAD_PRIORITY_HIGHEST);
	}

	for(i = 0; i < 5; ++i){
		WaitForSingleObject(aThread[i], INFINITE);
		CloseHandle(aThread[i]);
	}

	data[5].nSize = nSize;
	data[5].nOption = 5;
	aThread[5] = CreateThread(NULL, 0, executeThread, &data[i], 0, &idHilo[5]);
	SetThreadPriority(aThread[5], THREAD_PRIORITY_HIGHEST);
	WaitForSingleObject(aThread[5], INFINITE);
	CloseHandle(aThread[5]);

	clock_t finishTime = clock();
	printf("\nTime: %0.3fs\n", (double)(finishTime - startTime) / CLOCKS_PER_SEC);

	return 0;
}