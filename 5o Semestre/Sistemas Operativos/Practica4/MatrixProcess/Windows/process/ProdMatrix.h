double** prodMatrix(double** matrixA, double** matrixB, int size){
	double** resultMatrix = startMatrix(&size);
	for(int i = 0; i < size; ++i)
		for(int j = 0; j < size; ++j)
			for(int k = 0; k < size; ++k)
				resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
	return resultMatrix;
}