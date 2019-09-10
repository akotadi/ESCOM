/*
 * Copyright (c) 2019 ManuelCH.
 *
 * This file is part of Search_process 
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
#include <unistd.h>

#include "defs.h"
#include "arrays.h"
#include "process.h"

/**
 * Main function of the program, will generate the array, the process and
 * manage it through the execution.
 * @return 
 */
int main() {

    pid_t pid;
    int *data;

    printf("Probando procesos... \n");

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
