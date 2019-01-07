#include <windows.h>
#include <stdio.h>
#include <time.h>
#include "matriz.c"

HANDLE proceso(char *name, HANDLE hRead, int nivel){
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
	sprintf(args, "%s %d", name, nivel);
	CreateProcess(NULL, args, NULL, NULL, TRUE, 0, NULL, NULL, &si, &pi);
	return pi.hProcess;
}

void creaTuberia(HANDLE *hRead, HANDLE *hWrite){
	SECURITY_ATTRIBUTES pipeSeg = {sizeof(SECURITY_ATTRIBUTES), NULL, TRUE};
	CreatePipe(hRead, hWrite, &pipeSeg, 0);
}

void escribir(matriz A, HANDLE hWrite){
	for(int i = 0; i < N; ++i)
		for(int j = 0; j < N; ++j)
			WriteFile(hWrite, &A[i][j], sizeof(double), NULL, NULL);
}

void leer(matriz A, HANDLE hRead){
	for(int i = 0; i < N; ++i)
		for(int j = 0; j < N; ++j)
			ReadFile(hRead, &A[i][j], sizeof(double), NULL, NULL);
}

int main(int argc, char *argv[]){
	srand(time(NULL));
	int nivel = 0; //0-padre, 1-hijo, 2-nieto
	HANDLE hRead, hWrite;
	if(argc > 1)
		sscanf(argv[1], "%d", &nivel);
	if(nivel == 0){ //padre
		creaTuberia(&hRead, &hWrite);
		matriz A = matrizAleatoria();
		matriz B = matrizAleatoria();
		printf("Mandando matriz A del nivel 0 al nivel 1.\n\n");
		escribir(A, hWrite);
		printf("Mandando matriz B del nivel 0 al nivel 1.\n\n");
		escribir(B, hWrite);
		WriteFile(hWrite, &hWrite, sizeof(HANDLE), NULL, NULL);
		HANDLE hProc = proceso(argv[0], hRead, 1);
		WaitForSingleObject(hProc, INFINITE);

		matriz AB = nueva();
		leer(AB, hRead);
		printf("Se recibio el producto AB desde el nivel 1 al nivel 0.\n");
		printMatriz(AB);

		printf("Se recibio la suma C+D desde el nivel 2 al nivel 0.\n");
		matriz C_D = nueva();
		leer(C_D, hRead);
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
		hRead = GetStdHandle(STD_INPUT_HANDLE);
		matriz A = nueva();
		matriz B = nueva();
		printf("Recibiendo matriz A en el nivel 1.\n");
		leer(A, hRead);
		printMatriz(A);
		printf("Recibiendo matriz B en el nivel 1.\n");
		leer(B, hRead);
		printMatriz(B);
		printf("Calculando producto AB en el nivel 1.\n\n");
		matriz AB = multiplicacion(A, B);
		ReadFile(hRead, &hWrite, sizeof(HANDLE), NULL, NULL);
		escribir(AB, hWrite);

		HANDLE hRead2, hWrite2;
		creaTuberia(&hRead2, &hWrite2);
		matriz C = matrizAleatoria();
		matriz D = matrizAleatoria();
		C = matrizAleatoria();
		D = matrizAleatoria();
		printf("Mandando matriz C del nivel 1 al nivel 2.\n\n");
		escribir(C, hWrite2);
		printf("Mandando matriz D del nivel 1 al nivel 2.\n\n");
		escribir(D, hWrite2);
		WriteFile(hWrite2, &hWrite, sizeof(HANDLE), NULL, NULL);
		HANDLE hProc = proceso(argv[0], hRead2, 2);
		WaitForSingleObject(hProc, INFINITE);

		printf("Se ha terminado de calcular la suma C+D en el nivel 2.\n\n");

	}else if(nivel == 2){ //nieto
		hRead = GetStdHandle(STD_INPUT_HANDLE);
		matriz C = nueva();
		matriz D = nueva();
		printf("Recibiendo matriz C en el nivel 2.\n");
		leer(C, hRead);
		printMatriz(C);
		printf("Recibiendo matriz D en el nivel 2.\n");
		leer(D, hRead);
		printMatriz(D);
		ReadFile(hRead, &hWrite, sizeof(HANDLE), NULL, NULL);
		printf("Calculando suma C+D en el nivel 2.\n\n");
		matriz C_D = suma(C, D);
		escribir(C_D, hWrite);
	}
	return 0;
}