#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define NUM_PROC 4
#define N 32
#define MAX_RAND 256


void child_process(int, int[]);
void father_process(int[]);

int main() {
    pid_t pid;
    int pipefd[2], pipe_st;

    printf("Probando procesos... \n");

    pipe_st = pipe(pipefd);

    if (pipe_st) {
        perror("Error al crear la tubería\n");
        exit(EXIT_FAILURE);
    }

    for (register int np = 0; np < NUM_PROC; np++) {
        pid = fork();
        if ( pid == -1 ) {
            perror("Error al crear el proceso\n");
            exit(EXIT_FAILURE);
        }
        if ( !pid ) {
            child_process(np, pipefd);
        }
    }

    father_process(pipefd);

    return 0;
}

void child_process(int np, int pipefd[]) {
    int num1 = 20, num2 = 10;
    int add, sub, mult, div;
    int status;

    printf("Child process: %d, with pid %d\n", np, getpid());

    close(pipefd[0]);

    if (np == 0) {
        add = num1 + num2;
        status = write(pipefd[1], &add, sizeof(add));
    } else if (np == 1) {
        sub = num1 - num2;
        status = write(pipefd[1], &sub, sizeof(sub));
    } else if (np == 2) {
        mult = num1 * num2;
        status = write(pipefd[1], &mult, sizeof(mult));
    } else {
        div = num1 / num2;
        status = write(pipefd[1], &div, sizeof(div));
    }

    close(pipefd[1]);

    if (status < 0) {
        perror("Error al escribir el archivo\n");
        exit(EXIT_FAILURE);
    }

    exit(np);
}

void father_process(int pipefd[]) {
    int state, result, status;
    pid_t pid;

    printf("Father process with pid: %d\n", getpid());

    close(pipefd[1]);

    for (register int np = 0; np < NUM_PROC; ++np) {
        pid = wait(&state);
        status = read(pipefd[0], &result, sizeof(result));

        if (status < 0) {
            perror("Error al leer el archivo\n");
            exit(EXIT_FAILURE);
        }

        printf("Child process: %d, with pid %d and ", state>>8, pid);

        if (np == 0) {
            printf("addition");
        } else if (np == 1) {
            printf("substraction");
        } else if (np == 2) {
            printf("multiplication");
        } else {
            printf("division");
        }

        printf(" result %d finished\n", result);
    }

    close(pipefd[0]);
}
