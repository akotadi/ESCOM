#include <stdio.h>
#include <stdlib.h>

#include "defs.h"

void saveData( float data[] ){
	FILE *apFile;

	apFile = fopen( "sine.dat", "w");
	if( !apFile ){
		perror("Error al abrir el archivo");
		exit(EXIT_FAILURE);
	}

	for (register int n = 0; n < SAMPLES; ++n){
		fprintf(apFile, "%f\n", data[n]);
	}

	fclose(apFile);
}