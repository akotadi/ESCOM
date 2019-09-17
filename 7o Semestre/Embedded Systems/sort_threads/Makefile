# /*
#  * Copyright (c) 2019 ManuelCH.
#  *
#  * This file is part of Sort_threads 
#  * (see https://github.com/akotadi).
#  *
#  * This program is free software: you can redistribute it and/or modify
#  * it under the terms of the GNU General Public License as published by
#  * the Free Software Foundation, either version 3 of the License, or
#  * (at your option) any later version.
#  *
#  * This program is distributed in the hope that it will be useful,
#  * but WITHOUT ANY WARRANTY; without even the implied warranty of
#  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  * GNU General Public License for more details.
#  *
#  * You should have received a copy of the GNU General Public License
#  * along with this program. If not, see <http://www.gnu.org/licenses/>.
#  */

# Archivo Makefile
PROJECT=sort_threads
DESTDIR=/usr/local/bin
CFLAGS=-Wall
LDFLAGS=-lm -lpthread
CC=gcc
OBJS=$(patsubst %.c,%.o,$(wildcard *.c))



all: $(PROJECT)

%.o: %.c
	$(CC) -c $< $(CFLAGS)

$(PROJECT): $(OBJS)
	$(CC) $^ -o $@ $(LDFLAGS)

.PHONY: clean

install: all
	mkdir $(DESTDIR) -p
	sudo cp $(PROJECT) $(DESTDIR)

uninstall: 
	sudo rm -rf "$(DESTDIR)/$(PROJECT)"

clean:
	rm -f *.o $(PROJECT)
