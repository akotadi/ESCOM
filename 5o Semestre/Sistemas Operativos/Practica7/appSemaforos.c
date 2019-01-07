#include <windows.h>
#include <stdio.h>
#include <time.h>
#include "matriz.c"
#define TAM_MEM 400

double inf = 1e9;

void escribir(matriz A, float *p){
	for(int i = 0; i < N; ++i)
		for(int j = 0; j < N; ++j)
			*p++ = A[i][j];
}

void leer(matriz A, float *p){
	for(int i = 0; i < N; ++i)
		for(int j = 0; j < N; ++j)
			A[i][j] = *p++;
}

float *crearMemoria(char *idMem){
	HANDLE hMem = CreateFileMapping(INVALID_HANDLE_VALUE, NULL, PAGE_READWRITE, 0, TAM_MEM, idMem);
	return MapViewOfFile(hMem, FILE_MAP_ALL_ACCESS, 0, 0, TAM_MEM);
}

float *leerMemoria(char *idMem){
	HANDLE hMem = OpenFileMapping(FILE_MAP_ALL_ACCESS, FALSE, idMem);
	return MapViewOfFile(hMem, FILE_MAP_ALL_ACCESS, 0, 0, TAM_MEM);
}

void proceso(char *name, int nivel){
	STARTUPINFO si;
	PROCESS_INFORMATION pi;
	ZeroMemory(&si, sizeof(si));
	si.cb = sizeof(si);
	ZeroMemory(&pi, sizeof(pi));
	char args[100];
	sprintf(args, "%s %d", name, nivel);
	CreateProcess(NULL, args, NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi);
}

HANDLE hSem[5];

void crearSems(int nivel){
	for(int i = 0; i < 5; ++i){
		char name[100];
		sprintf(name, "sem%d", i);
		if(nivel == 0) hSem[i] = CreateSemaphore(NULL, 0, 1, name);
		else hSem[i] = OpenSemaphore(SEMAPHORE_ALL_ACCESS, FALSE, name);
	}
}

int main(int argc, char *argv[]){
	srand(time(NULL));
	int nivel = 0; //0-padre, 1-hijo, 2-nieto
	if(argc > 1) sscanf(argv[1], "%d", &nivel);

	crearSems(nivel);
	if(nivel < 2) proceso(argv[0], nivel + 1);

	if(nivel == 0){
		float *p = crearMemoria("mem");
		matriz A = matrizAleatoria(), B = matrizAleatoria();
		matriz AB = nueva(), C_D = nueva();

		printf("Mandando matriz A\n\n");
		escribir(A, p);
		ReleaseSemaphore(hSem[0], 1, NULL);
		WaitForSingleObject(hSem[1], INFINITE);
		
		printf("Mandando matriz B\n\n");
		escribir(B, p);
		ReleaseSemaphore(hSem[0], 1, NULL);
		WaitForSingleObject(hSem[1], INFINITE);
		
		printf("Recibiendo producto AB:\n");
		leer(AB, p);
		printMatriz(AB);
		ReleaseSemaphore(hSem[0], 1, NULL);

		WaitForSingleObject(hSem[4], INFINITE);
		printf("Recibiendo suma C+D:\n");
		leer(C_D, p);
		printMatriz(C_D);
		ReleaseSemaphore(hSem[0], 1, NULL);

		matriz AB_inv = inversa(AB);
		matriz C_D_inv = inversa(C_D);
		writeFile(AB_inv, "prodInv.txt");
		writeFile(C_D_inv, "addInv.txt");
		printf("Inversa de AB:\n");
		printMatriz(AB_inv);
		printf("Inversa de C+D:\n");
		printMatriz(C_D_inv);

	}else if(nivel == 1){
		float *p = leerMemoria("mem");
		matriz A = nueva(), B = nueva();
		matriz C = matrizAleatoria(), D = matrizAleatoria();
		C = matrizAleatoria(), D = matrizAleatoria();

		WaitForSingleObject(hSem[0], INFINITE);
		printf("Recibiendo matriz A:\n");
		leer(A, p);
		printMatriz(A);
		ReleaseSemaphore(hSem[1], 1, NULL);

		WaitForSingleObject(hSem[0], INFINITE);
		printf("Recibiendo matriz B:\n");
		leer(B, p);
		printMatriz(B);

		matriz AB = multiplicacion(A, B);
		printf("Mandando producto AB\n\n");
		escribir(AB, p);
		ReleaseSemaphore(hSem[1], 1, NULL);
		WaitForSingleObject(hSem[0], INFINITE);

		printf("Mandando matriz C\n\n");
		escribir(C, p);
		ReleaseSemaphore(hSem[2], 1, NULL);
		WaitForSingleObject(hSem[3], INFINITE);

		printf("Mandando matriz D\n\n");
		escribir(D, p);
		ReleaseSemaphore(hSem[2], 1, NULL);
		WaitForSingleObject(hSem[3], INFINITE);

	}else if(nivel == 2){
		float *p = leerMemoria("mem");
		matriz C = nueva();
		matriz D = nueva();

		WaitForSingleObject(hSem[2], INFINITE);
		printf("Recibiendo matriz C:\n");
		leer(C, p);
		printMatriz(C);
		ReleaseSemaphore(hSem[3], 1, NULL);

		WaitForSingleObject(hSem[2], INFINITE);
		printf("Recibiendo matriz D:\n");
		leer(D, p);
		printMatriz(D);

		matriz C_D = suma(C, D);
		printf("Mandando suma C+D\n\n");
		escribir(C_D, p);
		ReleaseSemaphore(hSem[4], 1, NULL);
		WaitForSingleObject(hSem[0], INFINITE);
		ReleaseSemaphore(hSem[3], 1, NULL);
	}
	return 0;
}