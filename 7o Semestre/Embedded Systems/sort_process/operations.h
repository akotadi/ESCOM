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

#ifndef OPER_H
#define OPER_H

/**
 * Function which search the greater number in an array.
 * @param  data Array to look.
 * @return      The greater number,
 */
int search_greater(int*);

/**
 * Function which search the lower number in an array.
 * @param  data Array to look.
 * @return      The lower number,
 */
int search_lower(int*);

/**
 * Function which search the average number in an array.
 * @param  data Array to look.
 * @return      The average number,
 */
int search_average(int*);

/**
 * Function which search the mode number in an array
 * doing the histogram of the array.
 * @param  data Array to look.
 * @return      The mode number,
 */
int search_mode(int*);

/**
 * Function which count the number of aparitions of each number
 * @param  data Array to make the histogram
 * @return      Pointer to the result array
 */
int* search_histogram(int*);

/**
 * Function which sorts and array with the bubble algorithm
 * @param  data Array to sort
 * @return      Pointer to the array sorted
 */
int* bubble_sort(int*);

#endif
