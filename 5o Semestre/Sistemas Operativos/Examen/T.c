#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>
void* funcion1 (void* args);
void* funcion2 (void* args);
int x = -11, y = 0;
void* funcion1 (void* args){
	pthread_t id_hilo;
	x+=5;
	pthread_create(&id_hilo, NULL, funcion2, (void*)NULL);
	pthread_join(id_hilo, NULL);
	pthread_exit(NULL);
}
void* funcion2 (void* args){
	x+=1;
	y+=25;
	pthread_exit(NULL);
}
int main(int argc, char const *argv[])
{
	int pid, t = 0;
	pthread_t id_hilo;
	pid = fork();
	if (pid == 0)
	{
		while(x!=31 || y!=175){
			pthread_create(&id_hilo, NULL, funcion1, (void*)NULL);
			pthread_join(id_hilo, NULL);
			t++;
		}
		printf("Termine sin ciclarme en %d ciclos\n", t);
		exit(0);
	}else{
		wait(0);
	}
	return 0;
}