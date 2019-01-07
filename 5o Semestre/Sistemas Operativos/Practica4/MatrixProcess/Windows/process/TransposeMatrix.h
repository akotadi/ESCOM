double** transposeMatrix(double** matrix, int size){
	double** transMatrix;
	int c, d;

	transMatrix = startMatrix(&size);

	for (c = 0; c < size; c++)
		for( d = 0 ; d < size ; d++ )
			transMatrix[d][c] = matrix[c][d];

	return transMatrix;
}