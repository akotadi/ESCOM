#include <windows.h>
#include <stdio.h>

DWORD WINAPI funcionHilo(LPVOID lpParam);

typedef struct Informacion info;

struct Informacion{
	int val_1;
	int val_2;
};

int main()
{
	DWORD idHilo;
	HANDLE manHilo;
	info argumentos;
	argumentos.val_1 = 10;
	argumentos.val_2 = 100;

	// Creación del hilo
	manHilo = CreateThread(NULL, 0, funcionHilo, &argumentos, 0, &idHilo);

	// Espera la finalización del hilo
	WaitForSingleObject(manHilo, INFINITE);

	printf("Valores al salir del Hilo: %i %i\n", argumentos.val_1, argumentos.val_2);

	// Cierre del manejador del hilo creado
	CloseHandle(manHilo);
	return 0;
}

DWORD WINAPI funcionHilo(LPVOID lpParam){
	info *datos = (info *)lpParam;
	printf("Valores al entrar al Hilo: %i %i\n", datos->val_1, datos->val_2);
	datos->val_1 *= 2;
	datos->val_2 *= 2;
	return 0;
}