#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>

int main ()
{
	char *argv[3];
	argv[1] = NULL;

	if(fork() == 0) {
		if(fork() == 0) {
			for (int i = 0; i < 3; ++i)
				if (fork () == 0) {
					printf ("Second - %d\n", i);
					if (i == 0)
						argv[0] = "/home/akotadi/Documents/Escuela/5oSemestre/SistemasOperativos/Practica4/ThreeProcess/1";
					if (i == 1)
						argv[0] = "/home/akotadi/Documents/Escuela/5oSemestre/SistemasOperativos/Practica4/ThreeProcess/2";
					if (i == 2)
						argv[0] = "/home/akotadi/Documents/Escuela/5oSemestre/SistemasOperativos/Practica4/ThreeProcess/3";
					execv (argv[0], argv);
					exit (0);
				}
		}else{
			printf("First\n");
			exit(0);
		}
	}else{
		printf ("Main\n");
		exit (0);
	}
}