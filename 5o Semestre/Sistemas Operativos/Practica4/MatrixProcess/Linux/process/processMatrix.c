#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
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

void executeProgram(int nOption, int nSize){
	char *argv[2];
	switch(nOption){
		case 0:
			argv[0] = "add";
			break;
		case 1:
			argv[0] = "sub";
			break;
		case 2:
			argv[0] = "prod";
			break;
		case 3:
			argv[0] = "trans";
			break;
		case 4:
			argv[0] = "inv";
			break;
		case 5:
			argv[0] = "show";
			break;
		default:
			exit(0);
	}
	sprintf(argv[1], "%d", nSize);
	execv(argv[0], argv);
}

int main()
{
	srand(time(0));
	clock_t startTime = clock();

	double **matrixA, **matrixB;
	int nSize, i;

	printf("Enter the order of the Matrix: ");
	scanf("%d", &nSize);

	matrixA = randomizeMatrix(&nSize);
	writeFile(matrixA, "matrixA.txt", nSize);

	matrixB = randomizeMatrix(&nSize);
	writeFile(matrixB, "matrixB.txt", nSize);

	for( i = 0; i < 5; ++i){
		if(fork() == 0){
			executeProgram(i, nSize);
			exit(0);
		}
	}
	for( i = 0; i < 5; ++i)
		wait(0);

	if(fork() == 0){
		executeProgram(5, nSize);
		exit(0);
	}
	wait(0);

	clock_t finishTime = clock();
	printf("\nTime: %0.3fs\n", (double)(finishTime - startTime) / CLOCKS_PER_SEC);

	return 0;
}