#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define NUM_PROC 4
#define N 32
#define MAX_RAND 256

int* reserve_memory(void);
void fill_array(int*);
void print(int*);

void child_process(int, int*);
void father_process(void);

int search_greater(int*);
int search_lower(int*);
int search_average(int*);
int search_mode(int*);

int main() {

    pid_t pid;
    int *data;

    printf("Probando procesos... \n");

    srand(getpid());

    data = reserve_memory();
    fill_array(data);
    print(data);

    for (register int np = 0; np < NUM_PROC; np++) {
        pid = fork();
        if ( pid == -1 ) {
            perror("Error al crear el proceso");
            exit(EXIT_FAILURE);
        }
        if ( !pid ) {
            child_process(np, data);
        }
    }

    father_process();

    return 0;
}

int search_greater(int *data) {
    int greater;

    greater = data[0];
    for (register int i = 1; i < N; ++i)
        greater = (data[i] > greater)?(data[i]):(greater);

    return greater;
}

int search_lower(int *data) {
    int lower;

    lower = data[0];
    for (register int i = 1; i < N; ++i)
        lower = (data[i] < lower)?(data[i]):(lower);

    return lower;
}

int search_average(int *data) {
    int average;

    average = 0;
    for (register int i = 0; i < N; ++i)
        average += data[i];

    average /= N;

    return average;
}

int search_mode(int *data) {
    int mode, *check;

    check = (int*)calloc(MAX_RAND, sizeof(int));

    for (register int i = 0; i < N; ++i)
        check[data[i]]++;

    mode = 0;
    for (register int i = 1; i < MAX_RAND; ++i)
        mode = (check[i] > check[mode])?(i):(mode);

    return mode;
}

void child_process(int np, int *data) {
    int greater, lower, average, mode;

    printf("Child process: %d, with pid %d\n", np, getpid());

    if (np == 0) {
        greater = search_greater(data);
        exit(greater);
    } else if (np == 1) {
        lower = search_lower(data);
        exit(lower);
    } else if (np == 2) {
        average = search_average(data);
        exit(average);
    } else {
        mode = search_mode(data);
        exit(mode);
    }
}

void father_process() {
    int state;
    pid_t pid;

    printf("Father process with pid: %d\n", getpid());
    for (register int np = 0; np < NUM_PROC; ++np) {
        pid = wait(&state);
        printf("Child process with pid %d and return %d finished\n", pid, state>>8);
    }
}

void print(int *data) {
    for (register int i = 0; i < N; ++i) {
        if ( !(i % 16) )
            printf("\n");
        printf("%3d ", data[i]);
    }
    printf("\n");
}

void fill_array(int *data) {
    srand(getpid());

    for (register int i = 0; i < N; ++i)
        data[i] = rand() % MAX_RAND;
}

int* reserve_memory(void) {
    int *mem;

    mem = (int*)calloc(N, sizeof(int));
    if (mem == NULL) {
        perror("Error de asignación de memoria");
        exit(EXIT_FAILURE);
    }

    return mem;
}