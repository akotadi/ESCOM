double** inverseMatrix(int size, double** matrix){
	double **invMatrix;
	int i, j;

	invMatrix = startMatrix(&size);
	copyMatrix(&size, matrix, invMatrix);

	if (size <= 1) return invMatrix;  // must be of dimension >= 2
	for (int i=1; i < size; i++) invMatrix[0][i] /= invMatrix[0][0]; // normalize row 0
	for (int i=1; i < size; i++)  { 
		for (int j=i; j < size; j++)  { // do a column of L
			double sum = 0.0;
			for (int k = 0; k < i; k++)  
				sum += invMatrix[j][k] * invMatrix[k][i];
			invMatrix[j][i] -= sum;
		}
		if (i == size-1) continue;
		for (int j=i+1; j < size; j++)  {  // do a row of U
			double sum = 0.0;
			for (int k = 0; k < i; k++)
				sum += invMatrix[i][k]*invMatrix[k][j];
			invMatrix[i][j] = (invMatrix[i][j]-sum) / invMatrix[i][i];
		}
	}
	for ( int i = 0; i < size; i++ )  // invert L
		for ( int j = i; j < size; j++ )  {
			double x = 1.0;
			if ( i != j ) {
				x = 0.0;
				for ( int k = i; k < j; k++ ) 
					x -= invMatrix[j][k]*invMatrix[k][i];
			}
			invMatrix[j][i] = x / invMatrix[j][j];
		}
	for ( int i = 0; i < size; i++ )   // invert U
		for ( int j = i; j < size; j++ )  {
			if ( i == j ) continue;
			double sum = 0.0;
			for ( int k = i; k < j; k++ )
				sum += invMatrix[k][j]*( (i==k) ? 1.0 : invMatrix[i][k] );
			invMatrix[i][j] = -sum;
		}
	for ( int i = 0; i < size; i++ )   // final inversion
		for ( int j = 0; j < size; j++ )  {
			double sum = 0.0;
			for ( int k = ((i>j)?i:j); k < size; k++ )  
				sum += ((j==k)?1.0:invMatrix[j][k])*invMatrix[k][i];
			invMatrix[j][i] = sum;
		}

	return invMatrix;
}