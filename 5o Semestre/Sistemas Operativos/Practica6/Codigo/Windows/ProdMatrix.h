double** prodMatrix(double** matrixA, double** matrixB, int nSize){
	int i, j, k;
	double** resultMatrix = startMatrix(&nSize);

	for(i = 0; i < nSize; ++i)
		for(j = 0; j < nSize; ++j)
			for(k = 0; k < nSize; ++k)
				resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
	
	return resultMatrix;
}