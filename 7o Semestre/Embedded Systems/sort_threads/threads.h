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

#ifndef THRE_H
#define THRE_H

/**
 * Function which receive the number of the thread and select which
 * function will realize.
 * @param np   Number of thread.
 * @param data Array to be processed in the thread.
 */
void *thread_function(void *);

/**
 * Function which wait the threads and print the return
 * of all of them.
 * @param threads Array with the thread_id
 */
void main_process(pthread_t *);

#endif
