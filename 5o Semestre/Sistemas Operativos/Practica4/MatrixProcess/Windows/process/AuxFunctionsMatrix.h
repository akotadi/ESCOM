double** startMatrix(int* size){
	double** newMatrix = calloc(*size, sizeof(double*));
	for(int i = 0; i < *size; ++i)
		newMatrix[i] = calloc(*size, sizeof(double));
	return newMatrix;
}

double** randomizeMatrix(int* size){
	double** randomMatrix = startMatrix(size);
	for(int i = 0; i < *size; ++i)
		for(int j = 0; j < *size; ++j){
			randomMatrix[i][j] = -5+(15. * rand())/RAND_MAX;
			while (randomMatrix[i][j] == 0)
			{
				randomMatrix[i][j] = -10+(20. * rand())/RAND_MAX;
			}
		}
	return randomMatrix;
}

void printMatrix(double** matrix, int* size){
	printf("\n");
	for(int i = 0; i < *size; ++i){
		for(int j = 0; j < *size; ++j)
			printf("%0.3lf\t", matrix[i][j]);
		printf("\n");
	}
}

void copyMatrix(int* size, double** origin, double** destiny){
	for (int i = 0; i < *size; ++i)
		for (int j = 0; j < *size; ++j)
			destiny[i][j] = origin[i][j];
}