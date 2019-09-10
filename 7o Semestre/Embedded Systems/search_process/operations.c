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

#include <stdlib.h>

#include "defs.h"

/**
 * Function which search the greater number in an array.
 * @param  data Array to look.
 * @return      The greater number,
 */
int search_greater(int *data) {
    int greater;

    greater = data[0];
    for (register int i = 1; i < N; ++i)
        greater = (data[i] > greater)?(data[i]):(greater);

    return greater;
}

/**
 * Function which search the lower number in an array.
 * @param  data Array to look.
 * @return      The lower number,
 */
int search_lower(int *data) {
    int lower;

    lower = data[0];
    for (register int i = 1; i < N; ++i)
        lower = (data[i] < lower)?(data[i]):(lower);

    return lower;
}

/**
 * Function which search the average number in an array.
 * @param  data Array to look.
 * @return      The average number,
 */
int search_average(int *data) {
    int average;

    average = 0;
    for (register int i = 0; i < N; ++i)
        average += data[i];

    average /= N;

    return average;
}

/**
 * Function which search the mode number in an array
 * doing the histogram of the array.
 * @param  data Array to look.
 * @return      The mode number,
 */
int search_mode(int *data) {
    int mode, *check;

    check = (int*)calloc(MAX_RAND, sizeof(int));

    for (register int i = 0; i < N; ++i)
        check[data[i]]++;

    mode = 0;
    for (register int i = 1; i < MAX_RAND; ++i)
        mode = (check[i] > check[mode])?(i):(mode);

    free(check);

    return mode;
}
