double** addMatrix(double** matrixA, double** matrixB, int nSize){
	int i, j;
	double** resultMatrix = startMatrix(&nSize);

	for(i = 0; i < nSize; ++i)
		for(j = 0; j < nSize; ++j)
			resultMatrix[i][j] = matrixA[i][j] + matrixB[i][j];

	return resultMatrix;
}