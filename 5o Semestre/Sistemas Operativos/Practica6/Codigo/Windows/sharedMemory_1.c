#include <windows.h>	/* Cliente de la memoria compartida */
#include <stdio.h>

#define TAM_MEM 27		/* Tamaño de la memoria compartida en bytes */

int main(){
	HANDLE hArchMapeo;
	char *idMemCompartida = "MemoriaCompartida";
	char *apDatos, *apTrabajo, c;
	if((hArchMapeo=OpenFileMapping(
		FILE_MAP_ALL_ACCESS,	// acceso lectura/escritura de la memoria compartida
		FALSE, 					// no se hereda el nombre
		idMemCompartida)		// identificador de la memoria compartida
		)==NULL)
	{
		printf("No se abrio archivo de mapeo de la memoria compartida: (%i)\n", GetLastError());
		exit(-1);
	}
	if((apDatos=(char *)MapViewOfFile(
		hArchMapeo, 			// Manejador del mapeo
		FILE_MAP_ALL_ACCESS, 	// Permiso de lectura/escritura en la memoria
		0, 
		0, 
		TAM_MEM)
		)==NULL)
	{
		printf("No se accedio a la memoria compartida: (%i)\n", GetLastError());
		CloseHandle(hArchMapeo);
		exit(-1);
	}
	for(apTrabajo=apDatos; *apTrabajo!='\0'; apTrabajo++) putchar(*apTrabajo);
		putchar('\n');
	*apDatos='*';
	UnmapViewOfFile(apDatos);
	CloseHandle(hArchMapeo);
	exit(0);
}