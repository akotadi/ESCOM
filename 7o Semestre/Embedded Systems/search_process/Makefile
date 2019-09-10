# Archivo Makefile
PROJECT=search_process
DESTDIR=/usr/local/bin
CFLAGS=-Wall
LDFLAGS=-lm
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