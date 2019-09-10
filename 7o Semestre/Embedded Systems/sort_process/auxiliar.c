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

/**
 * Function to swap two integers
 * @param a Pointer to the first integer to swap
 * @param b Pointer to the second integer to swap
 */
void swapi(int *a, int *b) {
    (*a) ^= (*b);
    (*b) ^= (*a);
    (*a) ^= (*b);
}
