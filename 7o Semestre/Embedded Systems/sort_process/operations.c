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

#include <stdlib.h>
#include <stdbool.h> 

#include "defs.h"
#include "auxiliar.h"

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

/**
 * Function which count the number of aparitions of each number
 * @param  data Array to make the histogram
 * @return      Pointer to the result array
 */
int* search_histogram(int* data) {
    int *histogram;

    histogram = (int*)calloc(MAX_RAND, sizeof(int));

    for (register int i = 0; i < N; ++i)
        histogram[data[i]]++;

    return histogram;
}

/**
 * Function which sorts and array with the bubble algorithm
 * @param  data Array to sort
 * @return      Pointer to the array sorted
 */
int* bubble_sort(int* data) {
    int *newData;
    bool swap;

    newData = (int*)calloc(N, sizeof(int));
    for (int i = 0; i < N; ++i)
        newData[i] = data[i];

    for (int i = 0; i < N-1; ++i) {
        swap = false;
        for (int j = 0; j < N-i-1; ++j) {
            if (newData[j] > newData[j+1]) {
                swapi(&newData[j], &newData[j+1]);
                swap = true;
            }
        }

        if (!swap)
            break;
    }

    return newData;
}
