#include <windows.h>
#include <stdio.h>
#include <time.h>
#include "AuxFunctionsMatrix.h"
#include "AddMatrix.h"
#include "ProdMatrix.h"
#include "InverseMatrix.h"

#define TAM_MEM 400

void writeMemory(double **A, float *pMemory, int nSize){
	for(int i = 0; i < nSize; ++i){
		for(int j = 0; j < nSize; ++j){
			*pMemory++ = A[i][j];
		}
	}
}

void readMemory(double **A, float *pMemory, int nSize){
	for(int i = 0; i < nSize; ++i){
		for(int j = 0; j < nSize; ++j){
			A[i][j] = *pMemory++;
		}
	}
}

float *newMemory(char *idMem){
	HANDLE hMem = CreateFileMapping(INVALID_HANDLE_VALUE, NULL, PAGE_READWRITE, 0, TAM_MEM, idMem);
	return MapViewOfFile(hMem, FILE_MAP_ALL_ACCESS, 0, 0, TAM_MEM);
}

float *checkMemory(char *idMem){
	HANDLE hMem = OpenFileMapping(FILE_MAP_ALL_ACCESS, FALSE, idMem);
	return MapViewOfFile(hMem, FILE_MAP_ALL_ACCESS, 0, 0, TAM_MEM);
}

void startNewProcess(char *name, int nLevel){
	STARTUPINFO si;
	PROCESS_INFORMATION pi;
	ZeroMemory(&si, sizeof(si));
	si.cb = sizeof(si);
	ZeroMemory(&pi, sizeof(pi));
	char args[100];
	sprintf(args, "%s %d", name, nLevel);
	CreateProcess(NULL, args, NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi);
}

HANDLE hSem[5];

void createSem(int nLevel){
	for(int i = 0; i < 5; ++i){
		char aName[100];
		sprintf(aName, "sem%d", i);
		if(nLevel == 0) {
			hSem[i] = CreateSemaphore(NULL, 0, 1, aName);
		}else {
			hSem[i] = OpenSemaphore(SEMAPHORE_ALL_ACCESS, FALSE, aName);
		}
	}
}

int main(int argc, char *argv[]){
	srand(time(NULL));
	int nLevel = 0, nSize = 10;
	HANDLE hRead, hWrite;
	double **matrixA, **matrixB;

	if(argc > 1){
		sscanf(argv[1], "%d", &nLevel);
	}

	createSem(nLevel);
	if(nLevel < 2) startNewProcess(argv[0], nLevel + 1);
	
	if(nLevel == 0){
		float *pMemory = newMemory("memory1");
		matrixA = randomizeMatrix(&nSize);
		printf("\nMatrix A: \n");
		printMatrix(matrixA, &nSize);
		matrixB = randomizeMatrix(&nSize);
		printf("\nMatrix B: \n");
		printMatrix(matrixB, &nSize);

		double **matrixAB = startMatrix(&nSize);
		double **matrixC_D = startMatrix(&nSize);

		writeMemory(matrixA, pMemory, nSize);
		ReleaseSemaphore(hSem[0], 1, NULL);
		WaitForSingleObject(hSem[1], INFINITE);
		
		writeMemory(matrixB, pMemory, nSize);
		ReleaseSemaphore(hSem[0], 1, NULL);
		WaitForSingleObject(hSem[1], INFINITE);
		
		readMemory(matrixAB, pMemory, nSize);
		ReleaseSemaphore(hSem[0], 1, NULL);

		WaitForSingleObject(hSem[4], INFINITE);
		readMemory(matrixC_D, pMemory, nSize);
		ReleaseSemaphore(hSem[0], 1, NULL);

		double **matrixAB_inv = inverseMatrix(nSize, matrixAB);
		double **matrixC_D_inv = inverseMatrix(nSize, matrixC_D);
		writeMatrixFile(matrixAB_inv, "productInverse.txt", nSize);
		writeMatrixFile(matrixC_D_inv, "addInverse.txt", nSize);

	}else if(nLevel == 1){
		float *pMemory = checkMemory("memory1");
		double **matrixA = startMatrix(&nSize);
		double **matrixB = startMatrix(&nSize);
		double **matrixC = randomizeMatrix(&nSize);
		double **matrixD = randomizeMatrix(&nSize);
		matrixC = randomizeMatrix(&nSize);
		matrixD = randomizeMatrix(&nSize);

		WaitForSingleObject(hSem[0], INFINITE);
		readMemory(matrixA, pMemory, nSize);
		ReleaseSemaphore(hSem[1], 1, NULL);

		WaitForSingleObject(hSem[0], INFINITE);
		readMemory(matrixA, pMemory, nSize);

		double **matrixAB = prodMatrix(matrixA, matrixB, nSize);
		writeMemory(matrixAB, pMemory, nSize);
		ReleaseSemaphore(hSem[1], 1, NULL);
		WaitForSingleObject(hSem[0], INFINITE);

		writeMemory(matrixC, pMemory, nSize);
		ReleaseSemaphore(hSem[2], 1, NULL);
		WaitForSingleObject(hSem[3], INFINITE);

		writeMemory(matrixD, pMemory, nSize);
		ReleaseSemaphore(hSem[2], 1, NULL);
		WaitForSingleObject(hSem[3], INFINITE);

	}else if(nLevel == 2){
		float *pMemory = checkMemory("memory1");
		double **matrixC = startMatrix(&nSize);
		double **matrixD = startMatrix(&nSize);

		WaitForSingleObject(hSem[2], INFINITE);
		readMemory(matrixC, pMemory, nSize);
		ReleaseSemaphore(hSem[3], 1, NULL);

		WaitForSingleObject(hSem[2], INFINITE);
		readMemory(matrixD, pMemory, nSize);

		double **matrixC_D = addMatrix(matrixC, matrixD, nSize);
		writeMemory(matrixC_D, pMemory, nSize);
		ReleaseSemaphore(hSem[4], 1, NULL);
		WaitForSingleObject(hSem[0], INFINITE);
		ReleaseSemaphore(hSem[3], 1, NULL);
	}
	return 0;
}