double** addMatrix(double** matrixA, double** matrixB, int size){
	double** resultMatrix = startMatrix(&size);
	for(int i = 0; i < size; ++i)
		for(int j = 0; j < size; ++j)
			resultMatrix[i][j] = matrixA[i][j] + matrixB[i][j];
	return resultMatrix;
}