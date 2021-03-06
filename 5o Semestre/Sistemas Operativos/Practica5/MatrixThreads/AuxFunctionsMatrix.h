double** startMatrix(int* nSize){
	int i;

	double** newMatrix = calloc(*nSize, sizeof(double*));
	
	for(i = 0; i < *nSize; ++i)
		newMatrix[i] = calloc(*nSize, sizeof(double));
	
	return newMatrix;
}

double** randomizeMatrix(int* nSize){
	int i,j;
	double** randomMatrix = startMatrix(nSize);

	for(i = 0; i < *nSize; ++i)
		for(j = 0; j < *nSize; ++j){
			randomMatrix[i][j] = -10+(20. * rand())/RAND_MAX;
			while (randomMatrix[i][j] == 0)
			{
				randomMatrix[i][j] = -10+(20. * rand())/RAND_MAX;
			}
		}
	return randomMatrix;
}

void printMatrix(double** matrix, int* nSize){
	int i, j;

	printf("\n");
	for(i = 0; i < *nSize; ++i){
		for(j = 0; j < *nSize; ++j)
			printf("%0.3lf\t", matrix[i][j]);
		printf("\n");
	}
}

void copyMatrix(int* nSize, double** origin, double** destiny){
	int i, j;

	for (i = 0; i < *nSize; ++i)
		for (j = 0; j < *nSize; ++j)
			destiny[i][j] = origin[i][j];
}