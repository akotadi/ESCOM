all: sampling

sampling.o: sampling.c
	gcc sampling.c -c -Wall 

processing.o: defs.h processing.c
	gcc processing.c -c -Wall

files.o: defs.h files.c 
	gcc files.c -c -Wall

sampling: sampling.o files.o processing.o 
	gcc sampling.o files.o processing.o  -o sampling -lm 

.PHONY: clean

install: all
	sudo mkdir /usr/local/bin -p
	sudo cp sampling /usr/local/bin

uninstall: 
	sudo rm /usr/local/bin/sampling

clean:
	rm -f *.o sampling