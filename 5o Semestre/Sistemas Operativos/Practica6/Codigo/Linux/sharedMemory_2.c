#include <sys/types.h>	/* Servidor de la memoria compartida */
#include <sys/ipc.h>	/* Ejecutar el servidor antes de ejecutar el cliente */
#include <sys/shm.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#define TAM_MEM 27		/* Tamaño de la memoria compartida en bytes */

int main ()
{
	char c;
	int shmid;
	key_t llave;
	char *shm, *s;
	llave = 5678;
	if ((shmid = shmget (llave, TAM_MEM, IPC_CREAT|0666)) < 0)
	{
		perror ("Error al obtener memoria compartida: shmget");
		exit (-1);
	}
	if ((shm = shmat (shmid, NULL, 0)) == (char *) -1)
	{
		perror ("Error al enlazar la memoria compartida: shmat");
		exit (-1);
	}
	s = shm;
	for (c = 'a'; c <= 'z'; c++)
		*s++ = c;
	*s = '\0';
	while (*shm != '*')
		sleep (1);
	exit (0);
}