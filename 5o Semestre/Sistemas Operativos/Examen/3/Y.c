#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>
#define MAX 256

int main(void)
{
	int tuberia1[2], tuberia2[2], pid;
	char men[MAX], mensaje[MAX];

	if(pipe(tuberia1)==-1 || pipe(tuberia2)==-1){
		perror("Error: ");
		exit(-1);
	}

	if((pid=fork())==-1){
		perror("Error: ");
		exit(-1);
	}

	strcpy(mensaje, "hola, como estas");

	if(pid==0){
		read(tuberia1[0], men, MAX);
		printf("HIJO: Mensaje recibido: %s\n", men);
		strcat(men, " el dia de hoy");
		write(tuberia2[1], men, strlen(men)+1);
		exit(0);
	}
	else{
		write(tuberia1[1], mensaje, strlen(mensaje)+1);
		read(tuberia2[0], men, MAX);
		printf("PADRE: Mensaje recibido: %s\n", men);
		wait(0);
	}
	exit(0);
}
	