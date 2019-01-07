double** transposeMatrix(double** matrix, int nSize){
	double** transMatrix;
	int i, j;

	transMatrix = startMatrix(&nSize);

	for (i = 0; i < nSize; i++)
		for(j = 0; j < nSize; j++)
			transMatrix[j][i] = matrix[i][j];

	return transMatrix;
}