#include <stdbool.h>
#include <math.h>
typedef double *vector;
typedef vector *matriz;
#define N 10

matriz
nueva ()
{
  matriz A = calloc (N, sizeof (vector));
  for (int i = 0; i < N; ++i)
    A[i] = calloc (N, sizeof (double));
  return A;
}

bool
esCero (double x)
{
  return fabs (x) < 1e-8;
}

matriz
suma (matriz A, matriz B)
{
  matriz C = nueva ();
  for (int i = 0; i < N; ++i)
    for (int j = 0; j < N; ++j)
      C[i][j] = A[i][j] + B[i][j];
  return C;
}

matriz
multiplicacion (matriz A, matriz B)
{
  matriz C = nueva ();
  for (int i = 0; i < N; ++i)
    for (int j = 0; j < N; ++j)
      for (int k = 0; k < N; ++k)
	C[i][j] += A[i][k] * B[k][j];
  return C;
}

matriz
inversa (matriz A)
{
  matriz inv = nueva ();
  for (int i = 0; i < N; ++i)
    inv[i][i] = 1;
  int i = 0, j = 0;
  while (i < N && j < N)
    {
      if (esCero (A[i][j]))
	{
	  for (int k = i + 1; k < N; ++k)
	    {
	      if (!esCero (A[k][j]))
		{
		  vector tmp = A[i];
		  A[i] = A[k];
		  A[k] = tmp;
		  tmp = inv[i];
		  inv[i] = inv[k];
		  inv[k] = tmp;
		  break;
		}
	    }
	}
      if (!esCero (A[i][j]))
	{
	  for (int l = 0; l < N; ++l)
	    inv[i][l] /= A[i][j];
	  for (int l = N - 1; l >= j; --l)
	    A[i][l] /= A[i][j];
	  for (int k = 0; k < N; ++k)
	    {
	      if (i == k)
		continue;
	      for (int l = 0; l < N; ++l)
		inv[k][l] -= inv[i][l] * A[k][j];
	      for (int l = N; l >= j; --l)
		A[k][l] -= A[i][l] * A[k][j];
	    }
	  ++i;
	}
      ++j;
    }
  return inv;
}

matriz
matrizAleatoria ()
{
  matriz A = nueva ();
  for (int i = 0; i < N; ++i)
    for (int j = 0; j < N; ++j)
      A[i][j] = rand () % 10;
  return A;
}

void
writeFile (matriz A, char *nombre)
{
  FILE *fp = fopen (nombre, "w");
  for (int i = 0; i < N; ++i)
    {
      for (int j = 0; j < N; ++j)
	fprintf (fp, "%0.3lf ", A[i][j]);
      fprintf (fp, "\n");
    }
  fclose (fp);
}

void
printMatriz (matriz A)
{
  for (int i = 0; i < N; ++i)
    {
      for (int j = 0; j < N; ++j)
	printf ("%0.3lf\t", A[i][j]);
      printf ("\n");
    }
}


void
writePipe (int tuberia, matriz A)
{
  for (int i = 0; i < N; i++)
    for (int k = 0; k < N; k++)
      write (tuberia, &A[i][k], sizeof (double));
}

void
readPipe (int tuberia, matriz A)
{
  for (int i = 0; i < N; i++)
    for (int k = 0; k < N; k++)
      read (tuberia, &A[i][k], sizeof (double));
}