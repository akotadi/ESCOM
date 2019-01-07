#include <windows.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include "AuxFunctionsMatrix.h"
#include "AddMatrix.h"
#include "ProdMatrix.h"
#include "InverseMatrix.h"

#define TAM_MEM 310

const double inf = 1e9;

void startNewProcess(char *name, int nLevel, int nSize){
	STARTUPINFO si;
	PROCESS_INFORMATION pi;
	ZeroMemory(&si, sizeof(si));
	si.cb = sizeof(si);
	ZeroMemory(&pi, sizeof(pi));
	char args[100];
	sprintf(args, "%s %d %d", name, nSize, nLevel);
	CreateProcess(NULL, args, NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi);
}

void writeMemory(double **A, double **pMemory, int nSize){
	for(int i = 0; i < nSize; ++i){
		for(int j = 0; j < nSize; ++j){
			**pMemory = A[i][j];
			(*pMemory)++;
		}
	}
}

void readMemory(double **A, double **pMemory, int nSize){
	for(int i = 0; i < nSize; ++i){
		for(int j = 0; j < nSize; ++j){
			A[i][j] = **pMemory;
			(*pMemory)++;
		}
	}
}

double *newMemory(char *idMem){
	HANDLE hMem = CreateFileMapping(INVALID_HANDLE_VALUE, NULL, PAGE_READWRITE, 0, TAM_MEM, idMem);
	return MapViewOfFile(hMem, FILE_MAP_ALL_ACCESS, 0, 0, TAM_MEM);
}

double *checkMemory(char *idMem){
	HANDLE hMem = OpenFileMapping(FILE_MAP_ALL_ACCESS, FALSE, idMem);
	return MapViewOfFile(hMem, FILE_MAP_ALL_ACCESS, 0, 0, TAM_MEM);
}

int main(int argc, char *argv[]){
	srand(time(NULL));
	int nLevel = 0, nSize;
	HANDLE hRead, hWrite;
	double **matrixA, **matrixB;

	if(argc > 2){
		sscanf(argv[1], "%d", &nSize);
		sscanf(argv[2], "%d", &nLevel);
	}

	if(nLevel == 0){ 
		printf("Enter the order of the Matrix: ");
		scanf("%d", &nSize);
		double *pMemory = newMemory("memoryProduct");
		matrixA = randomizeMatrix(&nSize);
		printf("\nMatrix A: \n");
		printMatrix(matrixA, &nSize);
		matrixB = randomizeMatrix(&nSize);
		printf("\nMatrix B: \n");
		printMatrix(matrixB, &nSize);
		writeMemory(matrixA, &pMemory, nSize);
		writeMemory(matrixB, &pMemory, nSize);
		startNewProcess(argv[0], 1, nSize);
		while(*pMemory != inf)
			Sleep(1);
		*pMemory++;

		double **matrixAB = startMatrix(&nSize);
		readMemory(matrixAB, &pMemory, nSize);

		while(*pMemory != inf)
			Sleep(1);

		double *pMemory2 = checkMemory("memoryAddition");
		double **matrixC_D = startMatrix(&nSize);
		readMemory(matrixC_D, &pMemory2, nSize);
		readMemory(matrixC_D, &pMemory2, nSize);
		*pMemory2++;
		readMemory(matrixC_D, &pMemory2, nSize);
		*pMemory2++ = inf;

		double **matrixAB_inv = inverseMatrix(nSize, matrixAB);
		writeMatrixFile(matrixAB_inv, "productInverse.txt", nSize);
		double **matrixC_D_inv = inverseMatrix(nSize, matrixC_D);
		writeMatrixFile(matrixC_D_inv, "addInverse.txt", nSize);

	}else if(nLevel == 1){ //hijo
		double *pMemory = checkMemory("memoryProduct");
		double **matrixA = startMatrix(&nSize);
		double **matrixB = startMatrix(&nSize);
		readMemory(matrixA, &pMemory, nSize);
		readMemory(matrixB, &pMemory, nSize);
		double **matrixAB = prodMatrix(matrixA, matrixB, nSize);
		*pMemory++ = inf;
		writeMemory(matrixAB, &pMemory, nSize);

		double *pMemory2 = newMemory("memoryAddition");
		double **matrixC = randomizeMatrix(&nSize);
		double **matrixD = randomizeMatrix(&nSize);
		writeMemory(matrixC, &pMemory2, nSize);
		writeMemory(matrixD, &pMemory2, nSize);
		startNewProcess(argv[0], 2, nSize);
		while(*pMemory2 != inf)
			Sleep(1);

		*pMemory++ = inf;

	}else if(nLevel == 2){ //nieto
		double *pMemory = checkMemory("memoryAddition");
		double **matrixC = startMatrix(&nSize);
		double **matrixD = startMatrix(&nSize);
		readMemory(matrixC, &pMemory, nSize);
		readMemory(matrixD, &pMemory, nSize);
		double **matrixC_D = addMatrix(matrixC, matrixD, nSize);
		*pMemory++ = inf;
		writeMemory(matrixC_D, &pMemory, nSize);
		while(*pMemory != inf)
			Sleep(1);
	}
	return 0;
}