/*
 * Copyright (c) 2019 ManuelCH.
 *
 * This file is part of Sort_process 
 * (see https://github.com/akotadi).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#include "defs.h"
#include "operations.h"

/**
 * Function which receive the number of the child and select which
 * function will realize.
 * @param np   Number of child.
 * @param data Array to be processed in the array.
 * @param pipefd Array with the descriptors of the pipe
 */
void child_process(int np, int *data, int pipefd[]) {
    int greater, lower, average, mode;
    int *sorted_array, *histogram_array;
    int status;

    printf("Child process: %d, with pid %d\n", np, getpid());

    close(pipefd[0]);

    if (np == 0) {
        greater = search_greater(data);
        status = write(pipefd[1], &greater, sizeof(greater));
    } else if (np == 1) {
        lower = search_lower(data);
        status = write(pipefd[1], &lower, sizeof(lower));
    } else if (np == 2) {
        average = search_average(data);
        status = write(pipefd[1], &average, sizeof(average));
    } else if (np == 3) {
        mode = search_mode(data);
        status = write(pipefd[1], &mode, sizeof(mode));
    } else if (np == 4) {
        histogram_array = search_histogram(data);
        status = write(pipefd[1], histogram_array, MAX_RAND * sizeof(int));
        free(histogram_array);
    } else {
        sorted_array = bubble_sort(data);
        status = write(pipefd[1], sorted_array, N * sizeof(int));
        free(sorted_array);
    }

    if (status < 0) {
        perror("Error al escribir el archivo\n");
        exit(EXIT_FAILURE);
    }

    close(pipefd[1]);

    exit(np);
}


/**
 * Function which wait the child process and print the return
 * of all of them.
 * @param pipefd Array with the descriptors of the pipe
 */
void father_process(int pipefd[]) {
    int greater, lower, average, mode;
    int *sorted_array, *histogram_array;
    int state, status;
    pid_t pid;

    printf("Father process with pid: %d\n", getpid());

    close(pipefd[1]);

    for (register int np = 0; np < NUM_PROC; ++np) {
        pid = wait(&state);

        state >>= 8;

        printf("Child process: %d, with pid %d and ", state, pid);

        if (state == 0) {
            status = read(pipefd[0], &greater, sizeof(greater));
            printf("greater value is: %d\n", greater);
        } else if (state == 1) {
            status = read(pipefd[0], &lower, sizeof(lower));
            printf("lower value is: %d\n", lower);
        } else if (state == 2) {
            status = read(pipefd[0], &average, sizeof(average));
            printf("average value is: %d\n", average);
        }  else if (state == 3) {
            status = read(pipefd[0], &mode, sizeof(mode));
            printf("mode value is: %d\n", mode);
        }  else if (state == 4) {
            histogram_array = (int*)calloc(MAX_RAND, sizeof(int));
            status = read(pipefd[0], histogram_array, MAX_RAND * sizeof(int));
            printf("histogram array is:\n");
            for (register int i = 0; i < MAX_RAND; ++i) {
                if ( !(i % 8) )
                    printf("\n");
                printf("%4d:%4d ", i, histogram_array[i]);
            }
            printf("\n");
        } else {
            sorted_array = (int*)calloc(N, sizeof(int));
            status = read(pipefd[0], sorted_array, N * sizeof(int));
            printf("sorted array is:\n");
            for (register int i = 0; i < N; ++i) {
                if ( !(i % 16) )
                    printf("\n");
                printf("%3d ", sorted_array[i]);
            }
            printf("\n");
        }

        if (status < 0) {
            perror("Error al leer el archivo\n");
            exit(EXIT_FAILURE);
        }
    }

    close(pipefd[0]);
}
