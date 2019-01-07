#include <windows.h>
#include <tchar.h>
#include <stdio.h>	
#include <time.h>
#include <stdlib.h>

int main(){
	int carpeta, carp, numArch;
	char Cad[200], palabra[] = "Este es un Archivo";

	printf("Ingrese la dirección del directorio a crear\n");
	scanf("%s", &Cad);

	srand(time(0));
	numArch= rand()%10; //rango del 0 al 9
	numArch++; //para evitar que salga un cero

	if (!CreateDirectory(Cad, NULL)) 
	{ 
		printf("CreateDirectory failed (%d)\n", GetLastError()); 
		exit(1);
	}
	return 0;
}
