#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
void *hilo(void *arg);
void *hilo2(void *arg);
void *hilo3(void *arg);
int main(void)
{
    pthread_t id_hilo;
    pid_t pid;
    int i=0,status;
    char *cad="Practica 5";
    char *cad2="";
    if((pid=fork())==0)
    {
        printf("Proceso=%u\n",getpid());
        for(i=0;i<15;i++)
        {
            pthread_create(&id_hilo,NULL,(void*)hilo,NULL);
            pthread_join(id_hilo,NULL);
        }
        waitpid(pid,&status,0);
        exit(0);
    }
    waitpid(pid,&status,0);
    return 0;
}
void *hilo(void *arg)
{
    int i;
    pthread_t id_hilo2;
    printf("Id hilo= %ld\n",(long) pthread_self());
    for(i=0;i<10;i++)
    {
        pthread_create(&id_hilo2,NULL,(void*)hilo2,NULL);
        pthread_join(id_hilo2,NULL);
    }
    return NULL;
}
void *hilo2(void *arg)
{
    int i;
    pthread_t id_hilo3;
    printf("Id hilo= %ld\n",(long) pthread_self());
    for(i=0;i<5;i++)
    {
        pthread_create(&id_hilo3,NULL,(void*)hilo3,NULL);
        pthread_join(id_hilo3,NULL);
    }
    return NULL;
}

void *hilo3(void *arg)
{
    printf("Practica 5\n");
    return NULL;
}