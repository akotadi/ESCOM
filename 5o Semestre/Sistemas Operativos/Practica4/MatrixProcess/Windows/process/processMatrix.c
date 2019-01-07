#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <windows.h>
#include "AuxFunctionsMatrix.h"

void writeFile(double** matrix, char* name, int size){
	FILE * auxFile = fopen(name, "w");

	for(int i = 0; i < size; ++i){
		for(int j = 0; j < size; ++j)
			fprintf(auxFile, "%0.3lf ", matrix[i][j]);
		fprintf(auxFile, "\n");
	}
	fclose(auxFile);
}

PROCESS_INFORMATION executeProgram(int nOption, int nSize){
	STARTUPINFO si;
	PROCESS_INFORMATION pi;
	ZeroMemory(&si, sizeof(si));
	si.cb = sizeof(si);
	ZeroMemory(&pi, sizeof(pi));
	char str[100];
	switch(nOption){
		case 0:
			sprintf(str, "add.exe %d", nSize);
			break;
		case 1:
			sprintf(str, "sub.exe %d", nSize);
			break;
		case 2:
			sprintf(str, "prod.exe %d", nSize);
			break;
		case 3:
			sprintf(str, "trans.exe %d", nSize);
			break;
		case 4:
			sprintf(str, "inv.exe %d", nSize);
			break;
		case 5:
			sprintf(str, "show.exe %d", nSize);
			break;
		default:
			exit(0);
	}
	CreateProcess(NULL, str, NULL, NULL, FALSE, IDLE_PRIORITY_CLASS, NULL, NULL, &si, &pi);
	return pi;
}

int main()
{
	srand(time(0));
	clock_t startTime = clock();

	double **matrixA, **matrixB;
	int nSize, i, j;

	printf("Enter the order of the Matrix: ");
	scanf("%d", &nSize);

	matrixA = randomizeMatrix(&nSize);
	writeFile(matrixA, "matrixA.txt", nSize);

	matrixB = randomizeMatrix(&nSize);
	writeFile(matrixB, "matrixB.txt", nSize);
	
	HANDLE aProcess[5], aThread[5];
	for(i = 0; i < 5; ++i){
		PROCESS_INFORMATION pi = executeProgram(i, nSize);
		aProcess[i] = pi.hProcess;
		aThread[i] = pi.hThread;
	}

	for(i = 0; i < 5; ++i){
		WaitForSingleObject(aProcess[i], INFINITE);
		CloseHandle(aProcess[i]);
		CloseHandle(aThread[i]);
	}

	PROCESS_INFORMATION pi = executeProgram(5, nSize);
	WaitForSingleObject(pi.hProcess, INFINITE);
	CloseHandle(pi.hProcess);
	CloseHandle(pi.hThread);

	clock_t finishTime = clock();
	printf("\nTime: %0.3fs\n", (double)(finishTime - startTime) / CLOCKS_PER_SEC);

	return 0;
}