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
#include <unistd.h>

#include "defs.h"

/**
 * Function made to reserve N spaces to integers in memory
 * for an array and return it.
 * @return  Array with space to N integers.
 */
int *reserve_memory(void) {
    int *mem;
    mem = (int *)calloc(N, sizeof(int));

    if (mem == NULL) {
        perror("Error de asignación de memoria");
        exit(EXIT_FAILURE);
    }

    return mem;
}

/**
 * Function made to fill an array with random numbers from
 * 0 to MAX_RAND.
 * @param data Pointer to the array to fill.
 */
void fill_array(int *data) {
    srand(getpid());

    for (register int i = 0; i < N; ++i)
        data[i] = rand() % MAX_RAND;
}

/**
 * Function which prints an array in a rows of 16 numbers and
 * 3 digits per number.
 * @param data Array to print.
 */
void print(int *data) {
    for (register int i = 0; i < N; ++i) {
        if (!(i % 16))
            printf("\n");

        printf("%3d ", data[i]);
    }

    printf("\n");
}
