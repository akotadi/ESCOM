#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	int desc1[2], desc2[2], desc3[2], i, j = 100, k = 0;
	char bufer1[25], bufer2[50];
	pipe(desc1);
	pipe(desc2);
	pipe(desc3);
	strcpy(bufer1, "HOLA");
	strcpy(bufer2, "ADIOS");
	int controlRead, controlWrite;
	for (int i = 0; i < 2; ++i)
	{
		fork();
		printf("En iteracion %d\n", i);
		if (i % 2 == 0)
		{
            if( (controlWrite = write(desc1[1], bufer1, strlen(bufer1)+1)) < 0)
            {
                perror("write");
                exit(EXIT_FAILURE);
            }
            if( (controlWrite = write(desc3[1], &j, sizeof(int))) < 0)
            {
                perror("write")
                exit(EXIT_FAILURE);
            }
		}else{
			printf("Estoy en 1\n");
            if( (controlRead = read(desc1[0], bufer2, sizeof(bufer2)) ) < 0)
            {
                perror("read");
                exit(EXIT_FAILURE);
            }
			printf("Estoy en 1.1\n");
            if( (controlRead = read(desc3[0], &k, sizeof(int)) ) < 0)
            {
                perror("read");
                exit(EXIT_FAILURE);
            }
			printf("Estoy en 1.2\n");
			k *= k;
		}
		printf("Tengo en bufer2: %s\n", bufer2);
		printf("Tengo en bufer1: %s\n", bufer1);
		printf("Tengo en k: %i\n", k);
		if (i == 1)
		{
			printf("Estoy en 2\n");
            if( (controlRead = read(desc2[0], bufer2, sizeof(bufer2)) ) < 0)
            {
                perror("read");
                exit(EXIT_FAILURE);
            }
		}else{
			strcat(bufer2, " AL GRUPO");
            if( (controlWrite = write(desc2[1], bufer2, strlen(bufer2)+1)) < 0)
            {
                perror("write");
                exit(EXIT_FAILURE);
            }
		}
		printf("Ahora tengo en bufer2: %s\n", bufer2);
	}
	exit(0);
}
