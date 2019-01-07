double** cofactorMatrix(double** matrix, int nSize){
	double **subMatrix, **cofacMatrix;
	int p, q, m, n, i, j;

	cofacMatrix = startMatrix(&nSize);
	subMatrix = startMatrix(&nSize);

	for (q = 0; q < nSize; q++)
	{
		for (p = 0; p < nSize; p++)
		{
			m = 0;
			n = 0;
			for (i = 0; i < nSize; i++)
			{
				for (j = 0; j < nSize; j++)
				{
					if (i != q && j != p)
					{
						subMatrix[m][n] = matrix[i][j];
						if (n < (nSize - 2))
							n++;
						else
						{
							n = 0;
							m++;
						}
					}
				}
			}
			cofacMatrix[q][p] = pow(-1, q + p) * determinantMatrix(subMatrix, nSize - 1);
		}
	}

	return cofacMatrix;
}