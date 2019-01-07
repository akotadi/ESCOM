double determinantMatrix(double** matrix, int size)
{
	double aux = 1, dDeterminant = 0, **subMatrix;
	int i, j, m, n, c;

	subMatrix = startMatrix(&size);

	if (size == 1)
	{
		return (matrix[0][0]);
	}
	else
	{
		dDeterminant = 0;
		for (c = 0; c < size; c++)
		{
			m = 0;
			n = 0;
			for (i = 0;i < size; i++)
			{
				for (j = 0 ;j < size; j++)
				{
					subMatrix[i][j] = 0;
					if (i != 0 && j != c)
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
			dDeterminant = dDeterminant + aux * (matrix[0][c] * determinantMatrix(subMatrix, size - 1));
			aux = -1 * aux;
		}
	}

	return dDeterminant;
}