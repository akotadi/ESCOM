#include <windows.h>
#include <stdio.h>
#include <time.h>
#include "matriz.c"
#define TAM_MEM ((3 * N * N + 10) * sizeof(double))

double inf = 1e9;

void escribir(matriz A, double **p){
	for(int i = 0; i < N; ++i){
		for(int j = 0; j < N; ++j){
			**p = A[i][j];
			(*p)++;
		}
	}
}

void leer(matriz A, double **p){
	for(int i = 0; i < N; ++i){
		for(int j = 0; j < N; ++j){
			A[i][j] = **p;
			(*p)++;
		}
	}
}

double *crearMemoria(char *idMem){
	HANDLE hMem = CreateFileMapping(INVALID_HANDLE_VALUE, NULL, PAGE_READWRITE, 0, TAM_MEM, idMem);
	return MapViewOfFile(hMem, FILE_MAP_ALL_ACCESS, 0, 0, TAM_MEM);
}

double *leerMemoria(char *idMem){
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

int main(int argc, char *argv[]){
	srand(time(NULL));
	int nivel = 0; //0-padre, 1-hijo, 2-nieto
	if(argc > 1) sscanf(argv[1], "%d", &nivel);
	
	if(nivel == 0){ //padre
		double *p = crearMemoria("Producto AB");
		matriz A = matrizAleatoria();
		matriz B = matrizAleatoria();
		printf("Mandando matriz A del nivel 0 al nivel 1.\n\n");
		escribir(A, &p);
		printf("Mandando matriz B del nivel 0 al nivel 1.\n\n");
		escribir(B, &p);
		proceso(argv[0], 1);
		while(*p != inf)
			Sleep(1);
		*p++;

		matriz AB = nueva();
		leer(AB, &p);

		while(*p != inf)
			Sleep(1);

		printf("Se recibio el producto AB desde el nivel 1 al nivel 0.\n");
		printMatriz(AB);

		double *p2 = leerMemoria("Suma C+D");
		printf("Se recibio la suma C+D desde el nivel 2 al nivel 0.\n");
		matriz C_D = nueva();
		leer(C_D, &p2);
		leer(C_D, &p2);
		*p2++;
		leer(C_D, &p2);
		*p2++ = inf;
		printMatriz(C_D);

		matriz AB_inv = inversa(AB);
		matriz C_D_inv = inversa(C_D);
		writeFile(AB_inv, "AB_inv.txt");
		writeFile(C_D_inv, "C+D_inv.txt");
		printf("Inversa de AB:\n");
		printMatriz(AB_inv);
		printf("Inversa de C+D:\n");
		printMatriz(C_D_inv);

	}else if(nivel == 1){ //hijo
		double *p = leerMemoria("Producto AB");
		matriz A = nueva();
		matriz B = nueva();
		printf("Recibiendo matriz A en el nivel 1.\n");
		leer(A, &p);
		printMatriz(A);
		printf("Recibiendo matriz B en el nivel 1.\n");
		leer(B, &p);
		printMatriz(B);
		printf("Calculando producto AB en el nivel 1.\n\n");
		matriz AB = multiplicacion(A, B);
		*p++ = inf;
		escribir(AB, &p);

		double *p2 = crearMemoria("Suma C+D");
		matriz C = matrizAleatoria();
		matriz D = matrizAleatoria();
		C = matrizAleatoria();
		D = matrizAleatoria();
		printf("Mandando matriz C del nivel 1 al nivel 2.\n\n");
		escribir(C, &p2);
		printf("Mandando matriz D del nivel 1 al nivel 2.\n\n");
		escribir(D, &p2);
		proceso(argv[0], 2);
		while(*p2 != inf)
			Sleep(1);

		printf("Se ha terminado de calcular la suma C+D en el nivel 2.\n\n");
		*p++ = inf;
	
	}else if(nivel == 2){ //nieto
		double *p = leerMemoria("Suma C+D");
		matriz C = nueva();
		matriz D = nueva();
		printf("Recibiendo matriz C en el nivel 2.\n");
		leer(C, &p);
		printMatriz(C);
		printf("Recibiendo matriz D en el nivel 2.\n");
		leer(D, &p);
		printMatriz(D);
		printf("Calculando suma C+D en el nivel 2.\n\n");
		matriz C_D = suma(C, D);
		*p++ = inf;
		escribir(C_D, &p);
		while(*p != inf)
			Sleep(1);
	}
	return 0;
}