#include <sys/types.h>		
#include <sys/ipc.h>		
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include "memory.c"

int
main ()
{
  int status, status1;
  matriz A, B, C, D, E, F;
  double *AuxMemoria1, *AuxMemoria2, *Multiplicacion, *Suma;
  key_t llave1 = 1000;
  key_t llave2 = 2000;
  key_t llave3 = 3000;
  key_t llave4 = 4000;

  AuxMemoria1 = startMemory (llave1);
  AuxMemoria2 = startMemory (llave2);
  Multiplicacion = startMemory (llave3);
  Suma = startMemory (llave4);

  if (fork () == 0)
    {
      if (fork () == 0)
	{			//Nieto
	  D = castMemory (AuxMemoria1);
	  E = castMemory (AuxMemoria2);
	  F = suma (D, E);
	  writeMemory (Suma, F);
	}
      else
	{			//Padre
	  A = castMemory (AuxMemoria1);
	  B = castMemory (AuxMemoria2);
	  C = multiplicacion (A, B);

	  D = matrizAleatoria ();
	  E = matrizAleatoria ();

	  writeMemory (AuxMemoria1, D);
	  writeMemory (AuxMemoria2, E);
	  writeMemory (Multiplicacion, C);
	}
    }
  else
    {				//Abuelo
      A = matrizAleatoria ();
      B = matrizAleatoria ();

      writeMemory (AuxMemoria1, A);
      writeMemory (AuxMemoria2, B);

      wait (&status);
      printf ("\nInversa de la multiplicacion de dos matrices\n");
      C = inversa (castMemory (Multiplicacion));
      writeFile (C, "inversaMultiplicacion.txt");
      printMatriz (C);

      printf ("\nInversa de la suma de dos matrices\n");
      F = inversa (castMemory (Suma));
      writeFile (F, "inversaSuma.txt");
      printMatriz (F);
    }
  exit (0);
}