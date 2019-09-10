# Archivo Makefile
PROJECT=process
DESTDIR=/usr/local/bin
CFLAGS=-Wall
LDFLAGS=-lm
CC=gcc


all: $(PROJECT)

%.o: %.c
	$(CC) -c $< $(CFLAGS)

$(PROJECT): $(patsubst %.c,%.o,$(wildcard *.c))#muestreo.o procesamiento.o archivos.o
	$(CC) $^ -o $@ $(LDFLAGS)

# muestreo.o: muestreo.c
# 	$(CC) -c muestreo.c $(CFLAGS)

# procesamiento.o: procesamiento.c procesamiento.h defs.h
# 	$(CC) -c procesamiento.c $(CFLAGS)

# archivos.o: archivos.c archivos.h defs.h
# 	$(CC) -c archivos.c $(CFLAGS)

# $(PROJECT): muestreo.o archivos.o procesamiento.o
# 	$(CC) muestreo.o archivos.o procesamiento.o -o $(PROJECT) $(LDFLAGS)


.PHONY: clean

install: all
	mkdir $(DESTDIR) -p
	sudo cp $(PROJECT) $(DESTDIR)

uninstall: 
	sudo rm -rf "$(DESTDIR)/$(PROJECT)"

clean:
	rm -f *.o $(PROJECT)