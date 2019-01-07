double** cofactorMatrix(double** matrix, int size)
{
	double **subMatrix, **cofacMatrix;
	int p, q, m, n, i, j;

	cofacMatrix = startMatrix(&size);
	subMatrix = startMatrix(&size);

	for (q = 0; q < size; q++)
	{
		for (p = 0; p < size; p++)
		{
			m = 0;
			n = 0;
			for (i = 0; i < size; i++)
			{
				for (j = 0; j < size; j++)
				{
					if (i != q && j != p)
					{
						subMatrix[m][n] = matrix[i][j];
						if (n < (size - 2))
							n++;
						else
						{
							n = 0;
							m++;
						}
					}
				}
			}
			cofacMatrix[q][p] = pow(-1, q + p) * determinantMatrix(subMatrix, size - 1);
		}
	}

	return cofacMatrix;
}