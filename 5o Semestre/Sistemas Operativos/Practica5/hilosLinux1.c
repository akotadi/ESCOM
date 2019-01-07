#include <pthread.h>
#include <stdio.h>
void *hilo(void *arg);

int main(void)
{
    pthread_t id_hilo;
    pthread_create(&id_hilo,NULL,(void*)hilo,NULL);
    pthread_join(id_hilo,NULL);
    return 0;
}
void *hilo(void *arg) 
{
    printf("Hola mundo desde el hilo en UNIX\n" );
    return NULL;
}