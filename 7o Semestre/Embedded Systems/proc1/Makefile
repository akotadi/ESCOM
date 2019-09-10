# Archivo Makefile
PROYECTO=procesos
DESTDIR=/usr/local/bin
CFLAGS=-Wall
LDFLAGS=-lm
CC=gcc


all: $(PROYECTO)

%.o: %.c
	$(CC) -c $< $(CFLAGS)

$(PROYECTO): $(patsubst %.c,%.o,$(wildcard *.c))#muestreo.o procesamiento.o archivos.o
	$(CC) $^ -o $@ $(LDFLAGS)

# muestreo.o: muestreo.c
# 	$(CC) -c muestreo.c $(CFLAGS)

# procesamiento.o: procesamiento.c procesamiento.h defs.h
# 	$(CC) -c procesamiento.c $(CFLAGS)

# archivos.o: archivos.c archivos.h defs.h
# 	$(CC) -c archivos.c $(CFLAGS)

# $(PROYECTO): muestreo.o archivos.o procesamiento.o
# 	$(CC) muestreo.o archivos.o procesamiento.o -o $(PROYECTO) $(LDFLAGS)


.PHONY: clean

install: all
	mkdir $(DESTDIR) -p
	sudo cp $(PROYECTO) $(DESTDIR)

uninstall: 
	sudo rm -rf "$(DESTDIR)/$(PROYECTO)"

clean:
	rm -f *.o $(PROYECTO)