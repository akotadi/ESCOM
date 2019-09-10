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

#ifndef PROC_H
#define PROC_H

/**
 * Function which receive the number of the child and select which
 * function will realize.
 * @param np   Number of child.
 * @param data Array to be processed in the array.
 * @param pipefd Array with the descriptors of the pipe
 */
void child_process(int, int*, int[]);

/**
 * Function which wait the child process and print the return
 * of all of them.
 * @param pipefd Array with the descriptors of the pipe
 */
void father_process(int[]);

#endif
