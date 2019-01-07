#include "matriz.c"
#define TAM_MEM 27
double *startMemory (key_t llave){
   int shmid;
   double *shm;
  if ((shmid = shmget (llave, TAM_MEM, IPC_CREAT|0666)) < 0)
    {
      perror ("Error al obtener memoria compartida: shmget");
      exit (-1);
    }
  if ((shm = shmat (shmid, NULL, 0)) == (double *)-1)
    {
      perror ("Error al enlazar la memoria compartida: shmat");
      exit (-1);
    }
	return shm;
}

void
writeMemory (double *shm, matriz A)
{
  for (int i = 0; i < N; i++)
    for (int k = 0; k < N; k++)
      *shm++ = A[i][k];
}

matriz
castMemory (double *shm)
{
  matriz A = nueva ();
  for (int i = 0; i < N; i++)
    for (int k = 0; k < N; k++)
      A[i][k] = *shm++;
  return A;
}