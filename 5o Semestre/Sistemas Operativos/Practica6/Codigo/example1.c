#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>

#define VALOR 1

int main (void)
{
	int desc_arch[2];
	char bufer[100];
	if (pipe (desc_arch) != 0)
		exit (1);
	if (fork () == 0)
	{
		while (VALOR)
		{
			read (desc_arch[0], bufer, sizeof (bufer));
			printf ("Se recibio: %s\n", bufer);
		}
	}
	while (VALOR)
	{
		fgets (bufer, 100, stdin);
		write (desc_arch[1], bufer, strlen (bufer) + 1);
	}
}