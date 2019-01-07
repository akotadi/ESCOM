#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>
#include "matriz.c"

int
main (void)
{
  int tuberia1[2];
  int tuberia2[2];
  pid_t pidpadre, pidnieto;
  int status;
  int status1;

  matriz A = nueva ();
  matriz B = nueva ();
  matriz C = nueva ();
  matriz D = nueva ();
  matriz E = nueva ();
  matriz F = nueva ();

  if (pipe (tuberia1) != 0)
    exit (1);

  if (pipe (tuberia2) != 0)
    exit (1);

  if ((pidpadre = fork ()) == 0)
    {
      if ((pidnieto = fork ()) == 0)
	{			//nieto
	  readPipe (tuberia2[0], D);
	  readPipe (tuberia2[0], E);

	  F = suma (D, E);
	  writePipe (tuberia1[1], F);
	}
      else
	{			//padre

	  readPipe (tuberia1[0], A);
	  readPipe (tuberia1[0], B);
	  C = multiplicacion (A, B);

	  D = matrizAleatoria ();
	  E = matrizAleatoria ();

	  writePipe (tuberia2[1], D);
	  writePipe (tuberia2[1], E);
	  writePipe (tuberia1[1], C);
	}
    }
  else
    {
      A = matrizAleatoria ();
      B = matrizAleatoria ();

      writePipe (tuberia1[1], A);
      writePipe (tuberia1[1], B);

      wait (&status);
      readPipe (tuberia1[0], C);
      printf ("\nInversa de la multiplicacion de dos matrices\n");
      C = inversa (C);
      writeFile (C, "inversaMultiplicacion.txt");
      printMatriz (C);

      readPipe (tuberia1[0], F);
      printf ("\nInversa de la suma de dos matrices\n");
      F = inversa (F);
      writeFile (F, "inversaSuma.txt");
      printMatriz (F);
    }
}