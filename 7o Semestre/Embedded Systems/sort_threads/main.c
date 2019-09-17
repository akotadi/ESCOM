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
#include "arrays.h"
#include "threads.h"

/**
 * Main function of the program, will generate the array, the threads and
 * manage it through the execution.
 * @return
 */
int main() {
    int *data;
    pthread_t threads[NUM_THR];
    data = reserve_memory();
    fill_array(data);
    print(data);
    printf("Probando hilos... \n");
    thread_args *args[NUM_THR] = { NULL, NULL, NULL, NULL, NULL };

    for (register int nt = 0; nt < NUM_THR; nt++) {
        args[nt] = calloc(sizeof(thread_args), 1);
        args[nt]->nt = nt;
        args[nt]->data = data;

        if (pthread_create(&threads[nt], NULL, thread_function, args[nt])) {
            perror("Error al crear el hilo");
            exit(EXIT_FAILURE);
        }
    }

    main_process(threads);
    return 0;
}
