/*
 * Copyright (c) 2019 ManuelCH.
 *
 * This file is part of Sort_threads
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
#include <pthread.h>

#include "defs.h"
#include "auxiliar.h"
#include "operations.h"

/**
 * Function which receive the number of the thread and select which
 * function will realize.
 * @param args   Pointer with the arguments to the function
 */
void *thread_function(void *_args) {
    thread_args *args = (thread_args *) _args;
    printf("Thread number: %d\n", args->nt);
    thread_result *result = calloc(sizeof(thread_result), 1);

    if (args->nt == 0)
        result->number = search_greater(args->data);
    else if (args->nt == 1)
        result->number = search_lower(args->data);
    else if (args->nt == 2)
        result->number = search_average(args->data);
    else if (args->nt == 3)
        result->number = search_mode(args->data);
    else if (args->nt == 4)
        result->array = search_histogram(args->data);
    else
        result->array = bubble_sort(args->data);

    free(args);
    pthread_exit(result);
}

/**
 * Function which wait the threads and print the return
 * of all of them.
 * @param threads Array with the thread_id
 */
void main_process(pthread_t *threads) {
    thread_result *results[NUM_THR];

    for (register int nt = 0; nt < NUM_THR; ++nt) {
        pthread_join(threads[nt], (void **)&results[nt]);
        printf("Thread number %d with id %lu and ", nt, threads[nt]);

        if (nt == 0)
            printf("greater value is: %d\n", results[nt]->number);
        else if (nt == 1)
            printf("lower value is: %d\n", results[nt]->number);
        else if (nt == 2)
            printf("average value is: %d\n", results[nt]->number);
        else if (nt == 3)
            printf("mode value is: %d\n", results[nt]->number);
        else if (nt == 4) {
            printf("histogram array is:\n");

            for (register int i = 0; i < MAX_RAND; ++i) {
                if (!(i % 8))
                    printf("\n");

                printf("%4d:%4d ", i, (results[nt]->array)[i]);
            }

            printf("\n");
            free(results[nt]->array);
        } else {
            printf("sorted array is:\n");

            for (register int i = 0; i < N; ++i) {
                if (!(i % 16))
                    printf("\n");

                printf("%3d ", (results[nt]->array)[i]);
            }

            printf("\n");
            free(results[nt]->array);
        }

        free(results[nt]);
    }
}
