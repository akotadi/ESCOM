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

#ifndef ARRAYS_H
#define ARRAYS_H

/**
 * Function made to reserve N spaces to integers in memory
 * for an array and return it.
 * @return  Array with space to N integers.
 */
int* reserve_memory(void);

/**
 * Function made to fill an array with random numbers from 
 * 0 to MAX_RAND.
 * @param data Pointer to the array to fill.
 */
void fill_array(int*);

/**
 * Function which prints an array in a rows of 16 numbers and
 * 3 digits per number.
 * @param data Array to print.
 */
void print(int*);

#endif
