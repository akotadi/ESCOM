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
#include <sys/wait.h>
#include <unistd.h>

#include "defs.h"
#include "operations.h"

/**
 * Function which receive the number of the child and select which
 * function will realize.
 * @param np   Number of child.
 * @param data Array to be processed in the array.
 */
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

/**
 * Function which wait the child process and print the return
 * of all of them.
 */
void father_process() {
    int state;
    pid_t pid;

    printf("Father process with pid: %d\n", getpid());
    for (register int np = 0; np < NUM_PROC; ++np) {
        pid = wait(&state);
        printf("Child process with pid %d and return %d finished\n", pid, state>>8);
    }
}
