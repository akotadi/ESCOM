#include <windows.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include "AuxFunctionsMatrix.h"
#include "AddMatrix.h"
#include "SubMatrix.h"
#include "ProdMatrix.h"
#include "InverseMatrix.h"

HANDLE startNewProcess(char *name, HANDLE hRead, int nLevel, int nSize){
	STARTUPINFO si;
	PROCESS_INFORMATION pi;
	ZeroMemory(&pi, sizeof(pi));
	ZeroMemory(&si, sizeof(si));
	GetStartupInfo(&si);
	si.hStdInput = hRead;
	si.hStdError = GetStdHandle(STD_ERROR_HANDLE);
	si.hStdOutput = GetStdHandle(STD_OUTPUT_HANDLE);
	si.dwFlags = STARTF_USESTDHANDLES;
	si.cb = sizeof(si);
	char args[100];
	sprintf(args, "%s %d %d", name, nSize, nLevel);
	CreateProcess(NULL, args, NULL, NULL, TRUE, 0, NULL, NULL, &si, &pi);
	return pi.hProcess;
}

void writeIntoPipe(double **A, HANDLE hWrite, int nSize){
	for(int i = 0; i < nSize; ++i)
		for(int j = 0; j < nSize; ++j)
			WriteFile(hWrite, &A[i][j], sizeof(double), NULL, NULL);
}

void readFromPipe(double **A, HANDLE hRead, int nSize){
	for(int i = 0; i < nSize; ++i)
		for(int j = 0; j < nSize; ++j)
			ReadFile(hRead, &A[i][j], sizeof(double), NULL, NULL);
}

void newPipe(HANDLE *hRead, HANDLE *hWrite){
	SECURITY_ATTRIBUTES pipeSeg = {sizeof(SECURITY_ATTRIBUTES), NULL, TRUE};
	CreatePipe(hRead, hWrite, &pipeSeg, 0);
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
		newPipe(&hRead, &hWrite);
		printf("Enter the order of the Matrix: ");
		scanf("%d", &nSize);
		matrixA = randomizeMatrix(&nSize);
		printf("\nMatrix A: \n");
		printMatrix(matrixA, &nSize);
		matrixB = randomizeMatrix(&nSize);
		printf("\nMatrix B: \n");
		printMatrix(matrixB, &nSize);
		writeIntoPipe(matrixA, hWrite, nSize);
		writeIntoPipe(matrixB, hWrite, nSize);
		WriteFile(hWrite, &hWrite, sizeof(HANDLE), NULL, NULL);
		HANDLE hProc = startNewProcess(argv[0], hRead, 1, nSize);
		WaitForSingleObject(hProc, INFINITE);

		double **matrixAB = startMatrix(&nSize);
		readFromPipe(matrixAB, hRead, nSize);

		double **matrixC_D = startMatrix(&nSize);
		readFromPipe(matrixC_D, hRead, nSize);

		double **matrixAB_inv = inverseMatrix(nSize, matrixAB);
		writeMatrixFile(matrixAB_inv, "productInverse.txt", nSize);
		double **matrixC_D_inv = inverseMatrix(nSize, matrixC_D);
		writeMatrixFile(matrixC_D_inv, "addInverse.txt", nSize);

	}else if(nLevel == 1){ //hijo
		hRead = GetStdHandle(STD_INPUT_HANDLE);
		double **matrixA = startMatrix(&nSize);
		double **matrixB = startMatrix(&nSize);
		readFromPipe(matrixA, hRead, nSize);
		readFromPipe(matrixB, hRead, nSize);
		double **matrixAB = prodMatrix(matrixA, matrixB, nSize);
		ReadFile(hRead, &hWrite, sizeof(HANDLE), NULL, NULL);
		writeIntoPipe(matrixAB, hWrite, nSize);

		HANDLE hRead2, hWrite2;
		newPipe(&hRead2, &hWrite2);
		double **matrixC = randomizeMatrix(&nSize);
		double **matrixD = randomizeMatrix(&nSize);
		writeIntoPipe(matrixC, hWrite2, nSize);
		writeIntoPipe(matrixD, hWrite2, nSize);
		WriteFile(hWrite2, &hWrite, sizeof(HANDLE), NULL, NULL);
		HANDLE hProc = startNewProcess(argv[0], hRead2, 2, nSize);
		WaitForSingleObject(hProc, INFINITE);

	}else if(nLevel == 2){ //nieto
		hRead = GetStdHandle(STD_INPUT_HANDLE);
		double **matrixC = startMatrix(&nSize);
		double **matrixD = startMatrix(&nSize);
		readFromPipe(matrixC, hRead, nSize);
		readFromPipe(matrixD, hRead, nSize);
		ReadFile(hRead, &hWrite, sizeof(HANDLE), NULL, NULL);
		double **matrixC_D = addMatrix(matrixC, matrixD, nSize);
		writeIntoPipe(matrixC_D, hWrite, nSize);
	}
	return 0;
}